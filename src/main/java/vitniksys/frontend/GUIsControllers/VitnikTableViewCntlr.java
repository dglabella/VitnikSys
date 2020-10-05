package vitniksys.frontend.GUIsControllers;

import java.net.URL;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.PropertyValueFactory;

public abstract class VitnikTableViewCntlr<Entity> extends VitnikViewCntlr implements Initializable
{
    private List<ObservableList<Entity>> tablesEntityLists;

    private List<SelectionMode> selectionModes;

    private List<TableView<Entity>> tables;

    private List<TableColumn> columns;

    private List<PropertyValueFactory> properties;

    // ================================= FXML variables =================================


    protected VitnikTableViewCntlr()
    {
        this.tables = new ArrayList<>();
        this.tablesEntityLists = new ArrayList<>();
    }

    protected VitnikTableViewCntlr(List<TableView<Entity>> tables)
    {
        this.tables = tables;
        this.tablesEntityLists = new ArrayList<>();
        for(int i = 0; i < this.tables.size(); i++)
        {
            this.tables.get(i).getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            this.tablesEntityLists.add(FXCollections.observableArrayList());
        }
    }

    // ================================= FXML methods ===================================

    // ================================= private methods ================================
    
    /**
     * This method initialize all the registered tables.
     * If the quantity of columns is greater than the
     * quantity of properties an exception will be thrown.
     * If the quantity of properties is greather than the
     * quantity of columns, the remaining properties simply
     * will be ignored.
     */
    private void initTables()
    {
        Iterator<TableColumn> columnsIterator = this.columns.iterator();
        Iterator<PropertyValueFactory> propertiesIterator = this.properties.iterator();

        while(columnsIterator.hasNext())
        {
            columnsIterator.next().setCellValueFactory(propertiesIterator.next());
        }
        
        //this.column1.setCellValueFactory(new PropertyValueFactory<Entity, Type>(propertyNameCol1));
    }

    // ================================= protected methods ==============================
    protected void registerTable(TableView<Entity> table)
    {
        this.tables.add(table);
    }

    protected TableView<Entity> getTable(int tableNumber)
    {
        return this.tables.get(tableNumber);
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