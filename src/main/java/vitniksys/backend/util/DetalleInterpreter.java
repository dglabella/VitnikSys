package vitniksys.backend.util;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import vitniksys.backend.model.ClienteBase;
import vitniksys.backend.util.PedidosObtainer;
import vitniksys.backend.model.ClientePreferencial;

/**
*This class contains the algorithm for translate the information
*contained in Detalle.csv File
*/
public class DetalleInterpreter extends PedidosObtainer
{
    private static DetalleInterpreter interpreter;

    //The file to be interpreted for gather the information of the incoming "pedidos".
    private File detalle;

    // ================================= Constructors =================================
    private DetalleInterpreter(File detalle)
    {
        this.detalle = detalle;
    }    

    // ================================= private methods =================================

    // ================================= protected methods =================================

    // ================================= public methods =================================

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

    @Override
    public List<ClientePreferencial> getInfo()
    {
        List<ClientePreferencial> customersWithNewOrders = new ArrayList<>();
        
        for(int i = 0; i<100000; i++)
        {
            customersWithNewOrders.add(new ClienteBase(i, "Name"+i, "LastName"+i));
            System.out.println(customersWithNewOrders.get(i).toString());
        }
        return customersWithNewOrders;
    }
}