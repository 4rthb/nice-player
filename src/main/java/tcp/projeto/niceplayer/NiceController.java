package tcp.projeto.niceplayer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import org.jfugue.player.ManagedPlayer;
import org.jfugue.player.ManagedPlayerListener;
import org.jfugue.player.Player;

import javax.sound.midi.Sequence;

public class NiceController {
    private Player player = new Player();
    private ManagedPlayer managedPlayer = player.getManagedPlayer();

    @FXML private TextArea userInput;
    @FXML private Button playButton;
    @FXML private Button pauseButton;
    @FXML private Button stopButton;
    @FXML private Button trashButton;

    public NiceController() {
        managedPlayer.addManagedPlayerListener(new ManagedPlayerListener() {
            @Override
            public void onStarted(Sequence sequence) {
                pauseButton.setDisable(false);
                stopButton.setDisable(false);
            }

            @Override
            public void onFinished() {
                pauseButton.setDisable(true);
                stopButton.setDisable(true);
            }

            @Override
            public void onPaused() {

            }

            @Override
            public void onResumed() {

            }

            @Override
            public void onSeek(long l) {

            }

            @Override
            public void onReset() {
                pauseButton.setDisable(true);
                stopButton.setDisable(true);
            }
        });
    }

    @FXML
    protected void onPlay() {
        try {
            managedPlayer.start(player.getSequence(userInput.getText()));
        } catch (Exception e) {

        }

    }

    @FXML
    protected void onPause() {
        if(managedPlayer.isPaused())
            managedPlayer.resume();
        else
            managedPlayer.pause();
    }

    @FXML
    protected void onStop() {
        managedPlayer.reset();
    }

    @FXML
    protected void onTrash() {
        managedPlayer.reset();
        userInput.clear();
    }
}