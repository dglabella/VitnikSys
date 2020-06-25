package vitniksys.backend.view_controllers;

import java.net.URL;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import vitniksys.backend.util.ExpressionChecker;

public class CatalogueQueryController extends VitnikController implements Initializable
{
    private ExpressionChecker expressionChecker;

    @FXML private TextField textField_link;
    @FXML private TextField textField_stock;
    @FXML private TextField textField_codCat;
    @FXML private TextField textField_precio;
    @FXML private TextField textField_stockInicial;

    @FXML Button button_plusButton;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        expressionChecker =  ExpressionChecker.getExpressionChecker();

        textField_stockInicial.setDisable(true);
        textField_stock.setDisable(true);
    }
    
    @Override
    protected void refresh()
    {

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
}