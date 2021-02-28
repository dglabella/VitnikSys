package vitniksys.backend.model.persistence;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import vitniksys.backend.model.enums.Reason;
import vitniksys.backend.model.entities.Article;
import vitniksys.backend.model.entities.Order;
import vitniksys.backend.model.enums.ArticleType;
import vitniksys.backend.model.entities.ReturnedArticle;
import vitniksys.backend.model.interfaces.IReturnedArticleOperator;

public class ReturnedArticleOperator implements IReturnedArticleOperator
{
    private static ReturnedArticleOperator operator;
	private boolean activeRow;

	private ReturnedArticleOperator()
	{
		this.activeRow = true;
	}

	public static ReturnedArticleOperator getOperator()
	{
		if (ReturnedArticleOperator.operator == null)
        ReturnedArticleOperator.operator = new ReturnedArticleOperator();

		return ReturnedArticleOperator.operator;
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
    public ReturnedArticleOperator setActiveRow(boolean activeRow)
    {
		this.activeRow = activeRow;
		return ReturnedArticleOperator.operator;
    }

    @Override
    public Integer insert(ReturnedArticle returnedArticle) throws Exception
    {
        Integer returnCode = null;
        String sqlStmnt = 
        "INSERT INTO `articulos_devueltos`(`cod_pedido`, `motivo`, `recomprado`) "+
        "VALUES (?, ?, ?);";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt, PreparedStatement.RETURN_GENERATED_KEYS);

        statement.setInt(1, returnedArticle.getOrderId());
        statement.setInt(2, returnedArticle.getReason().ordinal());
        statement.setBoolean(3, returnedArticle.isRepurchased());

        returnCode = statement.executeUpdate();

        ResultSet resultSet = statement.getGeneratedKeys();
        if(resultSet.next())
        {
            returnCode = resultSet.getInt(1);
        }
        
        statement.close();
  
        return returnCode;
    }

    @Override
    public Integer insertMany(List<ReturnedArticle> list) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Integer update(ReturnedArticle returnedArticle) throws Exception
    {
        Integer returnCode = null;
        String sqlStmnt = 
        "UPDATE `articulos_devueltos` SET `recomprado`= ? "+
        "WHERE `ejemplar` = ? AND `active_row` = ?;";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        statement.setBoolean(1, returnedArticle.isRepurchased());
        statement.setInt(2, returnedArticle.getUnitCode());
        statement.setBoolean(3, this.activeRow);

        returnCode = statement.executeUpdate();
        statement.close();
        return returnCode;
    }

    @Override
    public List<ReturnedArticle> findAll() throws Exception
    {
        List<ReturnedArticle> ret = new ArrayList<>();
        
        String sqlStmnt = 
        "SELECT `ejemplar`, `cod_pedido`, `motivo`, `recomprado`, `id_cp`, `nro_camp`, `nro_envio`, `cant`, `cant_devueltos`, `monto`, `fecha_retiro`, `fecha_registro`, `comisionable`, `articulos`.`letra`, `nombre`, `tipo`, `precio_unitario` "+
        "FROM `articulos_devueltos` "+
        "INNER JOIN `pedidos` ON `cod_pedido` = `pedidos`.`cod` "+
        "INNER JOIN `articulos` ON `pedidos`.`letra` = `articulos`.`letra` "+
        "WHERE `articulos_devueltos`.`active_row` = ? AND `pedidos`.`active_row` = ? AND `articulos`.`active_row` = ?;";

        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        statement.setBoolean(1, this.activeRow);
        statement.setBoolean(2, OrderOperator.getOperator().isActiveRow());
        statement.setBoolean(3, ArticleOperator.getOperator().isActiveRow());

		ResultSet resultSet = statement.executeQuery();

        ReturnedArticle returnedArticle;
		while (resultSet.next())
		{
            returnedArticle = new ReturnedArticle(resultSet.getInt(1), Reason.toEnum(resultSet.getInt(3)), resultSet.getBoolean(4));
            Order order = new Order(resultSet.getInt(2), resultSet.getInt(8), resultSet.getFloat(10), resultSet.getBoolean(13));
            order.setDeliveryNumber(resultSet.getInt(7));
            order.setQuantity(resultSet.getInt(8));
            order.setReturnedQuantity(resultSet.getInt(9));
            order.setCost(resultSet.getFloat(10));
            order.setWithdrawalDate(resultSet.getTimestamp(11));
            order.setRegistrationTime(resultSet.getTimestamp(12));
            order.setCommissionable(resultSet.getBoolean(13));

            Article article = new Article(resultSet.getString(14), resultSet.getString(15), ArticleType.toEnum(resultSet.getInt(16)), resultSet.getFloat(17));

            //fk ids
            order.setPrefClientId(resultSet.getInt(5));
            order.setCampNumber(resultSet.getInt(6));

            returnedArticle.setOrderId(resultSet.getInt(2));
            order.setArticleId(article.getId());
            

            //Associations
            order.setArticle(article);
            returnedArticle.setOrder(order);

			ret.add(returnedArticle);
		}

		statement.close();
		
		if(ret.size() == 0)
            ret = null;
		
        return ret;
    }

    @Override
    public List<ReturnedArticle> findAll(Integer prefClientId, Integer campNumb) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ReturnedArticle find(Integer id) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer delete(Integer id) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }
}