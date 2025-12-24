package vitniksys.backend.model.enums;

public enum OrderType
{
    NA, PEDIDO, OPORTUNIDAD, FREEPREMIUM, PROMO, CALZADO;

    /**
     * This method should be used to infer the type from 
     * the "observation" column in the detail file (.csv).
     * @param detailObs the observation in the detail file 
     * @return the representative enum type for that observation 
     */
    public static OrderType inferType(String detailObs)
    {
        OrderType ret;

        if(detailObs.contains("Free Premiums"))
        {
            ret = OrderType.FREEPREMIUM;
        }
        else if(detailObs.contains("Oportunidad"))
        {
            ret = OrderType.OPORTUNIDAD;   
        }
        else if(detailObs.contains("PR"))
        {
            ret = OrderType.PROMO;
        }
        else if(detailObs.contains("Calzado"))
        {
            ret = OrderType.CALZADO;
        }
        else
        {
            ret = OrderType.PEDIDO;
        }

        return ret;
    }
}