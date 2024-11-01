package com.example.PigeonsVoyageurs.organiser;

import com.example.PigeonsVoyageurs.organiser.service.OrganiserServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/organiser")
public class OrganiserAuthController {
    @Autowired
    private OrganiserServiceImpl organiserService;


    @Value("${jwt.secret}")
    private String secret;

    @PostMapping(name = "/login")
    public ResponseEntity<String> login(
            @RequestHeader("user_name") String userName,
            @RequestHeader("password") String password){
        List<OrganiserDTO> organiserDTOS = organiserService.getAll();

        Optional<OrganiserDTO> optionalOrganiserDTO = organiserDTOS.stream()
                .filter(organiserDTO -> organiserDTO.getUserName().equals(userName) && organiserDTO.getPassword().equals(password))
                .findFirst();

        if (optionalOrganiserDTO.isPresent()){
            OrganiserDTO authBreeder = optionalOrganiserDTO.get();

            Map<String, Object> claims = new HashMap<>();
            claims.put("id", authBreeder.getId());
            String optionalUsername = authBreeder.getUserName();

            String token = createToken(optionalUsername, claims);

            authBreeder.setToken(token);
            organiserService.update(authBreeder);

            return new ResponseEntity<>("Your token:" + token, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Incorrect credentials.", HttpStatus.EXPECTATION_FAILED);
        }
    }
    private String createToken(String username, Map<String, Object> claims) {
        Calendar expirationDay = Calendar.getInstance();
        expirationDay.setTime(new Date());
        expirationDay.add(Calendar.DAY_OF_MONTH, 1);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username).setIssuedAt(new Date())
                .setExpiration(expirationDay.getTime())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
    @PostMapping(name = "/signup")
    public ResponseEntity<String> signup(@RequestHeader @Valid OrganiserDTO organiserDTO){
        Optional<OrganiserDTO> optionalSavedBreeder = organiserService.save(organiserDTO);

        return optionalSavedBreeder
                .map(dto -> new ResponseEntity<>("Successful signup" + dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED));
    }
}
