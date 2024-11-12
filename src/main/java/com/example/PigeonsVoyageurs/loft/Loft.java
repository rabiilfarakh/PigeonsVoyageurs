package com.example.PigeonsVoyageurs.loft;

import com.example.PigeonsVoyageurs.coordinate.Coordinate;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "lofts")
public class Loft {
    @Id
    private String id;
    private String name;
    private Coordinate coordinate;

    public Loft() {
    }

    public Loft(String id, String name, Coordinate coordinate) {
        this.id = id;
        this.name = name;
        this.coordinate = coordinate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
