package vitniksys.backend.controllers;

import java.util.List;
import java.util.Iterator;
import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import vitniksys.backend.model.entities.Campaign;
import vitniksys.backend.util.OperationResult;
import vitniksys.backend.util.PedidosObtainer;
import vitniksys.frontend.views.OperationResultView;
import vitniksys.backend.model.persistence.Connector;
import vitniksys.backend.model.persistence.OrderOperator;
import vitniksys.frontend.views.CampQueryRegisterView;
import vitniksys.backend.model.persistence.CampaignOperator;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.persistence.PreferentialClientOperator;

public class CampManagementController
{
    //The list for save the result of "pedidosObtainer".
    //only used when detail file is loaded.
    private Future<List<PreferentialClient>> customersWithNewOrders;

    //Views
    private CampQueryRegisterView campQueryRegisterView;
    private OperationResultView operationResultView;

    public CampManagementController(CampQueryRegisterView campQueryRegisterView, OperationResultView operationResultView)
    {
        this.campQueryRegisterView = campQueryRegisterView;
        this.operationResultView = operationResultView;
    }

    //Getters && Setters
    public OperationResultView getOperationResultView()
    {
        return this.operationResultView;
    }

    public void setOperationResultView(OperationResultView operationResultView)
    {
        this.operationResultView = operationResultView;
    }
    
    public Future<List<PreferentialClient>> getCustomersWithNewOrders()
    {
        return this.customersWithNewOrders;
    }


    // ================================= private methods =================================

    // ================================= protected methods =================================

    // ================================= public methods =================================
    public void searchCamp(int campNumb) throws Exception
    {
        OperationResult operationResult = new OperationResult();
        try
        {
            Campaign camp = CampaignOperator.getOperator().find(campNumb);
            if(camp != null)
            {
                OrderOperator.getOperator();
                this.campQueryRegisterView.showQueriedCamp(camp);
            }
            else
            {
                this.campQueryRegisterView.showNoResult();
            }
        }
        catch (Exception exception)
        {
            operationResult.setCode(OperationResult.ERROR);
            operationResult.setException(exception);
            this.operationResultView.showResult(operationResult);
        }
        finally
        {
            Connector.getConnector().closeConnection();
        }
    }

    public void searchLastCamp() throws Exception
    {
        OperationResult operationResult = new OperationResult();
        try
        {
            Campaign camp = CampaignOperator.getOperator().findLast();
            if(camp != null)
            {
                this.campQueryRegisterView.showQueriedCamp(camp);
            }
            else
            {
                this.campQueryRegisterView.showNoResult();
            }
            operationResult.setCode(OperationResult.SUCCES);
        }
        catch (Exception exception)
        {
            operationResult.setCode(OperationResult.ERROR);
            operationResult.setException(exception);
            this.operationResultView.showResult(operationResult);
        }
        finally
        {
            Connector.getConnector().closeConnection();
        }
    }

    public void obtainOrders(PedidosObtainer pedidosObtainer) throws Exception
    {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        this.customersWithNewOrders = executorService.submit(pedidosObtainer);
    }

    public void registerOrders(List<PreferentialClient> cps) throws Exception
    {
        OperationResult operationResult = new OperationResult();
        try{
            Connector.getConnector().startTransaction();
            
            PreferentialClient cp;
            PreferentialClientOperator cpOperator;
            Iterator<PreferentialClient> cpsIterator = cps.iterator();

            while(cpsIterator.hasNext())
            {
                cp = cpsIterator.next();
                cpOperator = cp.operator();
                cpOperator.registerOrders(cp);
            }
            
            Connector.getConnector().commit();

            operationResult.setCode(OperationResult.SUCCES);
        }
        catch (Exception exception)
        {
            Connector.getConnector().rollBack();

            operationResult.setCode(OperationResult.ERROR);
            operationResult.setException(exception);
        }
        finally
        {
            Connector.getConnector().endTransaction();
            Connector.getConnector().closeConnection();
            this.operationResultView.showResult(operationResult);
        }
    }
}