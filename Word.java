package org.example;

public class Word {
    private String word_target;
    private String word_explain;

    public String getWord_target() {
        return word_target;
    }

    public void setWord_target(String word) {
        word_target = word;
    }

    public  String getWord_explain() { return word_explain;}

    public  void setWord_explain(String word) { word_explain = word;}

    public Word() {}

    public boolean equal(Word e) {
        if (this.word_target.equals(e.word_target)) {
            return true;
        }
        return false;
    }

    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }
}
