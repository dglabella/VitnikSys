package vitniksys.backend.model.persistence;

import java.sql.Date;
import java.util.List;
import java.sql.Types;
import java.time.ZoneId;
import java.time.Instant;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import vitniksys.backend.model.entities.Order;
import vitniksys.backend.model.entities.Leader;
import vitniksys.backend.model.entities.Payment;
import vitniksys.backend.model.entities.Balance;
import vitniksys.backend.model.entities.Commission;
import vitniksys.backend.model.entities.Repurchase;
import vitniksys.backend.util.VitnikSearchableList;
import vitniksys.backend.model.entities.Devolution;
import vitniksys.backend.model.entities.Observation;
import vitniksys.backend.model.entities.SubordinatedClient;
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
    public PreferentialClient findShort(Integer id) throws Exception
    {
        Leader ret = null;
        String sqlStmnt =
        "SELECT `dni`, `nombre`, `apellido`, `lugar`, `fecha_nac`, `email`, `tel` "+
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
            ret = new Leader(id, resultSet.getString(2), resultSet.getString(3));
            
            Long dni = resultSet.getLong(1);
            if(!resultSet.wasNull())
                ret.setDni(dni);
            else
                ret.setDni(null);
            ret.setLocation(resultSet.getString(4));
            Date date = resultSet.getDate(5);
            if(!resultSet.wasNull())
            {
                ret.setBirthDate(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            }
            ret.setEmail(resultSet.getString(6));

            Long phone = resultSet.getLong(7);
            if(!resultSet.wasNull())
                ret.setPhoneNumber(phone);
            else
                ret.setPhoneNumber(null);
            
        }

        statement.close();
            
        return ret;
    }
    
    @Override
    public PreferentialClient find(Integer id) throws Exception
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

            Long dni = resultSet.getLong(3);
            if(!resultSet.wasNull())
                ret.setDni(dni);
            else
                ret.setDni(null);
            ret.setLocation(resultSet.getString(6));
            Date date = resultSet.getDate(7);
            if(!resultSet.wasNull())
            {
                ret.setBirthDate(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            }

            ret.setEmail(resultSet.getString(8));

            Long phone = resultSet.getLong(9);
            if(!resultSet.wasNull())
                ret.setPhoneNumber(phone);
            else
                ret.setPhoneNumber(null);

            ret.setOrders(new VitnikSearchableList<Order>(OrderOperator.getOperator().findAll(ret.getId(), null)));
            ret.setDevolutions(new VitnikSearchableList<Devolution>(DevolutionOperator.getOperator().findAll(ret.getId(), null)));
            ret.setRepurchases(new VitnikSearchableList<Repurchase>(RepurchaseOperator.getOperator().findAll(ret.getId(), null)));
            ret.setPayments(new VitnikSearchableList<Payment>(PaymentOperator.getOperator().findAll(ret.getId(), null)));
            ret.setBalances(new VitnikSearchableList<Balance>(BalanceOperator.getOperator().findAll(ret.getId(), null)));

            ret.setObservations(new VitnikSearchableList<Observation>(ObservationOperator.getOperator().findAll(ret.getId(), null)));
            ret.setCatalogueDeliveries(CatalogueOperator.getOperator().findCatalogueDeliveries(ret.getId(), null));

            ret.setCommissions(new VitnikSearchableList<Commission>(CommissionOperator.getOperator().findAll(ret.getId(), null)));
            ret.setSubordinates(SubordinatedClientOperator.getOperator().findAll(id));
            
            Iterator<SubordinatedClient> it = ret.getSubordinates().iterator();
            while(it.hasNext())
            {
                SubordinatedClient subordinatedClient = it.next();
                ret.getOrders().addAll(subordinatedClient.getOrders());
            }
        }

        statement.close();
            
        return ret;     
    }

    @Override
    public Leader find(Integer id, Integer campNumber) throws Exception
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
            
            Long dni = resultSet.getLong(3);
            if(!resultSet.wasNull())
                ret.setDni(dni);
            else
                ret.setDni(null);

            ret.setLocation(resultSet.getString(6));
            Date date = resultSet.getDate(7);
            if(!resultSet.wasNull())
            {
                ret.setBirthDate(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            }

            ret.setEmail(resultSet.getString(8));

            Long phone = resultSet.getLong(9);
            if(!resultSet.wasNull())
                ret.setPhoneNumber(phone);
            else
                ret.setPhoneNumber(null);

            ret.setOrders(new VitnikSearchableList<Order>(OrderOperator.getOperator().findAll(ret.getId(), campNumber)));
            ret.setDevolutions(new VitnikSearchableList<Devolution>(DevolutionOperator.getOperator().findAll(ret.getId(), campNumber)));
            ret.setRepurchases(new VitnikSearchableList<Repurchase>(RepurchaseOperator.getOperator().findAll(ret.getId(), campNumber)));
            ret.setPayments(new VitnikSearchableList<Payment>(PaymentOperator.getOperator().findAll(ret.getId(), campNumber)));
            ret.setBalances(new VitnikSearchableList<Balance>(BalanceOperator.getOperator().findAll(ret.getId(), campNumber)));

            ret.setObservations(new VitnikSearchableList<Observation>(ObservationOperator.getOperator().findAll(ret.getId(), campNumber)));
            ret.setCatalogueDeliveries(CatalogueOperator.getOperator().findCatalogueDeliveries(ret.getId(), campNumber));

            ret.setCommissions(new VitnikSearchableList<Commission>(CommissionOperator.getOperator().findAll(ret.getId(), campNumber)));
            ret.setSubordinates(SubordinatedClientOperator.getOperator().findAll(id));
            
            Iterator<SubordinatedClient> it = ret.getSubordinates().iterator();
            while(it.hasNext())
            {
                SubordinatedClient subordinatedClient = it.next();
                ret.getOrders().addAll(subordinatedClient.getOrders());
            }
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
            
            Long dni = resultSet.getLong(3);
            if(!resultSet.wasNull())
                leader.setDni(dni);
            else
                leader.setDni(null);

            leader.setDni(resultSet.getLong(3));
            leader.setLocation(resultSet.getString(6));
            Date date = resultSet.getDate(7);
            
            if(!resultSet.wasNull())
            {
                leader.setBirthDate(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            }
            leader.setEmail(resultSet.getString(8));

            Long phone = resultSet.getLong(9);
            if(!resultSet.wasNull())
                leader.setPhoneNumber(phone);
            else
                leader.setPhoneNumber(null);

            ret.add(leader);
        }

        statement.close();
  
        if(ret.size() == 0)
            ret = null;

        return ret;
    }

    @Override
    public Integer insert(PreferentialClient cp) throws Exception
    {
        Integer returnCode = null;
        String sqlStmnt =
        "INSERT INTO `clientes_preferenciales`(`id_cp`, `dni`, `nombre`, `apellido`, `lugar`, `fecha_nac`, `email`, `tel`, `es_lider`) "+
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);
        statement.setInt(1, cp.getId());

        if(cp.getDni() != null)
        {
            statement.setLong(2, cp.getDni());
        }
        else
        {
            statement.setNull(2, Types.BIGINT);
        }
        
        statement.setString(3, cp.getName());
        statement.setString(4, cp.getLastName());

        if(cp.getLocation() != null && !cp.getLocation().isBlank())
        {
            statement.setString(5, cp.getLocation());
        }
        else
        {
            statement.setNull(5, Types.VARCHAR);
        }

        if(cp.getBirthDate() != null)
        {
            statement.setDate(6, Date.valueOf(cp.getBirthDate()));
        }
        else
        {
            statement.setNull(6, Types.DATE);
        }

        if(cp.getEmail() != null && !cp.getEmail().isBlank())
        {
            statement.setString(7, cp.getEmail());
        }
        else
        {
            statement.setNull(7, Types.VARCHAR);
        }

        if(cp.getPhoneNumber() != null)
        {
            statement.setLong(8, cp.getPhoneNumber());
        }
        else
        {
            statement.setNull(8, Types.BIGINT);
        }

        statement.setBoolean(9, true);

        returnCode = statement.executeUpdate();
        statement.close();
  
        return returnCode;
    }

    @Override
    public Integer insertMany(List<PreferentialClient> list) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }
}