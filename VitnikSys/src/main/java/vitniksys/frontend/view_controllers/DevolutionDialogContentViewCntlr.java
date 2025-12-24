package vitniksys.frontend.view_controllers;

import java.net.URL;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import vitniksys.backend.model.enums.Reason;

public class DevolutionDialogContentViewCntlr extends DialogContentViewCntlr implements Initializable
{
    // Getters && Setters
    public Reason getReason()
    {
        return this.reason.getValue();
    }

    public void setReason(Reason reason)
    {
        this.reason.setValue(reason);
    }

    // ================================= FXML variables =================================
    @FXML private ComboBox<Reason> reason;

    // ================================= FXML methods ===================================

    // ================================ private methods =================================

    // =============================== protected methods =================================

    // ================================= public methods ==================================

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.reason.setItems(FXCollections.observableArrayList());
        Reason [] reasons = Reason.values();
        for(int i = 0; i < reasons.length; i++)
        {
            this.reason.getItems().add(reasons[i]);
        }

        this.reason.setValue(Reason.OTROS);
    }
}