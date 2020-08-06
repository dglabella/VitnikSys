package vitniksys.backend.model.persistence;

import java.util.Iterator;
import vitniksys.backend.model.entities.Pedido;
import vitniksys.backend.model.entities.ClientePreferencial;

public class ClienteBaseOperator extends ClientePreferencialOperator
{
    private static ClienteBaseOperator operator;

    protected ClienteBaseOperator()
    {
        //Empty Constructor
    }

    public static ClienteBaseOperator getOperator()
    {
        if(ClienteBaseOperator.operator == null)
            ClienteBaseOperator.operator = new ClienteBaseOperator();

        return ClienteBaseOperator.operator;
    }

    @Override
    public int registerOrders(ClientePreferencial cp)
    {
        System.out.println("Agregando pedidos Cliente Preferencial Base");

        Pedido incomingOrder;
        Iterator<Pedido> incomingOrdersIterator = cp.getIncomingOrders().iterator();

        while(incomingOrdersIterator.hasNext())
        {
            incomingOrder = incomingOrdersIterator.next();
            //incomingOrder.getArticulo().
        }
        return 0;
    }  
}