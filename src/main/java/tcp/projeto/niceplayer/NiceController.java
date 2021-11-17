package tcp.projeto.niceplayer;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.jfugue.player.ManagedPlayer;
import org.jfugue.player.ManagedPlayerListener;
import org.jfugue.player.Player;

import javax.sound.midi.Sequence;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Optional;

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
            MusicPlayer MPlayer = new MusicPlayer();
            MPlayer.setTranslator(new Translator());
            Parser.parseText(userInput.getText());
            MPlayer.play(Parser.tokens);
//            managedPlayer.start(player.getSequence(userInput.getText()));
        } catch (Exception e) {
            System.out.println("Exception " + e.toString() + " detected");
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

    @FXML
    protected void onNew() {
        if(userInput.getLength() != 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação de novo arquivo");
            alert.setHeaderText("Confirma criar uma nova música?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.CANCEL) {
                return;
            }
        }
        userInput.setText("");
    }

    @FXML
    protected void onOpen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir nova música");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Arquivos de texto bruto", "*.txt"),
                new FileChooser.ExtensionFilter("Arquivos Nice", "*.nice")
        );
        File selectedFile = fileChooser.showOpenDialog(userInput.getScene().getWindow());

        if(selectedFile == null) return;

        Input input = new Input(selectedFile.getAbsolutePath());
        input.readFile();
        userInput.setText(input.getTextStream());
    }

    @FXML
    protected void onSaveMIDI() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar MIDI");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Arquivo MIDI", "*.mid")
        );
        File selectedFile = fileChooser.showSaveDialog(userInput.getScene().getWindow());
    }

    @FXML
    protected void onSaveTXT() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar TXT");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Arquivo TXT", "*.txt")
        );
        File selectedFile = fileChooser.showSaveDialog(userInput.getScene().getWindow());

        if(selectedFile == null) return;

        Input input = new Input(selectedFile.getAbsolutePath());
        input.setTextStream(userInput.getText());
        input.saveFile();
    }
}