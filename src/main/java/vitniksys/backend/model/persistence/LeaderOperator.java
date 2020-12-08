package vitniksys.backend.model.persistence;

import java.sql.Date;
import java.util.List;
import java.sql.Types;
import java.time.ZoneId;
import java.time.Instant;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import vitniksys.backend.model.entities.Leader;
import vitniksys.backend.model.entities.PreferentialClient;

public class LeaderOperator extends BaseClientOperator
{
    private static LeaderOperator operator;

    private LeaderOperator()
    {
        super();
    }

    public static LeaderOperator getOperator()
    {
        if(LeaderOperator.operator == null)
            LeaderOperator.operator = new LeaderOperator();

        return LeaderOperator.operator;
    }

    /**
     * Change the flag state with which the DAO operator performs a CRUD operation.
     * Ignore this if it not exist an implementation for active or inactive rows in
     * your Data Base.
     * Default value: true.
     * @param activeRow the value for the operation.
     */
    public LeaderOperator setActiveRow(Boolean activeRow)
    {
        this.activeRow = activeRow;
        return LeaderOperator.operator;
    }

    @Override
    public Leader find(Integer id) throws Exception
    {
        return (Leader)super.find(id);
    }

    @Override
    public List<PreferentialClient> findAll() throws Exception
    {
        List<PreferentialClient> ret  = new ArrayList<>();

        String sqlStmnt = "SELECT * FROM `clientes_preferenciales` WHERE `es_lider` = ? AND `active_row` = ?;";

        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);
        statement.setBoolean(1, true);
        statement.setBoolean(2, this.activeRow);
        ResultSet resultSet = statement.executeQuery();

        Leader leader;
        while(resultSet.next())
        {
            leader = new Leader(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(4));
            
            leader.setDni(resultSet.getLong(2));
            leader.setLocation(resultSet.getString(5));
            Date date = resultSet.getDate(6);
            if(!resultSet.wasNull())
            {
                leader.setBirthDate(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            }
            leader.setEmail(resultSet.getString(7));
            leader.setPhoneNumber(resultSet.getLong(8));

            ret.add(leader);
        }

        statement.close();
  
        if(ret.size() == 0)
            ret = null;

        return ret;
    }

    @Override
    public int insert(PreferentialClient cp) throws Exception
    {
        int returnCode;
        String sqlStmnt = "INSERT INTO `clientes_preferenciales`(`id_cp`, `dni`, `nombre`, `apellido`, `lugar`, `fecha_nac`, "+
            "`email`, `tel`, `es_lider`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);
        statement.setInt(1, cp.getId());

        if(cp.getDni() != null)
            statement.setLong(2, cp.getDni());
        else
            statement.setNull(2, Types.BIGINT);
        
        statement.setString(3, cp.getName());
        statement.setString(4, cp.getLastName());

        if(cp.getLocation() != null && !cp.getLocation().isBlank())
            statement.setString(5, cp.getLocation());
        else
            statement.setNull(5, Types.VARCHAR);

        if(cp.getBirthDate() != null)
            statement.setDate(6, Date.valueOf(cp.getBirthDate()));
        else
            statement.setNull(6, Types.DATE);

        if(cp.getEmail() != null && !cp.getEmail().isBlank())
            statement.setString(7, cp.getEmail());
        else
            statement.setNull(7, Types.VARCHAR);

        if(cp.getPhoneNumber() != null)
            statement.setLong(8, cp.getPhoneNumber());
        else
            statement.setNull(8, Types.BIGINT);

        statement.setBoolean(9, true);

        returnCode = statement.executeUpdate();
        statement.close();
  
        return returnCode;
    }

    @Override
    public int insertMany(List<PreferentialClient> list) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }
}