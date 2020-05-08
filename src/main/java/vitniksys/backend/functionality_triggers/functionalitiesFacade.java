package vitniksys.backend.functionality_triggers;

import java.util.List;
import java.util.Iterator;
import java.sql.Connection;
import vitniksys.backend.model.*;
import vitniksys.backend.persistence.*;
import vitniksys.backend.interfaces.*;

public class functionalitiesFacade implements IfunctionalitiesFacade
{
    public int agregarPedidos(List<ClientePreferencial> clientesPreferenciales) throws Exception
    {
        int returnCode = 1;
        Connection connection = Connector.getConnector().getConnection();
        try
        {
            //START TRANSACTION
            connection.setAutoCommit(false);
            Iterator<ClientePreferencial> clientesPreferencialesIterator = clientesPreferenciales.iterator();
            IClientePreferencialOperator clientePreferencialOperator = new ClientePreferencialOperator();

            //aux variable
            ClientePreferencial clientePreferencial = null;
            
            while(clientesPreferenciales.hasNext())
            {
                clientePreferencial = clientesPreferenciales.next();
                clientePreferencial.
                clientePreferencialOperator.insert(un pedido);
            }
            //COMMIT
            connection.commit();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            returnCode = 0;
        }
        finally
        {
            connection.setAutoCommit(true);
            connection.close();         
        }
        return returnCode;
    }
    
    @Override
    public int registrarVendedor(ClientePreferencial cp) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int registrarRecompras(List<Recompra> recs) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int registrarDevoluciones(List<Devolucion> devs) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Camp consultarUltimaCamp() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Camp consultarCamp() {
        // TODO Auto-generated method stub
        return null;
    }
}