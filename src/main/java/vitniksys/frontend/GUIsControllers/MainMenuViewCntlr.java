package vitniksys.frontend.GUIsControllers;

import java.net.URL;
import vitniksys.App;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
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

    // ================================== FXML methods ==================================
    @FXML
    private void idOnKeyTyped()
    {

    }

    @FXML
    private void idOnAction() throws Exception
    {
        searchButtonPressed();
    }

    @FXML
    private void textFieldCatOnAction() throws Exception
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
    private void catButtonOnMousePressed() throws Exception
    {
        String fileName = "catalogueQuery";
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(App.GUIs_LOCATION+fileName+App.FILE_EXTENSION));
        Scene scene = new Scene(fxmlLoader.load());
        CatalogueQueryViewCntlr viewCtrller = fxmlLoader.getController();
        viewCtrller.setStage(new Stage());
        viewCtrller.getStage().setResizable(false);
        viewCtrller.getStage().setScene(scene);
        viewCtrller.getStage().setTitle("Consultar Cátalogo");
        viewCtrller.setPrevViewCntlr(this);
        viewCtrller.getStage().show();
    }

    @FXML
    private void searchButtonPressed() throws Exception
    {

    }

    @FXML
    private void searchCampButtonPressed() throws Exception
    {
        String fileName = "searchCamps";
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(App.GUIs_LOCATION+fileName+App.FILE_EXTENSION));
        Scene scene = new Scene(fxmlLoader.load());
        SearchCampsViewCntlr viewCtrller = fxmlLoader.getController();
        viewCtrller.setStage(new Stage());
        viewCtrller.getStage().setResizable(false);
        viewCtrller.getStage().setScene(scene);
        viewCtrller.getStage().setTitle("Consultar campaña");
        viewCtrller.setPrevViewCntlr(this);
        viewCtrller.getStage().show();
        viewCtrller.manualInitialize();
    }

    @FXML
    private void newCpButtonPressed() throws Exception
    {
        String fileName = "clientRegister";
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(App.GUIs_LOCATION+fileName+App.FILE_EXTENSION));
        Scene scene = new Scene(fxmlLoader.load());
        ClientRegisterViewCntlr viewCtrller = fxmlLoader.getController();
        viewCtrller.setStage(new Stage());
        viewCtrller.getStage().setResizable(false);
        viewCtrller.getStage().setScene(scene);
        viewCtrller.getStage().setTitle("Formulario de registro de Cliente preferencial");
        viewCtrller.setPrevViewCntlr(this);
        viewCtrller.getStage().show();      
    }

    @FXML
    private void newCampButtonPressed() throws Exception
    {
        String fileName = "campRegister";
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(App.GUIs_LOCATION+fileName+App.FILE_EXTENSION));
        Scene scene = new Scene(fxmlLoader.load());
        CampRegisterViewCntlr viewCtrller = fxmlLoader.getController();
        viewCtrller.setStage(new Stage());
        viewCtrller.getStage().setResizable(false);
        viewCtrller.getStage().setScene(scene);
        viewCtrller.getStage().setTitle("Crear campaña");
        viewCtrller.setPrevViewCntlr(this);
        viewCtrller.getStage().show();
    }
    // ================================= private methods =================================


    // ================================= protected methods ===============================
    @Override
    protected void manualInitialize() throws Exception
    {

    }

    // ================================= public methods ==================================
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }
}