package vitniksys.backend.model.persistence;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import vitniksys.backend.model.entities.Order;
import vitniksys.backend.model.entities.Article;
import vitniksys.backend.model.entities.Balance;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.interfaces.IPreferentialClientOperator;

public abstract class PreferentialClientOperator implements IPreferentialClientOperator
{
    private Boolean activeRow;

    /**
     * Get the flag state with which the DAO operator performs a CRUD operation.
     * Ignore this if it not exist an implementation for active or inactive rows in
     * your Data Base. Default value: true.
     * 
     * @return The state of the entity.
     */
    public Boolean isActiveRow()
    {
        return this.activeRow;
    }

    /**
     * Change the flag state with which the DAO operator performs a CRUD operation.
     * Ignore this if it not exist an implementation for active or inactive rows in
     * your Data Base.
     * Default value: true.
     * @param activeRow the value for the operation.
     */
    public void setActiveRow(Boolean activeRow)
    {
        this.activeRow = activeRow;
    }

    @Override
    public int update(PreferentialClient cp)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int insertMany(List<PreferentialClient> list) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public List<PreferentialClient> findAll() throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PreferentialClient find(int id) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int delete(int id) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int registerOrders(PreferentialClient cp) throws Exception
    {
        int returnCode = 0;
        
        List<Article> articles =  new ArrayList<>();
        Iterator<Order> incomingOrdersIterator = cp.getIncomingOrders().iterator();

        while(incomingOrdersIterator.hasNext())
            articles.add(incomingOrdersIterator.next().getArticle());


        List<Order> orders =  new ArrayList<>();
        incomingOrdersIterator = cp.getIncomingOrders().iterator();

        while(incomingOrdersIterator.hasNext())
            orders.add(incomingOrdersIterator.next());

        
        Balance balance = new Balance();
        balance.setClient(cp);
        //Any camp is ok, since all incoming orders are from the same campaign
        balance.setCamp(cp.getIncomingOrders().get(0).getCampaign());
        incomingOrdersIterator = cp.getIncomingOrders().iterator();

        while(incomingOrdersIterator.hasNext())
            balance.setTotalInOrdersCom(balance.getTotalInOrdersCom()+incomingOrdersIterator.next().getCost());
        
        ArticleOperator articleOperator = ArticleOperator.getOperator();
        OrderOperator orderOperator = OrderOperator.getOperator();
        BalanceOperator balanceOperator = BalanceOperator.getOperator();
        
        returnCode += articleOperator.insertMany(articles);
        returnCode += orderOperator.insertMany(orders);
        returnCode += balanceOperator.update(balance);

        return returnCode;
    }
}