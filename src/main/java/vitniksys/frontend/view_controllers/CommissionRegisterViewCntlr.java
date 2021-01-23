package vitniksys.frontend.view_controllers;

import java.net.URL;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import com.jfoenix.controls.JFXButton;

public class CommissionRegisterViewCntlr extends ViewCntlr
{
    @FXML private Label idCpNameLastName;
    @FXML private Label invalidPrice;
    @FXML private Label invalidLink;

    @FXML private JFXButton update;
    @FXML private Slider lvl1RateSlider;
    @FXML private TextField lvl1RateField;
    @FXML private Spinner<Integer> lvl1Quantity;
    @FXML private Label actualRate;
    @FXML private Slider lvl2RateSlider;
    @FXML private TextField lvl2RateField;
    @FXML private Spinner<Integer> lvl2Quantity;
    @FXML private Slider lvl3RateSlider;
    @FXML private TextField lvl3RateField;
    @FXML private Spinner<Integer> lvl3Quantity;
    @FXML private Slider lvl4RateSlider;
    @FXML private TextField lvl4RateField;
    @FXML private Spinner<Integer> lvl4Quantity;
    @FXML private Spinner<Integer> minQuantity;

    @FXML
    private void updateButtonPressed()
    {

    }

    @Override
    protected void manualInitialize()
    {
        // TODO Auto-generated method stub
    }

    @Override
    public void customInitialize(URL location, ResourceBundle resources) throws Exception
    {
        this.actualRate
    }  
}