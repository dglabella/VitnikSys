package vitniksys.backend.business;

public enum TipoArt {
    PEDIDO, FREEPREMIUM, CALZADO, PROMO, OPORTUNIDAD, NA;
    
    public static TipoArt ConvertirIntAEnum(int val){
        TipoArt ret = null; 
        switch (val){
            case 1:
                ret = PEDIDO;
                break;
            case 2:
                ret = PROMO;
                break;
            case 3:
                ret = FREEPREMIUM;
                break;
            case 4:
                ret = CALZADO;
                break;
            case 5:
                ret = OPORTUNIDAD;
                break;
            default:
                ret = NA;
        }
        return ret;
    }
    
    public static int ConvertirEnumAInt(TipoArt tipo){
        int ret = 0;
        if(tipo!=null){
            switch (tipo){
                case PEDIDO:
                    ret = 1;
                    break;
                case PROMO:
                    ret = 2;
                    break;
                case FREEPREMIUM:
                    ret = 3;
                    break;
                case CALZADO:
                    ret = 4;
                    break;
                case OPORTUNIDAD:
                    ret = 5;
                    break;
                case NA:
                    ret = 0;
                    break;
            }
        } 
        return ret;
    }
}