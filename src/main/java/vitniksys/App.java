package vitniksys;

import java.net.URL;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.stage.WindowEvent;
import javafx.event.EventHandler;
//import javafx.stage.FileChooser;
import javafx.application.Platform;
import javafx.application.Application;
import javafx.scene.control.ButtonType;
import vitniksys.backend.util.CustomAlert;
import javafx.scene.control.Alert.AlertType;
import vitniksys.frontend.view_controllers.ViewCntlr;
import vitniksys.backend.model.bussines_logic.BLService;
import vitniksys.frontend.view_controllers.MainMenuViewCntlr;
import vitniksys.backend.model.bussines_logic.CampaignBLService;
import vitniksys.backend.model.bussines_logic.PreferentialClientBLService;


public class App extends Application
{
    public static class ConstraitConstants
    {
        public static final String GUIs_LOCATION = App.class.getResource("") + "../frontend/GUIs/";
        public static final String FILE_EXTENSION = ".fxml";
        public static final String CONFIG_FILE_LOCATION = App.class.getResource("../configs/config.txt").getPath();
        public static final String CONFIG_FILE_CONNECTION_SECTION = "[CONNECTION]";
        public static final String CONFIG_FILE_END_SECTION = "[END]";
        public static final String CONFIG_FILE_DATA_SEPARATOR = ":";
        public static final int CONFIG_FILE_TAG_SIDE = 0;
        public static final int CONFIG_FILE_DATA_SIDE = 1;

        public static final String CONFIG_FILE_IP_TAG = "ip";
        public static final String CONFIG_FILE_PORT_TAG = "port";
        public static final String CONFIG_FILE_DATABASE_TAG = "database";
        public static final String CONFIG_FILE_OPT_TAG = "options";
        public static final String CONFIG_FILE_USER_TAG = "user";
        public static final String CONFIG_FILE_PASS_TAG = "pass";

        public static final String FILTER_COMMAND_PREFIX = "//";

        public static final int MAX_LENGTH_CATALOGUE_LINK = 500;
        public static final int MAX_LENGTH_MONEY_LEFT_DIGITS = 10;
        public static final int MAX_LENGTH_MONEY_RIGHT_DIGITS = 2;

        /**
         * COMMISSION_RATIO_FACTOR is supposed to be used to divide the
         * output of the commission lvl algorithm.
         */
        public static final float COMMISSION_RATIO_FACTOR = 100f;
        public static final int MAX_COMMISSION_RATE = 50;

        public static final int MAX_LENGTH_PAYMENT_DESCRIPTOR = 60;

        public final static int THREAD_NUMBER = 1;
    }

    @Override
    public void start(final Stage stage) throws IOException
    {
        //new DetailFileInterpreter(new FileChooser().showOpenDialog(null)).insertClientFromDetailFile();
        String fileName = "mainMenu";

        FXMLLoader fxmlLoader = new FXMLLoader(new URL(ConstraitConstants.GUIs_LOCATION + fileName + ConstraitConstants.FILE_EXTENSION));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Menu principal");
        
        ViewCntlr viewCtrller = fxmlLoader.getController();
        
        stage.resizableProperty().set(false);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>()
        {
            @Override
            public void handle(WindowEvent event)
            {
                event.consume();
                new CustomAlert(AlertType.CONFIRMATION, "CERRAR", "Desea cerrar el Sistema?")
                .customShow().ifPresent(response ->
                {
                    if(response == ButtonType.OK)
                    {
                        Platform.exit();
                        System.exit(0);
                    }
                });
            }
        });

        BLService prefClientService = new PreferentialClientBLService();
        viewCtrller.addService(prefClientService);
        prefClientService.setBLServiceSubscriber(viewCtrller);

        BLService campaignService = new CampaignBLService();
        viewCtrller.addService(campaignService);
        campaignService.setBLServiceSubscriber(viewCtrller);

        stage.show();
        ((MainMenuViewCntlr)viewCtrller).init();
    }

    public static void main(final String[] args)
    {
        launch();
    }
}