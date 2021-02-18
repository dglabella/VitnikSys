package vitniksys.backend.model.persistence;

import java.util.List;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import vitniksys.backend.model.entities.Order;
import vitniksys.backend.model.entities.Article;
import vitniksys.backend.model.enums.ArticleType;
import vitniksys.backend.model.interfaces.IOrderOperator;

public class OrderOperator implements IOrderOperator
{
	private static OrderOperator operator;
	private boolean activeRow;

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
    public boolean isActiveRow()
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
    public OrderOperator setActiveRow(boolean activeRow)
    {
		this.activeRow = activeRow;
		return OrderOperator.operator;
    }

	@Override
	public Integer insert(Order order) throws Exception
	{
		Integer returnCode = null;
		String sqlStmnt = "INSERT INTO `pedidos`(`id_cp`, `nro_camp`, `letra`, `cant`, `monto`) "+
		"VALUES (?, ?, ?, ?, ?);";
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
	public Integer insertMany(List<Order> list) throws Exception
	{
		Integer returnCode = 0;
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
	public Integer update(Order order) throws Exception
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
	public List<Order> findAll(Integer prefClientId, Integer campNumb) throws Exception
	{
		List<Order> ret = new ArrayList<>();
		String sqlStmnt = null;
		PreparedStatement statement = null;

        if(prefClientId != null && campNumb != null)
        {
            sqlStmnt =
			"SELECT `cod`, `nro_envio`, `id_cp`, `nro_camp`, `pedidos`.`letra`, `cant`, `monto`, `fecha_retiro`, `comisionable`, `nombre`, `tipo`, `precio_unitario`"+
			"FROM `pedidos` "+
			"INNER JOIN `articulos` ON pedidos.letra = articulos.letra "+
			"WHERE `id_cp` = ? AND `nro_camp` = ? AND pedidos.active_row = ? AND articulos.active_row = ?;";

			statement = Connector.getConnector().getStatement(sqlStmnt);
			statement.setInt(1, prefClientId);
			statement.setInt(2, campNumb);
			statement.setBoolean(3, this.activeRow);
			statement.setBoolean(4, ArticleOperator.getOperator().isActiveRow());	
        }
        else if(prefClientId != null && campNumb == null)
        {
			sqlStmnt =
			"SELECT `cod`, `nro_envio`, `id_cp`, `nro_camp`, `pedidos`.`letra`, `cant`, `monto`, `fecha_retiro`, `comisionable`, `nombre`, `tipo`, `precio_unitario`"+
			"FROM `pedidos` "+
			"INNER JOIN `articulos` ON pedidos.letra = articulos.letra "+
			"WHERE `id_cp` = ? AND pedidos.active_row = ? AND articulos.active_row = ?;";

			statement = Connector.getConnector().getStatement(sqlStmnt);
			statement.setInt(1, prefClientId);
			statement.setBoolean(2, this.activeRow);
			statement.setBoolean(3, ArticleOperator.getOperator().isActiveRow());
        }
        else if(prefClientId == null && campNumb != null)
        {
            // Select devs with camp numb x
        }
        else
        {
            throw new Exception("Both campaign number and preferential client id are null");
		}

		ResultSet resultSet = statement.executeQuery();

		Order order;
		while (resultSet.next())
		{
			order = new Order(resultSet.getInt(1), resultSet.getInt(6), resultSet.getFloat(7), resultSet.getBoolean(9));
			order.setDeliveryNumber(resultSet.getInt(2));
			order.setWithdrawalDate(resultSet.getTimestamp(8));
			
			//fk ids
			order.setPrefClientId(resultSet.getInt(3));
			order.setCampNumber(resultSet.getInt(4));
			order.setArticleId(resultSet.getString(5));

			//Associations
			order.setArticle(new Article(resultSet.getString(5), resultSet.getString(10), ArticleType.toEnum(resultSet.getInt(11)), resultSet.getFloat(12)));
			
			ret.add(order);
		}

		statement.close();
		
		if(ret.size() == 0)
			ret = null;
			
        return ret;
	}

	@Override
	public Integer updateAll(List<Order> orders) throws Exception
	{
		Integer returnCode = 0;
		String sqlStmnt = 
		"UPDATE `pedidos` "+
		"SET `comisionable`= ? "+
		"WHERE `cod` = ? AND `active_row` = ?;";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        Order order;
		Iterator<Order> listIterator = orders.iterator();
		
        while(listIterator.hasNext())
        {
			order = listIterator.next();
			
			statement.setBoolean(1, order.isCommissionable());
            statement.setInt(2, order.getCode());
			statement.setBoolean(3, this.activeRow);

            returnCode += statement.executeUpdate();
        }

        statement.close();

        return returnCode;
	}

	@Override
	public Order find(int id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(int id)
	{
		// TODO Auto-generated method stub
		return 0;
	}
}