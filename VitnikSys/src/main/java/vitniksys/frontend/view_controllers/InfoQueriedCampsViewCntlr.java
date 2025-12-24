package vitniksys.frontend.view_controllers;

import java.io.File;
import java.net.URL;
import vitniksys.App;
import java.util.List;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.stage.FileChooser;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import org.apache.commons.io.FilenameUtils;

import vitniksys.backend.model.business_logic.CampaignBLService;
import vitniksys.backend.model.entities.Campaign;

public class InfoQueriedCampsViewCntlr extends ViewCntlr
{
    private List<Campaign> camps;
    private File detail;

    // ================================= FXML variables =================================
    @FXML private Label artPedidosQuantityFixed, artRetiradosQuantityFixed, artDevueltosQuantity, artRecompradosQuantity,
        totalInPedidosFixed, artDevueltosQuantityFixed, artRecompradosQuantityFixed, totalInRetirosFixed,
        artPedidosQuantity, artRetiradosQuantity, totalInPedidos, totalInRetiros, totalInDevolucionesFixed,
        totalInRecomprasFixed, totalInDevoluciones, totalInRecompras, catEntregadosQuantityFixed,
        catEntregadosQuantity, totalInCatalogosFixed, totalInCatalogos, totalInPaymentsFixed, totalInPayments,
        finalBalanceFixed, finalBalance, fileSelected, filePath;


    // ================================= FXML methods ===================================
    @FXML
    private void addOrdersButtonPressed()
    {
        /**
         * THIS METHOD IS SUPPOSED TO SELECT A "PEDIDOS" OBTAINING METHOD. ACTUALLY IS
         * HARDCODED FOR FILE SELECTING METHOD (Detail.csv FILE), BUT IF "PEDIDOS"
         * OBTAINING METHOD WILL BE ADDED, HERE IS WHERE IT HAS TO BE IMPLEMENTED.
         */

        // METHOD: FILE SELECTING .
        FileChooser fileChooser = new FileChooser();
        this.detail = fileChooser.showOpenDialog(null);
        
        if (this.detail != null)
        {
            if (FilenameUtils.getExtension(this.detail.getName()).equalsIgnoreCase(App.ConstraitConstants.DETAIL_FILE_EXTENSION))
            {
                this.fileSelected.setText("Archivo seleccionado:");
                this.fileSelected.setVisible(true);
                this.filePath.setTextFill(Color.web("#000000")); // Black
                this.filePath.setText(detail.getAbsolutePath());
                this.filePath.setVisible(true);
            }
            else
            {
                this.filePath.setTextFill(Color.web("#ff0000")); // Red
                this.filePath.setText("El archivo no tiene extension csv."+"\nSelecciones nuevamente o el archivo será descartado.");
                this.filePath.setVisible(true);
                this.detail = null;
            }
        }
    }

    @FXML
    private void loadOrdersButtonPressed()
    {
        try
        {
            ((CampaignBLService)this.getBLService(0)).registerOrders(detail);   
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

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