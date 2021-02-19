package vitniksys.frontend.view_controllers;

import java.net.URL;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class DevolutionDialogContentViewCntlr extends DialogContentViewCntlr implements Initializable
{
    private Integer min;
    private Integer max;

    // Getters && Setters
    public Integer getMin()
    {
        return this.min;
    }

    public void setMin(Integer min)
    {
        this.min = min;
    }

    public Integer getMax()
    {
        return this.max;
    }

    public void setMax(Integer max)
    {
        this.max = max;
    }

    // ================================= FXML variables =================================
    @FXML private Spinner<Integer> quantity;

    // ================================= FXML methods ===================================

    // ================================ private methods =================================

    // =============================== protected methods =================================

    // ================================= public methods ==================================

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.quantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, this.getMax()));
    }
}