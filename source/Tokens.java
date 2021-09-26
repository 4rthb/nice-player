package source;

abstract class Tokens {
    private String token;

    public String getTokens(){
        return this.token;
    }
    public void add(String tokens){
        this.token = this.token + tokens;
    }
    public void reset(){
        this.token = "";
    }
}
