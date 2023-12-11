package com.example.Wortsuche;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VorlagenService {
    @Autowired
    private VorlagenRepository vorlagenRepository;
    public List<Vorlagen> allVorlagen(){
        return vorlagenRepository.findAll();
    }

    public Optional<Vorlagen> singleVorlage(ObjectId id){
        return vorlagenRepository.findById(id);
    }

    public Optional<Vorlagen> singleVorlageByTitle(String title){
        return vorlagenRepository.findByTitle(title);
    }

    public Map<String, List<Object>> findWordsinVorlagen(Vorlagen vorlage) {
        List<Pair> foundWords = new ArrayList<>();
        char[][] grid = vorlage.getGrid();
        System.out.println(Arrays.deepToString(grid));
        List<String> loesungswoerter = vorlage.getLoesungswoerter();

        int rowLength = grid.length;
        int colLength = grid[0].length;

        while (!loesungswoerter.isEmpty()) {
            String word = loesungswoerter.remove(0);
            boolean found = false;

            // Horizontal search
            for (int row = 0; row < rowLength; row++) {
                String rowString = new String(grid[row]);
                int startIndexWort = rowString.indexOf(word.toUpperCase());
                if (startIndexWort != -1) {
                    found = true;
                    int endIndexWort = startIndexWort + word.length() - 1;
                    foundWords.add(new Pair(word, "Zeile: " + row, "Horizontal", new int[]{startIndexWort, endIndexWort}));
                    break;
                }
            }

            // Vertical search
            if (!found) {
                for (int col = 0; col < colLength; col++) {
                    StringBuilder colString = new StringBuilder();
                    for (int row = 0; row < rowLength; row++) {
                        colString.append(grid[row][col]);
                    }
                    int startIndexWort = colString.indexOf(word.toUpperCase());
                    if (startIndexWort != -1) {
                        found = true;
                        int endIndexWort = startIndexWort + word.length() - 1;
                        foundWords.add(new Pair(word, "Spalte: " + col, "Vertikal", new int[]{startIndexWort, endIndexWort}));
                        break;
                    }
                }
            }
            // Sollte nie passieren
            if (!found) {
                System.out.println("Wort: " + word + " nicht gefunden");
            }
        }
        Map<String, List<Object>> map = new HashMap<>();
        for (Pair pair : foundWords) {
            List<Object> attributes = new ArrayList<>();
            attributes.add(pair.getPlace());
            attributes.add(pair.getDirection());
            attributes.add(pair.getIndices()[0]);
            attributes.add(pair.getIndices()[1]);

            map.put(pair.getWord(), attributes);
        }
        return map;
    }
}
