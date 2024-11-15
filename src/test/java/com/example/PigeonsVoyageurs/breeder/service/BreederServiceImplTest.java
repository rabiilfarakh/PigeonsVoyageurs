package com.example.PigeonsVoyageurs.breeder.service;

import com.example.PigeonsVoyageurs.breeder.BreederMapper;
import com.example.PigeonsVoyageurs.breeder.BreederRepository;
import com.example.PigeonsVoyageurs.breeder.Breeder;
import com.example.PigeonsVoyageurs.breeder.dto.*;
import com.example.PigeonsVoyageurs.coordinate.Coordinate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BreederServiceImplTest {
    @Mock
    private BreederRepository breederRepository;

    @Mock
    private BreederMapper breederMapper;

    @InjectMocks
    private BreederServiceImpl breederService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        reset(breederRepository, breederMapper);
    }

    @Test
    void register() {
        BreederRequestDTO breederRequestDTO = new BreederRequestDTO("testuser", "testpassword", "test@example.com", new Coordinate(33.6589, 45.8168));

        Breeder breederEntity = new Breeder();
        breederEntity.setUserName("testuser");
        breederEntity.setPassword("hashed_password");
        breederEntity.setEmail("test@example.com");
        breederEntity.setCoordinates(new Coordinate(33.6589, 45.8168));

        BreederResponseDTO expectedResponseDTO = new BreederResponseDTO("testid", "testuser", "test@example.com", new Coordinate(33.6589, 45.8168));

        when(breederMapper.toEntity(breederRequestDTO)).thenReturn(breederEntity);
        when(breederRepository.save(breederEntity)).thenReturn(breederEntity);
        when(breederMapper.toResponseDTO(breederEntity)).thenReturn(expectedResponseDTO);

        BreederResponseDTO actualResponseDTO = breederService.register(breederRequestDTO);

        assertTrue(BCrypt.checkpw(breederRequestDTO.password(), breederEntity.getPassword()));

        assertEquals(expectedResponseDTO, actualResponseDTO);

        verify(breederRepository).save(breederEntity);
    }

    @Test
    void login_Success() {
        String email = "test@example.com";
        String password = "password123";
        Breeder breeder = new Breeder();
        breeder.setEmail(email);
        breeder.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));

        when(breederRepository.findByEmail(email)).thenReturn(Optional.of(breeder));

        Optional<String> result = breederService.login(email, password);

        assertTrue(result.isPresent());
        assertEquals("Login successful", result.get());
    }

    @Test
    void login_Failure() {
        String email = "test@example.com";
        String password = "password123";

        when(breederRepository.findByEmail(email)).thenReturn(Optional.empty());

        Optional<String> result = breederService.login(email, password);

        assertFalse(result.isPresent());
    }

    @Test
    void findById() {
        String id = "testid";

        Breeder breeder = new Breeder();
        breeder.setId(id);
        breeder.setUserName("testuser");
        breeder.setPassword("testpassword");
        breeder.setEmail("test@example.com");

        breeder.setCoordinates(new Coordinate(33.6589, 45.8168));BreederResponseDTO expectedResponse = new BreederResponseDTO("testid","testuser", "test@example.com", new Coordinate(33.6589, 45.8168));

        when(breederRepository.findById(id)).thenReturn(Optional.of(breeder));
        when(breederMapper.toResponseDTO(breeder)).thenReturn(expectedResponse);

        Optional<BreederResponseDTO> result = breederService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(expectedResponse, result.get());
    }

    @Test
    void findByEmail() {
        String email = "test@example.com";

        Breeder breeder = new Breeder();
        breeder.setId("testid");
        breeder.setUserName("testuser");
        breeder.setPassword("testpassword");
        breeder.setEmail(email);

        breeder.setCoordinates(new Coordinate(33.6589, 45.8168));BreederResponseDTO expectedResponse = new BreederResponseDTO("testid","testuser", "test@example.com", new Coordinate(33.6589, 45.8168));

        when(breederRepository.findByEmail(email)).thenReturn(Optional.of(breeder));
        when(breederMapper.toResponseDTO(breeder)).thenReturn(expectedResponse);

        Optional<BreederResponseDTO> result = breederService.findByEmail(email);

        assertTrue(result.isPresent());
        assertEquals(expectedResponse, result.get());
    }

    @Test
    void findAll() {
        Breeder breeder1 = new Breeder();
        breeder1.setId("id1");
        breeder1.setUserName("user1");
        breeder1.setPassword("password1");
        breeder1.setEmail("user1@example.com");
        breeder1.setCoordinates(new Coordinate(12.34, 56.78));

        Breeder breeder2 = new Breeder();
        breeder2.setId("id2");
        breeder2.setUserName("user2");
        breeder2.setPassword("password2");
        breeder2.setEmail("user2@example.com");
        breeder2.setCoordinates(new Coordinate(23.45, 67.89));

        List<Breeder> breeders = List.of(breeder1, breeder2);
        List<BreederResponseDTO> expectedResponses = breeders.stream()
                .map(b -> new BreederResponseDTO(b.getId(), b.getUserName(), b.getEmail(), b.getCoordinates()))
                .toList();

        when(breederRepository.findAll()).thenReturn(breeders);
        when(breederMapper.toResponseDTO(breeder1)).thenReturn(expectedResponses.get(0));
        when(breederMapper.toResponseDTO(breeder2)).thenReturn(expectedResponses.get(1));

        List<BreederResponseDTO> result = breederService.findAll();

        assertEquals(expectedResponses, result);
    }

    @Test
    void update() {
        String id = "testid";
        BreederRequestDTO breederRequestDTO = new BreederRequestDTO("new_testuser", "newPassword", "newtest@example.com", new Coordinate(38.6589, 10.8168));

        Breeder existingBreeder = new Breeder();
        existingBreeder.setId("testid");
        existingBreeder.setUserName("testuser");
        existingBreeder.setPassword("oldPassword");
        existingBreeder.setEmail("test@example.com");
        existingBreeder.setCoordinates(new Coordinate(33.6589, 45.8168));

        Breeder updatedBreeder = new Breeder();
        updatedBreeder.setId("testid");
        updatedBreeder.setUserName("new_testuser");
        updatedBreeder.setPassword("newPassword");
        updatedBreeder.setCoordinates(new Coordinate(38.6589, 10.8168));

        BreederResponseDTO expectedResponse = new BreederResponseDTO("testid", "new_testuser", "newtest@example.com", new Coordinate(38.6589, 10.8168));

        when(breederRepository.findById(id)).thenReturn(Optional.of(existingBreeder));
        when(breederRepository.save(any(Breeder.class))).thenReturn(updatedBreeder);
        when(breederMapper.toResponseDTO(updatedBreeder)).thenReturn(expectedResponse);

        BreederResponseDTO result = breederService.update(id, breederRequestDTO);

        assertTrue(BCrypt.checkpw(breederRequestDTO.password(), existingBreeder.getPassword()), "Password should be hashed");

        verify(breederRepository).save(existingBreeder);

        assertEquals(expectedResponse, result, "The returned DTO should match the expected response");

        assertEquals(breederRequestDTO.userName(), existingBreeder.getUserName(), "Username should be updated");
        assertEquals(breederRequestDTO.email(), existingBreeder.getEmail(), "Email should be updated");

    }

    @Test
    void delete() {
        String id = "1";
        doNothing().when(breederRepository).deleteById(id);

        breederService.delete(id);

        verify(breederRepository, times(1)).deleteById(id);
    }
}