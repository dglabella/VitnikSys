package vitniksys;

import java.net.URL;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
//import javafx.stage.FileChooser;
import javafx.application.Application;
import vitniksys.backend.model.services.Service;
import vitniksys.backend.util.ExpressionChecker;
import vitniksys.frontend.view_controllers.ViewCntlr;
import vitniksys.backend.model.services.CampaignService;
import vitniksys.backend.model.services.PreferentialClientService;
//import vitniksys.backend.util.DetailFileInterpreter;
import vitniksys.frontend.view_controllers.MainMenuViewCntlr;

public class App extends Application
{
    public static final String GUIs_LOCATION = App.class.getResource("") + "../frontend/GUIs/";
    public static final String FILE_EXTENSION = ".fxml";

    @Override
    public void start(final Stage stage) throws IOException
    {
        //new DetailFileInterpreter(new FileChooser().showOpenDialog(null)).insertClientFromDetailFile();

        String fileName = "mainMenu";

        FXMLLoader fxmlLoader = new FXMLLoader(new URL(GUIs_LOCATION + fileName + FILE_EXTENSION));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Menu principal");
        
        ViewCntlr viewCtrller = fxmlLoader.getController();

        Service prefClientService= new PreferentialClientService();
        viewCtrller.addService(prefClientService);
        prefClientService.setServiceSubscriber(viewCtrller);
        prefClientService.setExpressionChecker(ExpressionChecker.getExpressionChecker());

        Service campaignService = new CampaignService();
        viewCtrller.addService(campaignService);
        campaignService.setServiceSubscriber(viewCtrller);
        campaignService.setExpressionChecker(ExpressionChecker.getExpressionChecker());

        stage.show();
        ((MainMenuViewCntlr)viewCtrller).init();
    }

    public static void main(final String[] args)
    {
        launch();
    }
}