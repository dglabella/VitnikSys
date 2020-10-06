package vitniksys.frontend.GUIsControllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public abstract class VitnikViewCntlr
{
    private Stage stage;
    private VitnikViewCntlr prevViewCntlr;

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
    protected abstract void manualInitialize() throws Exception;
}