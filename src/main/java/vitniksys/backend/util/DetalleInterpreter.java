package vitniksys.backend.util;

import java.io.File;

public class DetalleInterpreter
{
    private static DetalleInterpreter interpreter;

    private DetalleInterpreter(){}    

    public static DetalleInterpreter getInterpretador()
    {
        if(interpreter == null)
        {
            interpreter = new DetalleInterpreter();
        }
        return interpreter;
    }

    public void interpretarDetalle(File detalle)
    {

    }
}