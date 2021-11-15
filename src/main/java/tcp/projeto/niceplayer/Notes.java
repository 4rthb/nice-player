package tcp.projeto.niceplayer;

public class Notes extends Tokens {
    private int octaveCounter = 0;

    public void IncreaseOctave() {
        if(octaveCounter == 10) {
            octaveCounter = 4;
            return;
        }
        octaveCounter += 1;
    }
    public void setOctaveCounter(int num) {
        octaveCounter = num;
    }
    public int getOctaveCounter() {
        return octaveCounter;
    }
    public String getToken() {
        return token + octaveCounter;
    }
}
