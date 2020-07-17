package vitniksys.backend.controllers;

import java.util.List;
import java.util.Iterator;
import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import vitniksys.backend.model.interfaces.*;
import vitniksys.backend.model.persistence.*;
import vitniksys.backend.util.PedidosObtainer;
import vitniksys.backend.model.entities.ClientePreferencial;

public class OrderController
{
    private Future<List<ClientePreferencial>> customersWithNewOrders;

    public Future<List<ClientePreferencial>> getCustomersWithNewOrders()
    {
        return this.customersWithNewOrders;
    }

    public void obtainOrders(PedidosObtainer pedidosObtainer) throws Exception
    {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        this.customersWithNewOrders = executorService.submit(pedidosObtainer);
    }

    public int registerOrders(List<ClientePreferencial> cps) throws Exception
    {
        int returnCode = 0;
        try{
            Connector.getConnector().startTransaction();
            
            ClientePreferencial cp;
            ClientePreferencialOperator cpOperator;
            Iterator<ClientePreferencial> cpsIterator = cps.iterator();

            while(cpsIterator.hasNext())
            {
                cp = cpsIterator.next();
                cpOperator = cp.operator();
                returnCode = cpOperator.registerOrders(cp);
            }
            
           Connector.getConnector().commit();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            returnCode = -1;
        }
        finally
        {
            Connector.getConnector().endTransaction();
            Connector.getConnector().closeConnection();
        }
        return returnCode;
    }
}