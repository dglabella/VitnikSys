package vitniksys.backend.model.enums;

public enum Bank
{
    NA, SANTANDER, NACION, ICBC, SUPERVILLE, HSBC, GALICIA;
    
    public static Bank toEnum(int val)
    {
        Bank ret = null;
        
        switch (val)
        {
            case 0:
                ret = NA;
                break;
            case 1:
                ret = SANTANDER;
                break;
            case 2:
                ret = NACION;
                break;
            case 3:
                ret = ICBC;
                break;
            case 4:
                ret = SUPERVILLE;
                break;
            case 5:
                ret = HSBC;
                break;
            case 6:
                ret = GALICIA;
                break;
        }
        return ret;
    }
}