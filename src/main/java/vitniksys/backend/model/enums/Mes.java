package vitniksys.backend.model.enums;

public enum Mes
{
    NA, ENERO, FEBRERO, MARZO, ABRIL, MAYO, JUNIO, JULIO, AGOSTO, SEPTIEMBRE, OCTUBRE, NOVIEMBRE, DICIEMBRE;

    public static Mes ConvertIntToEnum(int val){
        Mes ret = null;
        switch (val){
            case 1:
                ret = ENERO;
                break;
            case 2:
                ret = FEBRERO;
                break;
            case 3:
                ret = MARZO;
                break;
            case 4:
                ret = ABRIL;
                break;
            case 5:
                ret = MAYO;
                break;
            case 6:
                ret = JUNIO;
                break;
            case 7:
                ret = JULIO;
                break;
            case 8:
                ret = AGOSTO;
                break;
            case 9:
                ret = SEPTIEMBRE;
                break;
            case 10:
                ret = OCTUBRE;
                break;
            case 11:
                ret = NOVIEMBRE;
                break;
            case 12:
                ret = DICIEMBRE;
                break;
            default:
                ret = NA;
        }
        return ret;
    }

    public static int ConvertEnumToInt(Mes mes){
        int ret = 0;
        if(mes!=null){
            switch (mes){
                case ENERO:
                    ret = 1;
                    break;
                case FEBRERO:
                    ret = 2;
                    break;
                case MARZO:
                    ret = 3;
                    break;
                case ABRIL:
                    ret = 4;
                    break;
                case MAYO:
                    ret = 5;
                    break;
                case JUNIO:
                    ret = 6;
                    break;
                case JULIO:
                    ret = 7;
                    break;
                case AGOSTO:
                    ret = 8;
                    break;
                case SEPTIEMBRE:
                    ret = 9;
                    break;
                case OCTUBRE:
                    ret = 10;
                    break;
                case NOVIEMBRE:
                    ret = 11;
                    break;
                case DICIEMBRE:
                    ret = 12;
                    break;
                default:
                    ret = 0;
            }
        }
        return ret;
    }
}