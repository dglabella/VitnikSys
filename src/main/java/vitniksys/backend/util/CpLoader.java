package vitniksys.backend.util;

public class CpLoader
{
    private File file;

    public CpLoader(File file)
    {
        this.file = file;
    }

    public void insertClientFromFile()
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

            PreferentialClient prefClient;
            Balance balance;
            Campaign camp = orderMakers.get(0).getIncomingOrders().get(0).getCampaign();
            Iterator<PreferentialClient> prefClientIterator = associatedLeaders.iterator();

            Connector.getConnector().startTransaction();

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