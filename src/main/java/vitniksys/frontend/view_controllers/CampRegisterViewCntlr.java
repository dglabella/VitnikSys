package vitniksys.frontend.view_controllers;

import java.net.URL;
import java.io.File;
import java.util.List;
import java.time.Month;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vitniksys.backend.util.CustomAlert;
import org.apache.commons.io.FilenameUtils;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SpinnerValueFactory;
import vitniksys.backend.util.ExpressionChecker;
import vitniksys.backend.model.entities.Campaign;
import vitniksys.backend.util.DetailFileInterpreter;
import vitniksys.backend.model.services.CampaignService;
import vitniksys.frontend.views_subscriber.CampaignServiceSubscriber;

public class CampRegisterViewCntlr extends ViewCntlr implements Initializable, CampaignServiceSubscriber
{
    // Changing YEAR_MIN and YEAR_MAX values only affect the frontend view.
    private static final int YEAR_MIN = 2020;
    private static final int YEAR_MAX = 2038;

    private File detail;

    private ExpressionChecker expressionChecker;

    private Campaign lastCamp;

    private CampaignService campaignService;

    // ================================= FXML variables =================================
    @FXML private Spinner<Integer> campNumber;

    @FXML private TextField campAlias;
    @FXML private TextField catalogueCode;
    
    @FXML private Label fileSelected;
    @FXML private Label filePath;
    @FXML private Label campNumberInvalid;
    @FXML private Label campAliasInvalid;
    @FXML private Label catalogueCodeInvalid;
    @FXML private Label orders;

    @FXML private ChoiceBox<Month> campMonth;
    @FXML private ChoiceBox<Integer> campYear;

    @FXML private Button register;
    @FXML private Button addOrders;
    @FXML private Button plusCatalogue;

    // ================================= FXML methods =================================
    @FXML
    private void addOrdersButtonPressed() throws Exception
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
            if (FilenameUtils.getExtension(this.detail.getName()).equalsIgnoreCase(DetailFileInterpreter.FILE_EXTENSION))
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
    private void registerButtonPressed()
    {
        try
        {
            this.campaignService.registerCamp(this.campNumber.getValue(), this.campAlias.getText(), 
                this.campMonth.getValue() != null? this.campMonth.getValue().getValue() : null, 
                this.campYear.getValue(), this.catalogueCode.getText(), this.detail);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void plusCatalogueButtonPressed()
    {
        this.createStage("Consultar Cátalogo", "catalogueQuery").getStage().show();
    }

    @FXML
    private boolean campAliasCheck()
    {
        boolean ret;
    
        if (this.campAlias.getText().length() <= CampaignService.MAX_LENGTH_CAMP_ALIAS)
        {
            this.campAliasInvalid.setVisible(false);
            ret = true;
        }
        else
        {
            this.campAliasInvalid.setText("Dato invalido");
            this.campAliasInvalid.setVisible(true);
            ret = false;
        }
        return ret;
    }

    @FXML
    private boolean catalogoCodeCheck()
    {
        boolean ret;
    
        if (this.expressionChecker.isCatalogueCode(this.catalogueCode.getText(), true))
        {
            this.catalogueCodeInvalid.setVisible(false);
            ret = true;
        }
        else
        {
            this.catalogueCodeInvalid.setText("Dato invalido");
            this.catalogueCodeInvalid.setVisible(true);
            ret = false;
        }
        return ret;
    }

    // ================================= private methods =================================
    private void clearStage()
    {
        this.campNumber.getValueFactory().setValue(null);
        this.campMonth.setValue(null);
        this.campYear.setValue(null);
        this.catalogueCode.clear();

        this.filePath.setVisible(false);
        this.fileSelected.setVisible(false);
    }

    // ================================= protected methods =================================
    @Override
    protected void manualInitialize()
    {
        try
        {
            // to load the last camp number into the camp number spinner
            this.campaignService.searchLastCamp();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            this.getStage().close();
        }
        
        // setting the camp number spinner
        this.campNumber.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, lastCamp.getNumber()+1));

        this.campMonth.setValue(Month.of((this.lastCamp.getMonth()%12)+1));

        if(this.lastCamp.getMonth() == 12)
        {
            this.campYear.setValue(this.lastCamp.getYear()+1);
        }
    }

    // ================================= public methods =================================
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // Creating the expresssion checker object for checking inputs.
        expressionChecker = ExpressionChecker.getExpressionChecker();

        this.campaignService = new CampaignService(this);

        // Setting values and listener for campMonth choice box.
        ObservableList<Month> months = FXCollections.observableArrayList(null, Month.JANUARY, Month.FEBRUARY,
            Month.MARCH, Month.APRIL, Month.MAY, Month.JUNE, Month.JULY, Month.AUGUST, Month.SEPTEMBER,
            Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER);

        this.campMonth.setItems(months);

        // Setting values and listener for campYear choice box.
        ObservableList<Integer> years = FXCollections.observableArrayList();
        years.add(null);
        for (int i = CampRegisterViewCntlr.YEAR_MIN; i <= CampRegisterViewCntlr.YEAR_MAX; i++)
            years.add(i);
        this.campYear.setItems(years);
    }

    // ================================= view methods =================================
    @Override
    public CustomAlert showProcessIsWorking(String message)
    {
        return new CustomAlert(AlertType.NONE, "PROCESANDO", message).customShow();
    }

    @Override
    public void closeProcessIsWorking(CustomAlert customAlert)
    {
        customAlert.customClose();
    }

    @Override
    public void showSucces(String message)
    {
        new CustomAlert(AlertType.INFORMATION, "EXITO", message).customShow();
        this.getStage().close();
    }

    @Override
    public void showError(String message)
    {
        new CustomAlert(AlertType.ERROR, "ERROR", message).customShow();
    }

    @Override
    public void showError(String message, String description, Exception exception)
    {
        new CustomAlert(AlertType.ERROR, "ERROR", message, description, exception).customShow();
    }

    @Override
    public void showNoResult(String message)
    {
        
    }

    @Override
    public void showQueriedCamp(Campaign campaign) throws Exception
    {
        this.lastCamp = campaign;
    }

    @Override
    public void showQueriedCamp(List<Campaign> camps) throws Exception
    {
        //Do nothing
    }
}