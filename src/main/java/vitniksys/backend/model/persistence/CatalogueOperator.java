package vitniksys.backend.model.persistence;

import java.util.List;
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

    public int insert(Catalogue catalogue)
    {
        int errorCode = 0;

        return errorCode;
    }

    @Override
    public int insertMany(List<Catalogue> list) throws Exception
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
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);
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
        ArrayList<Catalogue> catalogos = new ArrayList<>();
        return catalogos;
    }

    @Override
    public int update(Catalogue catalogue) throws Exception
    {
        int errorCode = 0;

        return errorCode;
    }

    @Override
    public int delete(int id) throws Exception
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

			statement = Connector.getConnector().getStatement(sqlStmnt);
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

			statement = Connector.getConnector().getStatement(sqlStmnt);
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