package src.main.java.tcp.projeto.niceplayer;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Parser {
    private Tokens[] tokens;

    public Tokens getNotesByID(int id) {
        if (id >= tokens.length){
            id = tokens.length-1;
        }
        return tokens[id];
    }
    public void parseText(String inputString) {
        
    }
    public org.jfugue.pattern.Pattern getMusic() {
        org.jfugue.pattern.Pattern newPattern; 



        return newPattern;
    }
}
