package vitniksys.frontend.view_controllers;

import java.net.URL;
import vitniksys.App;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import vitniksys.backend.util.CustomAlert;
import javafx.scene.control.Alert.AlertType;
import vitniksys.backend.util.ExpressionChecker;
import vitniksys.backend.model.services.Service;
import vitniksys.frontend.views_subscriber.ServiceSubscriber;

public abstract class ViewCntlr implements Initializable, ServiceSubscriber
{
    private Stage stage;
    private Service service;
    private ViewCntlr prevViewCntlr;
    private ExpressionChecker expressionChecker;

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

    protected ViewCntlr createStage(String title, String sceneName, Service service)
    {
        String fileName = sceneName;
        FXMLLoader fxmlLoader = null;
        Scene scene = null;
        try
        {
            fxmlLoader = new FXMLLoader(new URL(App.GUIs_LOCATION+fileName+App.FILE_EXTENSION));
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
        
        viewCtrller.setService(service);
        service.setServiceSubscriber(viewCtrller);
        service.setExpressionChecker(this.expressionChecker);

        return viewCtrller;
    }

    // ==================================== FXML methods ====================================
    @FXML
    private void backButtonPressed()
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

    protected ExpressionChecker getExpressionChecker()
    {
        return this.expressionChecker;
    }

    protected void setExpressionChecker(ExpressionChecker expressionChecker)
    {
        this.expressionChecker = expressionChecker;
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
            this.expressionChecker = ExpressionChecker.getExpressionChecker();
            this.customInitialize(url, rb);    
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }   
    }

    // ================================= service subscriber methods =================================
    @Override
    public Service getService()
    {
        return this.service;
    }

    @Override
    public void setService(Service service)
    {
        this.service = service;
    }
    @Override
    public CustomAlert showProcessIsWorking(String message)
    {
        return new CustomAlert(AlertType.NONE, "PROCESANDO", message).customShow();
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
        this.getStage().close();
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
        
    }
}