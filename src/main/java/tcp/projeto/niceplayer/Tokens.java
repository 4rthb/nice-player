package tcp.projeto.niceplayer;

abstract class Tokens {
    protected String token;

    public void setToken(String newToken){
        token = newToken;
    }
    public String getToken(){
        return token;
    }
    public void reset(){
        token = "";
    }
}
