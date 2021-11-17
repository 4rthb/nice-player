package tcp.projeto.niceplayer;

import org.jfugue.player.ManagedPlayer;
import org.jfugue.player.Player;

import java.util.ArrayList;

public class MusicPlayer {
    private Translator translator;
    private Player player = new Player();

    public ManagedPlayer getManagedPlayer() { return player.getManagedPlayer(); }

    public void setTranslator(Translator translator){
        this.translator = translator;
        this.translator.setPlayer(player);
    }
    public void pause() {
        if(!translator.isPaused()){
            translator.pause();
        }
    }
    public void resume() {
        if(translator.isPaused()){
            translator.resume();
        }
    }
    public void reset() {
        translator.reset();
    }
    public void play(ArrayList<Tokens> parsedInput){
        if(!translator.isPlaying()){
            translator.play(parsedInput);
        }
    }
}
