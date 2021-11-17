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
    private ArrayList<ArrayList<String>> pseudoPatterns;
    private ArrayList<Tokens> tokenList;
    private int volume = 15, instrument = 0, row = 0, cursor = 1;

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
        for(Tokens token: parsedInput){
            System.out.println(token.getToken());
        }

        ArrayList<Pattern> patterns = getMusic();

        StaccatoParser staccatoParser = new StaccatoParser();
        MidiParserListener midiParserListener = new MidiParserListener();
        staccatoParser.addParserListener(midiParserListener);

        for(Pattern pattern : patterns) {
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
    }
    public ArrayList<Pattern> getMusic() {
        ArrayList<Pattern> patternList = new ArrayList<Pattern>();
        pseudoPatterns = new ArrayList<ArrayList<String>>();
        pseudoPatterns.add(new ArrayList<String>());

        pseudoPatterns.get(row).add(cursor - 1, ":CON(7, " + volume + ")");
        for (int i = 0; i < tokenList.size(); i++, cursor++) {
            if(tokenList.get(i) instanceof Notes) {
                pseudoPatterns.get(row).add(cursor, tokenList.get(i).getToken());
                continue;
            }
            actionHandler(((Commands) tokenList.get(i)).getAction());
        }

        for(int i = 0; i < pseudoPatterns.size(); i++) {
            String completePattern;
            completePattern = pseudoPatterns.get(i).get(0) + " ";
            for(int j = 1; j < pseudoPatterns.get(i).size(); j++) {
                completePattern += pseudoPatterns.get(i).get(j) + " ";
            }
            patternList.add(new Pattern(completePattern));
        }

        return patternList;
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
        pseudoPatterns.get(row).add(cursor, "I" + volume);
    }
    private void setHarpsiHandler() {
        instrument = 6;
        pseudoPatterns.get(row).add(cursor, "I" + instrument);
    }
    private void setBellsHandler() {
        instrument = 14;
        pseudoPatterns.get(row).add(cursor, "I" + instrument);
    }
    private void setFluteHandler() {
        instrument = 75;
        pseudoPatterns.get(row).add(cursor, "I" + instrument);
    }
    private void setOrganHandler() {
        instrument = 19;
        pseudoPatterns.get(row).add(cursor, "I" + instrument);
    }
    private void changeInstrumentHandler() {
        instrument += Integer.parseInt(tokenList.get(cursor - 1).getToken());
        pseudoPatterns.get(row).add(cursor, "I" + instrument);
    }
    private void repeatLastHandler() {
        if(cursor > 1 && tokenList.get(cursor-2) instanceof Notes) {
            pseudoPatterns.get(row).add(cursor, tokenList.get(cursor-2).getToken());
            return;
        }
        pseudoPatterns.get(row).add(cursor, "R");
    }
    private void volumeUpHandler() {
        volume *= 2;
        if (volume > 128) {
            volume = 15;
        }
        row++;
        cursor = 0;
        pseudoPatterns.add(new ArrayList<String>());
        pseudoPatterns.get(row).add(cursor, ":CON(7, " + volume + ")");
    }
    private void incOctaveHandler() {
        Notes.IncreaseOctave();
        cursor-=1;
    }

}
