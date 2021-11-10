package src.main.java.tcp.projeto.niceplayer;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Parser {
    private Tokens[] notes, commands;

    public Tokens getNotesByID(int id){
        if (id >= notes.length){
            id = notes.length-1;
        }
        return this.notes[id];
    }
    public Tokens getCommandsByID(int id){
        if (id >= commands.length){
            id = commands.length-1;
        }
        return this.commands[id];
    }
    
}
