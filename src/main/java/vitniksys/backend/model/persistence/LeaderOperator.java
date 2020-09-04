package vitniksys.backend.model.persistence;

import vitniksys.backend.model.entities.PreferentialClient;

public class LeaderOperator extends BaseClientOperator
{
    private static LeaderOperator operator;

    private LeaderOperator()
    {
        super();
    }

    public static LeaderOperator getOperator()
    {
        if(LeaderOperator.operator == null)
            LeaderOperator.operator = new LeaderOperator();

        return LeaderOperator.operator;
    }

    @Override
    public int registerOrders(PreferentialClient cp)
    {
        System.out.println("Agregando pedidos Lider");
        return 0;
    }
}