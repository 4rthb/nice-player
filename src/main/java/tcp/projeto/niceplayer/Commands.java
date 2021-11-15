package tcp.projeto.niceplayer;

import java.util.regex.Pattern;

public class Commands extends Tokens { 
    private Action MappingTokenToAction() {
        Pattern letter = Pattern.compile("[a-zA-Z]");
        Pattern notes = Pattern.compile("[ABCDEFG]");
        Pattern validVogal = Pattern.compile("[OoIiUu]");
        Pattern digit = Pattern.compile("\\d");

        if(letter.matcher(token).find()) {
            if(notes.matcher(token).find()) {
                return Action.NONE;
            }
            if(validVogal.matcher(token).find()) {
                return Action.INST_HARPSI;
            }
            return Action.REPEAT_LAST;
        }
        if(digit.matcher(token).find()) {
            return Action.INST_CHANGE;
        }
        if(token == " ") {
            return Action.VOL_UP;
        }
        if(token == "!") {
            return Action.INST_AGOGO;
        }
        if(token == "?" || token == ".") {
            return Action.INC_OCTAVE;
        }
        if(token == "\\n") {
            return Action.INST_BELLS;
        }
        if(token == ";") {
            return Action.INST_FLUTE;
        }
        if(token == ",") {
            return Action.INST_ORGAN;
        }
        return Action.REPEAT_LAST;
    }
    public enum Action {
        INST_AGOGO,
        INST_HARPSI,
        INST_BELLS,
        INST_FLUTE,
        INST_ORGAN,
        INST_CHANGE,
        REPEAT_LAST,
        VOL_UP,
        INC_OCTAVE,
        NONE
    }
    public Action getAction() {
        return MappingTokenToAction();
    }
}