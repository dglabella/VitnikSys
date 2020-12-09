package vitniksys.backend.model.persistence;

import java.sql.Date;
import java.util.List;
import java.sql.Types;
import java.time.ZoneId;
import java.time.Instant;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import vitniksys.backend.model.entities.BaseClient;
import vitniksys.backend.model.entities.PreferentialClient;

public class BaseClientOperator extends PreferentialClientOperator
{
    private static BaseClientOperator operator;

    protected BaseClientOperator()
    {
        this.activeRow = true;
    }

    public static BaseClientOperator getOperator()
    {
        if (BaseClientOperator.operator == null)
            BaseClientOperator.operator = new BaseClientOperator();

        return BaseClientOperator.operator;
    }

    /**
     * Change the flag state with which the DAO operator performs a CRUD operation.
     * Ignore this if it not exist an implementation for active or inactive rows in
     * your Data Base.
     * Default value: true.
     * @param activeRow the value for the operation.
     */
    public BaseClientOperator setActiveRow(Boolean activeRow)
    {
        this.activeRow = activeRow;
        return BaseClientOperator.operator;
    }

    @Override
    public List<PreferentialClient> findAll() throws Exception
    {
        List<PreferentialClient> ret  = new ArrayList<>();

        String sqlStmnt = "SELECT * FROM `clientes_preferenciales` WHERE `id_lider` IS NULL AND `es_lider` = ? AND `active_row` = ?;";

        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);
        statement.setBoolean(1, false);
        statement.setBoolean(2, this.activeRow);
        ResultSet resultSet = statement.executeQuery();

        BaseClient baseClient;
        while(resultSet.next())
        {
            baseClient = new BaseClient(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(4));
            
            baseClient.setDni(resultSet.getLong(2));
            baseClient.setLocation(resultSet.getString(5));
            Date date = resultSet.getDate(6);
            if(!resultSet.wasNull())
            {
                baseClient.setBirthDate(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            }
            baseClient.setEmail(resultSet.getString(7));
            baseClient.setPhoneNumber(resultSet.getLong(8));

            ret.add(baseClient);
        }

        statement.close();
  
        if(ret.size() == 0)
            ret = null;

        return ret;
    }

    @Override
    public PreferentialClient find(Integer id) throws Exception
    {
        PreferentialClient ret = null;
        String sqlStmnt = "SELECT * FROM `clientes_preferenciales` WHERE `id_cp` = ? AND `active_row` = ?;";

        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);
        statement.setBoolean(2, this.activeRow);

        if(id != null)
        {
            statement.setInt(1, id);
        }
        else
        {
            throw new Exception("Preferential Client id is null");
        }

        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next())
        {
            //if it is leader
            if(resultSet.getBoolean(10))
            {
                ret = new Leader(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(4));
            }
            else
            {
                int leaderId = resultSet.getInt(9);
                if(!resultSet.wasNull())
                {
                    ret = new SubordinatedClient(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(4));
                    ((SubordinatedClient)ret).setLeader( new Leader(leaderId));
                }
                else
                {
                    ret = new BaseClient(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(4));
                }
            }

            ret.setDni(resultSet.getLong(2));
            ret.setLocation(resultSet.getString(5));
            Date date = resultSet.getDate(6);
            if(!resultSet.wasNull())
            {
                ret.setBirthDate(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            }
            ret.setEmail(resultSet.getString(7));
            ret.setPhoneNumber(resultSet.getLong(8));
        }
        
        statement.close();
            
        return ret;      
    }

    @Override
    public int insert(PreferentialClient cp) throws Exception
    {
        int returnCode;
        String sqlStmnt = "INSERT INTO `clientes_preferenciales`(`id_cp`, `dni`, `nombre`, `apellido`, `lugar`, `fecha_nac`, "+
            "`email`, `tel`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            
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