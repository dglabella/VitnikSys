package vitniksys.backend.model.services;

import java.util.List;
import javafx.concurrent.Task;
import javafx.application.Platform;
import vitniksys.backend.util.CustomAlert;
import vitniksys.backend.model.entities.Order;
import vitniksys.backend.model.entities.Leader;
import vitniksys.backend.model.entities.Commission;
import vitniksys.backend.model.persistence.Connector;
import vitniksys.backend.model.persistence.OrderOperator;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.persistence.CommissionOperator;
import vitniksys.frontend.views_subscriber.CommissionServiceSubscriber;

public class CommissionService extends Service
{
    /**
     * COMMISSION_RATIO_FACTOR is supposed to be used to divide the
     * output of the commission lvl algorithm.
     */
    private final float COMMISSION_RATIO_FACTOR = 100f;

    public void createDefaultCommission(PreferentialClient prefClient, Integer campNumber) throws Exception
    {
        //CustomAlert customAlert = this.getServiceSubscriber().showProcessIsWorking("Espere un momento mientras se realiza el proceso.");
        Task<Integer> task = new Task<>()
        {
            @Override
            protected Integer call() throws Exception
            {
                //returnCode is intended for future implementations
                int returnCode = 0;
                Commission commission = new Commission(prefClient.getId(), campNumber);
                commission.setActualQuantity(0);
                commission.setActualRate(0);
                    
                try
                {
                    Connector.getConnector().startTransaction();
                    
                    CommissionOperator.getOperator().insert(commission);

                    Connector.getConnector().commit();

                    commission = CommissionOperator.getOperator().find(prefClient.getId(), campNumber);

                    calculateCommissionRate(prefClient, campNumber);

                    searchCommission(prefClient.getId(), campNumber);

                    //getServiceSubscriber().closeProcessIsWorking(customAlert);
                    //getServiceSubscriber().showSucces("Los niveles de comisión para el cliente preferencial " + prefClientId +
                    //    " en la campaña "+ campNumb +" se han registrado exitosamente!");
                }
                catch (Exception exception)
                {
                    Connector.getConnector().rollBack();
                    //getServiceSubscriber().closeProcessIsWorking(customAlert);
                    //getServiceSubscriber().showError("Error al intentar registrar los niveles de comisión.", null, exception);
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
    

    private Float calculateCommissionRate(PreferentialClient prefClient, Integer campNumber)asdkjaslkdjaskl
    {
        float ret = 0f;

        if(prefClient instanceof Leader)
        {
            Commission commission = ((Leader)prefClient).getCommissions().locateWithCampNumb(campNumber);
            int actualQuantity = prefClient.getCommissionablesQuantity(campNumber);
            
            if(commission != null)
            {
                if(commission.getMinQuantity() <= actualQuantity && actualQuantity <= commission.getLvl1Quantity())
                {
                    ret = commission.getLvl1Factor();
                }
                else if(actualQuantity <= commission.getLvl2Quantity())
                {
                    ret = commission.getLvl2Factor();
                }
                else if(actualQuantity <= commission.getLvl3Quantity())
                {
                    ret = commission.getLvl3Quantity();
                }
                else if(actualQuantity <= commission.getLvl4Quantity())
                {
                    ret = commission.getLvl4Factor();
                }
                else
                {
                    ret = 15;
                }

                ((CommissionServiceSubscriber)this.getServiceSubscriber()).showCommission(commission);
            }
            else
            {
                ((CommissionServiceSubscriber)this.getServiceSubscriber()).suggestCommisionCreation();
            }
        }
        else
        {
            getServiceSubscriber().showNoResult("Solo se puede calcular comisión para un líder");
        }

        return ret/COMMISSION_RATIO_FACTOR;
    }

    public void updateCommissionableOrders(List<Order> orders)
    {
        CustomAlert customAlert = this.getServiceSubscriber().showProcessIsWorking("Actualizando pedidos comisionables.");
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

                    OrderOperator.getOperator().updateAll(orders);

                    Connector.getConnector().commit();
                    
                    getServiceSubscriber().closeProcessIsWorking(customAlert);
                    getServiceSubscriber().showSucces("Pedidos actualizados!");
                }
                catch(Exception exception)
                {
                    exception.printStackTrace();
                }
                finally
                {
                    Connector.getConnector().closeConnection();
                }

                return returnCode;
            }
        };
        Platform.runLater(task);
        //this.getExecutorService().execute(task);
	}

    public void searchCommission(Integer prefClientId, Integer campNumber) throws Exception
    {
        try
        {
            Commission commission = CommissionOperator.getOperator().find(prefClientId, campNumber);

            if(commission != null)
            {
                ((CommissionServiceSubscriber)this.getServiceSubscriber()).showCommission(commission);
            }
            else
            {
                this.getServiceSubscriber().showNoResult("No existe comisión para el cliente "+prefClientId+" en la campaña "+campNumber);
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            getServiceSubscriber().showError("Error al intentar recuperar la comisión del cliente "+prefClientId+" en la campaña "+campNumber);
        }
        finally
        {
            Connector.getConnector().closeConnection();
        }
	}
}