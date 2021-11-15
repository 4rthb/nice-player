package src.main.java.tcp.projeto.niceplayer;


public class MusicPlayer {
    private src.main.java.tcp.projeto.niceplayer.Translator translator;
    public void setTranslator(src.main.java.tcp.projeto.niceplayer.Translator translator){
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
    public void play(src.main.java.tcp.projeto.niceplayer.Music music){
        Pattern pattern = new Pattern(music.getPattern())
                            .setTempo(music.getTempo());
        player.play(pattern);
    }*/
}
