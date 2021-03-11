package vitniksys.backend.model.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.concurrent.Task;
import javafx.application.Platform;
import vitniksys.backend.util.CustomAlert;
import vitniksys.backend.model.entities.Balance;
import vitniksys.backend.model.entities.Repurchase;
import vitniksys.backend.model.persistence.Connector;
import vitniksys.backend.model.entities.ReturnedArticle;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.persistence.BalanceOperator;
import vitniksys.backend.model.entities.SubordinatedClient;
import vitniksys.backend.model.persistence.RepurchaseOperator;
import vitniksys.backend.model.persistence.ReturnedArticleOperator;
import vitniksys.frontend.views_subscriber.StockAvailableServiceSubscriber;

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
                        getServiceSubscriber().closeProcessIsWorking(customAlert);
                        ((StockAvailableServiceSubscriber)getServiceSubscriber()).showStockAvailable(returnedArticles);
                    }
                    else
                    {
                        getServiceSubscriber().closeProcessIsWorking(customAlert);
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

    public void registerRepurchase(PreferentialClient prefClient, Integer campNumber, Integer unitCode, Float repurchasePrice) throws Exception
    {
        CustomAlert customAlert = this.getServiceSubscriber().showProcessIsWorking("Espere un momento mientras se realiza el proceso.");
        Task<Integer> task = new Task<>()
        {
            @Override
            protected Integer call() throws Exception
            {
                //returnCode is intended for future implementations
                int returnCode = 0;

                ReturnedArticle returnedArticle = new ReturnedArticle();
                returnedArticle.setUnitCode(unitCode);
                returnedArticle.setRepurchased(true);

                Repurchase repurchase = new Repurchase(repurchasePrice);
                repurchase.setPrefClientId(prefClient.getId());
                repurchase.setCampNumber(campNumber);
                repurchase.setReturnedArticleId(unitCode);

                Balance balance = new Balance();
                balance.setPrefClientId(prefClient.getId());
                balance.setCampNumber(campNumber);
                balance.setTotalInRepurchases(repurchasePrice);

                try
                {
                    Connector.getConnector().startTransaction();

                    ReturnedArticleOperator.getOperator().update(returnedArticle);
                    RepurchaseOperator.getOperator().insert(repurchase);
                    BalanceOperator.getOperator().update(balance);

                    if(prefClient instanceof SubordinatedClient)
                    {
                        //update also the leader balance
                        balance.setPrefClientId(((SubordinatedClient)prefClient).getLeaderId());
                        BalanceOperator.getOperator().update(balance); //UPDATE
                    }
                    
                    Connector.getConnector().commit();

                    getServiceSubscriber().closeProcessIsWorking(customAlert);
                    getServiceSubscriber().showSucces("La recompra se ha registrado exitosamente!");
                    getServiceSubscriber().refresh();
                }
                catch (Exception exception)
                {
                    Connector.getConnector().rollBack();
                    getServiceSubscriber().closeProcessIsWorking(customAlert);
                    getServiceSubscriber().showError("Error al realizar la recompra.", null, exception);
                }
                finally
                {
                    Connector.getConnector().endTransaction();
                    Connector.getConnector().closeConnection();
                }
                return returnCode;
            }
        };

        Platform.runLater(task);
    }

    public void registerVitnikResend(List<Integer> unitCodes) throws Exception
    {
        CustomAlert customAlert = this.getServiceSubscriber().showProcessIsWorking("Espere un momento mientras se realiza el proceso...");
        Task<Integer> task = new Task<>()
        {
            @Override
            protected Integer call() throws Exception
            {
                //returnCode is intended for future implementations
                int returnCode = 0;

                try
                {
                    Connector.getConnector().startTransaction();

                    ReturnedArticle returnedArticle;
                    List<ReturnedArticle> returnedArticles = new ArrayList<>();
                    Iterator<Integer> it = unitCodes.iterator();
                    while(it.hasNext())
                    {
                        returnedArticle = new ReturnedArticle();
                        returnedArticle.setUnitCode(it.next());
                        returnedArticle.setRepurchased(false);
                        returnedArticle.setForwarded(true);
                        returnedArticles.add(returnedArticle);
                    }

                    ReturnedArticleOperator.getOperator().updateAll(returnedArticles);
                    
                    Connector.getConnector().commit();

                    getServiceSubscriber().closeProcessIsWorking(customAlert);
                    getServiceSubscriber().showSucces("Reenvios registrados exitosamente!");
                    getServiceSubscriber().refresh();
                }
                catch (Exception exception)
                {
                    Connector.getConnector().rollBack();
                    getServiceSubscriber().closeProcessIsWorking(customAlert);
                    getServiceSubscriber().showError("Error al registrar el reenvio a VITNIK.", null, exception);
                }
                finally
                {
                    Connector.getConnector().endTransaction();
                    Connector.getConnector().closeConnection();
                }
                return returnCode;
            }
        };

        Platform.runLater(task);  
    }
}
