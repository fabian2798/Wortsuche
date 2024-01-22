package com.example.Wortsuche;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "spielvorlagen")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vorlagen {
    @Id
    private ObjectId id;
    private String title;
    private List<String> loesungswoerter;
    private char[][] grid;


    public Vorlagen(String title, List<String> loesungswoerter, char[][] grid) {
        this.title = title;
        this.loesungswoerter = loesungswoerter;
        this.grid = grid;
    }

    public char[][] getGrid() {
        return this.grid;
    }

    public List<String> getLoesungswoerter() {
        return this.loesungswoerter;
    }
}
