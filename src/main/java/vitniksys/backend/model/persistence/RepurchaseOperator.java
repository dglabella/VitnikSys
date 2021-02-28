package vitniksys.backend.model.persistence;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import vitniksys.backend.model.enums.Reason;
import vitniksys.backend.model.entities.Article;
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
        // TODO Auto-generated method stub
        return 0;
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
			"SELECT `cod`, `id_cp`, `nro_camp`, recompras.`ejemplar`, `precio_recompra`, `comisionable`, `fecha_registro`, articulos_devueltos.`letra`, `motivo`, `recomprado`, `nombre`, `tipo`, `precio_unitario` "+
            "FROM `recompras` "+
            "INNER JOIN `articulos_devueltos` ON recompras.ejemplar = articulos_devueltos.ejemplar "+
            "INNER JOIN `articulos` ON articulos_devueltos.letra = articulos.letra "+
            "WHERE `id_cp` = ? AND `nro_camp` = ? AND recompras.active_row = ? AND articulos_devueltos.active_row = ? AND articulos.active_row = ?;";

            statement = Connector.getConnector().getStatement(sqlStmnt);
            statement.setInt(1, prefClientId);
            statement.setInt(2, campNumb);
            statement.setBoolean(3, this.activeRow);
            statement.setBoolean(4, ReturnedArticleOperator.getOperator().isActiveRow());
            statement.setBoolean(5, ArticleOperator.getOperator().isActiveRow());	
        }
        else if(prefClientId != null && campNumb == null)
        {
			sqlStmnt = 
			"SELECT `cod`, `id_cp`, `nro_camp`, recompras.`ejemplar`, `precio_recompra`, `comisionable`, `fecha_registro`, articulos_devueltos.`letra`, `motivo`, `recomprado`, `nombre`, `tipo`, `precio_unitario` "+
            "FROM `recompras` "+
            "INNER JOIN `articulos_devueltos` ON recompras.ejemplar = articulos_devueltos.ejemplar "+
            "INNER JOIN `articulos` ON articulos_devueltos.letra = articulos.letra "+
            "WHERE `id_cp` = ? AND recompras.active_row = ? AND articulos_devueltos.active_row = ? AND articulos.active_row = ?;";

            statement = Connector.getConnector().getStatement(sqlStmnt);
            statement.setInt(1, prefClientId);
            statement.setBoolean(2, this.activeRow);
            statement.setBoolean(3, ReturnedArticleOperator.getOperator().isActiveRow());
            statement.setBoolean(4, ArticleOperator.getOperator().isActiveRow());
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

        Article article;
        Repurchase repurchase;
        ReturnedArticle returnedArticle;
		while (resultSet.next())
		{
            article = new Article(resultSet.getString(8), resultSet.getString(11), ArticleType.toEnum(resultSet.getInt(12)), resultSet.getFloat(13));
            returnedArticle = new ReturnedArticle(resultSet.getInt(4), Reason.toEnum(resultSet.getInt(9)), resultSet.getBoolean(10));
            repurchase = new Repurchase(resultSet.getInt(1), resultSet.getFloat(5), resultSet.getTimestamp(7));
            repurchase.setCommissionable(resultSet.getBoolean(6);

            
            //fk ids
            returnedArticle.setArticleId(article.getId());
            repurchase.setPrefClientId(resultSet.getInt(2));
            repurchase.setCampNumber(resultSet.getInt(3));
            repurchase.setReturnedArticleId(resultSet.getInt(4));

            //Associations
            repurchase.setReturnedArticle(returnedArticle);
			
			ret.add(repurchase);
		}

		statement.close();
		
		if(ret.size() == 0)
            ret = null;
		
        return ret;
    }

    @Override
    public Repurchase find(int id) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer delete(int id) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }
}