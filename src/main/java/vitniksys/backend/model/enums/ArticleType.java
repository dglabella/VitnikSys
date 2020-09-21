package vitniksys.backend.model.enums;

public enum ArticleType
{
    PEDIDO, FREEPREMIUM, CALZADO, PROMO, OPORTUNIDAD, NA;
    
    public static ArticleType IntToEnum(int val)
    {
        ArticleType ret = null; 
        switch (val)
        {
            case 1:
                ret = ArticleType.PEDIDO;
                break;
            case 2:
                ret = ArticleType.PROMO;
                break;
            case 3:
                ret = ArticleType.FREEPREMIUM;
                break;
            case 4:
                ret = ArticleType.CALZADO;
                break;
            case 5:
                ret = ArticleType.OPORTUNIDAD;
                break;
            default:
                ret = NA;
        }
        return ret;
    }
    
    public static int EnumToInt(ArticleType articleType)
    {
        int ret = 0;
        if(articleType!=null)
        {
            switch (articleType)
            {
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
    public static ArticleType inferType(String detailObs)
    {
        ArticleType ret;

        if(detailObs.contains("Free Premiums"))
        {
            ret = ArticleType.FREEPREMIUM;
        }
        else if(detailObs.contains("Oportunidad"))
        {
            ret = ArticleType.OPORTUNIDAD;   
        }
        else if(detailObs.contains("PR"))
        {
            ret = ArticleType.PROMO;
        }
        else if(detailObs.contains("Calzado"))
        {
            ret = ArticleType.CALZADO;
        }
        else
        {
            ret = ArticleType.PEDIDO;
        }

        return ret;
    }
}