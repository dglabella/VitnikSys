package vitniksys.backend.util;

import java.io.File;
import java.util.List;
import vitniksys.backend.model.Pedido;
import vitniksys.backend.interfaces.PedidosObtainer;

/**
*This class contains the algorithm for traduct the information
*contained in Detalle File
*/
public class DetalleInterpreter implements PedidosObtainer
{
    private static DetalleInterpreter interpreter;

    private File detalle;

    private DetalleInterpreter(File detalle)
    {
        this.detalle = detalle;
    }    

    public static DetalleInterpreter createInterpreter(File detalle)
    {
        if(interpreter == null)
        {
            interpreter = new DetalleInterpreter(detalle);
        }
        return interpreter;
    }

    @Override
    public List<Pedido> getInfo()
    { 
        return interpret();
    } 

    private List<Pedido> interpret()
    {
        return null;
    }
}