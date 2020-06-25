package vitniksys;

import java.net.URL;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;

public class App extends Application 
{
    @Override
    public void start(final Stage stage) throws IOException
    {
        String fxml = "mainMenu";
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(App.class.getResource("")+"../frontend/views/"+fxml+".fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(final String[] args)
    {
        launch();
    }    
}