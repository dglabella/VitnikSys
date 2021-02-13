package vitniksys.backend.model.enums;

public enum PayItem
{
    PEDIDO, CATALAGO, RECOMPRA, PAGO_AJUSTE, NA;

    public static PayItem toEnum(int val)
    {
        PayItem ret;

        switch (val)
        {
            case 1:
                ret = PayItem.PEDIDO;
                break;
            case 2:
                ret = PayItem.CATALAGO;
                break;
            case 3:
                ret = PayItem.RECOMPRA;
                break;
            case 4:
                ret = PayItem.PAGO_AJUSTE;
                break;
            default:
                ret = PayItem.NA;
        }

        return ret;
    }
}