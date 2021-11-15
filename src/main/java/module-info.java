module tcp.projeto.niceplayer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    requires org.kordamp.bootstrapfx.core;
    requires jfugue;
    requires java.desktop;

    opens tcp.projeto.niceplayer to javafx.fxml;
    exports tcp.projeto.niceplayer;
}