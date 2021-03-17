package vitniksys.backend.util;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;
import java.util.ArrayList;
import vitniksys.backend.model.entities.Balance;
import vitniksys.backend.model.entities.Campaign;
import vitniksys.backend.model.persistence.Connector;
import vitniksys.backend.model.persistence.BalanceOperator;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.persistence.CampaignOperator;
import vitniksys.backend.util.DetailFileInterpreter;

public class CpLoader
{
    private File file;
    private List<CpLoadFileRow> cpLoadFileRow;

    private String lASTNAME_NAME_SEPARATOR = ",";

    private final int CP = 0;
    private final int LASTNAME_NAME = 1;
    private final int DNI = 2;

    public CpLoader(File file)
    {
        this.file = file;
        this.cpLoadFileRow = new ArrayList<>();
    }

    public void insertClientFromFile()
    {
        String [] splitedLine;
        String [] aux;

        try{
            Scanner inputStream = new Scanner(this.file);
            
            //Gathering all the lines in the file into primary memory (detailFileRows).
            while(inputStream.hasNext())
            {
                splitedLine = inputStream.nextLine().split(DetailFileInterpreter.SEPARATOR);
                aux = splitedLine[LASTNAME_NAME].split(lASTNAME_NAME_SEPARATOR);
                this.cpLoadFileRow.add(new CpLoadFileRow(splitedLine[CP], aux[0], aux[1], splitedLine[DNI]));
            }
            inputStream.close();



            List<PreferentialClient> prefClients = new ArrayList<>(); 
            CpLoadFileRow cpLFR;
            PreferentialClient prefClient;
            Balance balance;
            

            Iterator<CpLoadFileRow> it = this.cpLoadFileRow.iterator();
            while(it.hasNext())
            {
                cpLFR = it.next(); 
                prefClients.add(new PreferentialClient(cpLFR.getClientId(), cpLFR.getName(), cpLFR.getLastName()));
            }

            Connector.getConnector().startTransaction();

            Campaign camp = CampaignOperator.getOperator().findLast();

            Iterator<PreferentialClient> prefClientIterator = prefClients.iterator();
            while(prefClientIterator.hasNext())
            {
                prefClient = prefClientIterator.next();
                prefClient.operator().insert(prefClient);

                balance = new Balance();
                balance.setClient(prefClient);
                balance.setCamp(camp);

                BalanceOperator.getOperator().insert(balance);
            }

            Connector.getConnector().commit();

            Connector.getConnector().endTransaction();
            Connector.getConnector().closeConnection();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            orderMakers = null;
        }
    }
}