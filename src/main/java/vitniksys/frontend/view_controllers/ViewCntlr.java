package vitniksys.frontend.view_controllers;

import java.net.URL;
import vitniksys.App;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import vitniksys.backend.model.business_logic.BLService;
import vitniksys.backend.util.CustomAlert;
import vitniksys.frontend.view_subscribers.BLServiceSubscriber;
import javafx.scene.control.Alert.AlertType;

public abstract class ViewCntlr implements Initializable, BLServiceSubscriber
{
    private Stage stage;
    private List<BLService> services;
    private ViewCntlr prevViewCntlr;

    // ================================= Getters && setters =================================
    protected void setPrevViewCntlr(ViewCntlr prevViewCntlr)
    {
        this.prevViewCntlr = prevViewCntlr;
    }

    protected ViewCntlr getPrevViewCntlr()
    {
        return this.prevViewCntlr;
    }

    protected void setStage(Stage stage)
    {
        this.stage = stage;
    }

    protected Stage getStage()
    {
        return this.stage;
    }

    // ==================================== FXML methods ====================================
    @FXML
    protected void backButtonPressed()
    {
        this.stage.close();
    }

    // =================================== private methods ==================================


    // ================================= protected methods ==================================
    /**
     * This method is supposed to be used when some code needs
     * to be separated from the initialize (javafx.fxml.Initializable)
     * that execute automatically.
     */
    protected abstract void manualInitialize();

    protected ViewCntlr createStage(String title, String sceneName, BLService ... services)
    {
        String fileName = sceneName;
        FXMLLoader fxmlLoader = null;
        Scene scene = null;
        try
        {
            fxmlLoader = new FXMLLoader(new URL(App.ConstraitConstants.GUIs_LOCATION+fileName+App.ConstraitConstants.FXML_FILE_EXTENSION));
            scene = new Scene(fxmlLoader.load());
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        
        ViewCntlr viewCtrller = fxmlLoader.getController();
        viewCtrller.setStage(new Stage());
        viewCtrller.getStage().setResizable(false);
        viewCtrller.getStage().setScene(scene);
        viewCtrller.getStage().setTitle(title);
        viewCtrller.setPrevViewCntlr(this);
        
        for(BLService service: services)
        {
            viewCtrller.addService(service);
            service.setBLServiceSubscriber(viewCtrller);
        }
        
        return viewCtrller;
    }

    // =================================== public methods ===================================
    /**
     * This method will be called automatically before the view is displayed.
     * Use this method to initialize the controller.
     */
    public abstract void customInitialize(URL location, ResourceBundle resources) throws Exception;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try 
        {
            this.services = new ArrayList<>();
            this.customInitialize(url, rb);    
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }   
    }

    // ================================= service subscriber methods =================================
    @Override
    public BLService getBLService(int location)
    {
        return this.services.get(location);
    }

    @Override
    public int addService(BLService service)
    {
        this.services.add(service);
        return this.services.size()-1;
    }

    @Override
    public void closeSubscriberStage()
    {
        this.stage.close();
    }

    @Override
    public CustomAlert showProcessIsWorking(String message)
    {
        CustomAlert customAlert = new CustomAlert(AlertType.NONE, "PROCESANDO", message);
        customAlert.customShow();
        return customAlert; 
    }

    @Override
    public void closeProcessIsWorking(CustomAlert customAlert)
    {
        customAlert.customClose();
    }

    @Override
    public void showSucces(String message)
    {
        new CustomAlert(AlertType.INFORMATION, "EXITO", message).customShow();
    }

    @Override
    public void showError(String message)
    {
        new CustomAlert(AlertType.ERROR, "ERROR", message).customShow();
    }

    @Override
    public void showError(String message, String description, Exception exception)
    {
        new CustomAlert(AlertType.ERROR, "ERROR", message, description, exception).customShow();
    }

    @Override
    public void showNoResult(String message)
    {
        new CustomAlert(AlertType.WARNING, "SIN RESULTADOS", message).customShow();   
    }
}