package vitniksys.backend.persistence;

import vitniksys.backend.model.ClientePreferencial;

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
    public int agregarPedidos(ClientePreferencial cp)
    {
        System.out.println("Agregando pedidos Cliente Preferencial Base");
        return 0;
    }  
}