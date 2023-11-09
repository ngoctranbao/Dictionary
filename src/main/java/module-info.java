module mycutedict.frontend {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires freetts;
    requires jsapi;

    opens mycutedict.frontend to javafx.fxml;
    exports mycutedict.frontend;
}