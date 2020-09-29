package vitniksys;

import java.net.URL;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.application.Application;
import vitniksys.backend.util.DetailFileInterpreter;

public class App extends Application
{
    public static final String GUIs_LOCATION = App.class.getResource("") + "../frontend/GUIs/";
    public static final String FILE_EXTENSION = ".fxml";

    @Override
    public void start(final Stage stage) throws IOException
    {
        String fileName = "mainMenu";
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(GUIs_LOCATION + fileName + FILE_EXTENSION));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(final String[] args)
    {
        launch();
    }    
}