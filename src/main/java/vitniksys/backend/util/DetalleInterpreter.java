package vitniksys.backend.util;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import vitniksys.backend.model.Lider;
import vitniksys.backend.model.ClienteBase;
import vitniksys.backend.util.PedidosObtainer;
import vitniksys.backend.model.ClienteSubordinado;
import vitniksys.backend.model.ClientePreferencial;

/**
*This class contains the algorithm for translate the information
*contained in Detalle.csv File
*/
public class DetailFileInterpreter extends PedidosObtainer
{
    private static DetailFileInterpreter interpreter;

    //The file to be interpreted for gather the information of the incoming "pedidos".
    private File detailsFile;

    private final int LEADER_ID = 0;
    private final int CLIENT_ID = 1;
    private final int DELIVERY_NUMBER = 2;
    private final int LETTERS = 3;
    private final int BARCODE = 4;
    private final int NAME = 5;
    private final int QUANT = 6;
    private final int UNIT_PRICE = 7;
    private final int DESC_CP = 8;
    private final int PRICE = 9;
    private final int AGENT_COMM = 10;
    private final int FINAL_PRICE = 11;
    private final int CAMP = 12;
    private final int OBS = 13;

    // ================================= Constructors =================================
    private DetailFileInterpreter(File detailsFile)
    {
        this.detailsFile = detailsFile;
    }

    // ================================= private methods =================================
    private List<ClienteSubordinado> getSubClients(File detailsFile)
    {
        String line;
        String [] splitedLine;
        ClienteSubordinado client;

        try{
            Scanner inputStream = new Scanner(this.detailsFile);
            System.out.println("================ Sub Clients ================");
            while(inputStream.hasNext())
            {
                line = inputStream.nextLine();
                System.out.println(line);
                splitedLine = line.split(";");

                if(!splitedLine[this.LEADER_ID].isEmpty())
                {
                    client = new ClienteSubordinado(splitedLine[this.CLIENT_ID]);
                }
            }
    
            inputStream.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private ClientList getLeaders(File detailsFile)
    {
        String line;
        String [] splitedLine;
        Lider leader;

        try{
            Scanner inputStream = new Scanner(this.detailsFile);
            System.out.println("================ Leaders ================");
            while(inputStream.hasNext())
            {
                line = inputStream.nextLine();
                System.out.println(line);
                splitedLine = line.split(";");


            }
    
            inputStream.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }  
    }

    private List<ClienteBase> getClientesBase(File detalle, ClientList leaders)
    {
        String line;
        String [] splitedLine;
        ClienteBase client;

        try{
            Scanner inputStream = new Scanner(this.detailsFile);
            System.out.println("================ Base Clients ================");
            while(inputStream.hasNext())
            {
                line = inputStream.nextLine();
                System.out.println(line);
                splitedLine = line.split(";");
                
                if(splitedLine[this.LEADER_ID].isEmpty() && !leaders.belongs(Integer.parseInt(splitedLine[this.LEADER_ID])))
                {
                    client = new ClienteBase(splitedLine[this.CLIENT_ID]);
                }
            }
    
            inputStream.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }  
    }

    // ================================= protected methods =================================

    // ================================= public methods = ================================

    /**
     * Create (if not exist) the instance for read the data from the
     * detalle.csv file.
     * @param detalle the .csv file to be read. This file is supposed to have
     * a default format for the interpreter, otherwise an error will be occur
     * and no data from the file can be obtained.
     * @return an interpreter instance.
     */
    public static DetailFileInterpreter createInterpreter(File detalle)
    {
        if(interpreter == null)
        {
            interpreter = new DetailFileInterpreter(detalle);
        }
        return interpreter;
    }

    @Override
    public List<ClientePreferencial> getInfo()
    {
        String line;
        String [] splitedLine;
        ClienteSubordinado clienteSub;
        ClienteBase clienteBase;
        Lider lider;
        List<ClienteSubordinado> clientesSubordinados = new ArrayList<>();
        List<ClienteBase> clientesBase = new ArrayList<>();
        List<Lider> lideres = new ArrayList<>();

        //return
        List<ClientePreferencial> ret = new ArrayList<>();

        try{
            Scanner inputStream = new Scanner(this.detailsFile);

            while(inputStream.hasNext())
            {
                
            }
    
            inputStream.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return ret;
    }
}