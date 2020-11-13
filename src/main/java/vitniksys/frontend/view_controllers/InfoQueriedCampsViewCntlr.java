package vitniksys.frontend.view_controllers;

import java.net.URL;
import javafx.fxml.FXML;

import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import vitniksys.backend.model.entities.Campaign;

public class InfoQueriedCampsViewCntlr extends ViewCntlr implements Initializable
{
    private List<Campaign> camps;

    // ================================= FXML variables =================================
    @FXML
    private Label artPedidosQuantityFixed, artRetiradosQuantityFixed, artDevueltosQuantity, artRecompradosQuantity, 
        totalInPedidosFixed, artDevueltosQuantityFixed, artRecompradosQuantityFixed, totalInRetirosFixed, 
        artPedidosQuantity, artRetiradosQuantity, totalInPedidos, totalInRetiros, totalInDevolucionesFixed,
        totalInRecomprasFixed, totalInDevoluciones, totalInRecompras, catEntregadosQuantityFixed,
        catEntregadosQuantity, totalInCatalogosFixed, totalInCatalogos, totalInPaymentsFixed, totalInPayments,
        finalBalanceFixed, finalBalance;


    // ================================= FXML methods ===================================


    // ================================= private methods ================================


    // ================================= protected methods ==============================
    @Override
    protected void manualInitialize()
    {

    }

    protected void loadQueriedCamps(List<Campaign> camps)
    {
        this.camps = camps;
        //Extra code to show the camps
    }

    // ================================= public methods =================================
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // TODO Auto-generated method stub

    }
}
