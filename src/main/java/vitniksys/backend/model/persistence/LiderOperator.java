package vitniksys.backend.model.persistence;

import vitniksys.backend.model.entities.ClientePreferencial;

public class LiderOperator extends ClientePreferencialOperator
{
    private static LiderOperator operator;

    private LiderOperator()
    {
        //Empty Constructor
    }

    public static LiderOperator getOperator()
    {
        if(LiderOperator.operator == null)
            LiderOperator.operator = new LiderOperator();

        return LiderOperator.operator;
    }

    @Override
    public int registerOrders(ClientePreferencial cp)
    {
        System.out.println("Agregando pedidos Lider");
        return 0;
    }
}