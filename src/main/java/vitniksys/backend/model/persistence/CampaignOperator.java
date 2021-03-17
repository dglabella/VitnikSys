package vitniksys.backend.model.persistence;

import java.util.List;
import java.sql.Types;
import java.time.Month;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import vitniksys.backend.model.entities.Campaign;
import vitniksys.backend.model.entities.Catalogue;
import vitniksys.backend.model.interfaces.ICampaignOperator;

public class CampaignOperator implements ICampaignOperator
{
    private static CampaignOperator operator;

    private boolean activeRow;

    private CampaignOperator()
    {
        this.activeRow = true;
    }

    public static CampaignOperator getOperator()
    {
        if (CampaignOperator.operator == null)
            CampaignOperator.operator = new CampaignOperator();

        return CampaignOperator.operator;
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
    public CampaignOperator setActiveRow(boolean activeRow)
    {
        this.activeRow = activeRow;
        return CampaignOperator.operator;
    }

    @Override
    public Integer insert(Campaign camp) throws Exception
    {
        Integer returnCode = null;

        String sqlStmnt =
        "INSERT INTO `camps`(`nro_camp`, `alias`, `mes`, `year`, `cod_cat`) "+
        "VALUES (?, ?, ?, ?, ?);";

        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        statement.setInt(1, camp.getNumber());

        if(camp.getAlias() != null && !camp.getAlias().isBlank())
            statement.setString(2, camp.getAlias());
        else
            statement.setNull(2, Types.VARCHAR);

        statement.setInt(3, camp.getMonth());
        statement.setInt(4, camp.getYear());

        if(camp.getCatalogue() != null && camp.getCatalogue().getCode() != null)
            statement.setInt(5, camp.getCatalogue().getCode());
        else
            statement.setNull(5, Types.INTEGER);

        returnCode = statement.executeUpdate();
        statement.close();
        
        return returnCode;
    }

    @Override
    public Integer insertMany(List<Campaign> list) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Integer update(Campaign campaign) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Campaign> findAll() throws Exception
    {
        List<Campaign> ret = new ArrayList<>();
        
        String sqlStmnt =
        "SELECT `nro_camp`, `cod_cat`, `alias`, `mes`, `year`, camps.`fecha_registro`, `stock_inicial`, `stock`, `precio`, `link`, catalogos.`fecha_registro` "+
        "FROM `camps` "+
        "LEFT JOIN `catalogos` ON camps.cod_cat = catalogos.cod "+
        "WHERE camps.active_row = ? AND (catalogos.active_row = ? OR catalogos.active_row IS NULL) "+
        "ORDER BY `nro_camp` DESC;";

        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);
        statement.setBoolean(1, this.activeRow);
        statement.setBoolean(2, CatalogueOperator.getOperator().isActiveRow());

        ResultSet resultSet = statement.executeQuery();

        Campaign camp;
        Catalogue catalogue;
        Integer codCat;

        while (resultSet.next())
        {
            camp = new Campaign(resultSet.getInt(1), resultSet.getInt(4), resultSet.getInt(5));
            camp.setAlias(resultSet.getString(3));
            camp.setRegistrationTime(resultSet.getTimestamp(6));

            codCat = resultSet.getInt(2);
            if(!resultSet.wasNull())
            {
                catalogue = new Catalogue(codCat, resultSet.getInt(7), resultSet.getFloat(9));
                catalogue.setActualStock(resultSet.getInt(8));
                catalogue.setLink(resultSet.getString(10));
                catalogue.setRegistrationTime(resultSet.getTimestamp(11));

                //fk ids
                camp.setCatalogueCode(codCat);

                //Associations
                camp.setCatalogue(catalogue);
            }

            ret.add(camp);
        }

        statement.close();
        
        if(ret.size() == 0)
            ret = null;

        return ret;
    }

    @Override
    public Campaign find(int id) throws Exception
    {
        Campaign ret = null;
        
        String sqlStmnt =
        "SELECT `nro_camp`, `cod_cat`, `alias`, `mes`, `year`, `fecha_registro` "+
        "FROM `camps` "+
        "WHERE `nro_camp` = ? AND `active_row` = ?;";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);
        statement.setInt(1, id);
        statement.setBoolean(2, this.activeRow);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next())
        {
            ret = new Campaign(resultSet.getInt(1), resultSet.getInt(4), resultSet.getInt(5));
            ret.setAlias(resultSet.getString(3));
            ret.setRegistrationTime(resultSet.getTimestamp(6));

            //fk ids
            ret.setCatalogueCode(resultSet.getInt(2));

            //Associations
            ret.setCatalogue(CatalogueOperator.getOperator().find(resultSet.getInt(2)));
        }

        statement.close();
        
        return ret;
    }

    @Override
    public List<Campaign> findAll(String alias) throws Exception
    {
        List<Campaign> ret = new ArrayList<>();

        String sqlStmnt =
        "SELECT `nro_camp`, `cod_cat`, `alias`, `mes`, `year`, camps.`fecha_registro`, `stock_inicial`, `stock`, `precio`, `link`, catalogos.`fecha_registro` "+
        "FROM `camps` "+
        "LEFT JOIN `catalogos` ON camps.cod_cat = catalogos.cod "+
        "WHERE `alias` LIKE '%"+(alias != null && !alias.isBlank()?alias:"")+"%' AND camps.active_row = ? AND (catalogos.active_row = ? OR catalogos.active_row IS NULL) "+
        "ORDER BY `nro_camp` DESC;";

        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);
        statement.setBoolean(1, this.activeRow);
        statement.setBoolean(2, CatalogueOperator.getOperator().isActiveRow());

        ResultSet resultSet = statement.executeQuery();

        Campaign camp;
        Catalogue catalogue;
        Integer codCat;

        while (resultSet.next())
        {
            camp = new Campaign(resultSet.getInt(1), resultSet.getInt(4), resultSet.getInt(5));
            camp.setAlias(resultSet.getString(3));
            camp.setRegistrationTime(resultSet.getTimestamp(6));

            codCat = resultSet.getInt(2);
            if(!resultSet.wasNull())
            {
                catalogue = new Catalogue(codCat, resultSet.getInt(7), resultSet.getFloat(9));
                catalogue.setActualStock(resultSet.getInt(8));
                catalogue.setLink(resultSet.getString(10));
                catalogue.setRegistrationTime(resultSet.getTimestamp(11));

                //fk ids
                camp.setCatalogueCode(codCat);

                //Associations
                camp.setCatalogue(catalogue);
            }

            ret.add(camp);
        }

        statement.close();
        
        if(ret.size() == 0)
            ret = null;

        return ret;
    }

    @Override
    public Campaign find(int month, int year) throws Exception
    {
        Campaign ret = null;
        
        String sqlStmnt =
        "SELECT `nro_camp`, `cod_cat`, `alias`, `mes`, `year`, `fecha_registro` "+
        "FROM `camps` "+
        "WHERE `mes` = ? AND `year` = ? AND `active_row` = ?;";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);
        
        statement.setInt(1, month);
        statement.setInt(2, year);
        statement.setBoolean(3, this.activeRow);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next())
        {
            ret = new Campaign(resultSet.getInt(1), resultSet.getInt(4), resultSet.getInt(5));
            ret.setAlias(resultSet.getString(3));
            ret.setRegistrationTime(resultSet.getTimestamp(6));

            //fk ids
            ret.setCatalogueCode(resultSet.getInt(2));

            //Associations
            ret.setCatalogue(CatalogueOperator.getOperator().find(resultSet.getInt(2)));
        }

        statement.close();
        
        return ret;
    }

    @Override
    public List<Campaign> findAll(Month month) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Campaign> findAll(int year) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Campaign> findByCatalogue(int code) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Campaign findLast() throws Exception
    {
        Campaign ret = null;
        
        // String sqlStmnt = "SELECT * FROM `camps` WHERE `active_row` = ? ORDER BY
        // `nro_camp` DESC;";
        String sqlStmnt =
        "SELECT `nro_camp`, `cod_cat`, `alias`, `mes`, `year`, `fecha_registro` "+
        "FROM `camps` "+
        "WHERE `nro_camp` = (SELECT MAX(`nro_camp`) FROM `camps` WHERE `active_row` = ?) AND `active_row` = ?;";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);
        statement.setBoolean(1, this.activeRow);
        statement.setBoolean(2, this.activeRow);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next())
        {
            ret = new Campaign(resultSet.getInt(1), resultSet.getInt(4), resultSet.getInt(5));
            ret.setAlias(resultSet.getString(3));
            ret.setRegistrationTime(resultSet.getTimestamp(6));

            //fk ids
            ret.setCatalogueCode(resultSet.getInt(2));

            //Associations
            ret.setCatalogue(CatalogueOperator.getOperator().find(resultSet.getInt(2)));
        }

        statement.close();
        
        return ret;  
    }

    @Override
    public Integer delete(int id) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }
}