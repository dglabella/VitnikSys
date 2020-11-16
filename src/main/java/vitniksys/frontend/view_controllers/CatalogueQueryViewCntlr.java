package vitniksys.frontend.view_controllers;

import java.net.URL;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import vitniksys.backend.util.ExpressionChecker;

public class CatalogueQueryViewCntlr extends ViewCntlr
{
    private ExpressionChecker expressionChecker;

    // ================================= FXML variables =================================
    @FXML private TextField textField_link;
    @FXML private TextField textField_stock;
    @FXML private TextField textField_codCat;
    @FXML private TextField textField_precio;
    @FXML private TextField textField_stockInicial;

    @FXML private Button button_plusButton;

    // ================================= FXML methods ===================================


    // ================================= protected methods ==============================
    @Override
    protected void manualInitialize()
    {
        
    }

    // ================================= public methods =================================
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        expressionChecker =  ExpressionChecker.getExpressionChecker();
        textField_stockInicial.setDisable(true);
        textField_stock.setDisable(true);
    }

    @FXML private void plusButtonPressed()
    {

    }

    @FXML private void consultarButtonPressed()
    {

    }

    @FXML private void actualizarButtonPressed()
    {

    }

    @Override
    public void customInitialize(URL location, ResourceBundle resources) throws Exception
    {
        // TODO Auto-generated method stub

    }
}