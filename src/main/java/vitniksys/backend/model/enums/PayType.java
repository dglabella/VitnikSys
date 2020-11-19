package vitniksys.backend.model.enums;

public enum PayType
{
    EFECTIVO,DEPOSITO,TRANSFERENCIA,TARJETA,RAPIPAGO,CONVENIO,DEBITO,NA;
    
    public static PayType toEnum(int val)
    {
        PayType ret = null;
        
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
    
    public static int toInt(PayType type)
    {
        int ret = 0;

        if(type!=null){
            switch (type){
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