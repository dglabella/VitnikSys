package vitniksys.frontend.GUIsControllers;

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

public class MainMenuViewCntlr extends VitnikViewCntlr implements Initializable
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
        String fileName = "catalogueQuery";
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(App.GUIs_LOCATION+fileName+App.FILE_EXTENSION));
        Scene scene = new Scene(fxmlLoader.load());
        CatalogueQueryViewCntlr ctrller = fxmlLoader.getController();
        ctrller.setStage(new Stage());
        ctrller.getStage().setResizable(false);
        ctrller.getStage().setScene(scene);
        ctrller.getStage().setTitle("Consultar Cátalogo");
        ctrller.setPrevViewCntlr(this);
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
        String fileName = "clientRegister";
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(App.GUIs_LOCATION+fileName+App.FILE_EXTENSION));
        Scene scene = new Scene(fxmlLoader.load());
        ClientRegisterViewCntlr ctrller = fxmlLoader.getController();
        ctrller.setStage(new Stage());
        ctrller.getStage().setResizable(false);
        ctrller.getStage().setScene(scene);
        ctrller.getStage().setTitle("Formulario de registro de Cliente preferencial");
        ctrller.setPrevViewCntlr(this);
        ctrller.getStage().show();      
    }

    @FXML
    private void campButtonPressed() throws IOException
    {
        String fileName = "campQueryRegister";
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(App.GUIs_LOCATION+fileName+App.FILE_EXTENSION));
        Scene scene = new Scene(fxmlLoader.load());
        CampQueryRegisterViewCntlr ctrller = fxmlLoader.getController();
        ctrller.setStage(new Stage());
        ctrller.getStage().setResizable(false);
        ctrller.getStage().setScene(scene);
        ctrller.getStage().setTitle("Consultar campaña");
        ctrller.setPrevViewCntlr(this);
        ctrller.getStage().show();
    }
    // ================================= private methods =================================

    // ================================= protected methods =================================

    // ================================= public methods =================================
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }
}