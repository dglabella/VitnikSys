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
}