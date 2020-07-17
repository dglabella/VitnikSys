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
     * Refresh the stage with new data.
     * This method should be called if it is necesary
     * show some changes on the stage.
     */
    protected abstract void refresh();
}