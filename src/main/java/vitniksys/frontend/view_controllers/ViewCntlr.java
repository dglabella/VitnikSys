package vitniksys.frontend.view_controllers;

import java.net.URL;
import vitniksys.App;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public abstract class ViewCntlr
{
    private Stage stage;
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

    protected ViewCntlr createStage(String title, String sceneName)
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
        return viewCtrller;
    }

    // ==================================== FXML methods ====================================
    @FXML
    private void backButtonPressed()
    {
        this.stage.close();
    }

    /**
     * This method is supposed to be used when some code needs
     * to be separated from the initialize (javafx.fxml.Initializable)
     * that execute automatically.
     */

    // =================================== private methods ==================================


    // ================================= protected methods ==================================


    // =================================== public methods ===================================
    protected abstract void manualInitialize();
}