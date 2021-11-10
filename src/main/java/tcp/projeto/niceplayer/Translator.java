package src.main.java.tcp.projeto.niceplayer;

public class Translator {
    private Parser parser;
    private MusicPlayer player;
    private Player player;
    private ManagedPlayer managedPlayer;
    
    public void MusicPlayer(){
        player = new Player();
        managedPlayer = player.getManagedPlayer();
    }
    public void pause() {
        if(managedPlayer.isPlaying()){
            managedPlayer.pause();
        }
    }
    public void resume() {
        if(managedPlayer.isPaused()){ 
            managedPlayer.resume();
        }
    }
    public void reset() {
        managedPlayer.reset();
    }
    public void play(Music music){
        Pattern pattern = new Pattern(music.getPattern())
                            .setTempo(music.getTempo());
        player.play(pattern);
    }
}
