module com.chatapp.chatappjavaclient {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.chatapp.chatappjavaclient to javafx.fxml;
    exports com.chatapp.chatappjavaclient;
    requires org.json;
}
