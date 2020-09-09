package vitniksys.backend.util;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;
import java.util.ArrayList;
import vitniksys.backend.model.enums.TipoArt;
import vitniksys.backend.model.entities.Order;
import vitniksys.backend.model.entities.Leader;
import vitniksys.backend.model.entities.Article;
import vitniksys.backend.model.entities.Campaign;
import vitniksys.backend.model.entities.BaseClient;
import vitniksys.backend.model.entities.SubordinatedClient;
import vitniksys.backend.model.entities.PreferentialClient;

/**
*This class contains the algorithm for translate the information
*contained in Detalle.csv File
*/
public class DetailFileInterpreter extends OrderObtainer
{
    private static String SEPARATOR = ";";

    //The file to be interpreted for gather the information of the incoming orders.
    private File detailFile;
    private List<DetailFileRow> detailFileRows;

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
    /**
     * Creates an instance for read the data from the
     * detalle.csv file.
     * @param detailFile the .csv file to be read. This file is supposed to have
     * a default format for the interpreter, otherwise an error will be occur
     * and no data from the file can be obtained.
     * @return an interpreter instance.
     */
    public DetailFileInterpreter(File detailFile)
    {
        this.detailFile = detailFile;
        this.detailFileRows = new ArrayList<>();
    }

    // ================================= private methods =================================
    private ClientList getAssociatedLeaders()
    {
        DetailFileRow row;
        ClientList ret = new ClientList();
        Iterator<DetailFileRow> detailFileRowsIterator = this.detailFileRows.iterator();

        while(detailFileRowsIterator.hasNext())
        {
            row = detailFileRowsIterator.next();
            if(row.getLeaderId() != -1 && !ret.exist(row.getLeaderId()))
            {
                ret.add(new Leader(row.getLeaderId()));
            }  
        }
        System.out.println("================ Associated Leaders ================");
        Iterator<PreferentialClient> printList = ret.iterator();
        while(printList.hasNext())
        {
            System.out.println(printList.next().toString()); 
        }
        return ret;
    }

    private ClientList getOrderMakers(ClientList associatedLeaders)
    {
        Order order;
        Article article;
        PreferentialClient client;
        DetailFileRow row;
        ClientList ret = new ClientList();
        Iterator<DetailFileRow> detailFileRowsIterator = this.detailFileRows.iterator();

        SubordinatedClient subClient;

        while(detailFileRowsIterator.hasNext())
        {
            row = detailFileRowsIterator.next();
            if(!ret.exist(row.getClientId()))
            {
                if(row.getLeaderId() != -1)
                {
                    subClient = new SubordinatedClient(row.getClientId());
                    subClient.setLeader((Leader)associatedLeaders.get(associatedLeaders.locate(row.getLeaderId())));
                    ret.add(subClient);
                }
                else
                {
                    ret.add(new BaseClient(row.getClientId()));
                }
            }  
        }

        //Aux variable for optimize the process
        int lastId = -1;
        client = null;
        //reset iterator
        detailFileRowsIterator = this.detailFileRows.iterator();

        while(detailFileRowsIterator.hasNext())
        {
            row = detailFileRowsIterator.next();
            if(lastId != row.getClientId())
            {
                lastId = row.getClientId();
                client = ret.get(ret.locate(lastId));
            }
            
            //all orders are commissionable by default and it's supposed to
            //be checked manually by the user if some orders are not commissionable.
            order = new Order(row.getQuant(), row.getPrice(), true);
            article = new Article(row.getLetters(), row.getName(), TipoArt.inferType(row.getObs()), row.getUnitPrice());
            order.setArticle(article);
            order.setCampaign(new Campaign(row.getCampNumb()));

            client.getIncomingOrders().add(order);
        }

        System.out.println("================ Order Makers ================");
        Iterator<PreferentialClient> printList = ret.iterator();
        while(printList.hasNext())
        {
            System.out.println(printList.next().toString()); 
        }
        return ret;
    }

    // ================================= protected methods =================================

    // ================================= public methods = ================================
    @Override
    public void getInfo()
    {
        String [] splitedLine;
        
        ClientList orderMakers;
        ClientList associatedLeaders;

        try{
            Scanner inputStream = new Scanner(this.detailFile);
            
            //Gathering all the lines in the file into primary memory (detailFileRows).
            while(inputStream.hasNext())
            {
                splitedLine = inputStream.nextLine().split(DetailFileInterpreter.SEPARATOR);
                this.detailFileRows.add(new DetailFileRow(splitedLine[LEADER_ID], splitedLine[CLIENT_ID], 
                    splitedLine[DELIVERY_NUMBER], splitedLine[LETTERS], splitedLine[BARCODE], splitedLine[NAME], 
                    splitedLine[QUANT], splitedLine[UNIT_PRICE], splitedLine[DESC_CP], splitedLine[PRICE], 
                    splitedLine[AGENT_COMM], splitedLine[FINAL_PRICE], splitedLine[CAMP], splitedLine[OBS]));
            }
            inputStream.close();

            associatedLeaders = getAssociatedLeaders();
            orderMakers = getOrderMakers(associatedLeaders);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            orderMakers = null;
        }
    }
}