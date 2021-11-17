package tcp.projeto.niceplayer;

import java.util.ArrayList;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.ManagedPlayer;
import org.jfugue.player.Player;

public class Translator {
    private static Player player;
    private static ManagedPlayer managedPlayer;
    private ArrayList<ArrayList<String>> pseudoPatterns;
    private ArrayList<Tokens> tokenList;
    private int volume = 70, instrument = 0, row = 0, cursor = 0;

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
    public void reset() {
        managedPlayer.reset();
    }
    public void play(ArrayList<Tokens> parsedInput) {
        tokenList = parsedInput;
        ArrayList<Pattern> patterns = getMusic();

        for(Pattern pattern : patterns) {
            player.play(pattern);
        }
    }
    public ArrayList<Pattern> getMusic() {
        ArrayList<Pattern> patternList = new ArrayList<Pattern>();
        pseudoPatterns = new ArrayList<ArrayList<String>>();
        pseudoPatterns.add(new ArrayList<String>());

        for (int i = 0; i < tokenList.size(); i++, cursor++) {
            System.out.println(tokenList.get(i).getToken());
            if(pseudoPatterns.size() == row) {
                pseudoPatterns.add(new ArrayList<String>());
            }
            if(tokenList.get(i) instanceof Notes) {
                pseudoPatterns.get(row).add(cursor, tokenList.get(i).getToken());
                continue;
            }
            actionHandler(((Commands) tokenList.get(i)).getAction());
        }
        pseudoPatterns.get(row).add(cursor-1, ":CON(7, " + volume + ")");

        for(int i = 0; i < pseudoPatterns.size(); i++) {
            String completePattern;
            completePattern = pseudoPatterns.get(i).get(pseudoPatterns.get(i).size() - 1) + " ";
            for(int j = 0; j < pseudoPatterns.get(i).size() - 1; j++) {
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
        pseudoPatterns.get(row).add(cursor, "I[" + volume + "]");
    }
    private void setHarpsiHandler() {
        instrument = 6;
        pseudoPatterns.get(row).add(cursor, "I[" + instrument + "]");
    }
    private void setBellsHandler() {
        instrument = 14;
        pseudoPatterns.get(row).add(cursor, "I[" + instrument + "]");
    }
    private void setFluteHandler() {
        instrument = 75;
        pseudoPatterns.get(row).add(cursor, "I[" + instrument + "]");
    }
    private void setOrganHandler() {
        instrument = 19;
        pseudoPatterns.get(row).add(cursor, "I[" + instrument + "]");
    }
    private void changeInstrumentHandler() {
        instrument += Integer.parseInt(tokenList.get(cursor).getToken());
        pseudoPatterns.get(row).add(cursor, "I[" + instrument + "]");
    }
    private void repeatLastHandler() {
        if(cursor > 0 && tokenList.get(cursor-1) instanceof Notes) {
            pseudoPatterns.get(row).add(cursor, tokenList.get(cursor-1).toString());
            return;
        }
        pseudoPatterns.get(row).add(cursor, "R");
    }
    private void volumeUpHandler() {
        volume *= 2;
        if (volume > 16383) {
            volume = 70;
        }
        pseudoPatterns.get(row).add(cursor, ":CON(7, " + volume + ")");
        row += 1;
        cursor = -1;
    }
    private void incOctaveHandler() {
        Notes.IncreaseOctave();
    }
}
