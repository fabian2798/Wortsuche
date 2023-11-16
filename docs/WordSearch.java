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

        List<String> foundWords = new ArrayList<>();

        findAndRemoveWords(grid, words, foundWords);

        
        System.out.println("Gefunden wurde: " + foundWords);
    }

    public static void findAndRemoveWords(char[][] grid, List<String> searchedWords, List<String> foundWords) {
        int rowLength = grid.length;
        int colLength = grid[0].length;

        while (!searchedWords.isEmpty()) {
            String word = searchedWords.remove(0);
            boolean found = false;

            // Horizontal search
            for (int row = 0; row < rowLength; row++) {
                String rowString = new String(grid[row]);
                if (rowString.contains(word)) {
                    System.out.println("Wort: " + word + " gefunden in Zeile " + (row + 1));
                    found = true;
                    foundWords.add(word);
                    break;
                }
            }

            // Vertical search
            if (!found) {
                for (int col = 0; col < colLength; col++) {
                    StringBuilder colString = new StringBuilder();
                    for (int row = 0; row < rowLength; row++) {
                        colString.append(grid[row][col]);
                        System.out.println(colString);
                    }
                    if (colString.toString().contains(word)) {
                        System.out.println("Wort: " + word + " gefunden in Spalte " + (col + 1));
                        found = true;
                        foundWords.add(word);
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
