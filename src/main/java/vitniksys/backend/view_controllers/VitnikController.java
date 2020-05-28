package vitniksys.backend.view_controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public abstract class VitnikController
{
    private Stage stage;
    private VitnikController prevController;

    protected void setPrevController(VitnikController prevController)
    {
        this.prevController = prevController;
    }

    protected VitnikController getPrevController()
    {
        return this.prevController;
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