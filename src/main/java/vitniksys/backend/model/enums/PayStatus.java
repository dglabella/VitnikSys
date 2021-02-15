package vitniksys.backend.model.enums;

public enum PayStatus
{
    NA, RECIBIDO, ENVIADO, ENVIO_PENDIENTE, COBRADO, COBRO_PENDIENTE;
    
    public static PayStatus toEnum(int val)
    {
        PayStatus ret = null;
        
        switch (val)
        {
            case 0:
                ret = NA;
                break;
            case 1:
                ret = RECIBIDO;
                break;
            case 2:
                ret = ENVIADO;
                break;
            case 3:
                ret = ENVIO_PENDIENTE;
                break;
            case 4:
                ret = COBRADO;
                break;
            case 5:
                ret = COBRO_PENDIENTE;
                break;                
        }
        return ret;
    } 
}