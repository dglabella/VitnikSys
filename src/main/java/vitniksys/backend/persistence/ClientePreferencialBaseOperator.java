package vitniksys.backend.persistence;

import vitniksys.backend.model.ClientePreferencial;

public class ClientePreferencialBaseOperator extends ClientePreferencialOperator
{
    private static ClientePreferencialBaseOperator operator;

    private ClientePreferencialBaseOperator()
    {
        //Empty Constructor
    }

    public static ClientePreferencialBaseOperator getOperator()
    {
        if(ClientePreferencialBaseOperator.operator == null)
            ClientePreferencialBaseOperator.operator = new ClientePreferencialBaseOperator();

        return ClientePreferencialBaseOperator.operator;
    }

    @Override
    public int agregarPedidos(ClientePreferencial cp)
    {
        System.out.println("Agregando pedidos Cliente Preferencial Base");
        return 0;
    }  
}