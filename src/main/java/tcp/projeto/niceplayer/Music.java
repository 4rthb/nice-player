package src.main.java.tcp.projeto.niceplayer;

public class Music {
    private String pattern, tempo, instrument;
    private src.main.java.tcp.projeto.niceplayer.Notes notes;
    private src.main.java.tcp.projeto.niceplayer.Commands commands;

    public String getPattern(){
        return this.pattern;
    }
    public void setPattern(){
        this.pattern = this.notes.getTokens() + this.commands.getAction();
        // doesn't make sense like this
    }
    public String getTempo(){
        return this.tempo;
    }
    public void setTempo(String tempo){
        this.tempo = tempo;
    }
    public String getInstrument(){
        return this.instrument;
    }
    public void setInstrument(String instrument){
        this.instrument = instrument;
    }
    public void addNotes(String notes){
        //this.notes.add(notes);
    }
    public void resetNotes(){
        this.notes.reset();
    }
    public void addCommands(String commands){
        //this.commands.add(commands);
    }
    public void resetCommands(){

        this.commands.reset();
    }

}
