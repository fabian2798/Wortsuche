package com.example.Wortsuche;

public class Pair {
    String word;
    String place;
    String direction;
    int[] indices;

    public Pair(String word, String place, String direction, int[] indices) {
        this.word = word;
        this.place = place;
        this.direction = direction;
        this.indices = indices;
    }

    public String getWord() {
        return word;
    }

    public String getPlace() {
        return place;
    }

    public String getDirection() {
        return direction;
    }

    public int[] getIndices() {
        return indices;
    }
}

