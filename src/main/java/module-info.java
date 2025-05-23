module org.chekizybra.kanecting {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires okhttp3;
    requires org.json;

    opens org.chekizybra.kanecting to javafx.fxml;
    exports org.chekizybra.kanecting;
}