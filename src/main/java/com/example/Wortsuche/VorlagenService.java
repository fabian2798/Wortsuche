package com.example.Wortsuche;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Business Layer
 */
@Service
public class VorlagenService {
    @Autowired
    private VorlagenRepository vorlagenRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    public List<Vorlagen> allVorlagen(){
        // return all vorlagen in database
        return vorlagenRepository.findAll();
    }

    public Optional<Vorlagen> singleVorlage(ObjectId id){
        // return specific vorlage from database through id
        return vorlagenRepository.findById(id);
    }

    public Optional<Vorlagen> singleVorlageByTitle(String title){
        // return specific vorlage from database through title
        return vorlagenRepository.findByTitle(title);
    }

    public Map<String, List<Object>> findWordsinVorlagen(Vorlagen vorlage) {
        //error handling for not found words in Database[Loesungswoerter]
        final Logger log = LoggerFactory.getLogger(VorlagenService.class);

        List<Pair> foundWords = new ArrayList<>();
        // In the previous line, are the <> brackets after ArrayList necessary?
        // I think they are not necessary, but I am not sure.
        char[][] grid = vorlage.getGrid();
        //
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
                    Map<String, Integer> place = new HashMap<>();
                    place.put("Zeile",row);
                    foundWords.add(new Pair(word, place, "Horizontal", new int[]{startIndexWort, endIndexWort}));
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
                        Map<String, Integer> place = new HashMap<>();
                        place.put("Spalte",col);
                        foundWords.add(new Pair(word, place, "Vertikal", new int[]{startIndexWort, endIndexWort}));
                        break;
                    }
                }
            }
            // only happens, cause no diagonal search algorithmus
            if (!found) {
                log.warn("Wort: {} nicht gefunden", word);
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

    static List<String> getDiagonalGrid(char[][] grid, int[] direction) {
        List<String> listoflines = new ArrayList<>();
        for (int i = 0; i < xmax; i++) {
            StringBuilder newline = new StringBuilder();
            int[] next = {0, i};
            while (next[0] < ymax && next[1] < xmax) {
                newline.append(grid[next[0]][next[1]]);
                next[0] += direction[0];
                next[1] += direction[1];
            }
            listoflines.add(newline.toString());
        }
        for (int i = 1; i < ymax; i++) {
            StringBuilder newline = new StringBuilder();
            int[] next = {i, 0};
            while (next[0] < ymax && next[1] < xmax) {
                newline.append(grid[next[0]][next[1]]);
                next[0] += direction[0];
                next[1] += direction[1];
            }
            listoflines.add(newline.toString());
        }
        return listoflines;
    }
    /**
     * create new vorlagen
     * TODO: Implement creation of new vorlagen
     */
    public Vorlagen createVorlagen(String title, List<String> loesungswoerter, char[][] grid){

        return null;
    }
}
