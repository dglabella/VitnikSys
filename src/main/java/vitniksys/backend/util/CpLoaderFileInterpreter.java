package vitniksys.backend.util;

import java.io.File;
import vitniksys.App;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import vitniksys.backend.model.entities.Leader;
import vitniksys.backend.model.entities.BaseClient;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.entities.SubordinatedClient;
import vitniksys.backend.model.business_logic.PreferentialClientBLService;

public class CpLoaderFileInterpreter extends FileInterpreter
{
    private String LASTNAME_NAME_SEPARATOR = ",";
    private String IS_LEADER_IDENTIFICATOR = "si";

    private final int IS_LEADER = 0;
    private final int CP = 1;
    private final int LASTNAME_NAME = 2;
    private final int DNI = 3;
    private final int LEADER_ID = 4;

    /**
     * 
     * @param file file the file wich contains the information of all preferentias clients.
     * @param service service for execute the use case.
     */
    public CpLoaderFileInterpreter(File file, PreferentialClientBLService service)
    {
        super(file, service);
    }

    @Override
    public void interpret()
    {
        String [] splitedLine;
        String [] aux;
        PreferentialClient preferentialClient;
        List<PreferentialClient> preferentialClients =  new ArrayList<>();
        
        try
        {
            Scanner inputStream = new Scanner(this.getFile());
            
            while(inputStream.hasNext())
            {
                splitedLine = inputStream.nextLine().split(App.ConstraitConstants.CSV_SEPARATOR);
                aux = splitedLine[LASTNAME_NAME].split(LASTNAME_NAME_SEPARATOR);
                
                if(splitedLine[IS_LEADER] != null && splitedLine[IS_LEADER].equals(IS_LEADER_IDENTIFICATOR))
                {
                    preferentialClient = new Leader(Integer.parseInt(splitedLine[CP]), aux[1].toUpperCase(), aux[0].toUpperCase());
                }
                else if(splitedLine.length > LEADER_ID)
                {
                    preferentialClient = new SubordinatedClient(Integer.parseInt(splitedLine[CP]), aux[1].toUpperCase(), aux[0].toUpperCase());
                    ((SubordinatedClient)preferentialClient).setLeaderId(Integer.parseInt(splitedLine[LEADER_ID]));
                }
                else
                {
                    preferentialClient = new BaseClient(Integer.parseInt(splitedLine[CP]), aux[1].toUpperCase(), aux[0].toUpperCase());
                }

                //attributes
                preferentialClient.setDni(Long.parseLong(splitedLine[DNI]));

                preferentialClients.add(preferentialClient); 
            }
            
            inputStream.close();

            ((PreferentialClientBLService)this.getService(0)).registerPrefClients(preferentialClients); 
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
