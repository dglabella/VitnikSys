package vitniksys.backend.model.enums;

public enum ArticleType
{
    NA, PEDIDO, PROMO, FREEPREMIUM, CALZADO, OPORTUNIDAD;
    
    public static ArticleType toEnum(int val)
    {
        ArticleType ret = null; 
        switch (val)
        {
            case 0:
                ret = NA;
                break;
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