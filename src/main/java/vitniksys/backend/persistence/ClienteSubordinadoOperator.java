package vitniksys.backend.persistence;

import java.util.List;
import vitniksys.backend.model.ClientePreferencial;

public class ClienteSubordinadoOperator extends ClientePreferencialOperator
{
    private static ClienteSubordinadoOperator operator;

    private ClienteSubordinadoOperator()
    {
        //Empty Constructor
    }

    public static ClienteSubordinadoOperator getOperator()
    {
        if(ClienteSubordinadoOperator.operator == null)
            ClienteSubordinadoOperator.operator = new ClienteSubordinadoOperator();

        return ClienteSubordinadoOperator.operator;
    }

    @Override
    public int agregarPedidos(ClientePreferencial cp)
    {
        System.out.println("Agregando pedidos Cliente Preferencial Subordinado");
        return 0;
    }
}