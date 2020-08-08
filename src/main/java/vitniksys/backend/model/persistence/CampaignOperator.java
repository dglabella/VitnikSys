package vitniksys.backend.model.persistence;

import java.util.List;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import vitniksys.backend.model.enums.Mes;
import vitniksys.backend.model.entities.Campaign;
import vitniksys.backend.model.interfaces.ICampaignOperator;

public class CampaignOperator implements ICampaignOperator
{
    private static CampaignOperator operator;

    private final boolean ACTIVE_ROW = true;

    private CampaignOperator()
    {
        //Empty Constructor
    }        

    public static CampaignOperator getOperator()
    {
        if(CampaignOperator.operator == null)
            CampaignOperator.operator = new CampaignOperator();

		return CampaignOperator.operator;
	}

    @Override
    public int insert(Campaign e) throws Exception
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
        statement.setBoolean(2, ACTIVE_ROW);

        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next())
        {
            ret = new Campaign(resultSet.getInt(1), Mes.ConvertIntToEnum(resultSet.getInt(4)), resultSet.getInt(5));
            ret.setName(resultSet.getString(2));
            ret.setRegistrationTime(resultSet.getTimestamp(6));
            ret.setCatalogue(CatalogueOperator.getOperator().find(resultSet.getInt(7)));
        }

        statement.close();
        return ret;
    }

    @Override
    public Campaign findLast() throws Exception
    {
        Campaign ret = null;
        //String sqlStmnt = "SELECT * FROM `camps` WHERE `active_row` = ? ORDER BY `nro_camp` DESC;";
        String sqlStmnt = "SELECT * FROM `camps` WHERE `nro_camp` = (SELECT MAX(`nro_camp`) FROM `camps` WHERE `active_row` = ?) AND `active_row` = ?;";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);
        statement.setBoolean(1, ACTIVE_ROW);
        statement.setBoolean(2, ACTIVE_ROW);

        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next())
        {
            ret = new Campaign(resultSet.getInt(1), Mes.ConvertIntToEnum(resultSet.getInt(3)), resultSet.getInt(4));
            ret.setName(resultSet.getString(2));
            ret.setRegistrationTime(resultSet.getTimestamp(5));
            ret.setCatalogue(CatalogueOperator.getOperator().find(resultSet.getInt(6)));
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