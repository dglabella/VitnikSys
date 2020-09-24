package vitniksys.frontend.GUIsControllers;

import java.net.URL;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

public class ProcessingViewCntlr extends VitnikViewCntlr implements Initializable
{
    // ================================= FXML variables =================================
    @FXML private Label message1;
    @FXML private Label message2;

    @FXML private ProgressIndicator progressIndicator;
    // ================================= FXML methods ===================================

    // ================================= private methods ================================

    // ================================= protected methods ==============================

    // ================================= public methods =================================
    

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // TODO Auto-generated method stub
    }

    @Override
    protected void refresh()
    {
        // TODO Auto-generated method stub
    }
}