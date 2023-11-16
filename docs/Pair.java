
public class Pair {
    String word;
    String direction;
    int[] indices;

    public Pair(String word, String direction, int[] indices) {
        this.word = word;
        this.direction = direction;
        this.indices = indices;
    }
    
    public String getWord() {
        return word;
    }

    public String getDirection() {
        return direction;
    }
  
    public int[] getIndices() {
        return indices;
    }
}