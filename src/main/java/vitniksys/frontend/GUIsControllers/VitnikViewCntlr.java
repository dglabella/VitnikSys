package vitniksys.frontend.GUIsControllers;

import java.net.URL;
import vitniksys.App;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public abstract class VitnikViewCntlr
{
    private Stage stage;
    private VitnikViewCntlr prevViewCntlr;

    // ================================= Getters && setters =================================
    protected void setPrevViewCntlr(VitnikViewCntlr prevViewCntlr)
    {
        this.prevViewCntlr = prevViewCntlr;
    }

    protected VitnikViewCntlr getPrevViewCntlr()
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

    protected VitnikViewCntlr createStage(String title, String sceneName) throws Exception
    {
        String fileName = sceneName;
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(App.GUIs_LOCATION+fileName+App.FILE_EXTENSION));
        Scene scene = new Scene(fxmlLoader.load());
        VitnikViewCntlr viewCtrller = fxmlLoader.getController();
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
    protected abstract void manualInitialize() throws Exception;
}