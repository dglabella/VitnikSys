package vitniksys.backend.model.persistence;

import vitniksys.backend.model.entities.PreferentialClient;

public class BaseClientOperator extends PreferentialClientOperator
{
    private static BaseClientOperator operator;

    protected BaseClientOperator()
    {
        // Empty Constructor
    }

    public static BaseClientOperator getOperator()
    {
        if (BaseClientOperator.operator == null)
            BaseClientOperator.operator = new BaseClientOperator();

        return BaseClientOperator.operator;
    }

    @Override
    public int registerOrders(PreferentialClient cp) throws Exception
    {
        System.out.println("Agregando pedidos Cliente Preferencial Base");
        int ret = super.registerOrders(cp);

        BalanceOperator balanceOperator = BalanceOperator.getOperator();
        balanceOperator.update(balance);

        return 0;
    }
}