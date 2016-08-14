package com.meet4s.offlinenoadsenglishdictionary;

public class Word {
    private String word;
    private String type;
    private String definition;

    public String getWord() {
        return word;
    }
    public String getType() {
        return type;
    }
    public String getDefinition() {
        return definition;
    }
    public Word(String word, String type, String definition) {
        this.word = word;
        this.type = type;
        this.definition = definition;
    }
}
