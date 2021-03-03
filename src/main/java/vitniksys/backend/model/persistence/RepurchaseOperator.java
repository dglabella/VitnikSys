package vitniksys.backend.model.persistence;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.sql.PreparedStatement;
import vitniksys.backend.model.enums.Reason;
import vitniksys.backend.model.entities.Article;
import vitniksys.backend.model.entities.Order;
import vitniksys.backend.model.enums.ArticleType;
import vitniksys.backend.model.entities.Repurchase;
import vitniksys.backend.model.entities.ReturnedArticle;
import vitniksys.backend.model.interfaces.IRepurchaseOperator;

public class RepurchaseOperator implements IRepurchaseOperator
{
    private static RepurchaseOperator operator;
	private boolean activeRow;

	private RepurchaseOperator()
	{
		this.activeRow = true;
	}

	public static RepurchaseOperator getOperator()
	{
		if (RepurchaseOperator.operator == null)
        RepurchaseOperator.operator = new RepurchaseOperator();

		return RepurchaseOperator.operator;
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
    public RepurchaseOperator setActiveRow(boolean activeRow)
    {
		this.activeRow = activeRow;
		return RepurchaseOperator.operator;
    }

    @Override
    public Integer insert(Repurchase repurchase) throws Exception
    {
        Integer returnCode = null;
        String sqlStmnt =
        "INSERT INTO `recompras`(`id_cp`, `nro_camp`, `ejemplar`, `precio_recompra`, `comisionable`) "+
        "VALUES (?, ?, ?, ?, ?);";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        statement.setInt(1, repurchase.getPrefClientId());
        statement.setInt(2, repurchase.getCampNumber());
        statement.setInt(3, repurchase.getReturnedArticleId() );
        statement.setFloat(4, repurchase.getCost());
        statement.setBoolean(5, repurchase.isCommissionable());

        returnCode = statement.executeUpdate();
        statement.close();
  
        return returnCode;
    }

    @Override
    public Integer insertMany(List<Repurchase> list) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Integer update(Repurchase entity) throws Exception
    {
        Integer returnCode = null;

        return returnCode;
    }

    @Override
    public Integer updateAll(List<Repurchase> repurchases) throws Exception
    {
        Integer returnCode = 0;

		String sqlStmnt = 
		"UPDATE `recompras` "+
        "SET `comisionable`= ?, `devuelta`= ? "+
		"WHERE `cod` = ? AND `active_row` = ?;";

        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        Repurchase repurchase;
		Iterator<Repurchase> listIterator = repurchases.iterator();
		
        while(listIterator.hasNext())
        {
			repurchase = listIterator.next();
			
			statement.setBoolean(1, repurchase.isCommissionable());
            statement.setBoolean(2, repurchase.isReturned());
            statement.setInt(3, repurchase.getCode());
			statement.setBoolean(4, this.activeRow);

            returnCode += statement.executeUpdate();
        }

        statement.close();

        return returnCode;
    }

    @Override
    public List<Repurchase> findAll() throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Repurchase> findAll(Integer prefClientId, Integer campNumb) throws Exception
    {
        List<Repurchase> ret = new ArrayList<>();
        String sqlStmnt = null;
        PreparedStatement statement = null;

        if(prefClientId != null && campNumb != null)
        {
            sqlStmnt = 
			"SELECT `recompras`.`cod`, `recompras`.`ejemplar`, `precio_recompra`, `recompras`.`comisionable`, `devuelta`, `recompras`.`fecha_registro`, `cod_pedido`, `motivo`, `recomprado`, `pedidos`.`id_cp`, `pedidos`.`nro_camp`, `pedidos`.`letra`, `nro_envio`, `cant`, `cant_devueltos`, `monto`, `fecha_retiro`, `pedidos`.`fecha_registro`, `pedidos`.`comisionable`, `nombre`, `tipo`, `precio_unitario` "+
            "FROM `recompras` "+
            "INNER JOIN `articulos_devueltos` ON `recompras`.`ejemplar` = `articulos_devueltos`.`ejemplar` "+
            "INNER JOIN `pedidos` ON `articulos_devueltos`.`cod_pedido` = `pedidos`.`cod` "+
            "INNER JOIN `articulos` ON `pedidos`.`letra` = `articulos`.`letra` "+
            "WHERE `recompras`.`id_cp` = ? AND `recompras`.`nro_camp` = ? AND `recompras`.`active_row` = ? AND `articulos_devueltos`.`active_row` = ? AND `pedidos`.`active_row` = ? AND `articulos`.`active_row` = ?;";

            statement = Connector.getConnector().getStatement(sqlStmnt);
            statement.setInt(1, prefClientId);
            statement.setInt(2, campNumb);
            statement.setBoolean(3, this.activeRow);
            statement.setBoolean(4, ReturnedArticleOperator.getOperator().isActiveRow());
            statement.setBoolean(5, OrderOperator.getOperator().isActiveRow());
            statement.setBoolean(6, ArticleOperator.getOperator().isActiveRow());
        }
        else if(prefClientId != null && campNumb == null)
        {
			sqlStmnt = 
			"SELECT `recompras`.`cod`, `recompras`.`ejemplar`, `precio_recompra`, `recompras`.`comisionable`, `devuelta`, `recompras`.`fecha_registro`, `cod_pedido`, `motivo`, `recomprado`, `pedidos`.`id_cp`, `pedidos`.`nro_camp`, `pedidos`.`letra`, `nro_envio`, `cant`, `cant_devueltos`, `monto`, `fecha_retiro`, `pedidos`.`fecha_registro`, `pedidos`.`comisionable`, `nombre`, `tipo`, `precio_unitario` "+
            "FROM `recompras` "+
            "INNER JOIN `articulos_devueltos` ON `recompras`.`ejemplar` = `articulos_devueltos`.`ejemplar` "+
            "INNER JOIN `pedidos` ON `articulos_devueltos`.`cod_pedido` = `pedidos`.`cod` "+
            "INNER JOIN `articulos` ON `pedidos`.`letra` = `articulos`.`letra` "+
            "WHERE `recompras`.`id_cp` = ? AND `recompras`.`active_row` = ? AND `articulos_devueltos`.`active_row` = ? AND `pedidos`.`active_row` = ? AND `articulos`.`active_row` = ?;";

            statement = Connector.getConnector().getStatement(sqlStmnt);
            statement.setInt(1, prefClientId);
            statement.setBoolean(2, this.activeRow);
            statement.setBoolean(3, ReturnedArticleOperator.getOperator().isActiveRow());
            statement.setBoolean(4, OrderOperator.getOperator().isActiveRow());
            statement.setBoolean(5, ArticleOperator.getOperator().isActiveRow());
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
        Article article;
        Repurchase repurchase;
        ReturnedArticle returnedArticle;
		while (resultSet.next())
		{
            repurchase =  new Repurchase(resultSet.getInt(1), resultSet.getFloat(3), resultSet.getTimestamp(6));
            repurchase.setCommissionable(resultSet.getBoolean(4));
            repurchase.setReturned(resultSet.getBoolean(5));
            returnedArticle = new ReturnedArticle(resultSet.getInt(2), Reason.toEnum(resultSet.getInt(8)), resultSet.getBoolean(9));
            order = new Order(resultSet.getInt(7), resultSet.getInt(14), resultSet.getFloat(16), resultSet.getBoolean(19));
            order.setDeliveryNumber(resultSet.getInt(13));
            order.setReturnedQuantity(resultSet.getInt(15));
            order.setWithdrawalDate(resultSet.getTimestamp(17));
            order.setRegistrationTime(resultSet.getTimestamp(18));
            article = new Article(resultSet.getString(12), resultSet.getString(20), ArticleType.toEnum(resultSet.getInt(21)), resultSet.getFloat(22));

            //fk ids
            repurchase.setPrefClientId(prefClientId);
            repurchase.setCampNumber(campNumb);
            repurchase.setReturnedArticleId(returnedArticle.getUnitCode());
            returnedArticle.setOrderId(order.getCode());
            order.setPrefClientId(resultSet.getInt(10));
            order.setCampNumber(resultSet.getInt(11));
            order.setArticleId(article.getId());

            //Associations
            order.setArticle(article);
            returnedArticle.setOrder(order);
            repurchase.setReturnedArticle(returnedArticle);
            
			ret.add(repurchase);
		}

		statement.close();
		
		if(ret.size() == 0)
            ret = null;
		
        
        System.out.println("HOLAAAAA "+ret);

        return ret;
    }

    @Override
    public Repurchase find(Integer id) throws Exception
    {
        Repurchase ret = null;

        String sqlStmnt =
        "SELECT `id_cp`, `nro_camp`, `ejemplar`, `precio_recompra`, `comisionable`, `devuelta`, `fecha_registro` "+
        "FROM `recompras` "+
        "WHERE `cod` = ? AND `active_row` = ?;";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);
        
        statement.setInt(1, id);
        statement.setBoolean(2, this.activeRow);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next())
        {
            ret = new Repurchase(id, resultSet.getFloat(4), resultSet.getTimestamp(7));
            ret.setCommissionable(resultSet.getBoolean(5));
            ret.setReturned(resultSet.getBoolean(6));

            //fk ids
            ret.setPrefClientId(resultSet.getInt(1));
            ret.setCampNumber(resultSet.getInt(2));
            ret.setReturnedArticleId(resultSet.getInt(3));

            //Associations
            
        }

        statement.close();

        return ret;
    }

    @Override
    public Integer delete(Integer id) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Integer setCommissionable(Integer id, boolean commissionable) throws Exception 
    {
        Integer returnCode = null;
        
        String sqlStmnt = 
        "UPDATE `recompras` "+
        "SET `comisionable` = ? "+
        "WHERE `cod` = ? AND `active_row` = ?";

        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        statement.setBoolean(1, commissionable);
        statement.setInt(2, id);
        statement.setBoolean(3, this.activeRow);

        returnCode = statement.executeUpdate();
        statement.close();

        return returnCode;
    }

    @Override
    public Integer setReturned(Integer id) throws Exception
    {
        Integer returnCode = null;

        String sqlStmnt = 
        "UPDATE `recompras` "+
        "SET `comisionable`= ?, `devuelta`= ? "+
        "WHERE `cod` = ? AND `active_row` = ?";

        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        statement.setBoolean(1, false);
        statement.setBoolean(2, true);
        statement.setInt(3, id);
        statement.setBoolean(4, this.activeRow);

        returnCode = statement.executeUpdate();
        statement.close();

        return returnCode;
    }
}