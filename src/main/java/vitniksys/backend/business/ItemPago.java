package vitniksys.backend.business;

public enum ItemPago {
    PEDIDO, CATALAGO, RECOMPRA, NA;

    public static ItemPago ConvertirIntAEnum(int val) {
        ItemPago ret;
        switch (val){
            case 1:
                ret = PEDIDO;
                break;
            case 2:
                ret = CATALAGO;
                break;
            case 3:
                ret = RECOMPRA;
                break;
            default:
                ret = NA;
        }
        return ret;
    }

    public static int ConvertirEnumAInt(ItemPago item) {
        int ret;
        switch (item){
            case PEDIDO:
                ret = 1;
                break;
            case CATALAGO:
                ret = 2;
                break;
            case RECOMPRA:
                ret = 3;
                break;
            default:
                ret = 0;
        }
        return ret;
    }
}