package tcp.projeto.niceplayer;

public class MusicPlayer {
    private Translator translator;
    public void setTranslator(Translator translator){
        this.translator = translator;
    }
    /*
    public void pause() {
        if(managedPlayer.isPaused()){
            this.translator.pause();
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
    }*/
}