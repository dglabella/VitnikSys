package vitniksys.backend.model;

public enum Banco
{
    NA,SANTANDER,NACION,ICBC,SUPERVILLE,HSBC,GALICIA;
    
    public static Banco ConvertirIntAEnum(int val){
        Banco ret = null;
        
        switch (val){
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
            default:
                ret = NA;
        }
        return ret;
    }
    
    public static int ConvertirEnumAInt(Banco banco){
        int ret = 0;
        if(banco!=null){
            switch (banco){
                case SANTANDER:
                    ret = 1;
                    break;
                case NACION:
                    ret = 2;
                    break;
                case ICBC:
                    ret = 3;
                    break;
                case SUPERVILLE:
                    ret = 4;
                    break;
                case HSBC:
                    ret = 5;
                    break;
                case GALICIA:
                    ret = 6;
                    break;
                case NA:
                    ret = 0;
            }
        } 
        return ret;
    }
}