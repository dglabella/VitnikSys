package vitniksys.backend.business;

public enum FormaPago {
    EFECTIVO,DEPOSITO,TRANSFERENCIA,TARJETA,RAPIPAGO,CONVENIO,DEBITO,NA;
    
    public static FormaPago ConvertirIntAEnum(int val){
        FormaPago ret = null;
        
        switch (val){
            case 1:
                ret = EFECTIVO;
                break;
            case 2:
                ret = DEPOSITO;
                break;
            case 3:
                ret = TRANSFERENCIA;
                break;
            case 4:
                ret = TARJETA;
                break;
            case 5:
                ret = RAPIPAGO;
                break;
            case 6:
                ret = CONVENIO;
                break;
            case 7:
                ret = DEBITO;
                break;
            default:
                ret = NA;
        }
        
        return ret;
    }
    
    public static int ConvertirEnumAInt(FormaPago forma){
        int ret = 0;
        if(forma!=null){
            switch (forma){
                case EFECTIVO:
                    ret = 1;
                    break;
                case DEPOSITO:
                    ret = 2;
                    break;
                case TRANSFERENCIA:
                    ret = 3;
                    break;
                case TARJETA:
                    ret = 4;
                    break;
                case RAPIPAGO:
                    ret = 5;
                    break;
                case CONVENIO:
                    ret = 6;
                    break;
                case DEBITO:
                    ret = 7;
                    break;
                default:
                    ret = 0;
            }
        } 
        return ret;
    }  
}