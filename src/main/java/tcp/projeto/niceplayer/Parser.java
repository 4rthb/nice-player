package tcp.projeto.niceplayer;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Parser {
    public static ArrayList<Tokens> tokens;

    public static Tokens getNotesByID(int id) {
        if (id >= tokens.size()){
            id = tokens.size()-1;
        }
        return tokens.get(id);
    }
    public static void parseText(String inputString) {
        tokens = new ArrayList<Tokens>();
        Pattern notes = Pattern.compile("[ABCDEFG]");
        for(int i = 0; i < inputString.length(); i++) {
            if(!notes.matcher(Character.toString(inputString.charAt(i))).find()) {
                Commands newToken = new Commands();
                newToken.setToken(Character.toString(inputString.charAt(i)));
                tokens.add(newToken);
                continue;
            }
            Notes newToken = new Notes();
            newToken.setToken(Character.toString(inputString.charAt(i)));
            tokens.add(newToken);
        }
    }
}
