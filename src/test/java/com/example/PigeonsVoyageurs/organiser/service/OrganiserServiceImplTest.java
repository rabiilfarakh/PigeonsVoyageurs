package com.example.PigeonsVoyageurs.organiser.service;

import com.example.PigeonsVoyageurs.organiser.Organiser;
import com.example.PigeonsVoyageurs.organiser.OrganiserMapper;
import com.example.PigeonsVoyageurs.organiser.OrganiserRepository;
import com.example.PigeonsVoyageurs.organiser.dto.OrganiserRequestDTO;
import com.example.PigeonsVoyageurs.organiser.dto.OrganiserResponseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class OrganiserServiceImplTest {
    @Mock
    private OrganiserRepository organiserRepository;

    @Mock
    private OrganiserMapper organiserMapper;

    @InjectMocks
    private OrganiserServiceImpl organiserService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        reset(organiserRepository, organiserMapper);
    }

    @Test
    void register() {
        OrganiserRequestDTO organiserRequestDTO = new OrganiserRequestDTO("testuser", "testpassword", "test@example.com");
        Organiser organiserEntity = new Organiser("testuser", "hashed_password", "test@example.com");
        Organiser savedOrganiserEntity = new Organiser("testuser", "hashed_password", "test@example.com");
        OrganiserResponseDTO expectedResponseDTO = new OrganiserResponseDTO("testid", "testuser", "test@example.com");

        when(organiserMapper.toEntity(organiserRequestDTO)).thenReturn(organiserEntity);
        when(organiserRepository.save(organiserEntity)).thenReturn(savedOrganiserEntity);
        when(organiserMapper.toResponseDTO(savedOrganiserEntity)).thenReturn(expectedResponseDTO);

        OrganiserResponseDTO actualResponseDTO = organiserService.register(organiserRequestDTO);

        assertTrue(BCrypt.checkpw(organiserRequestDTO.password(), organiserEntity.getPassword()));

        assertEquals(expectedResponseDTO, actualResponseDTO);

        verify(organiserRepository).save(organiserEntity);
    }

    @Test
    void login_Success() {
        String email = "test@example.com";
        String password = "password123";
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        Organiser organiser = new Organiser("testuser", hashedPassword, email);

        when(organiserRepository.findByEmail(email)).thenReturn(Optional.of(organiser));

        Optional<String> result = organiserService.login(email, password);

        assertTrue(result.isPresent());
        assertEquals("Login successful", result.get());
    }

    @Test
    void login_Failure() {
        String email = "test@example.com";
        String password = "password123";

        when(organiserRepository.findByEmail(email)).thenReturn(Optional.empty());

        Optional<String> result = organiserService.login(email, password);

        assertFalse(result.isPresent());
    }

    @Test
    void findById() {
        String id = "testid";
        Organiser organiser = new Organiser("testuser", "testpassword", "test@example.com");
        organiser.setId(id);
        OrganiserResponseDTO expectedResponse = new OrganiserResponseDTO("testid","testuser", "test@example.com");

        when(organiserRepository.findById(id)).thenReturn(Optional.of(organiser));
        when(organiserMapper.toResponseDTO(organiser)).thenReturn(expectedResponse);

        Optional<OrganiserResponseDTO> result = organiserService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(expectedResponse, result.get());
    }

    @Test
    void findByEmail() {
        String email = "test@example.com";
        Organiser organiser = new Organiser("testuser", "testpassword", "test@example.com");
        OrganiserResponseDTO expectedResponse = new OrganiserResponseDTO("testid","testuser", "test@example.com");

        when(organiserRepository.findByEmail(email)).thenReturn(Optional.of(organiser));
        when(organiserMapper.toResponseDTO(organiser)).thenReturn(expectedResponse);

        Optional<OrganiserResponseDTO> result = organiserService.findByEmail(email);

        assertTrue(result.isPresent());
        assertEquals(expectedResponse, result.get());
    }

    @Test
    void findAll() {
        Organiser organiser1 = new Organiser("test1user", "test1password", "test1@example.com");
        organiser1.setId("test1");
        Organiser organiser2 = new Organiser("test2user", "test2password", "test2@example.com");
        organiser2.setId("test2");
        List<Organiser> organisers = List.of(organiser1, organiser2);
        List<OrganiserResponseDTO> expectedResponses = organisers.stream()
                .map(b -> new OrganiserResponseDTO(b.getId(), b.getUserName(), b.getEmail()))
                .toList();

        when(organiserRepository.findAll()).thenReturn(organisers);
        when(organiserMapper.toResponseDTO(organiser1)).thenReturn(expectedResponses.get(0));
        when(organiserMapper.toResponseDTO(organiser2)).thenReturn(expectedResponses.get(1));

        List<OrganiserResponseDTO> result = organiserService.findAll();

        assertEquals(expectedResponses, result);
    }

    @Test
    void update() {
        String id = "testid";
        OrganiserRequestDTO organiserRequestDTO = new OrganiserRequestDTO("new_testuser", "newPassword", "newtest@example.com");
        Organiser existingOrganiser = new Organiser("testuser", "oldPassword", "test@example.com");
        existingOrganiser.setId("testid");
        Organiser updatedOrganiser  = new Organiser("new_testuser", "newPassword", "newtest@example.com");
        updatedOrganiser.setId("testid");
        OrganiserResponseDTO expectedResponse = new OrganiserResponseDTO("testid", "new_testuser", "newtest@example.com");

        when(organiserRepository.findById(id)).thenReturn(Optional.of(existingOrganiser));
        when(organiserRepository.save(any(Organiser.class))).thenReturn(updatedOrganiser);
        when(organiserMapper.toResponseDTO(updatedOrganiser)).thenReturn(expectedResponse);

        OrganiserResponseDTO result = organiserService.update(id, organiserRequestDTO);

        assertTrue(BCrypt.checkpw(organiserRequestDTO.password(), existingOrganiser.getPassword()), "Password should be hashed");

        verify(organiserRepository).save(existingOrganiser);

        assertEquals(expectedResponse, result, "The returned DTO should match the expected response");

        assertEquals(organiserRequestDTO.userName(), existingOrganiser.getUserName(), "Username should be updated");
        assertEquals(organiserRequestDTO.email(), existingOrganiser.getEmail(), "Email should be updated");

    }

    @Test
    void delete() {
        String id = "1";
        doNothing().when(organiserRepository).deleteById(id);

        organiserService.delete(id);

        verify(organiserRepository, times(1)).deleteById(id);
    }
}