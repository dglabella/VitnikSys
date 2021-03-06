package vitniksys.backend.model.persistence;

import java.util.List;
import java.sql.Types;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import vitniksys.backend.model.entities.Catalogue;
import vitniksys.backend.model.entities.CatalogueDeliver;
import vitniksys.backend.model.interfaces.ICatalogueOperator;

//This class intanciates the DAO Object for Catalogo
public class CatalogueOperator implements ICatalogueOperator
{
    private static CatalogueOperator operator;

    private boolean activeRow;

    private CatalogueOperator()
    {
        this.activeRow = true;
    }

    public static CatalogueOperator getOperator()
    {
        if (CatalogueOperator.operator == null)
            CatalogueOperator.operator = new CatalogueOperator();

        return CatalogueOperator.operator;
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
    public CatalogueOperator setActiveRow(boolean activeRow)
    {
        this.activeRow = activeRow;
        return CatalogueOperator.operator;
    }

    @Override
    public Integer insert(Catalogue catalogue) throws Exception
    {
        Integer returnCode = null;

        String sqlStmnt =
        "INSERT INTO `catalogos`(`cod`, `stock_inicial`, `stock`, `precio`, `link`) "+
        "VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE `precio` = ?,`link` = ?;";
        PreparedStatement statement = Connector.getInstance().getStatement(sqlStmnt);

        statement.setInt(1, catalogue.getCode());
        statement.setInt(2, catalogue.getInitialStock());
        statement.setInt(3, catalogue.getActualStock());
        statement.setFloat(4, catalogue.getPrice());

        if(catalogue.getLink() != null && !catalogue.getLink().isBlank())
            statement.setString(5, catalogue.getLink());
        else
            statement.setNull(5, Types.VARCHAR);

        statement.setFloat(6, catalogue.getPrice());

        if(catalogue.getLink() != null && !catalogue.getLink().isBlank())
            statement.setString(7, catalogue.getLink());
        else
            statement.setNull(7, Types.VARCHAR);

        returnCode = statement.executeUpdate();
        statement.close();
  
        return returnCode;
    }

    @Override
    public Integer insertMany(List<Catalogue> list) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Catalogue find(int code) throws Exception 
    {
        Catalogue ret = null;
        String sqlStmnt =
        "SELECT `cod`, `stock_inicial`, `stock`, `precio`, `link`, `fecha_registro` "+
        "FROM `catalogos` "+
        "WHERE `cod` = ? AND `active_row` = ?;";
        PreparedStatement statement = Connector.getInstance().getStatement(sqlStmnt);
        statement.setInt(1, code);
        statement.setBoolean(2, this.activeRow);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next())
        {
            ret = new Catalogue(code, resultSet.getInt(2), resultSet.getFloat(4));
            ret.setActualStock(resultSet.getInt(3));
            ret.setLink(resultSet.getString(5));
        }

        statement.close();
        return ret;
    }

    @Override
    public ArrayList<Catalogue> findAll() throws Exception
    {
        ArrayList<Catalogue> ret = new ArrayList<>();

        String sqlStmnt =
        "SELECT `cod`, `stock_inicial`, `stock`, `precio`, `link`, `fecha_registro` "+
        "FROM `catalogos` "+ 
        "WHERE `active_row` = ?;";

        PreparedStatement statement = Connector.getInstance().getStatement(sqlStmnt);
        statement.setBoolean(1, this.activeRow);

        ResultSet resultSet = statement.executeQuery();

        Catalogue catalogue;
        while (resultSet.next())
        {
            catalogue = new Catalogue(resultSet.getInt(1), resultSet.getInt(2), resultSet.getFloat(4));
            catalogue.setActualStock(resultSet.getInt(3));
            catalogue.setLink(resultSet.getString(5));
            catalogue.setRegistrationTime(resultSet.getTimestamp(6));

            ret.add(catalogue);
        }

        statement.close();

        if(ret.size() == 0)
            ret = null;

        return ret;
    }

    @Override
    public Integer update(Catalogue catalogue) throws Exception
    {
        Integer returnCode = 0;

        return returnCode;
    }

    @Override
    public Integer delete(int id) throws Exception
    {
        int errorCode = 0;

        return errorCode;
    }

    @Override
    public List<CatalogueDeliver> findCatalogueDeliveries(Integer baseClientId, Integer catalogueId) throws Exception
    {
        List<CatalogueDeliver> ret = new ArrayList<>();
		String sqlStmnt = null;
		PreparedStatement statement = null;

        if(baseClientId != null && catalogueId != null)
        {
            sqlStmnt =
			"SELECT `nro_entrega`, `id_cp`, `cod_cat`, `cant`, `precio`, `fecha_registro` "+
            "FROM `entregas_catalogos` "+
            "WHERE `id_cp` = ? AND `cod_cat` = ? AND `active_row` = ?;";

			statement = Connector.getInstance().getStatement(sqlStmnt);
			statement.setInt(1, baseClientId);
			statement.setInt(2, catalogueId);
			statement.setBoolean(3, this.activeRow);
        }
        else if(baseClientId != null && catalogueId == null)
        {
			sqlStmnt =
			"SELECT `nro_entrega`, `id_cp`, `cod_cat`, `cant`, `precio`, `fecha_registro` "+
            "FROM `entregas_catalogos` "+
            "WHERE `id_cp` = ? AND `active_row` = ?;";

			statement = Connector.getInstance().getStatement(sqlStmnt);
			statement.setInt(1, baseClientId);
			statement.setBoolean(2, this.activeRow);
        }
        else if(baseClientId == null && catalogueId != null)
        {
            // Select devs with camp numb x
        }
        else
        {
            throw new Exception("Both campaign number and preferential client id are null");
		}

		ResultSet resultSet = statement.executeQuery();

		CatalogueDeliver catalogueDeliver;
		while (resultSet.next())
		{
			catalogueDeliver = new CatalogueDeliver(resultSet.getInt(1), resultSet.getInt(4), resultSet.getFloat(5));
			catalogueDeliver.setRegistrationTime(resultSet.getTimestamp(6));
			
			//fk ids
			catalogueDeliver.setBaseClientId(baseClientId);
			catalogueDeliver.setCatalogueId(catalogueId);
            
			//Associations
			
			ret.add(catalogueDeliver);
		}

		statement.close();
		
		if(ret.size() == 0)
            ret = null;
		
        return ret;
    }
}