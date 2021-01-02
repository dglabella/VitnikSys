package vitniksys.frontend.view_controllers;

import java.net.URL;
import java.util.List;
import javafx.fxml.FXML;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import com.jfoenix.controls.JFXButton;
import vitniksys.backend.util.AutoCompletionTool;
import vitniksys.backend.util.ExpressionChecker;
import vitniksys.backend.model.entities.Catalogue;
import vitniksys.backend.model.services.CatalogueService;
import vitniksys.frontend.views_subscriber.CatalogueServiceSubscriber;

public class CatalogueQueryViewCntlr extends ViewCntlr implements CatalogueServiceSubscriber
{
    private final String BUTTON_TEXT_REGISTER = "Registrar";
    private final String BUTTON_TEXT_UPDATE = "Actualizar";

    private AutoCompletionTool autoCompletionTool;
    private List<String> suggestions;

    // ================================= FXML variables =================================
    @FXML private Spinner<Integer> initialStock;
    @FXML private Spinner<Integer> stock;

    @FXML private TextField catalogueCode;
    @FXML private TextField price;
    @FXML private TextField link;

    @FXML private Label invalidCode;
    @FXML private Label invalidInitialStock;
    @FXML private Label invalidStock;
    @FXML private Label invalidPrice;
    @FXML private Label invalidLink;

    @FXML private JFXButton update;

    // ================================= FXML methods ===================================
    @FXML
    private void codeCheck()
    {
        if(this.getExpressionChecker().isCatalogueCode(this.catalogueCode.getText(), false))
        {
            this.invalidCode.setVisible(false);
        }
        else
        {
            this.invalidCode.setText("Dato invalido");
            this.invalidCode.setVisible(true);
        }
    }

    @FXML
    private void priceCheck()
    {
        if(this.getExpressionChecker().moneyValue(this.price.getText(), 2, 2, false))
        {
            this.invalidPrice.setVisible(false);
        }
        else
        {
            this.invalidPrice.setText("Dato invalido");
            this.invalidPrice.setVisible(true);
        }
    }

    @FXML
    private void linkCheck()
    {
        if(this.link.getText().length() <= CatalogueService.MAX_LENGTH_LINK)
        {
            this.invalidLink.setVisible(false);
        }
        else
        {
            this.invalidLink.setText("Dato invalido");
            this.invalidLink.setVisible(true);
        }
    }

    @FXML
    private void plusButtonPressed()
    {
        this.initialStock.setDisable(false);
        this.stock.setDisable(false);
        this.catalogueCode.clear();
        this.price.clear();
        this.link.clear();

        this.update.setText(BUTTON_TEXT_REGISTER);
    }

    @FXML
    private void searchButtonPressed()
    {

    }

    @FXML
    private void updateButtonPressed()
    {
        try
        {
            ((CatalogueService)this.getService(0)).registerCatalogue(this.catalogueCode.getText(), this.ca , price, link);    
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    // ================================= protected methods ==============================
    @Override
    protected void manualInitialize()
    {
        this.autoCompletionTool = new AutoCompletionTool(this.catalogueCode, this.suggestions);
    }

    // ================================= public methods =================================
    @Override
    public void customInitialize(URL location, ResourceBundle resources) throws Exception
    {
        ((CatalogueService)this.getService(0)).searchCatalogues();

        this.initialStock.setDisable(true);
        this.stock.setDisable(true);
    }
    
    // ================================= service subscriber methods =================================
    @Override
    public void showQueriedCamp(Catalogue catalogue) throws Exception
    {
		// TODO Auto-generated method stub
		
	}

	@Override
    public void showQueriedCamps(List<Catalogue> catalogues) throws Exception
    {
		// TODO Auto-generated method stub
		
	}
}