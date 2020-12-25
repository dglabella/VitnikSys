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
    public LeaderOperator setActiveRow(boolean activeRow)
    {
        this.activeRow = activeRow;
        return LeaderOperator.operator;
    }

    
    @Override
    public Leader find(Integer id) throws Exception
    {
        Leader ret = null;
        String sqlStmnt =
        "SELECT `id_cp`, `id_lider`, `dni`, `nombre`, `apellido`, `lugar`, `fecha_nac`, `email`, `tel` "+
        "FROM `clientes_preferenciales` "+
        "WHERE `id_cp` = ? AND `id_lider` IS NULL AND `es_lider` = ? AND `active_row` = ?;";

        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        if(id != null)
        {
            statement.setInt(1, id);
        }
        else
        {
            throw new Exception("Leader id is null");
        }

        statement.setBoolean(2, true);
        statement.setBoolean(3, this.activeRow);

        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next())
        {
            ret = new Leader(resultSet.getInt(1), resultSet.getString(4), resultSet.getString(5));
            
            ret.setDni(resultSet.getLong(3));
            ret.setLocation(resultSet.getString(6));
            Date date = resultSet.getDate(7);
            if(!resultSet.wasNull())
            {
                ret.setBirthDate(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            }

            ret.setEmail(resultSet.getString(8));
            ret.setPhoneNumber(resultSet.getLong(9));

            ret.setOrders(OrderOperator.getOperator().findAll(ret.getId(), null));
            ret.setDevolutions(DevolutionOperator.getOperator().findAll(ret.getId(), null));
            ret.setRepurchases(RepurchaseOperator.getOperator().findAll(ret.getId(), null));
            ret.setPayments(PaymentOperator.getOperator().findAll(ret.getId(), null));
            ret.setBalances(BalanceOperator.getOperator().findAll(ret.getId(), null));

            ret.setObservations(ObservationOperator.getOperator().findAll(ret.getId(), null));
            ret.setCatalogueDeliveries(CatalogueOperator.getOperator().findCatalogueDeliveries(ret.getId(), null));

            ret.setCommissions(CommisionOperator.getOperator().findAll(ret.getId(), null));
            ret.setSubordinates(SubordinatedClientOperator.getOperator().findAll(id));
        }

        statement.close();
            
        return ret;     
    }

    @Override
    public List<PreferentialClient> findAll() throws Exception
    {
        List<PreferentialClient> ret  = new ArrayList<>();

        String sqlStmnt =
        "SELECT `id_cp`, `id_lider`, `dni`, `nombre`, `apellido`, `lugar`, `fecha_nac`, `email`, `tel` "+
        "FROM `clientes_preferenciales` "+
        "WHERE `id_lider` IS NULL AND `es_lider` = ? AND `active_row` = ?;";

        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);
        statement.setBoolean(1, true);
        statement.setBoolean(2, this.activeRow);
        ResultSet resultSet = statement.executeQuery();

        Leader leader;
        while(resultSet.next())
        {
            leader = new Leader(resultSet.getInt(1), resultSet.getString(4), resultSet.getString(5));
            
            leader.setDni(resultSet.getLong(3));
            leader.setLocation(resultSet.getString(6));
            Date date = resultSet.getDate(7);
            
            if(!resultSet.wasNull())
            {
                leader.setBirthDate(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            }
            leader.setEmail(resultSet.getString(8));
            leader.setPhoneNumber(resultSet.getLong(9));

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
        String sqlStmnt =
        "INSERT INTO `clientes_preferenciales`(`id_cp`, `dni`, `nombre`, `apellido`, `lugar`, `fecha_nac`, `email`, `tel`, `es_lider`) "+
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

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