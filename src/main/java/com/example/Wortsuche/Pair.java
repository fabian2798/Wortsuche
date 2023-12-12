package com.example.Wortsuche;


import java.util.Map;

public class Pair {
    String word;
    Map<String, Integer> place;
    String direction;
    int[] indices;

    public Pair(String word, Map<String, Integer> place, String direction, int[] indices) {
        this.word = word;
        this.place = place;
        this.direction = direction;
        this.indices = indices;
    }

    public String getWord() {
        return word;
    }

    public Map<String, Integer> getPlace() {
        return place;
    }

    public String getDirection() {
        return direction;
    }

    public int[] getIndices() {
        return indices;
    }
}

