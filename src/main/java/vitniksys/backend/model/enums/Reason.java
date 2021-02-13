package vitniksys.backend.model.enums;

public enum Reason
{
  Falla_en_cierres_botones_accesorios, Fallas_de_confección, Fallas_en_tela_desteñimiento, Mal_envasado_etiquetado,
  Envío_equivocado, No_satisface_calce, No_satisface_producto, Producto_mal_pedido, Producto_difiere_con_tabla_medidas,
  Segunda_Factor_proceso_reciclaje, Taller_Factor_proceso_reciclaje, Lavadero_Factor_proceso_reciclaje, Otros, NA;
  
  public static Reason toEnum(int val)
  {
        Reason ret = null;
        switch (val)
        {
            case 1:
                ret = Falla_en_cierres_botones_accesorios;
                break;
            case 2:
                ret = Fallas_de_confección;
                break;
            case 3:
                ret = Fallas_en_tela_desteñimiento;
                break;
            case 4:
                ret = Mal_envasado_etiquetado;
                break;
            case 5:
                ret = Envío_equivocado;
                break;
            case 6:
                ret = No_satisface_calce;
                break;
            case 7:
                ret = No_satisface_producto;
                break;
            case 8:
                ret = Producto_mal_pedido;
                break;
            case 9:
                ret = Producto_difiere_con_tabla_medidas;
                break;
            case 10:
                ret = Segunda_Factor_proceso_reciclaje;
                break;
            case 11:
                ret = Taller_Factor_proceso_reciclaje;
                break;
            case 12:
                ret =  Lavadero_Factor_proceso_reciclaje;
                break; 
            case 13:
                ret = Otros;
            default:
                ret = NA;
        }
        return ret;
    }
}