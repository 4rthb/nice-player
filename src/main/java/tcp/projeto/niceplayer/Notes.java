package tcp.projeto.niceplayer;

public class Notes extends Tokens {
    private static int octaveCounter = 4;

    public static void IncreaseOctave() {
        if(octaveCounter == 7) {
            octaveCounter = 4;
            return;
        }
        octaveCounter += 1;
    }
    public static void resetOctaveCounter() {
        octaveCounter = 4;
    }
    public static int getOctaveCounter() {
        return octaveCounter;
    }
    public String getToken() {
        return token + octaveCounter;
    }
}
