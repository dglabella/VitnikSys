package vitniksys.frontend.view_controllers;

import java.net.URL;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import vitniksys.backend.util.ExpressionChecker;

public class RepurchaseDialogContentViewCntlr extends DialogContentViewCntlr implements Initializable
{
    // ================================= FXML variables =================================
    @FXML private Label costLabel;
    @FXML private TextField cost;

    // ================================= FXML methods ===================================
    @FXML
    private boolean costCheck()
    {
        boolean ret;
    
        if (ExpressionChecker.getExpressionChecker().moneyValue(this.cost.getText(), 10, 2, false))
        {
            this.costLabel.setText("ID / Descriptor");
            this.costLabel.setTextFill(Color.web("#000000"));
            ret = true;
        }
        else
        {
            this.costLabel.setText("Dato invalido");
            this.costLabel.setTextFill(Color.web("#ff0000"));
            ret = false;
        }
        return ret;
    }

    // ================================= private methods ===================================

    // ================================= protected methods ===================================

    // ================================= public methods ===================================
    public Float getCost()
    {
        return Float.parseFloat(this.cost.getText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        
    }
}