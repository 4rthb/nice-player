package src.main.java.tcp.projeto.niceplayer;

import org.jfugue.player.Player;

public class Nice {
    public static void main(String[] args) {
        Player player = new Player();
        player.play("I[Piano] T[Lento] C4 C4 C4 C4 C4 C0 C4 C4 C4 T[Presto] C4 C4 F6 I[Flute] G9 A2 B");
    }
}