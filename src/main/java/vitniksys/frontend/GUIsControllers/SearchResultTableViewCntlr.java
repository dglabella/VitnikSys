package vitniksys.frontend.GUIsControllers;

import java.net.URL;
import java.util.List;
import javafx.fxml.FXML;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import vitniksys.backend.model.entities.Campaign;
import javafx.scene.control.cell.PropertyValueFactory;

public class SearchResultTableViewCntlr extends VitnikViewCntlr implements Initializable
{
    private ObservableList<Campaign> campaigns;

    private String propertyNameCol1 = "number";
    private String propertyNameCol2 = "name";
    private String propertyNameCol3 = "alias";
    private String propertyNameCol4 = "month";
    private String propertyNameCol5 = "year";
    private String propertyNameCol6 = "CatalogueCode";

    // ================================= FXML variables =================================
    @FXML private TableView<Campaign> resultTable;

    @FXML private TableColumn<Campaign, Integer> column1;
    @FXML private TableColumn<Campaign, String> column2;
    @FXML private TableColumn<Campaign, String> column3;
    @FXML private TableColumn<Campaign, Integer> column4;
    @FXML private TableColumn<Campaign, Integer> column5;
    @FXML private TableColumn<Campaign, Timestamp> column6;

    // ================================= FXML methods ===================================
    @FXML
    private void acceptButtonPressed()
    {

    }

    // ================================= private methods ================================
    private void fillTable()
    {
        campaigns = FXCollections.observableArrayList();
        this.resultTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.column1.setCellValueFactory(new PropertyValueFactory<Campaign, Integer>(propertyNameCol1));
        this.column2.setCellValueFactory(new PropertyValueFactory<Campaign, String>(propertyNameCol2));
        this.column3.setCellValueFactory(new PropertyValueFactory<Campaign, String>(propertyNameCol3));
        this.column4.setCellValueFactory(new PropertyValueFactory<Campaign, Integer>(propertyNameCol4));
        this.column5.setCellValueFactory(new PropertyValueFactory<Campaign, Integer>(propertyNameCol5));
        this.column6.setCellValueFactory(new PropertyValueFactory<Campaign, Timestamp>(propertyNameCol6));
    }

    // ================================= protected methods ==============================

    // ================================= public methods =================================
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.fillTable();
    }

    public void setCamps(List<Campaign> camps)
    {
        this.campaigns.addAll(camps);
        this.resultTable.setItems(this.campaigns);
    }
}