package vitniksys.backend.model.persistence;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import vitniksys.backend.model.entities.Commission;
import vitniksys.backend.model.interfaces.ICommisionOperator;

public class CommisionOperator implements ICommisionOperator
{
    private static CommisionOperator operator;
	private boolean activeRow;

	private CommisionOperator()
	{
		this.activeRow = true;
	}

	public static CommisionOperator getOperator()
	{
		if (CommisionOperator.operator == null)
            CommisionOperator.operator = new CommisionOperator();

		return CommisionOperator.operator;
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
    public CommisionOperator setActiveRow(boolean activeRow)
    {
		this.activeRow = activeRow;
		return CommisionOperator.operator;
    }

    @Override
    public int insert(Commission entity) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int insertMany(List<Commission> list) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(Commission entity) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Commission> findAll() throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Commission> findAll(Integer prefClientId, Integer campNumb) throws Exception
    {
        List<Commission> ret = new ArrayList<>();
		String sqlStmnt = null;
		PreparedStatement statement = null;

        if(prefClientId != null && campNumb != null)
        {
            sqlStmnt =
			"SELECT `cod`, `nro_envio`, `id_cp`, `nro_camp`, `pedidos`.`letra`, `cant`, `monto`, `fecha_retiro`, `comisionable`, `nombre`, `tipo`, `precio_unitario`"+
			"FROM `pedidos` "+
			"INNER JOIN `articulos` ON pedidos.letra = articulos.letra WHERE `id_cp` = ? AND `nro_camp` = ? AND pedidos.active_row = ? AND articulos.active_row = ?;";

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
			"INNER JOIN `articulos` ON pedidos.letra = articulos.letra WHERE `id_cp` = ? AND pedidos.active_row = ? AND articulos.active_row = ?;";

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
    public Commission find(Integer prefClientId, Integer campNumber) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int delete(Integer prefClientId, Integer campNumber) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }
}