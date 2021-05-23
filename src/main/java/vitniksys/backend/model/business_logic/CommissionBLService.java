package vitniksys.backend.model.business_logic;

import vitniksys.App;
import java.util.List;
import java.util.Iterator;
import javafx.concurrent.Task;
import javafx.application.Platform;
import vitniksys.backend.util.CustomAlert;
import vitniksys.frontend.view_subscribers.CommissionBLServiceSubscriber;
import vitniksys.backend.model.entities.Order;
import vitniksys.backend.model.entities.Leader;
import vitniksys.backend.model.entities.Commission;
import vitniksys.backend.model.entities.Repurchase;
import vitniksys.backend.model.persistence.Connector;
import vitniksys.backend.model.persistence.OrderOperator;
import vitniksys.backend.model.persistence.BalanceOperator;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.persistence.CommissionOperator;
import vitniksys.backend.model.persistence.RepurchaseOperator;

public class CommissionBLService extends BLService
{
    // Getters && Setters
    

    public static int calculateArticlesQuantity(List<Order> orders)
    {
        int ret = 0;
        
        if(orders != null)
        {
            Iterator<Order> it = orders.iterator();
            while(it.hasNext())
            {
                ret += it.next().getQuantity();
            }
        }

        return ret;
    }

    public static int calculateCommissionablesQuantity(List<Order> orders, List<Repurchase> repurchases)
    {
        int ret = 0;
        
        if(orders != null)
        {
            Iterator<Order> it = orders.iterator();
            Order order = null;
            while(it.hasNext())
            {
                order = it.next();
                if(order.isCountForCommission())
                {
                    ret += order.getQuantity() - order.getReturnedQuantity();
                }
            }
        }

        if(repurchases != null)
        {
            Iterator<Repurchase> it = repurchases.iterator();
            Repurchase repurchase = null;
            while(it.hasNext())
            {
                repurchase = it.next();
                if(repurchase.isCountForCommission() && !repurchase.isReturned())
                {
                    ret++;
                }
            }
        }
        
        return ret;
    }

    public static float calculateTotalInCommission(Commission commission, List<Order> orders)
    {
        float ret = 0f;
        float comFactor = commission.getActualRate()/App.ConstraitConstants.COMMISSION_RATIO_FACTOR;
        float fpFactor = commission.getFpFactor()/App.ConstraitConstants.COMMISSION_RATIO_FACTOR;
        float otherFactor = commission.getOtherFactor()/App.ConstraitConstants.COMMISSION_RATIO_FACTOR;
        Iterator<Order> it = orders.iterator();
        Order order = null;
        while(it.hasNext())
        {
            order = it.next();
            if(order.isCommissionable())
            {
                switch(order.getType())
                {
                    case PEDIDO:
                    case OPORTUNIDAD:
                        ret += ((order.getCost() / order.getQuantity()) * (order.getQuantity() - order.getReturnedQuantity())) * comFactor;
                    break;

                    case FREEPREMIUM:
                    case PROMO:
                        ret += ((order.getCost() / order.getQuantity()) * (order.getQuantity() - order.getReturnedQuantity())) * fpFactor;
                    break;

                    default:
                        ret += ((order.getCost() / order.getQuantity()) * (order.getQuantity() - order.getReturnedQuantity())) * otherFactor;
                }
            }
        }

        return ret;
    }

    // ================================== private methods ==================================
    protected void updateCommission(Commission commission, List<Order> orders, List<Repurchase> repurchases) throws Exception
    {
        int actualQuantity =  CommissionBLService.calculateCommissionablesQuantity(orders, repurchases);
        int commissionFactor = 0;

        if(commission != null && orders != null && orders.size() > 0)
        {
            if(actualQuantity < commission.getMinQuantity())
            {
                commissionFactor = 0;
            }
            else if(commission.getMinQuantity() <= actualQuantity && actualQuantity < commission.getLvl1Quantity())
            {
                commissionFactor = commission.getLvl1Factor();
            }
            else if(actualQuantity < commission.getLvl2Quantity())
            {
                commissionFactor = commission.getLvl2Factor();
            }
            else if(actualQuantity < commission.getLvl3Quantity())
            {
                commissionFactor = commission.getLvl3Factor();
            }
            else
            {
                commissionFactor = commission.getLvl4Factor();
            }

            commission.setActualQuantity(actualQuantity);
            commission.setActualRate(commissionFactor);

            CommissionOperator.getOperator().update(commission);

            Float totalInCommission = CommissionBLService.calculateTotalInCommission(commission, orders);

            BalanceOperator.getOperator().correctCommission(commission.getPrefClientId(), commission.getCampNumber(), totalInCommission);
        }
        else
        {
            throw new Exception("Commission is null or orders list is null or orders list sizer is 0");
        }
    }

    // ================================== public methods ==================================
    public void createDefaultCommission(PreferentialClient prefClient, List<Order> orders, List<Repurchase> repurchases) throws Exception
    {
        CustomAlert customAlert = this.getBLServiceSubscriber().showProcessIsWorking("Creando comisión por defecto.");

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
                    if(prefClient != null && prefClient instanceof Leader)
                    {
                        if(orders != null && orders.size() > 0)
                        {
                            Connector.getConnector().startTransaction();
                            Commission commission = new Commission(prefClient.getId(), orders.get(0).getCampNumber());
                            commission.setActualQuantity(0);
                            commission.setActualRate(0);

                            CommissionOperator.getOperator().insert(commission);
                            //get commission from database with default lvls
                            commission = CommissionOperator.getOperator().find(prefClient.getId(), orders.get(0).getCampNumber());

                            updateCommission(commission, orders, repurchases);

                            Connector.getConnector().commit();

                            getBLServiceSubscriber().closeProcessIsWorking(customAlert);
                            getBLServiceSubscriber().showSucces
                            (
                                "Se han creado para el cliente preferencial " + prefClient.getId() +
                                "\nen la campaña " + orders.get(0).getCampNumber() + " los niveles de comisión por defecto. " +
                                "\nPueden modificarse y actualizarse a preferencia."
                            );

                            ((CommissionBLServiceSubscriber)getBLServiceSubscriber()).showCommission(commission);
                            getBLServiceSubscriber().refresh();
                        }
                        else
                        {
                            throw new Exception("No orders registered.");
                        }
                    }
                    else
                    {
                        throw new Exception("This preferential client ("+(prefClient != null ? prefClient.getId():null)+") is not a leader");
                    }
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                    Connector.getConnector().rollBack();
                    getBLServiceSubscriber().closeProcessIsWorking(customAlert);
                    getBLServiceSubscriber().showError("Error al intentar registrar los niveles de comisión. Puede que el cliente \npreferencial "+
                        "no sea un líder ó no haya pedidos registrados.", null, exception);

                    getBLServiceSubscriber().closeSubscriberStage();
                }
                finally
                {
                    getBLServiceSubscriber().closeProcessIsWorking(customAlert);
                    Connector.getConnector().endTransaction();
                    Connector.getConnector().closeConnection();
                }
                return returnCode;
            }
        };
        Platform.runLater(task);
    }

    public void modifyCommission(Integer prefClientId, Integer campNumber, Integer actualQuantity, Integer actualRate, Integer minQuantity, Integer lvl1Quantity, Integer lvl2Quantity, Integer lvl3Quantity, 
        Integer lvl1Factor, Integer lvl2Factor, Integer lvl3Factor, Integer lvl4Factor, Integer fpFactor, Integer otherFactor, List<Order> orders, List<Repurchase> repurchases) throws Exception
    {
        CustomAlert customAlert = this.getBLServiceSubscriber().showProcessIsWorking("Modificando comisión.");
           
        Commission commission = new Commission(actualQuantity, actualRate, minQuantity, lvl1Quantity, lvl2Quantity, lvl3Quantity, lvl1Factor, lvl2Factor, lvl3Factor, lvl4Factor, fpFactor, otherFactor);
        commission.setPrefClientId(prefClientId);
        commission.setCampNumber(campNumber);
        try
        {
            Connector.getConnector().startTransaction();
            
            updateCommission(commission, orders, repurchases);

            Connector.getConnector().commit();
            
            getBLServiceSubscriber().closeProcessIsWorking(customAlert);
            getBLServiceSubscriber().showSucces("Se han modificado los niveles de comisión exitosamente.");
            getBLServiceSubscriber().refresh();
        }
        catch (Exception exception)
        {
            Connector.getConnector().rollBack();
            getBLServiceSubscriber().closeProcessIsWorking(customAlert);
            getBLServiceSubscriber().showError("Error al intentar modificar los niveles de comisión.", null, exception);
        }
        finally
        {
            Connector.getConnector().endTransaction();
            Connector.getConnector().closeConnection();
        }
    }
    
    public void searchCommission(Integer prefClientId, Integer campNumber) throws Exception
    {
        try
        {
            Commission commission = CommissionOperator.getOperator().find(prefClientId, campNumber);

            if(commission != null)
            {
                ((CommissionBLServiceSubscriber)this.getBLServiceSubscriber()).showCommission(commission);
            }
            else
            {
                this.getBLServiceSubscriber().showNoResult("No existe comisión para el cliente "+prefClientId+" en la campaña "+campNumber);
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            getBLServiceSubscriber().showError("Error al intentar recuperar la comisión del cliente "+prefClientId+" en la campaña "+campNumber);
        }
        finally
        {
            Connector.getConnector().closeConnection();
        }
    }
    
    public void updateCommissionableOrders(Commission commission, List<Order> orders, List<Repurchase> repurchases)
    {
        if(commission != null)
        {
            if(orders != null && orders.size() > 0)
            {
                CustomAlert customAlert = this.getBLServiceSubscriber().showProcessIsWorking("Actualizando pedidos comisionables.");
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

                            OrderOperator.getOperator().updateAllForCommission(orders);
                            RepurchaseOperator.getOperator().updateAll(repurchases);

                            updateCommission(commission, orders, repurchases);

                            Connector.getConnector().commit();
                            
                            getBLServiceSubscriber().closeProcessIsWorking(customAlert);
                            getBLServiceSubscriber().showSucces("Pedidos actualizados!");

                            getBLServiceSubscriber().refresh();
                            //Leader leader = LeaderOperator.getOperator().find(commission.getPrefClientId());
                            //((PreferentialClientServiceSubscriber)getServiceSubscriber()).showQueriedPrefClient(leader);
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
                this.getBLServiceSubscriber().showNoResult("No hay pedidos que actualizar");
            }
        }
        else
        {
            ((CommissionBLServiceSubscriber)this.getBLServiceSubscriber()).suggestCommisionCreation();
        }
	}
}