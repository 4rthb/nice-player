package tcp.projeto.niceplayer;

import java.util.ArrayList;

public class MusicPlayer {
    private Translator translator;
    public void setTranslator(Translator translator){
        this.translator = translator;
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
    public void play(Music music){
        Pattern pattern = new Pattern(music.getPattern())
                            .setTempo(music.getTempo());
        player.play(pattern);
    }
}
