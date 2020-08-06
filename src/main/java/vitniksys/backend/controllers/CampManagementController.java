package vitniksys.backend.controllers;

import java.util.List;
import java.util.Iterator;
import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import vitniksys.backend.util.OperationResult;
import vitniksys.backend.util.PedidosObtainer;
import vitniksys.frontend.views.OperationResultView;
import vitniksys.backend.model.persistence.Connector;
import vitniksys.backend.model.entities.Camp;
import vitniksys.backend.model.entities.ClientePreferencial;
import vitniksys.backend.model.persistence.ClientePreferencialOperator;

public class CampManagementController
{
    //The list for save the result of "pedidosObtainer".
    //only used when detail file is loaded.
    private Future<List<ClientePreferencial>> customersWithNewOrders;

    //Views
    private OperationResultView operationResultView;

    //Getters && Setters
    public OperationResultView getOperationResultView()
    {
        return this.operationResultView;
    }

    public void setOperationResultView(OperationResultView operationResultView)
    {
        this.operationResultView = operationResultView;
    }
    
    public Future<List<ClientePreferencial>> getCustomersWithNewOrders()
    {
        return this.customersWithNewOrders;
    }


    // ================================= private methods =================================

    // ================================= protected methods =================================

    // ================================= public methods =================================
    public void searchCamp()
    {
        OperationResult operationResult = new OperationResult();
        try
        {
            
        }
        catch (Exception exception)
        {
            //TODO: handle exception
        }
    }

    public void obtainOrders(PedidosObtainer pedidosObtainer) throws Exception
    {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        this.customersWithNewOrders = executorService.submit(pedidosObtainer);
    }

    public void registerOrders(List<ClientePreferencial> cps) throws Exception
    {
        OperationResult operationResult = new OperationResult();
        try{
            Connector.getConnector().startTransaction();
            
            ClientePreferencial cp;
            ClientePreferencialOperator cpOperator;
            Iterator<ClientePreferencial> cpsIterator = cps.iterator();

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