package vitniksys.backend.model.persistence;

import java.util.List;
import java.sql.Types;
import java.time.Month;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import vitniksys.backend.model.entities.Campaign;
import vitniksys.backend.model.interfaces.ICampaignOperator;

public class CampaignOperator implements ICampaignOperator
{
    private static CampaignOperator operator;

    private Boolean activeRow;

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
    public Boolean isActiveRow()
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
    public CampaignOperator setActiveRow(Boolean activeRow)
    {
        this.activeRow = activeRow;
        return CampaignOperator.operator;
    }

    @Override
    public int insert(Campaign camp) throws Exception
    {
        int returnCode;
        String sqlStmnt = "INSERT INTO `camps`(`nro_camp`, `nombre`, `alias`, `mes`, `year` , `cod_cat`) VALUES"+
        "(?, ?, ?, ?, ?, ?);";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        statement.setInt(1, camp.getNumber());
        statement.setString(2, camp.getName());

        if(camp.getAlias() == null || camp.getAlias().isBlank())
        
            statement.setString(3, camp.getAlias());
        else
            statement.setNull(3, Types.VARCHAR);

        statement.setInt(4, camp.getMonth());
        statement.setInt(5, camp.getYear());

        if(camp.getCatalogue()!=null && camp.getCatalogue().getCode()!=null)
            statement.setInt(6, camp.getCatalogue().getCode());
        else
            statement.setNull(6, Types.INTEGER);

        returnCode = statement.executeUpdate();
        statement.close();
        
        return returnCode;
    }

    @Override
    public int insertMany(List<Campaign> list) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(Campaign e) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Campaign> findAll() throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Campaign find(int id) throws Exception
    {
        Campaign ret = null;
        
        String sqlStmnt = "SELECT * FROM `camps` WHERE `nro_camp` = ? AND `active_row` = ?;";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);
        statement.setInt(1, id);
        statement.setBoolean(2, this.activeRow);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next())
        {
            ret = new Campaign(resultSet.getInt(1), resultSet.getInt(4), resultSet.getInt(5));
            ret.setName(resultSet.getString(2));
            ret.setAlias(resultSet.getString(3));
            ret.setRegistrationTime(resultSet.getTimestamp(6));
            ret.setCatalogue(CatalogueOperator.getOperator().find(resultSet.getInt(7)));
        }

        statement.close();
        
        return ret;
    }

    @Override
    public Campaign find(String alias) throws Exception
    {
        Campaign ret = null;
        
        String sqlStmnt = "SELECT * FROM `camps` WHERE `alias` LIKE '%"+(alias != null && !alias.isBlank()?alias:"")+"%' AND `active_row` = ?;";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);
        
        statement.setBoolean(1, this.activeRow);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next())
        {
            ret = new Campaign(resultSet.getInt(1), resultSet.getInt(4), resultSet.getInt(5));
            ret.setName(resultSet.getString(2));
            ret.setAlias(resultSet.getString(3));
            ret.setRegistrationTime(resultSet.getTimestamp(6));
            ret.setCatalogue(CatalogueOperator.getOperator().find(resultSet.getInt(7)));
        }

        statement.close();
        
        return ret;
    }

    @Override
    public Campaign find(int month, int year) throws Exception
    {
        Campaign ret = null;
        
        String sqlStmnt = "SELECT * FROM `camps` WHERE `mes` = ? AND `year` = ? AND `active_row` = ?;";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);
        
        statement.setInt(1, month);
        statement.setInt(2, year);
        statement.setBoolean(3, this.activeRow);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next())
        {
            ret = new Campaign(resultSet.getInt(1), resultSet.getInt(4), resultSet.getInt(5));
            ret.setName(resultSet.getString(2));
            ret.setAlias(resultSet.getString(3));
            ret.setRegistrationTime(resultSet.getTimestamp(6));
            ret.setCatalogue(CatalogueOperator.getOperator().find(resultSet.getInt(7)));
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
        String sqlStmnt = "SELECT * FROM `camps` WHERE `nro_camp` = (SELECT MAX(`nro_camp`) FROM `camps` WHERE `active_row` = ?) AND `active_row` = ?;";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);
        statement.setBoolean(1, this.activeRow);
        statement.setBoolean(2, this.activeRow);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next())
        {
            ret = new Campaign(resultSet.getInt(1), resultSet.getInt(4), resultSet.getInt(5));
            ret.setName(resultSet.getString(2));
            ret.setAlias(resultSet.getString(3));
            ret.setRegistrationTime(resultSet.getTimestamp(6));
            ret.setCatalogue(CatalogueOperator.getOperator().find(resultSet.getInt(7)));
            ret.setActive(resultSet.getBoolean(8));
        }

        statement.close();
        
        return ret;  
    }

    @Override
    public int delete(int id) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }
}