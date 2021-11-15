package src.main.java.tcp.projeto.niceplayer;

abstract class Tokens {
    protected String token;

    public String getToken(){
        return token;
    }
    public void reset(){
        token = "";
    }
}
