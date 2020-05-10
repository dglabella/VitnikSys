package vitniksys.backend.persistence;

import java.util.List;
import vitniksys.backend.model.ClientePreferencial;

public class ClientePreferencialSubordinadoOperator extends ClientePreferencialOperator
{
    private static ClientePreferencialSubordinadoOperator operator;

    private ClientePreferencialSubordinadoOperator()
    {
        //Empty Constructor
    }

    public static ClientePreferencialSubordinadoOperator getOperator()
    {
        if(ClientePreferencialSubordinadoOperator.operator == null)
            ClientePreferencialSubordinadoOperator.operator = new ClientePreferencialSubordinadoOperator();

        return ClientePreferencialSubordinadoOperator.operator;
    }

    @Override
    public int agregarPedidos(ClientePreferencial cp)
    {
        System.out.println("Agregando pedidos Cliente Preferencial Subordinado");
        return 0;
    }
}