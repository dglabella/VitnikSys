package vitniksys.backend.functionality_triggers;

import java.util.List;
import java.util.Iterator;
import java.sql.Connection;
import vitniksys.backend.model.*;
import java.util.concurrent.Future;
import vitniksys.backend.interfaces.*;
import vitniksys.backend.persistence.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import vitniksys.backend.util.PedidosObtainer;

public class Functionalities implements IFunctionalities
{
    private static Functionalities functionalities;

    private Future<List<ClientePreferencial>> customersWithNewOrders;

    private Functionalities()
    {
        //Empty constructor
    }

    public static Functionalities getFunctionalities()
    {
        if(Functionalities.functionalities == null)
            Functionalities.functionalities = new Functionalities();

        return Functionalities.functionalities;
    }

    @Override
    public void obtenerPedidos(PedidosObtainer pedidosObtainer) throws Exception
    {
        List<ClientePreferencial> ret = null;

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
        Connection connection = Connector.getConnector().getConnection();
        try{
            //START TRANSACTION
            connection.setAutoCommit(false);
            
            ClientePreferencial cp;
            ClientePreferencialOperator cpOperator;
            Iterator<ClientePreferencial> cpsIterator = cps.iterator();

            while(cpsIterator.hasNext())
            {
                cp = cpsIterator.next();
                cpOperator = cp.operator();
                returnCode = cpOperator.agregarPedidos(cp);
            }
            //COMMIT
            connection.commit();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            returnCode = -1;
        }
        finally
        {
            connection.setAutoCommit(true);
            connection.close();         
        }
        return returnCode;
    }
    
    @Override
    public int registrarVendedor(ClientePreferencial cp)
    {
        // TODO Auto-generated method stub
        return 0;
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