package vitniksys.backend.model.persistence;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import vitniksys.backend.model.enums.Reason;
import vitniksys.backend.model.entities.Order;
import vitniksys.backend.model.entities.Article;
import vitniksys.backend.model.enums.ArticleType;
import vitniksys.backend.model.entities.Devolution;
import vitniksys.backend.model.entities.ReturnedArticle;
import vitniksys.backend.model.interfaces.IDevolutionOperator;

public class DevolutionOperator implements IDevolutionOperator
{
    private static DevolutionOperator operator;
    private boolean activeRow;

    private DevolutionOperator()
    {
        this.activeRow = true;
    }

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

    /**
     * Change the flag state with which the DAO operator performs a CRUD operation.
     * Ignore this if it not exist an implementation for active or inactive rows in
     * your Data Base. Default value: true.
     * 
     * @param activeRow the value for the operation.
     */
    public DevolutionOperator setActiveRow(boolean activeRow)
    {
        this.activeRow = activeRow;

        return DevolutionOperator.operator;
    }

    public static DevolutionOperator getOperator()
    {
        if (DevolutionOperator.operator == null)
            DevolutionOperator.operator = new DevolutionOperator();

        return DevolutionOperator.operator;
    }

    @Override
    public Integer insert(Devolution devolution) throws Exception
    {
        Integer returnCode = null;
        String sqlStmnt = 
        "INSERT INTO `devoluciones`(`id_cp`, `nro_camp`, `ejemplar`, `monto`) " +
        "VALUES (?, ?, ?, ?);";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        statement.setInt(1, devolution.getPrefClientId());
        statement.setInt(2, devolution.getCampNumber());
        statement.setInt(3, devolution.getUnitCode());
        statement.setFloat(4, devolution.getCost());

        returnCode = statement.executeUpdate();
        statement.close();
  
        return returnCode;
    }

    @Override
    public Integer insertMany(List<Devolution> list) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Integer update(Devolution entity) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Devolution> findAll() throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Devolution find(int id) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Devolution> findAll(Integer prefClientId, Integer campNumb) throws Exception
    {
        List<Devolution> ret = new ArrayList<>();
        String sqlStmnt = null;
        PreparedStatement statement = null;

        if(prefClientId != null && campNumb != null)
        {
            sqlStmnt = 
			"SELECT `cod`, `devoluciones`.`ejemplar`, `devoluciones`.`monto`, `devoluciones`.`fecha_registro`, `cod_pedido`, `articulos_devueltos`.`motivo`, `recomprado`, `pedidos`.`letra`, `nro_envio`, `cant`, `cant_devueltos`, `pedidos`.`monto`, `fecha_retiro`, `pedidos`.`fecha_registro`, `comisionable`, `nombre`, `tipo`, `precio_unitario` "+
            "FROM `devoluciones` "+
            "INNER JOIN `articulos_devueltos` ON `devoluciones`.`ejemplar` = `articulos_devueltos`.`ejemplar` "+
            "INNER JOIN `pedidos` ON `articulos_devueltos`.`cod_pedido` = `pedidos`.`cod` "+
            "INNER JOIN `articulos` ON `pedidos`.`letra` = `articulos`.`letra` "+
            "WHERE `devoluciones`.`id_cp` = ? AND `devoluciones`.`nro_camp` = ? AND `devoluciones`.`active_row` = ? AND `articulos_devueltos`.`active_row` = ? AND `pedidos`.`active_row` = ? AND `articulos`.`active_row` = ?;";
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
			"SELECT `cod`, `devoluciones`.`id_cp`, `devoluciones`.`nro_camp`, `devoluciones`.`ejemplar`, `devoluciones`.`monto`, `devoluciones`.`fecha_registro`, `cod_pedido`, `articulos_devueltos`.`motivo`, `recomprado`, `pedidos`.`letra`, `nro_envio`, `cant`, `cant_devueltos`, `pedidos`.`monto`, `fecha_retiro`, `pedidos`.`fecha_registro`, `comisionable`, `nombre`, `tipo`, `precio_unitario` "+
            "FROM `devoluciones` "+
            "INNER JOIN `articulos_devueltos` ON `devoluciones`.`ejemplar` = `articulos_devueltos`.`ejemplar` "+
            "INNER JOIN `pedidos` ON `articulos_devueltos`.`cod_pedido` = `pedidos`.`cod` "+
            "INNER JOIN `articulos` ON `pedidos`.`letra` = `articulos`.`letra` "+
            "WHERE `devoluciones`.`id_cp` = ? AND `devoluciones`.`active_row` = ? AND `articulos_devueltos`.`active_row` = ? AND `pedidos`.`active_row` = ? AND `articulos`.`active_row` = ?;";

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

		Devolution devolution;
		while (resultSet.next())
		{
            devolution = new Devolution(resultSet.getInt(1), resultSet.getFloat(3), resultSet.getTimestamp(4));
            ReturnedArticle returnedArticle = new ReturnedArticle(resultSet.getInt(2), Reason.toEnum(resultSet.getInt(6)), resultSet.getBoolean(7));
            Order order = new Order(resultSet.getInt(5), resultSet.getInt(10), resultSet.getFloat(12), resultSet.getBoolean(15));
            order.setDeliveryNumber(resultSet.getInt(9));
            order.setReturnedQuantity(resultSet.getInt(11));
            order.setWithdrawalDate(resultSet.getTimestamp(13));
            order.setRegistrationTime(resultSet.getTimestamp(15));
            Article article = new Article(resultSet.getString(8), resultSet.getString(16), ArticleType.toEnum(resultSet.getInt(17)), resultSet.getFloat(18));

			//fk ids
            devolution.setPrefClientId(resultSet.getInt(prefClientId));
            devolution.setCampNumber(resultSet.getInt(campNumb));
            devolution.setUnitCode(returnedArticle.getUnitCode());
            returnedArticle.setOrderId(order.getCode());
            order.setPrefClientId(prefClientId);
            order.setCampNumber(campNumb);
            order.setArticleId(article.getId());

            //Associations
            order.setArticle(article);
            returnedArticle.setOrder(order);
            devolution.setReturnedArticle(returnedArticle);
			
			ret.add(devolution);
		}

		statement.close();
		
		if(ret.size() == 0)
            ret = null;
		
        return ret;
    }

    @Override
    public Integer delete(int id) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }
}