package vitniksys.backend.model.persistence;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.time.ZoneId;
import java.util.HashMap;
import java.time.Instant;
import java.util.Iterator;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import vitniksys.backend.util.ClientList;
import vitniksys.backend.model.entities.Order;
import vitniksys.backend.model.entities.Leader;
import vitniksys.backend.model.entities.Article;
import vitniksys.backend.model.entities.Balance;
import vitniksys.backend.model.entities.BaseClient;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.entities.SubordinatedClient;
import vitniksys.backend.model.interfaces.IPreferentialClientOperator;


public abstract class PreferentialClientOperator implements IPreferentialClientOperator
{
    protected boolean activeRow;
    
    /**
     * Get the flag state with which the DAO operator performs a CRUD operation.
     * Ignore this if it not exist an implementation for active or inactive rows in
     * your Data Base. Default value: true.
     * 
     * @return The state of the entity.
     */
    public boolean isActiveRow()
    {
        return this.activeRow;
    }

    @Override
    public int update(PreferentialClient prefClient)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * Find all the preferential Clients.
     * @param activeRow is the value to filter if the row is active or not in the DB.
     * @return A list of preferential Clients
     * @throws Exception may throw SQLException.
     */
    public static List<PreferentialClient> findAllPrefClients(Boolean activeRow) throws Exception
    {
        ClientList ret = new ClientList();
        String sqlStmnt =
        "SELECT `id_cp`, `id_lider`, `dni`, `nombre`, `apellido`, `lugar`, `fecha_nac`, `email`, `tel`, `es_lider`"+
        "FROM `clientes_preferenciales` "+
        "WHERE `active_row` = ? "+
        "ORDER BY `es_lider` DESC;";

        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);
        statement.setBoolean(1, activeRow);

        ResultSet resultSet = statement.executeQuery();

        PreferentialClient prefClient;

        while(resultSet.next())
        {
            //if it is leader
            if(resultSet.getBoolean(10))
            {
                prefClient = new Leader(resultSet.getInt(1), resultSet.getString(4), resultSet.getString(5));
            }
            else
            {
                int leaderId = resultSet.getInt(2);
                if(!resultSet.wasNull())
                {
                    prefClient = new SubordinatedClient(resultSet.getInt(1), resultSet.getString(4), resultSet.getString(5));
                    ((SubordinatedClient)prefClient).setLeader((Leader)ret.get(ret.locate(leaderId)));
                }
                else
                {
                    prefClient = new BaseClient(resultSet.getInt(1), resultSet.getString(4), resultSet.getString(5));
                }
            }

            prefClient.setDni(resultSet.getLong(3));
            prefClient.setLocation(resultSet.getString(6));
            Date date = resultSet.getDate(7);
            if(!resultSet.wasNull())
            {
                prefClient.setBirthDate(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            }
            prefClient.setEmail(resultSet.getString(8));
            prefClient.setPhoneNumber(resultSet.getLong(9));

            ret.add(prefClient);
        }

        /*
        System.out.println("================ prefClients ================");
        Iterator<PreferentialClient> printList = ret.iterator();
        while(printList.hasNext())
        {
            System.out.println(printList.next().toString());
        }
        */
        
        statement.close();

        if(ret.size() == 0)
            ret = null;
            
        return ret;
    }

    private List<List<Order>> organizeOrdersByCamps(List<Order> orders)
    {
        List<List<Order>> ret = new ArrayList<>();
        HashMap<Integer,List<Order>> hashMap = new HashMap<>();
        Iterator<Order> orderIterator = orders.iterator();
        
        Order order;
        List<Order> ordersBySomeCamp;
        while(orderIterator.hasNext())
        {
            order = orderIterator.next();
            ordersBySomeCamp = hashMap.get(order.getCampNumber());

            if(ordersBySomeCamp == null)
            {
                hashMap.put(order.getCampNumber(), new ArrayList<Order>());
                hashMap.get(order.getCampNumber()).add(order);
            }
            else
            {
                ordersBySomeCamp.add(order);
            }
        }
        
        Set<Integer> keySet = hashMap.keySet();
        Iterator<Integer> keySetsIterator = keySet.iterator();

        while(keySetsIterator.hasNext())
        {
            ret.add(hashMap.get(keySetsIterator.next()));
        }

        return ret;
    }

    @Override
    public int delete(Integer id) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int registerOrders(PreferentialClient prefClient) throws Exception
    {
        int returnCode = 0;
        
        List<Article> articles =  new ArrayList<>();
        Iterator<Order> incomingOrdersIterator = prefClient.getIncomingOrders().iterator();

        while(incomingOrdersIterator.hasNext())
            articles.add(incomingOrdersIterator.next().getArticle());

        
        List<Order> orders =  new ArrayList<>();
        incomingOrdersIterator = prefClient.getIncomingOrders().iterator();

        while(incomingOrdersIterator.hasNext())
            orders.add(incomingOrdersIterator.next());


        ArticleOperator articleOperator = ArticleOperator.getOperator();
        OrderOperator orderOperator = OrderOperator.getOperator();
        BalanceOperator balanceOperator = BalanceOperator.getOperator();
        
        returnCode += articleOperator.insertMany(articles);
        returnCode += orderOperator.insertMany(orders);

        //Group orders by campaign number
        List<List<Order>> ordersByCamp = organizeOrdersByCamps(orders);

        Iterator<List<Order>> ordersByCampIterator = ordersByCamp.iterator();
        Iterator<Order> ordersIterator;

        Balance balance = new Balance();
        balance.setPrefClientId(prefClient.getId());
        while(ordersByCampIterator.hasNext())
        {
            //re-using variable
            orders = ordersByCampIterator.next();
            //Any camp is ok, since all orders from this sublist are from the same campaign
            balance.setCampNumber(orders.get(0).getCampNumber());

            ordersIterator = orders.iterator();
            while(ordersIterator.hasNext())
                balance.setTotalInOrders(balance.getTotalInOrders()+ordersIterator.next().getCost());
            
            returnCode += balanceOperator.update(balance);
        }

        /*
        //Any camp is ok, since all incoming orders are from the same campaign
        balance.setCamp(prefClient.getIncomingOrders().get(0).getCampaign());

        incomingOrdersIterator = prefClient.getIncomingOrders().iterator();

        while(incomingOrdersIterator.hasNext())
            balance.setTotalInOrdersCom(balance.getTotalInOrdersCom()+incomingOrdersIterator.next().getCost());
        
        ArticleOperator articleOperator = ArticleOperator.getOperator();
        OrderOperator orderOperator = OrderOperator.getOperator();
        BalanceOperator balanceOperator = BalanceOperator.getOperator();
        
        returnCode += articleOperator.insertMany(articles);
        returnCode += orderOperator.insertMany(orders);
        returnCode += balanceOperator.update(balance);
        */

        return returnCode;
    }
}