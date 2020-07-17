package vitniksys.backend.model.persistence;

import vitniksys.backend.model.entities.ClientePreferencial;

public class ClienteBaseOperator extends ClientePreferencialOperator
{
    private static ClienteBaseOperator operator;

    private ClienteBaseOperator()
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
        return 0;
    }  
}