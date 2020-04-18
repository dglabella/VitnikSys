package vitniksys.backend.controllers;

import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vitniksys.App;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class MenuPrincipalController extends VitnikController implements Initializable
{
    @FXML private ImageView catButton;
    @FXML private TextField textField_cp;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }
    
    @Override
    protected void refresh()
    {

    }

    @FXML
    private void textFieldCpOnKeyTyped()
    {

    }

    @FXML
    private void textFieldCpOnAction() throws IOException 
    {
        buscarButtonPressed();
    }

    @FXML
    private void textFieldCatOnAction() throws IOException
    {
        buscarButtonPressed();
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
        String fxml = "ConsultarCatalogo";
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(App.class.getResource("")+"../frontend/fxmls/"+fxml+".fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ConsultarCatalogoController ctrller = fxmlLoader.getController();
        ctrller.setStage(new Stage());
        ctrller.getStage().setScene(scene);
        ctrller.getStage().setTitle("Consultar Cátalogo");
        ctrller.setPrevController(this);
        //stage.setX(0);
        //stage.setY(0);
        //stage.setMaximized(true);
        ctrller.getStage().show();
    }

    @FXML
    private void buscarButtonPressed() throws IOException
    {
        
    }

    @FXML
    private void buscarCampButtonPressed()
    {

    }

    @FXML
    private void nuevoCpButtonPressed() throws IOException
    {
            
    }

    @FXML
    private void CampButtonPressed() throws IOException
    {
        
    }
}