package vitniksys.backend.functionality_triggers;

import java.util.List;
import java.util.Iterator;
import vitniksys.backend.model.*;
import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import vitniksys.backend.interfaces.*;
import vitniksys.backend.persistence.*;
import java.util.concurrent.ExecutorService;
import vitniksys.backend.util.PedidosObtainer;

public class Functionalities implements IFunctionalities
{
    private Future<List<ClientePreferencial>> customersWithNewOrders;

    public Functionalities()
    {
        //Empty constructor
    }

    @Override
    public void obtenerPedidos(PedidosObtainer pedidosObtainer) throws Exception
    {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        this.customersWithNewOrders = executorService.submit(pedidosObtainer);
    }
    
    @Override
    public Future<List<ClientePreferencial>> getCustomersWithNewOrders()
    {
        return this.customersWithNewOrders;
    }

    @Override
    public int agregarPedidos(List<ClientePreferencial> cps) throws Exception
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
                returnCode = cpOperator.agregarPedidos(cp);
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
    
    @Override
    public int registrarCliente(ClientePreferencial cp) throws Exception
    {
        int returnCode = 0;
        try
        {
            Connector.getConnector().startTransaction();

            cp.operator().insert(cp);

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

    @Override
    public int registrarRecompras(List<Recompra> recs)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int registrarDevoluciones(List<Devolucion> devs)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Camp consultarUltimaCamp()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Camp consultarCamp()
    {
        // TODO Auto-generated method stub
        return null;
    }
}