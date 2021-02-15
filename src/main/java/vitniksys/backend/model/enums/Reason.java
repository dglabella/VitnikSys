package vitniksys.backend.model.enums;

public enum Reason
{
    NA, FALLA_EN_CIERRES_BOTONES_ACCESORIOS, FALLAS_DE_CONFECCIÓN, FALLAS_EN_TELA_DESTEÑIMIENTO, MAL_ENVASADO_ETIQUETADO,
    ENVÍO_EQUIVOCADO, NO_SATISFACE_CALCE, NO_SATISFACE_PRODUCTO, PRODUCTO_MAL_PEDIDO, PRODUCTO_DIFIERE_CON_TABLA_MEDIDAS,
    SEGUNDA_FACTOR_PROCESO_RECICLAJE, TALLER_FACTOR_PROCESO_RECICLAJE, LAVADERO_FACTOR_PROCESO_RECICLAJE, OTROS;
  
  public static Reason toEnum(int val)
  {
        Reason ret = null;
        switch (val)
        {
            case 0:
                ret = NA;
                break;
            case 1:
                ret = FALLA_EN_CIERRES_BOTONES_ACCESORIOS;
                break;
            case 2:
                ret = FALLAS_DE_CONFECCIÓN;
                break;
            case 3:
                ret = FALLAS_EN_TELA_DESTEÑIMIENTO;
                break;
            case 4:
                ret = MAL_ENVASADO_ETIQUETADO;
                break;
            case 5:
                ret = ENVÍO_EQUIVOCADO;
                break;
            case 6:
                ret = NO_SATISFACE_CALCE;
                break;
            case 7:
                ret = NO_SATISFACE_PRODUCTO;
                break;
            case 8:
                ret = PRODUCTO_MAL_PEDIDO;
                break;
            case 9:
                ret = PRODUCTO_DIFIERE_CON_TABLA_MEDIDAS;
                break;
            case 10:
                ret = SEGUNDA_FACTOR_PROCESO_RECICLAJE;
                break;
            case 11:
                ret = TALLER_FACTOR_PROCESO_RECICLAJE;
                break;
            case 12:
                ret =  LAVADERO_FACTOR_PROCESO_RECICLAJE;
                break; 
            case 13:
                ret = OTROS;
        }
        return ret;
    }
}