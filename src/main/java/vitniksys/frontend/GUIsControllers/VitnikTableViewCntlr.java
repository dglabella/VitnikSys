package vitniksys.frontend.GUIsControllers;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import vitniksys.backend.util.ColumnProperty;
import javafx.scene.control.cell.PropertyValueFactory;

public abstract class VitnikTableViewCntlr<Entity> extends VitnikViewCntlr implements Initializable
{
    private ObservableList<Entity> entities;
    private SelectionMode selectionMode;

    private List<TableColumn> columns;

    private List<PropertyValueFactory> properties;

    private String propertyNameCol1 = "number";
    private String propertyNameCol2 = "name";
    private String propertyNameCol3 = "alias";
    private String propertyNameCol4 = "month";
    private String propertyNameCol5 = "year";
    private String propertyNameCol6 = "CatalogueCode";

    protected VitnikTableViewCntlr()
    {
        entities = FXCollections.observableArrayList();
        this.selectionMode =  SelectionMode.MULTIPLE;
    }
    
    // ================================= FXML variables =================================
    @FXML private TableView<Entity> table;


    // ================================= FXML methods ===================================

    // ================================= private methods ================================
    private void initTables()
    {
        this.table.getSelectionModel().setSelectionMode(selectionMode);

        if(columnsIterator)
        {
            Iterator<TableColumn> columnsIterator = this.columns.iterator();
            Iterator<PropertyValueFactory> propertiesIterator = this.properties.iterator();

            while(columnsIterator.hasNext())
            {
                columnsIterator.next().setCellValueFactory(propertiesIterator.next());
            }
        }
        //this.column1.setCellValueFactory(new PropertyValueFactory<Entity, Type>(propertyNameCol1));
    }

    // ================================= protected methods ==============================
    /**
     * Set the selection mode.
     * Default value is SelectionMode.MULTIPLE.
     */
    protected void setSelectionMode()
    {

    }

    protected SelectionMode getSelectionMode()
    {
        return this.selectionMode;
    }

    // ================================= public methods =================================

    /**
     * This method is called inside the initialize method
     * from the class javafx.fxml.Initializable therefore
     * it should be treated like such.
     */
    public abstract void customInitialize(URL location, ResourceBundle resources);

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.initTables();
        this.customInitialize(location, resources);
    }
}