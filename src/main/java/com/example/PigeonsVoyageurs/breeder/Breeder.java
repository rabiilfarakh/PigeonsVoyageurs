package com.example.PigeonsVoyageurs.breeder;

import com.example.PigeonsVoyageurs.coordinate.Coordinate;
import com.example.PigeonsVoyageurs.pigeon.Pigeon;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "breeders")
public class Breeder {
    @Id
    private Long id;
    private String dovecoteName;
    private String userName;
    private String password;
    private Coordinate coordinates;
    // Relation One-to-Many avec Pigeon
    @DBRef
    private List<Pigeon> pigeons;
}