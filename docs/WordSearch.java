import java.util.ArrayList;
import java.util.List;


public class WordSearch {

    public static void main(String[] args) {
        char[][] grid = {
                {'A', 'B', 'C', 'D'},
                {'E', 'F', 'G', 'H'},
                {'I', 'J', 'K', 'L'},
                {'M', 'N', 'O', 'P'}
        };

        List<String> words = new ArrayList<>();
        words.add("BC");
        words.add("EI");
        words.add("LP");
        words.add("NO");

        List<Pair> foundWords = new ArrayList<>();

        findAndRemoveWords(grid, words, foundWords);

        for (Pair pair : foundWords) {
            System.out.println("Wort: " + pair.getWord());
            System.out.println("Ausrichtung: " + pair.getDirection());
            System.out.println("Startindex: " + pair.getIndices()[0]);
            System.out.println("Endindex: " + pair.getIndices()[1]);
        }
 
    }

    public static void findAndRemoveWords(char[][] grid, List<String> searchedWords, List<Pair> foundWords) {
        int rowLength = grid.length;
        int colLength = grid[0].length;

        while (!searchedWords.isEmpty()) {
            String word = searchedWords.remove(0);
            boolean found = false;

            // Horizontal search
            for (int row = 0; row < rowLength; row++) {
                String rowString = new String(grid[row]);
                int startIndexWort = rowString.indexOf(word);
                if (startIndexWort != -1) {
                    System.out.println("Wort: " + word + " gefunden in Zeile " + (row + 1));
                    found = true;
                    int endIndexWort = startIndexWort + word.length() -1;
                    foundWords.add(new Pair(word, "Horizontal", new int[]{startIndexWort, endIndexWort}));
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
                    int startIndexWort = colString.indexOf(word);
                    if (startIndexWort != -1) {
                        System.out.println("Wort: " + word + " gefunden in Spalte " + (col + 1));
                        found = true;
                        int endIndexWort = startIndexWort + word.length() -1;
                        foundWords.add(new Pair(word, "Vertikal", new int[]{startIndexWort, endIndexWort}));
                        break;
                    }
                }
            }
            // Sollte nie passieren
            if (!found) {
                System.out.println("Wort: " + word + " nicht gefunden");
            }
        }
    }
}
