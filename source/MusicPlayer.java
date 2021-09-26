package source;


import org.jfugue.pattern.Pattern;
import org.jfugue.player.ManagedPlayer;
import org.jfugue.player.Player;

public class MusicPlayer {
    private static Player player;
    private static ManagedPlayer managedPlayer;
    
    public MusicPlayer(){
        player = new Player();
        managedPlayer = player.getManagedPlayer();
    }
    public static void pause() {
        if(managedPlayer.isPlaying()){
            managedPlayer.pause();
        }
    }
    public static void resume() {
        if(managedPlayer.isPaused()){ 
            managedPlayer.resume();
        }
    }
    public static void reset() {
        managedPlayer.reset();
    }
    public static void play(Music music){
        Pattern pattern = new Pattern(music.getPattern())
                            .setInstrument(music.getInstrument())
                            .setTempo(music.getTempo());
        player.play(pattern);
    }
}
