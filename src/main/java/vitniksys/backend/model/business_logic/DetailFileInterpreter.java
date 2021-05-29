package vitniksys.backend.model.business_logic;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;
import java.util.ArrayList;
import vitniksys.backend.util.ClientList;
import vitniksys.backend.util.DetailFileRow;
import vitniksys.backend.util.FileInterpreter;
import vitniksys.backend.model.entities.Order;
import vitniksys.backend.model.entities.Leader;
import vitniksys.backend.model.enums.OrderType;
import vitniksys.backend.model.entities.Article;
import vitniksys.backend.model.entities.Balance;
import vitniksys.backend.model.entities.Campaign;
import vitniksys.backend.model.entities.BaseClient;
import vitniksys.backend.model.persistence.Connector;
import vitniksys.backend.model.persistence.BalanceOperator;
import vitniksys.backend.model.entities.SubordinatedClient;
import vitniksys.backend.model.entities.PreferentialClient;

/**
*This class contains the algorithm for translate the information
*contained in detail.csv File
*/
public class DetailFileInterpreter extends FileInterpreter
{
    private static final String SEPARATOR = ";";
    private static final int FIRST_LINES_TO_IGNORE = 6;

    //private static String SEPARATOR = ";";

    //The file to be interpreted for gather the information of the incoming orders.
    private List<DetailFileRow> detailFileRows;

    // private final int LEADER_ID = 0;
    // private final int CLIENT_ID = 1;
    // private final int DELIVERY_NUMBER = 2;
    // private final int LETTERS = 3;
    // private final int BARCODE = 4;
    // private final int NAME = 5;
    // private final int QUANT = 6;
    // private final int UNIT_PRICE = 7;
    // private final int DESC_CP = 8;
    // private final int PRICE = 9;
    // private final int AGENT_COMM = 10;
    // private final int FINAL_PRICE = 11;
    // private final int CAMP = 12;
    // private final int OBS = 13;

    private final int LEADER_ID = 0;
    private final int CLIENT_ID = 1;
    //private final int BLANK_1 = 2;
    private final int DELIVERY_NUMBER = 3;
    private final int COMP = 4;
    private final int LETTERS = 5;
    private final int BARCODE = 6;
    private final int NAME = 7;
    //private final int BLANK_2 = 8;
    private final int QUANT = 9;
    //private final int BLANK_3 = 10;
    private final int UNIT_PRICE = 11;
    private final int DESC_CP = 12;
    private final int PRICE = 13;
    private final int AGENT_COMM = 14;
    private final int FINAL_PRICE = 15;
    private final int CAMP = 16;
    private final int OBS = 17;

    // ================================= Constructors =================================
    /**
     * Creates an instance for read the data from the
     * detalle.csv file.
     * @param detailFile the .csv file to be read. This file is supposed to have
     * a default format for the interpreter, otherwise an error will be occur
     * and no data from the file can be obtained.
     * @return an interpreter instance.
     */
    public DetailFileInterpreter(File detailFile, CampaignBLService service)
    {
        super(detailFile, service);
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
        
        /*
        System.out.println("================ Associated Leaders ================");
        Iterator<PreferentialClient> printList = ret.iterator();
        while(printList.hasNext())
        {
            System.out.println(printList.next().toString()); 
        }
        */
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

        Leader leader;
        SubordinatedClient subClient;

        while(detailFileRowsIterator.hasNext())
        {
            row = detailFileRowsIterator.next();
            if(!ret.exist(row.getClientId()))
            {
                if(row.getLeaderId() == -1)
                {
                    ret.add(new BaseClient(row.getClientId()));
                }
                else 
                {
                    //This conditions is evaluated because a leader can be or not an ordermaker but also, in the detail, is "his own leader"
                    if (associatedLeaders.exist(row.getClientId()))
                    {
                        ret.add(associatedLeaders.get(associatedLeaders.locate(row.getClientId())));
                    }
                    else
                    {
                        subClient = new SubordinatedClient(row.getClientId());
                        leader = (Leader)associatedLeaders.get(associatedLeaders.locate(row.getLeaderId()));
                        subClient.setLeader(leader);
                        //leader.getSubordinates().add(subClient);
                        ret.add(subClient);
                    }
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
            order = new Order(row.getQuant(), row.getPrice());
            order.setDeliveryNumber(row.getDeliveryNumber());
            order.setType(OrderType.inferType(row.getObs()));
            order.setUnitPrice(row.getUnitPrice());
            article = new Article(row.getLetters(), row.getName());
            order.setArticle(article);
            order.setCampaign(new Campaign(row.getCampNumb()));
            order.setClient(client);
            order.setArticleId(article.getId());
            order.setCampNumber(row.getCampNumb());
            order.setPrefClientId(client.getId());
            client.getIncomingOrders().add(order);
        }

        /*
        System.out.println("================ Order Makers ================");
        Iterator<PreferentialClient> printList = ret.iterator();
        while(printList.hasNext())
        {
            System.out.println(printList.next().toString());
        }
        */
        return ret;
    }

    // ================================= protected methods =================================

    // ================================= public methods = ================================

    /**
     * Testing purpose
     */
    public void insertClientFromDetailFile()
    {
        String [] splitedLine;
        
        ClientList orderMakers;
        ClientList associatedLeaders;

        try
        {
            Scanner inputStream = new Scanner(this.getFile());
            
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

            PreferentialClient prefClient;
            Balance balance;
            Campaign camp = orderMakers.get(0).getIncomingOrders().get(0).getCampaign();
            Iterator<PreferentialClient> prefClientIterator = associatedLeaders.iterator();

            Connector.getInstance().startTransaction();

            while(prefClientIterator.hasNext())
            {
                prefClient = prefClientIterator.next();
                prefClient.setName("Test Name "+prefClient.getId());
                prefClient.setLastName("Test Lastname "+prefClient.getId());
                prefClient.operator().insert(prefClient);

                balance = new Balance();
                balance.setClient(prefClient);
                balance.setCamp(camp);

                BalanceOperator.getOperator().insert(balance);
            }

            prefClientIterator = orderMakers.iterator();

            while(prefClientIterator.hasNext())
            {
                prefClient = prefClientIterator.next();

                if(!associatedLeaders.exist(prefClient.getId()))
                {
                    prefClient.setName("Test Name "+prefClient.getId());
                    prefClient.setLastName("Test Lastname "+prefClient.getId());
                    prefClient.operator().insert(prefClient);

                    balance = new Balance();
                    balance.setClient(prefClient);
                    balance.setCamp(camp);

                    BalanceOperator.getOperator().insert(balance);
                }
            }

            Connector.getInstance().commit();

            Connector.getInstance().endTransaction();
            Connector.getInstance().closeConnection();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            orderMakers = null;
        }
    }

    @Override
    public void interpret() throws Exception
    {
        String [] splitedLine;
        
        ClientList orderMakers;
        ClientList associatedLeaders;
        
        Scanner inputStream = new Scanner(this.getFile());
        
        //ignoring trash lines from the file
        for(int i = 0; i < DetailFileInterpreter.FIRST_LINES_TO_IGNORE; i++)
        {
            inputStream.nextLine();
        }


        //Gathering all the lines in the file into primary memory (detailFileRows).
        while(inputStream.hasNext())
        {
            splitedLine = inputStream.nextLine().split(DetailFileInterpreter.SEPARATOR);

            // System.out.println(splitedLine[LEADER_ID]+" -- "+splitedLine[CLIENT_ID]+" -- "+splitedLine[DELIVERY_NUMBER]+
            //     " -- "+splitedLine[LETTERS]+" -- "+splitedLine[BARCODE]+" -- "+splitedLine[NAME]+" -- "+splitedLine[QUANT]+
            //     " -- "+splitedLine[UNIT_PRICE]+" -- "+splitedLine[DESC_CP]+" -- "+splitedLine[PRICE]+" -- "+
            //     splitedLine[AGENT_COMM]+" -- "+splitedLine[FINAL_PRICE]+" -- "+splitedLine[CAMP]+" -- "+splitedLine[OBS]);

            this.detailFileRows.add(new DetailFileRow(splitedLine[LEADER_ID], splitedLine[CLIENT_ID], 
                splitedLine[DELIVERY_NUMBER], splitedLine[LETTERS], splitedLine[BARCODE], splitedLine[NAME], 
                splitedLine[QUANT], splitedLine[UNIT_PRICE], splitedLine[DESC_CP], splitedLine[PRICE], 
                splitedLine[AGENT_COMM], splitedLine[FINAL_PRICE], splitedLine[CAMP], splitedLine[OBS]));
        }
        inputStream.close();

        associatedLeaders = getAssociatedLeaders();
        orderMakers = getOrderMakers(associatedLeaders);

        ((CampaignBLService)this.getService(0)).registerIncomingOrders(orderMakers);
    }
}
