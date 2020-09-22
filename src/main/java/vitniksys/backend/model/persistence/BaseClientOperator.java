package vitniksys.backend.model.persistence;

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
}