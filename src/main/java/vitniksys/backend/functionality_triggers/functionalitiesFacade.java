package vitniksys.backend.functionality_triggers;

import java.util.List;
import java.util.Iterator;
import java.sql.Connection;
import vitniksys.backend.model.*;
import vitniksys.backend.interfaces.*;
import vitniksys.backend.persistence.*;

/**
*This class implements the facade pattern design.
*View controllers should call methods in this class whenever it 
*tries to Trigger some vitniksys functionalities (uses cases)
*/
public class FunctionalitiesFacade implements IFunctionalitiesFacade
{
    private static FunctionalitiesFacade functionalities;

    private FunctionalitiesFacade()
    {
        //Empty Contructor
    }

    public static FunctionalitiesFacade getFunctionalities()
    {
        if(FunctionalitiesFacade.functionalities == null)
            FunctionalitiesFacade.functionalities = new FunctionalitiesFacade();

        return FunctionalitiesFacade.functionalities;
    }


    @Override
    public List<ClientePreferencial> obtenerPedidos(PedidosObtainer pedidosObtainer) throws Exception
    {
        pedidosObtainer.getInfo();
        return null;
    }

    @Override
    public int agregarPedidos(List<ClientePreferencial> cps) throws Exception
    {
        int returnCode = 0;
        Connection connection = Connector.getConnector().getConnection();
        try
        {
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