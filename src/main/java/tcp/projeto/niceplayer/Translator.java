package tcp.projeto.niceplayer;

import java.util.ArrayList;

import org.jfugue.pattern.Atom;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.ManagedPlayer;
import org.jfugue.player.Player;

public class Translator {
    private static Player player;
    private static ManagedPlayer managedPlayer;
    private ArrayList<Tokens> tokenList;
    private String volume = "10", instrument = "0";
    private int cursor = 0;

    public Translator(){
        player = new Player();
        managedPlayer = player.getManagedPlayer();
    }
    public void pause() {
        managedPlayer.pause();
    }
    public boolean isPaused() {
        return managedPlayer.isPaused();
    }
    public void resume() {
        managedPlayer.resume();
    }
    public boolean isPlaying() {
        return managedPlayer.isPlaying();
    }
    public void play(ArrayList<Tokens> parsedInput) {
        tokenList = new ArrayList<Tokens>();
        tokenList = parsedInput;
        org.jfugue.pattern.Pattern pattern = getMusic();
        player.play(pattern);
    }
    public Pattern getMusic() {
        Pattern newPattern;
        for (Tokens token: tokenList) {
            if(token instanceof Notes) {
                continue;
            }
        }

        return newPattern;
    }
    private actionHandler(Commands.Action action) {
        switch (action) {
            case INST_AGOGO:  setAgogoHandler();         break;
            case INST_HARPSI: setHarpsiHandler();        break;
            case INST_BELLS:  setBellsHandler();         break;
            case INST_FLUTE:  setFluteHandler();         break;
            case INST_ORGAN:  setOrganHandler();         break;
            case INST_CHANGE: changeInstrumentHandler(); break;
            case REPEAT_LAST: repeatLastHandler();       break;
            case VOL_UP:      volumeUpHandler();         break;
            case INC_OCTAVE:  incOctaveHandler();        break;
            default:            break;
        }
    }
    private void setAgogoHandler() {
        instrument = "113";
    }
    private void setHarpsiHandler() {
        instrument = "6";
    }
    private void setBellsHandler() {
        instrument = "14";
    }
    private void setFluteHandler() {
        instrument = "75";
    }
    private void setOrganHandler() {
        instrument = "19";
    }
    private void changeInstrumentHandler() {
        //instrument += tokenList.get(cursor).getToken().toString();
    }
    private void repeatLastHandler() {
        if(cursor > 0 && tokenList.get(cursor-1) instanceof Notes) {

        }
        managedPlayer.pause();
    }
    private void volumeUpHandler() {

    }
    private void incOctaveHandler() {
        if()
    }
}
