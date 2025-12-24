package vitniksys.frontend.view_controllers;

import java.net.URL;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

public class ProcessingViewCntlr extends ViewCntlr implements Initializable
{
    // ================================= FXML variables =================================
    @FXML private Label message1;
    @FXML private Label message2;

    @FXML private ProgressIndicator progressIndicator;
    // ================================= FXML methods ===================================

    // ================================= private methods ================================

    // ================================= protected methods ==============================
    @Override
    protected void manualInitialize()
    {
        
    }

    // ================================= public methods =================================
    public void setMessage1(String message1)
    {
        this.message1.setText(message1);
    }

    public void setMessage2(String message2)
    {
        this.message2.setText(message2);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // TODO Auto-generated method stub
    }

    @Override
    public void customInitialize(URL location, ResourceBundle resources) throws Exception
    {
        // TODO Auto-generated method stub

    }

    // ================================= service subscriber methods =================================
    @Override
    public void refresh()
    {
        
    }
}