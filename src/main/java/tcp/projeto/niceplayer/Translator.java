package tcp.projeto.niceplayer;

import java.util.ArrayList;
import org.jfugue.midi.MidiParserListener;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.ManagedPlayer;
import org.jfugue.player.Player;
import org.jfugue.theory.Note;
import org.staccato.StaccatoParser;

public class Translator {
    private Player player;
    private ManagedPlayer managedPlayer;
    private String completePattern;
    private ArrayList<Tokens> tokenList;
    private int volume = 15, instrument = 0, cursor;

    public void setPlayer(Player player){
        this.player = player;
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
    public void reset() {
        managedPlayer.reset();
    }

    public void play(ArrayList<Tokens> parsedInput) {
        tokenList = parsedInput;
        Pattern pattern = getMusic();

        StaccatoParser staccatoParser = new StaccatoParser();
        MidiParserListener midiParserListener = new MidiParserListener();
        staccatoParser.addParserListener(midiParserListener);

        System.out.print("Pattern: ");
        System.out.println(pattern);
            try {
            System.out.print("Sequence: ");
            staccatoParser.parse(pattern);
            System.out.println(midiParserListener.getSequence());
            managedPlayer.start(midiParserListener.getSequence());
            Notes.resetOctaveCounter();
        } catch (Exception e) {
            System.out.println("Invalid MIDI detected!");
            e.printStackTrace();
        }
    }
    public Pattern getMusic() {
        completePattern = "";

        completePattern += ":CON(7, " + volume + ")";
        for (cursor = 0; cursor < tokenList.size(); cursor++) {
            if(tokenList.get(cursor) instanceof Notes) {
                completePattern += " " + tokenList.get(cursor).getToken();
                continue;
            }
            actionHandler(((Commands) tokenList.get(cursor)).getAction());
        }
        System.out.println(completePattern);
        return new Pattern(completePattern);
    }

    private void actionHandler(Commands.Action action) {
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
            default:                                     break;
        }
    }
    private void setAgogoHandler() {
        instrument = 113;
        completePattern += " I" + instrument;
    }
    private void setHarpsiHandler() {
        instrument = 6;
        completePattern += " I" + instrument;
    }
    private void setBellsHandler() {
        instrument = 14;
        completePattern += " I" + instrument;
    }
    private void setFluteHandler() {
        instrument = 75;
        completePattern += " I" + instrument;
    }
    private void setOrganHandler() {
        instrument = 19;
        completePattern += " I" + instrument;
    }
    private void changeInstrumentHandler() {
        instrument += Integer.parseInt(tokenList.get(cursor - 1).getToken());
        completePattern += " I" + instrument;
    }
    private void repeatLastHandler() {
        if(cursor > 1 && tokenList.get(cursor-1) instanceof Notes) {
            completePattern += " " + tokenList.get(cursor-1).getToken();
            return;
        }
        completePattern += " R";
    }
    private void volumeUpHandler() {
        volume *= 2;
        if (volume > 128) {
            volume = 15;
        }
        completePattern += " :CON(7, " + volume + ")";
    }
    private void incOctaveHandler() {
        Notes.IncreaseOctave();
    }
}
