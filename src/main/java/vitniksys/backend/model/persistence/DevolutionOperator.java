package vitniksys.backend.model.persistence;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import vitniksys.backend.model.enums.Reason;
import vitniksys.backend.model.entities.Article;
import vitniksys.backend.model.enums.ArticleType;
import vitniksys.backend.model.entities.Devolution;
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
    public int insert(Devolution entity) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int insertMany(List<Devolution> list) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(Devolution entity) throws Exception
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
			"SELECT `cod`, `id_cp`, `nro_camp`, devoluciones.`letra`, `cant`, `monto`, `motivo`, `fecha_registro`, `nombre`, `tipo`, `precio_unitario` "+
            "FROM `devoluciones` "+
            "INNER JOIN `articulos` ON devoluciones.letra = articulos.letra "+
            "WHERE `id_cp` = ? AND `nro_camp` = ? AND devoluciones.active_row = ? AND articulos.active_row = ?;";
            statement = Connector.getConnector().getStatement(sqlStmnt);
            statement.setInt(1, prefClientId);
            statement.setInt(2, campNumb);
            statement.setBoolean(3, this.activeRow);
            statement.setBoolean(4, ArticleOperator.getOperator().isActiveRow());
			
        }
        else if(prefClientId != null && campNumb == null)
        {
			sqlStmnt =
			"SELECT `cod`, `id_cp`, `nro_camp`, devoluciones.`letra`, `cant`, `monto`, `motivo`, `fecha_registro`, `nombre`, `tipo`, `precio_unitario` "+
            "FROM `devoluciones` "+
            "INNER JOIN `articulos` ON devoluciones.letra = articulos.letra "+
            "WHERE `id_cp` = ? AND devoluciones.active_row = ? AND articulos.active_row = ?;";

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

		Devolution devolution;
		while (resultSet.next())
		{
            devolution = new Devolution(resultSet.getInt(1), resultSet.getInt(5), resultSet.getFloat(6), Reason.toEnum(resultSet.getInt(7)), resultSet.getTimestamp(8));
			
			//fk ids
            devolution.setPrefClientId(resultSet.getInt(2));
            devolution.setCampNumber(resultSet.getInt(3));
            devolution.setArticleId(resultSet.getString(4));

            //Associations
            devolution.setArticle(new Article(resultSet.getString(4), resultSet.getString(9), ArticleType.toEnum(resultSet.getInt(10)), resultSet.getFloat(11)));
			
			ret.add(devolution);
		}

		statement.close();
		
		if(ret.size() == 0)
            ret = null;
		
        return ret;
    }

    @Override
    public int delete(int id) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }
}