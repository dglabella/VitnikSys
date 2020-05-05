package vitniksys.backend.view_controllers;

import java.net.URL;
import java.io.File;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.stage.FileChooser;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import vitniksys.backend.util.ExpressionChecker;

public class ConsultarCampController extends VitnikController implements Initializable
{
    private File detalle;
    private ExpressionChecker expressionChecker;

    @FXML private TextField textField_nroCamp;
    @FXML private TextField textField_codCat;

    @FXML private Label label_cantArtPedidos;
    @FXML private Label label_cantArtRetirados;
    @FXML private Label label_cantDevueltos;
    @FXML private Label label_cantRecomprados;
    @FXML private Label label_cantCatEntregados;
    @FXML private Label label_totalPedidos;
    @FXML private Label label_totalRetiros;
    @FXML private Label label_totalDevol;
    @FXML private Label label_totalRecompras;
    @FXML private Label label_totalCat;
    @FXML private Label label_archivoSeleccionado;
    @FXML private Label label_path;
    @FXML private Label label_nroCampInvalido;

    @FXML private ChoiceBox choiceBox_mesCamp;
    @FXML private ChoiceBox choiceBox_yearCamp;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        expressionChecker = ExpressionChecker.getExpressionChecker();
    }

    @Override
    protected void refresh()
    {
        
    }

    @FXML private void homeButtonPressed()
    {

    }
    
    @FXML private void ingresarButtonPressed()
    {

    }

    @FXML private void seleccionarArchivoButtonPressed()
    {
        FileChooser fileChooser = new FileChooser();
        this.detalle = fileChooser.showOpenDialog(null);

        if (detalle != null)
        {
            this.label_archivoSeleccionado.setText("Archivo seleccionado:");
            this.label_archivoSeleccionado.setVisible(true);
            if(FilenameUtils.getExtension(detalle.getName()).equalsIgnoreCase("xlsx") )
            {
                this.label_path.setTextFill(Color.web("#000000")); //Black
                this.label_path.setText(detalle.getAbsolutePath());
                this.label_path.setVisible(true);
            }
            else
            {
                this.label_path.setTextFill(Color.web("#ff0000")); //Red
                this.label_path.setText("el archivo no tiene extension xlsx");
                this.label_path.setVisible(true);
            }
        }
    }

    @FXML private void plusCatButtonPressed()
    {

    }

    @FXML private void plusCampButtonPressed()
    {

    }

    @FXML private void nroCampCheck()
    {

    }
}