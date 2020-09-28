package vitniksys;

import java.net.URL;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import vitniksys.backend.util.DetailFileInterpreter;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;

public class App extends Application
{
    public static final String GUIs_LOCATION = App.class.getResource("") + "../frontend/GUIs/";
    public static final String FILE_EXTENSION = ".fxml";

    @Override
    public void start(final Stage stage) throws IOException
    {
        FileChooser fileChooser = new FileChooser();
        File detail = fileChooser.showOpenDialog(null);
        DetailFileInterpreter dfi = new DetailFileInterpreter(detail);
        dfi.insertClientFromDetailFile();

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