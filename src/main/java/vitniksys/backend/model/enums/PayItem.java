package vitniksys.backend.model.enums;

public enum PayItem
{
    NA, PEDIDO, CATALAGO, RECOMPRA, PAGO_AJUSTE;

    public static PayItem toEnum(int val)
    {
        PayItem ret = null;

        switch (val)
        {
            case 0:
                ret = PayItem.NA;
                break;
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
        }

        return ret;
    }
}