package vitniksys.frontend.view_controllers;

import java.net.URL;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.TextField;
import vitniksys.backend.util.ExpressionChecker;

public class CatalogueQueryViewCntlr extends ViewCntlr
{
    private ExpressionChecker expressionChecker;
    private final String BUTTON_TEXT_REGISTER = "Registrar";
    private final String BUTTON_TEXT_UPDATE = "Actualizar";

    // ================================= FXML variables =================================
    @FXML private JFXButton update;

    @FXML private TextField stock;
    @FXML private TextField price;
    @FXML private TextField link;
    @FXML private TextField catalogueCode;
    @FXML private TextField initialStock;
    // ================================= FXML methods ===================================


    // ================================= protected methods ==============================
    @Override
    protected void manualInitialize()
    {
        
    }

    // ================================= public methods =================================
    @FXML private void plusButtonPressed()
    {
        this.initialStock.setDisable(false);
        this.initialStock.clear();
        this.stock.setDisable(false);
        this.stock.clear();
        this.catalogueCode.clear();
        this.price.clear();
        this.link.clear();

        this.update.setText(BUTTON_TEXT_REGISTER);
    }

    @FXML private void searchButtonPressed()
    {

    }

    @FXML private void updateButtonPressed()
    {
        
    }

    @Override
    public void customInitialize(URL location, ResourceBundle resources) throws Exception
    {
        expressionChecker =  ExpressionChecker.getExpressionChecker();
        this.initialStock.setDisable(true);
        this.stock.setDisable(true);
    }
}