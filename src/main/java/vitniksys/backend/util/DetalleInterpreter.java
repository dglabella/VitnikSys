package vitniksys.backend.util;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
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

    private final int ID_LIDER = 0;
    private final int ID_CLIENTE = 1;
    private final int NRO_ENVIO = 2;
    private final int LETRA = 3;
    private final int COD_BARRA = 4;
    private final int NOMBRE = 5;
    private final int CANT = 6;
    private final int PRECIO_UNIT = 7;
    private final int DESC_CP = 8;
    private final int PRECIO = 9;
    private final int COM_AGENTE = 10;
    private final int PRECIO_FINAL = 11;
    private final int CAMP = 12;
    private final int OBS = 13;

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
        String line;
        String [] splitedLine;
        List<ClientePreferencial> customersWithNewOrders = new ArrayList<>();
        // for(int i = 0; i<100000; i++)
        // {
        //     customersWithNewOrders.add(new ClienteBase(i, "Name"+i, "LastName"+i));
        //     System.out.println(customersWithNewOrders.get(i).toString());
        // }
        try{
            Scanner inputStream = new Scanner(this.detalle);

            while(inputStream.hasNext())
            {
                line = inputStream.nextLine();
                System.out.println(line);
                splitedLine = line.split(";");
                for(int i = 0; i< splitedLine.length; i++)
                {
                    System.out.println(splitedLine[i]);
                }
            }

            inputStream.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return customersWithNewOrders;
    }
}