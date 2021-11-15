package tcp.projeto.niceplayer;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Parser {
    public ArrayList<Tokens> tokens;

    public Tokens getNotesByID(int id) {
        if (id >= tokens.size()){
            id = tokens.size()-1;
        }
        return tokens.get(id);
    }
    public void parseText(String inputString) {

        Pattern notes = Pattern.compile("[ABCDEFG]");
        Pattern octave = Pattern.compile("\\d");
        for(int i = 0; i < inputString.length(); i++) {
            if(!notes.matcher(Character.toString(inputString.charAt(i))).find()) {
                Commands newToken = new Commands();
                newToken.setToken(Character.toString(inputString.charAt(i)));
                tokens.add(newToken);
                continue;
            }
            Notes newToken = new Notes();
            newToken.setToken(Character.toString(inputString.charAt(i)));
            if(octave.matcher(Character.toString(inputString.charAt(i+1))).find()) {
                int octaveCounter = Character.getNumericValue(inputString.charAt(i+1));
                if(inputString.length() <= i+2 && inputString.substring(i+1, i+2).equals("10")) {
                    octaveCounter = 10;
                    i+=1;
                }
                newToken.setOctaveCounter(octaveCounter);
                i+=1;
            }
            tokens.add(newToken);
        }
    }
}
