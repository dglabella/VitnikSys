package vitniksys.backend.view_controllers;

import java.net.URL;
import vitniksys.App;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;

public class MainMenuController extends VitnikController implements Initializable
{
    // ================================= FXML variables =================================
    @FXML private TextField id;
    @FXML private ImageView catButton;

    // ================================= FXML methods =================================
    @FXML
    private void idOnKeyTyped()
    {

    }

    @FXML
    private void idOnAction() throws IOException 
    {
        searchButtonPressed();
    }

    @FXML
    private void textFieldCatOnAction() throws IOException
    {
        searchButtonPressed();
    }

    @FXML
    private void catButtonOnMouseEntered()
    {
        catButton.setFitHeight(catButton.getFitHeight()+10);
        catButton.setFitWidth(catButton.getFitWidth()+10);
    }

    @FXML
    private void catButtonOnMouseExited()
    {
        catButton.setFitHeight(catButton.getFitHeight()-10);
        catButton.setFitWidth(catButton.getFitWidth()-10);
    }


    @FXML
    private void catButtonOnMousePressed() throws IOException
    {
        String fxml = "catalogueQuery";
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(App.class.getResource("")+"../frontend/views/"+fxml+".fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        CatalogueQueryController ctrller = fxmlLoader.getController();
        ctrller.setStage(new Stage());
        ctrller.getStage().setResizable(false);
        ctrller.getStage().setScene(scene);
        ctrller.getStage().setTitle("Consultar Cátalogo");
        ctrller.setPrevController(this);
        ctrller.getStage().show();
    }

    @FXML
    private void searchButtonPressed() throws IOException
    {

    }

    @FXML
    private void searchCampButtonPressed()
    {

    }

    @FXML
    private void newCpButtonPressed() throws IOException
    {
        String fxml = "clientRegister";
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(App.class.getResource("")+"../frontend/views/"+fxml+".fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ClientRegisterController ctrller = fxmlLoader.getController();
        ctrller.setStage(new Stage());
        ctrller.getStage().setResizable(false);
        ctrller.getStage().setScene(scene);
        ctrller.getStage().setTitle("Formulario de registro de Cliente preferencial");
        ctrller.setPrevController(this);
        ctrller.getStage().show();      
    }

    @FXML
    private void campButtonPressed() throws IOException
    {
        String fxml = "campQueryRegister";
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(App.class.getResource("")+"../frontend/views/"+fxml+".fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        CampQueryRegisterController ctrller = fxmlLoader.getController();
        ctrller.setStage(new Stage());
        ctrller.getStage().setResizable(false);
        ctrller.getStage().setScene(scene);
        ctrller.getStage().setTitle("Consultar campaña");
        ctrller.setPrevController(this);
        ctrller.getStage().show();
    }
    // ================================= private methods =================================

    // ================================= protected methods =================================
    @Override
    protected void refresh()
    {

    }

    // ================================= public methods =================================
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }
}