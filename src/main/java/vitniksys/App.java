package vitniksys;

import java.net.URL;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.stage.WindowEvent;
import javafx.event.EventHandler;
// import javafx.stage.FileChooser;
import javafx.application.Platform;
import javafx.application.Application;
import javafx.scene.control.ButtonType;
import vitniksys.backend.util.CustomAlert;
import javafx.scene.control.Alert.AlertType;
import vitniksys.backend.util.ConfigFileInterpreter;
import vitniksys.frontend.view_controllers.ViewCntlr;
import vitniksys.backend.model.business_logic.BLService;
import vitniksys.frontend.view_controllers.MainMenuViewCntlr;
import vitniksys.backend.model.business_logic.CampaignBLService;
import vitniksys.backend.model.business_logic.PreferentialClientBLService;

public class App extends Application {
    public static class ConstraitConstants {
        public static final String GUIs_LOCATION = App.class.getResource("") + "../frontend/GUIs/";
        public static final String FXML_FILE_EXTENSION = ".fxml";
        public static final String DETAIL_FILE_EXTENSION = "csv";
        public static final String CSV_SEPARATOR = ";";

        private String DATABASE_BACKUP_COMMAND = "";

        public static final String FILTER_COMMAND_PREFIX = "//";

        public static final int MAX_LENGTH_CATALOGUE_LINK = 500;
        public static final int MAX_LENGTH_MONEY_LEFT_DIGITS = 10;
        public static final int MAX_LENGTH_MONEY_RIGHT_DIGITS = 2;

        /**
         * COMMISSION_RATIO_FACTOR is supposed to be used to divide the output of the
         * commission lvl algorithm.
         */
        public static final float COMMISSION_RATIO_FACTOR = 100f;
        public static final int MAX_COMMISSION_RATE = 50;

        public static final int MAX_LENGTH_PAYMENT_DESCRIPTOR = 60;

        public final static int THREAD_NUMBER = 1;
    }

    @Override
    public void start(final Stage stage) throws IOException {
        String fileName = "mainMenu";

        FXMLLoader fxmlLoader = new FXMLLoader(
                new URL(ConstraitConstants.GUIs_LOCATION + fileName + ConstraitConstants.FXML_FILE_EXTENSION));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Menu principal");

        ViewCntlr viewCtrller = fxmlLoader.getController();

        stage.resizableProperty().set(false);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
                new CustomAlert(AlertType.CONFIRMATION, "CERRAR", "Desea cerrar el Sistema?").customShow()
                        .ifPresent(response -> {
                            if (response == ButtonType.OK) {
                                Platform.exit();
                                System.exit(0);
                            }
                        });
            }
        });

        try {
            ConfigFileInterpreter cfi = ConfigFileInterpreter.getInstance(ConfigFileInterpreter.CONFIG_FILE_LOCATION);
            cfi.interpret();
            new CustomAlert(AlertType.INFORMATION, "INFO",
                    "Archivo de configuraciones cargado exitosamente.\n" + "Configuración para arhivo detalle:\n"
                            + "Primeras filas ignoradas = " + ConfigFileInterpreter.getFirstRowsSkipped()).customShow();
        } catch (Exception exception) {
            exception.printStackTrace();
            new CustomAlert(AlertType.ERROR, "ERROR", "Se produjo un error al leer el archivo de configuraciones.\n")
                    .customShow();
        }

        BLService prefClientService = new PreferentialClientBLService();
        viewCtrller.addService(prefClientService);
        prefClientService.setBLServiceSubscriber(viewCtrller);

        BLService campaignService = new CampaignBLService();
        viewCtrller.addService(campaignService);
        campaignService.setBLServiceSubscriber(viewCtrller);

        stage.show();
        ((MainMenuViewCntlr) viewCtrller).init();
    }
}
