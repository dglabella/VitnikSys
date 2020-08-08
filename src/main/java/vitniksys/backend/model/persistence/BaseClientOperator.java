package vitniksys.backend.model.persistence;

import java.util.Iterator;
import vitniksys.backend.model.entities.Order;
import vitniksys.backend.model.entities.PreferentialClient;

public class BaseClientOperator extends PreferentialClientOperator
{
    private static BaseClientOperator operator;

    protected BaseClientOperator()
    {
        //Empty Constructor
    }

    public static BaseClientOperator getOperator()
    {
        if(BaseClientOperator.operator == null)
            BaseClientOperator.operator = new BaseClientOperator();

        return BaseClientOperator.operator;
    }

    @Override
    public int registerOrders(PreferentialClient cp)
    {
        System.out.println("Agregando pedidos Cliente Preferencial Base");

        Order incomingOrder;
        Iterator<Order> incomingOrdersIterator = cp.getIncomingOrders().iterator();

        while(incomingOrdersIterator.hasNext())
        {
            incomingOrder = incomingOrdersIterator.next();
            //
            //incomingOrder.getArticulo().
        }
        return 0;
    }  
}