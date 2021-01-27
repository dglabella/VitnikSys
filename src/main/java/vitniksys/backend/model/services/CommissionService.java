package vitniksys.backend.model.services;

import java.util.List;
import java.util.Iterator;
import javafx.concurrent.Task;
import javafx.application.Platform;
import vitniksys.backend.util.CustomAlert;
import vitniksys.backend.model.entities.Order;
import vitniksys.backend.model.entities.Leader;
import vitniksys.backend.model.entities.Commission;
import vitniksys.backend.model.persistence.Connector;
import vitniksys.backend.model.persistence.OrderOperator;
import vitniksys.backend.model.persistence.LeaderOperator;
import vitniksys.backend.model.persistence.CommissionOperator;
import vitniksys.frontend.views_subscriber.CommissionServiceSubscriber;
import vitniksys.frontend.views_subscriber.PreferentialClientServiceSubscriber;

public class CommissionService extends Service
{
    /**
     * COMMISSION_RATIO_FACTOR is supposed to be used to divide the
     * output of the commission lvl algorithm.
     */
    private final float COMMISSION_RATIO_FACTOR = 100f;

    // Getters && Setters

    public static int calculateArticlesQuantity(List<Order> orders)
    {
        int ret = 0;
        
        Iterator<Order> it = orders.iterator();
        while(it.hasNext())
        {
            ret += it.next().getQuantity();
        }

        return ret;
    }

    public static int calculateCommissionablesQuantity(List<Order> orders)
    {
        int ret = 0;
        
        Iterator<Order> it = orders.iterator();
        Order order = null;
        while(it.hasNext())
        {
            order = it.next();
            ret += (order.isCommissionable() ? order.getQuantity() : 0);
        }
        return ret;
    }

    // ================================== private methods ==================================
    private void updateCommission(Commission commission, List<Order> orders) throws Exception
    {
        int actualQuantity =  CommissionService.calculateCommissionablesQuantity(orders);
        int commissionFactor = 0;

        if(commission != null && orders != null && orders.size() > 0)
        {
            if(commission.getMinQuantity() <= actualQuantity && actualQuantity <= commission.getLvl1Quantity())
            {
                commissionFactor = commission.getLvl1Factor();
            }
            else if(actualQuantity <= commission.getLvl2Quantity())
            {
                commissionFactor = commission.getLvl2Factor();
            }
            else if(actualQuantity <= commission.getLvl3Quantity())
            {
                commissionFactor = commission.getLvl3Factor();
            }
            else if(actualQuantity <= commission.getLvl4Quantity())
            {
                commissionFactor = commission.getLvl4Factor();
            }
            else
            {
                commissionFactor = 20;
            }

            commission.setActualQuantity(actualQuantity);
            commission.setActualRate(commissionFactor);

            CommissionOperator.getOperator().update(commission);
        }
        else
        {
            throw new Exception("Commission is null or orders list is null or orders list sizer is 0");
        }
    }

    // ================================== public methods ==================================
    public void createDefaultCommission(List<Order> orders) throws Exception
    {
        if(orders != null && orders.size() > 0)
        {
            CustomAlert customAlert = this.getServiceSubscriber().showProcessIsWorking("Creando comisión por defecto.");
            Task<Integer> task = new Task<>()
            {
                @Override
                protected Integer call() throws Exception
                {
                    //returnCode is intended for future implementations
                    int returnCode = 0;
                    Commission commission = new Commission(orders.get(0).getPrefClientId(), orders.get(0).getCampNumber());
                    commission.setActualQuantity(0);
                    commission.setActualRate(0);
                        
                    try
                    {
                        Connector.getConnector().startTransaction();
                        
                        CommissionOperator.getOperator().insert(commission);
                        //get commission from database with default lvls
                        commission = CommissionOperator.getOperator().find(orders.get(0).getPrefClientId(), orders.get(0).getCampNumber());
                        updateCommission(commission, orders);

                        Connector.getConnector().commit();

                        getServiceSubscriber().closeProcessIsWorking(customAlert);
                        getServiceSubscriber().showSucces
                        (
                            "Se han creado para el cliente preferencial " + orders.get(0).getPrefClientId() +
                            "\nen la campaña "+ orders.get(0).getCampNumber() +"los niveles de comisión por defecto. "+
                            "\nPueden modificarse y actualizarse a preferencia."
                        );
                    }
                    catch (Exception exception)
                    {
                        Connector.getConnector().rollBack();
                        getServiceSubscriber().closeProcessIsWorking(customAlert);
                        getServiceSubscriber().showError("Error al intentar registrar los niveles de comisión.", null, exception);
                    }
                    finally
                    {
                        Connector.getConnector().endTransaction();
                        Connector.getConnector().closeConnection();
                        searchCommission(orders.get(0).getPrefClientId(), orders.get(0).getCampNumber());
                    }
                    return returnCode;
                }
            };
            Platform.runLater(task);
        }
        else
        {
            this.getServiceSubscriber().showNoResult("No hay pedidos");
        }
    }

    public void modifyCommission(Commission commission) throws Exception
    {
        CustomAlert customAlert = this.getServiceSubscriber().showProcessIsWorking("Modificando comisión.");
        Task<Integer> task = new Task<>()
        {
            int returnCode = 0;
            @Override
            protected Integer call() throws Exception
            {       
                try
                {
                    Connector.getConnector().startTransaction();
                    
                    CommissionOperator.getOperator().update(commission);

                    Connector.getConnector().commit();

                    getServiceSubscriber().closeProcessIsWorking(customAlert);
                    getServiceSubscriber().showSucces("Se han modificado los niveles de comisión exitosamente.");
                }
                catch (Exception exception)
                {
                    Connector.getConnector().rollBack();
                    getServiceSubscriber().closeProcessIsWorking(customAlert);
                    getServiceSubscriber().showError("Error al intentar modificar los niveles de comisión.", null, exception);
                }
                finally
                {
                    Connector.getConnector().endTransaction();
                    Connector.getConnector().closeConnection();
                    searchCommission(prefClientId, campNumber);
                }
                return returnCode;
            }
        };
        Platform.runLater(task);        
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

    public void updateCommissionableOrders(Commission commission, List<Order> orders)
    {
        if(commission != null)
        {
            if(orders != null && orders.size() > 0)
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

                            updateCommission(commission, orders);

                            Connector.getConnector().commit();
                            
                            getServiceSubscriber().closeProcessIsWorking(customAlert);
                            getServiceSubscriber().showSucces("Pedidos actualizados!");

                            Leader leader = LeaderOperator.getOperator().find(commission.getPrefClientId());

                            ((PreferentialClientServiceSubscriber)getServiceSubscriber()).showQueriedPrefClient(leader);
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
            else
            {
                this.getServiceSubscriber().showNoResult("No hay pedidos que actualizar");
            }
        }
        else
        {
            ((CommissionServiceSubscriber)this.getServiceSubscriber()).suggestCommisionCreation();
        }
	}
}