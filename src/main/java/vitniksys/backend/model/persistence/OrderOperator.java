package vitniksys.backend.model.persistence;

import java.util.List;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import vitniksys.backend.model.entities.Order;
import vitniksys.backend.model.interfaces.IOrderOperator;

public class OrderOperator implements IOrderOperator
{
	private static OrderOperator operator;
	private Boolean activeRow;

	private OrderOperator()
	{
		this.activeRow = true;
	}

	public static OrderOperator getOperator()
	{
		if (OrderOperator.operator == null)
			OrderOperator.operator = new OrderOperator();

		return OrderOperator.operator;
	}

	/**
     * Get the flag state with which the DAO operator performs a CRUD operation.
     * Ignore this if it not exist an implementation for active or inactive rows in
     * your Data Base.
     * Default value: true.
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
    public OrderOperator setActiveRow(Boolean activeRow)
    {
		this.activeRow = activeRow;
		return OrderOperator.operator;
    }

	@Override
	public int insert(Order order) throws Exception
	{
		int returnCode;
		String sqlStmnt = "INSERT INTO `pedidos`(`id_cp`, `nro_camp`, `letra`, `cant`, `monto`) VALUES "+
		"(?, ?, ?, ?, ?);";
		PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);
		
		statement.setInt(1, order.getPrefClientId() );
		statement.setInt(2, order.getCampNumber());
        statement.setString(3, order.getArticleId());
		statement.setInt(4, order.getQuantity());
		statement.setFloat(5, order.getCost());

        returnCode = statement.executeUpdate();
        statement.close();
  
        return returnCode;
	}

	@Override
	public int insertMany(List<Order> list) throws Exception
	{
		int returnCode = 0;
        String sqlStmnt = "INSERT INTO `pedidos`(`nro_envio`, `id_cp`, `nro_camp`, `letra`, `cant`, `monto`) VALUES "+
		"(?, ?, ?, ?, ?, ?);";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        Order order;
        Iterator<Order> listIterator = list.iterator();

        while(listIterator.hasNext())
        {
			order = listIterator.next();
			
			statement.setInt(1, order.getDeliveryNumber());
            statement.setInt(2, order.getPrefClientId() );
			statement.setInt(3, order.getCampNumber());
			statement.setString(4, order.getArticleId());
			statement.setInt(5, order.getQuantity());
			statement.setFloat(6, order.getCost());

            returnCode += statement.executeUpdate();
        }

        statement.close();

        return returnCode;
	}

	@Override
	public int update(Order order) throws Exception
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Order> findAll() throws Exception
	{
		return null;
	}

	@Override
	public List<Order> findAll(Integer campNumb, Integer prefClientId) throws Exception
	{
		List<Order> ret = new ArrayList<>();
		/*
        
        String sqlStmnt = "SELECT * FROM `pedidos` WHERE `id_cp` = ? AND `nro_camp` = ? AND `active_row` = ?;";
		PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);
        statement.setBoolean(3, this.activeRow);

        ResultSet resultSet = statement.executeQuery();

        Order order;
        while (resultSet.next())
        {
            order = new Order(resultSet.getInt(1), resultSet.getInt(6), resultSet.getFloat(7), resultSet.getBoolean(10));
			order.setDeliveryNumber(resultSet.getInt(2));
			order.setArticle(ArticleOperator.getOperator().find(resultSet.getString(5)));
            order.setRegistrationTime(resultSet.getTimestamp(5));
            order.setCatalogue(CatalogueOperator.getOperator().find(resultSet.getInt(6)));
            ret.add(order);
        }

        statement.close();
        
        if(ret.size() == 0)
			ret = null;
		*/
		
        return ret;
	}

	public List<Order> findAll(Integer id)
	{
		return null;
	}

	@Override
	public Order find(int id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int id)
	{
		// TODO Auto-generated method stub
		return 0;
	}
}