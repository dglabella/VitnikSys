package vitniksys.frontend.view_controllers;

import java.net.URL;
import vitniksys.App;
import java.util.List;
import javafx.fxml.FXML;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.TextField;
import javafx.scene.input.ScrollEvent;
import vitniksys.backend.util.ExpressionChecker;
import vitniksys.frontend.view_subscribers.CatalogueBLServiceSubscriber;
import javafx.scene.control.SpinnerValueFactory;
import vitniksys.backend.util.AutoCompletionTool;
import vitniksys.backend.model.business_logic.CatalogueBLService;
import vitniksys.backend.model.entities.Catalogue;

public class CatalogueQueryViewCntlr extends ViewCntlr implements CatalogueBLServiceSubscriber
{
    private final String BUTTON_TEXT_REGISTER = "Registrar";
    private final String BUTTON_TEXT_UPDATE = "Actualizar";

    private AutoCompletionTool autoCompletionTool;

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
        if(ExpressionChecker.getExpressionChecker().isCatalogueCode(this.catalogueCode.getText(), false))
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
        if(ExpressionChecker.getExpressionChecker().moneyValue(this.price.getText(), App.ConstraitConstants.MAX_LENGTH_MONEY_LEFT_DIGITS, App.ConstraitConstants.MAX_LENGTH_MONEY_RIGHT_DIGITS, false))
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
        if(this.link.getText().length() <= App.ConstraitConstants.MAX_LENGTH_CATALOGUE_LINK)
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
    private void updateStockSpinnerOnMouseClicked()
    {
        this.stock.getValueFactory().setValue(this.initialStock.getValue());
    }

    @FXML
    private void updateStockSpinnerOnKeyReleased()
    {
        this.stock.getValueFactory().setValue(this.initialStock.getValue());
    }

    @FXML
    private void updateStockSpinnerOnScroll(ScrollEvent scrollEvent)
    {
        if(scrollEvent.getDeltaY() < 0)
        {
            this.initialStock.getValueFactory().increment(1);
            this.stock.getValueFactory().setValue(this.initialStock.getValue());
        }
        else if(scrollEvent.getDeltaY() > 0)
        {
            this.initialStock.getValueFactory().decrement(1);
            this.stock.getValueFactory().setValue(this.initialStock.getValue());
        }
    }

    @FXML
    private void plusButtonPressed()
    {
        this.initialStock.setDisable(false);
        this.initialStock.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9999, 200));

        this.stock.setDisable(false);
        this.stock.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9999, 200));

        this.catalogueCode.clear();
        this.invalidCode.setVisible(false);
        this.autoCompletionTool.getSuggestionsList().setVisible(false);
        this.price.clear();
        this.link.clear();

        this.update.setText(BUTTON_TEXT_REGISTER);
    }

    @FXML
    private void searchButtonPressed()
    {
        try
        {
            ((CatalogueBLService)this.getBLService(0)).searchCatalogue(this.catalogueCode.getText());     
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    @FXML
    private void updateButtonPressed()
    {
        try
        {
            ((CatalogueBLService)this.getBLService(0)).registerCatalogue(this.catalogueCode.getText(), this.initialStock.getValue(), 
                this.stock.getValue(), this.price.getText(), this.link.getText());    
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
        ((CatalogueBLService)this.getBLService(0)).searchCatalogues();
    }

    // ================================= public methods =================================
    @Override
    public void customInitialize(URL location, ResourceBundle resources) throws Exception
    {
        this.autoCompletionTool = new AutoCompletionTool(this.catalogueCode, new ArrayList<String>(), 135);
        
        this.initialStock.setDisable(true);
        this.stock.setDisable(true);
    }
    
    // ================================= service subscriber methods =================================
    @Override
    public void refresh() 
    {
        
    }
    
    @Override
    public void showQueriedCatalogue(Catalogue catalogue) throws Exception
    {
        if(this.initialStock.getValueFactory() == null)
            this.initialStock.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9999));

        this.initialStock.getValueFactory().setValue(catalogue.getInitialStock());

        if(this.stock.getValueFactory() == null)
            this.stock.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9999));

        this.stock.getValueFactory().setValue(catalogue.getActualStock());
        this.price.setText(""+catalogue.getPrice());
        this.link.setText(catalogue.getLink());
	}

	@Override
    public void showQueriedCatalogues(List<Catalogue> catalogues) throws Exception
    {
        this.autoCompletionTool.getSuggestions().clear();

        Iterator<Catalogue> catalogueIterator = catalogues.iterator();
        while(catalogueIterator.hasNext())
            this.autoCompletionTool.getSuggestions().add(""+catalogueIterator.next().getCode());
	}
}