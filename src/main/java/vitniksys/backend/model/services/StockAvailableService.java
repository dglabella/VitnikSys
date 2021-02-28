package vitniksys.backend.model.services;

import java.util.List;
import javafx.concurrent.Task;
import javafx.application.Platform;
import vitniksys.backend.util.CustomAlert;
import vitniksys.frontend.views_subscriber.StockAvailableServiceSubscriber;
import vitniksys.backend.model.persistence.Connector;
import vitniksys.backend.model.entities.ReturnedArticle;
import vitniksys.backend.model.persistence.ReturnedArticleOperator;

public class StockAvailableService extends Service
{
    public void getStockAvailable() throws Exception
    {
        CustomAlert customAlert = getServiceSubscriber().showProcessIsWorking("Obteniendo Stock disponible...");
        Task<Void> task = new Task<>()
        {
            @Override
            protected Void call() throws Exception
            {
                List<ReturnedArticle> returnedArticles = null;
                try
                {   
                    returnedArticles = ReturnedArticleOperator.getOperator().findAll();

                    if(returnedArticles != null)
                    {
                        ((StockAvailableServiceSubscriber)getServiceSubscriber()).showStockAvailable(returnedArticles);
                    }
                    else
                    {
                        getServiceSubscriber().showNoResult("No hay stock disponible");
                    }
                }
                catch(Exception exception)
                {
                    exception.printStackTrace();
                    getServiceSubscriber().closeProcessIsWorking(customAlert);
                    getServiceSubscriber().showError("No se pudo obtener el Stock disponible", null, exception);
                }
                finally
                {
                    Connector.getConnector().closeConnection();
                }

                return null;
            }
        };
        Platform.runLater(task);
    }    
}
