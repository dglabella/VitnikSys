package vitniksys.backend.model;

public enum Motivo {
  Falla_en_cierres_botones_accesorios, Fallas_de_confección, Fallas_en_tela_desteñimiento, Mal_envasado_etiquetado,
  Envío_equivocado, No_satisface_calce, No_satisface_producto, Otros, Producto_mal_pedido, Producto_difiere_con_tabla_medidas,
  Segunda_Factor_proceso_reciclaje, Taller_Factor_proceso_reciclaje, Lavadero_Factor_proceso_reciclaje;
  
  public static Motivo ConvertirIntAEnum(int val)
  {
        Motivo ret = null;
        switch (val){
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
        }
        return ret;
    }
  
  public static int ConvertirEnumAInt(Motivo motivo){
        int ret = 0;
        if(motivo!=null){
            switch (motivo){
            case Falla_en_cierres_botones_accesorios:
                ret = 1;
                break;
            case Fallas_de_confección:
                ret = 2;
                break;
            case Fallas_en_tela_desteñimiento:
                ret = 3;
                break;
            case Mal_envasado_etiquetado:
                ret = 4;
                break;
            case Envío_equivocado:
                ret = 5;
                break;
            case No_satisface_calce:
                ret = 6;
                break;
            case No_satisface_producto:
                ret = 7;
                break;
            case Producto_mal_pedido:
                ret = 8;
                break;
            case Producto_difiere_con_tabla_medidas:
                ret = 9;
                break;
            case Segunda_Factor_proceso_reciclaje:
                ret = 10;
                break;
            case Taller_Factor_proceso_reciclaje:
                ret = 11;
                break;
            case Lavadero_Factor_proceso_reciclaje:
                ret =  12;
                break; 
            case Otros:
                ret =  13;
                break; 
            }
        } 
        return ret;
    }
}