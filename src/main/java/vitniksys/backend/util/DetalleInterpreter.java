package vitniksys.backend.util;

import java.io.File;
import java.util.List;
import java.lang.Runnable;
import vitniksys.backend.model.Pedido;
import vitniksys.backend.interfaces.PedidosObtainer;

/**
*This class contains the algorithm for translate the information
*contained in Detalle.csv File
*/
public class DetalleInterpreter implements PedidosObtainer, Runnable
{
    private static DetalleInterpreter interpreter;

    //The file to be interpreted for gather the information of the incoming "pedidos".
    private File detalle;

    //This list contains all the incoming "pedidos".
    private List<Pedido> info;

    private DetalleInterpreter(File detalle)
    {
        this.detalle = detalle;
    }    

    /**
     * Create (if not exist) the instance for read the data from the
     * detalle.csv file.
     * @param detalle the .csv file to be read. This file is supposed to have
     * a default format for the interpreter, otherwise an error will be occur
     * and no data from the file can be obtained.
     * @return an interpreter instance.
     */
    public static DetalleInterpreter createInterpreter(File detalle)
    {
        if(interpreter == null)
        {
            interpreter = new DetalleInterpreter(detalle);
        }
        return interpreter;
    }

    // ================================= private methods =================================
    private List<Pedido> interpret()
    {
        for(int i = 0; i<1000; i++)
        {
            System.out.println(i);
        }
        return null;
    }

    // ================================= protected methods =================================


    // ================================= public methods =================================
    
    /**
     * This method should NOT be called for start the gathering information
     * process. Instead use "startGatheringInfo". Can be called from an
     * "PedidosObtainer" object.
     */
    @Override
    public void run()
    {
        this.info = interpret();
    }

    @Override
    public void startGatheringInfo()
    {
        this.run();
    }

    @Override
    public List<Pedido> getInfo()
    { 
        return this.info;
    }
}