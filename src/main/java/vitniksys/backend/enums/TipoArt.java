package vitniksys.backend.enums;

public enum TipoArt
{
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

    /**
     * This method should be used to infer the type from 
     * the "observation" column in the detail file (.csv).
     * @param detailObs the observation in the detail file 
     * @return the representative enum type for that observation 
     */
    public static TipoArt inferType(String detailObs)
    {
        TipoArt ret;

        if(detailObs.contains("Free Premiums"))
        {
            ret = TipoArt.FREEPREMIUM;
        }
        else if(detailObs.contains("Oportunidad"))
        {
            ret = TipoArt.OPORTUNIDAD;   
        }
        else if(detailObs.contains("PR"))
        {
            ret = TipoArt.PROMO;
        }
        else if(detailObs.contains("Calzado"))
        {
            ret = TipoArt.CALZADO;
        }
        else
        {
            ret = TipoArt.PEDIDO;
        }

        return ret;
    }
}