package vitniksys.backend.controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public abstract class VitnikController
{
    private Stage _stage;
    private VitnikController _prevController;

    protected void setPrevController(VitnikController prevController)
    {
        _prevController = prevController;
    }

    protected VitnikController getPrevController()
    {
        return _prevController;
    }

    protected void setStage(Stage stage)
    {
        _stage = stage;
    }

    protected Stage getStage()
    {
        return _stage;
    }

    @FXML
    private void homeButtonPressed()
    {
        _stage.close();
    }

    protected abstract void refresh();
}