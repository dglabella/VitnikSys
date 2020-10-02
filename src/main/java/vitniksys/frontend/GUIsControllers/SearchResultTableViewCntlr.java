package vitniksys.frontend.GUIsControllers;

import java.net.URL;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class SearchResultTableViewCntlr extends VitnikViewCntlr implements Initializable
{
    // ================================= FXML variables =================================
    @FXML private TableView resultTable;

    @FXML private TableColumn column1;
    @FXML private TableColumn column2;
    @FXML private TableColumn column3;
    @FXML private TableColumn column4;
    @FXML private TableColumn column5;
    @FXML private TableColumn column6;

    // ================================= FXML methods ===================================
    @FXML
    private void acceptButtonPressed()
    {
        
    }

    // ================================= protected methods ==============================

    // ================================= public methods =================================
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
}