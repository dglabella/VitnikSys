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
    private List<ObservableList<Entity>> tableDataLists;

    private List<SelectionMode> selectionModes;

    private List<TableView<Entity>> tables;

    private List<TableColumn> columns;

    private List<PropertyValueFactory> propertiesValues;

    // ================================= FXML variables =================================

    // ================================= FXML methods ===================================

    // ================================= private methods ================================

    /**
     * This method initialize all the registered tables. If the quantity of columns
     * is greater than the quantity of properties an exception will be thrown. If
     * the quantity of properties is greather than the quantity of columns, the
     * remaining properties simply will be ignored.
     */
    private void initTables() 
    {
        for (int i = 0; i < this.tables.size(); i++)
        {
            this.tables.get(i).getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            this.tableDataLists.add(FXCollections.observableArrayList());
            this.tables.get(i).setItems(this.tableDataLists.get(i));
        }

        Iterator<TableColumn> columnsIterator = this.columns.iterator();
        Iterator<PropertyValueFactory> propertiesValuesIterator = this.propertiesValues.iterator();

        while (columnsIterator.hasNext())
            columnsIterator.next().setCellValueFactory(propertiesValuesIterator.next());
    }

    // ================================= protected methods ==============================
    protected void registerTable(TableView<Entity> table)
    {
        this.tables.add(table);
    }

    protected void registerTables(List<TableView<Entity>> tables)
    {
        this.tables.addAll(tables);
    }

    /*
    protected TableView<Entity> getTable(int tableNumber)
    {
        return this.tables.get(tableNumber);
    }

    protected List<TableView<Entity>> getTables()
    {
        return this.tables;
    }
    */

    protected void registerColumn(TableColumn column)
    {
        this.columns.add(column);
    }

    protected void registerColumns(List<TableColumn> columns)
    {
        this.columns.addAll(columns);
    }

    protected void registerPropertiesValues(PropertyValueFactory propertyValue)
    {

        this.propertiesValues.add(propertyValue);
    }

    protected void registerPropertiesValues(List<PropertyValueFactory> propertiesValues)
    {
        this.propertiesValues.addAll(propertiesValues);
    }

    protected void loadData(int tableNumber, Entity data)
    {
        this.tableDataLists.get(tableNumber).add(data);
    }

    protected void loadData(int tableNumber, int position, Entity data)
    {
        this.tableDataLists.get(tableNumber).add(position, data);
    }

    protected void loadData(int tableNumber, List<Entity> data)
    {
        this.tableDataLists.get(tableNumber).addAll(data);
    }

    protected void loadData(int tableNumber, int position, List<Entity> data)
    {
        this.tableDataLists.get(tableNumber).addAll(position, data);
    }

    protected ObservableList<Entity> getObservableListFromTable(int tableNumber)
    {
        return this.tableDataLists.get(tableNumber);
    }

    // ================================= public methods =================================

    /**
     * This method is called inside the initialize method. IMPORTANT: This method is
     * supposed to be used for register the tables, the columns and the properties
     * values. Also, the order in which you register the table, columns and
     * properties values is important to match all the elements correctly.
     * Tables id will be: 0 for the first registered table, 1 for the second and so on.
     */
    public abstract void customInitialize(URL location, ResourceBundle resources) throws Exception;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.tables = new ArrayList<>();
        this.columns = new ArrayList<>();
        this.propertiesValues = new ArrayList<>();
        this.tableDataLists = new ArrayList<>();
        
        try
        {
            this.customInitialize(location, resources);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        this.initTables();
    }
}