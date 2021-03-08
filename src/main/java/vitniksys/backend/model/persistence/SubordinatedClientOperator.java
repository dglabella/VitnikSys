package vitniksys.backend.model.persistence;

import java.sql.Date;
import java.sql.Types;
import java.util.List;
import java.time.ZoneId;
import java.time.Instant;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import vitniksys.backend.model.entities.Order;
import vitniksys.backend.model.entities.Payment;
import vitniksys.backend.model.entities.Balance;
import vitniksys.backend.util.VitnikSearchableList;
import vitniksys.backend.model.entities.Repurchase;
import vitniksys.backend.model.entities.Devolution;
import vitniksys.backend.model.entities.SubordinatedClient;
import vitniksys.backend.model.entities.PreferentialClient;

public class SubordinatedClientOperator extends PreferentialClientOperator
{
    private static SubordinatedClientOperator operator;

    private SubordinatedClientOperator()
    {
        this.activeRow = true;
    }

    public static SubordinatedClientOperator getOperator()
    {
        if(SubordinatedClientOperator.operator == null)
            SubordinatedClientOperator.operator = new SubordinatedClientOperator();

        return SubordinatedClientOperator.operator;
    }

    /**
     * Change the flag state with which the DAO operator performs a CRUD operation.
     * Ignore this if it not exist an implementation for active or inactive rows in
     * your Data Base.
     * Default value: true.
     * @param activeRow the value for the operation.
     */
    public SubordinatedClientOperator setActiveRow(boolean activeRow)
    {
        this.activeRow = activeRow;
        return SubordinatedClientOperator.operator;
    }

    @Override
    public List<PreferentialClient> findAll() throws Exception
    {
        List<PreferentialClient> ret  = new ArrayList<>();

        String sqlStmnt =
        "SELECT `id_cp`, `id_lider`, `dni`, `nombre`, `apellido`, `lugar`, `fecha_nac`, `email`, `tel`"+
        "FROM `clientes_preferenciales` "+
        "WHERE `id_lider` IS NOT NULL AND `active_row` = ?;";
        
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        statement.setBoolean(1, this.activeRow);

        ResultSet resultSet = statement.executeQuery();

        SubordinatedClient subClient;
        while(resultSet.next())
        {
            subClient = new SubordinatedClient(resultSet.getInt(1), resultSet.getString(4), resultSet.getString(5));
            
            subClient.setDni(resultSet.getLong(3));
            subClient.setLocation(resultSet.getString(6));
            Date date = resultSet.getDate(7);
            if(!resultSet.wasNull())
            {
                subClient.setBirthDate(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            }
            subClient.setEmail(resultSet.getString(8));
            subClient.setPhoneNumber(resultSet.getLong(9));

            ret.add(subClient);
        }

        statement.close();
  
        if(ret.size() == 0)
            ret = null;

        return ret;
    }

    public List<SubordinatedClient> findAll(Integer leaderId) throws Exception
    {
        List<SubordinatedClient> ret = new ArrayList<>();
		String sqlStmnt = null;
		PreparedStatement statement = null;

        if(leaderId != null)
        {
            sqlStmnt =
            "SELECT `id_cp`, `dni`, `nombre`, `apellido`, `lugar`, `fecha_nac`, `email`, `tel` "+
            "FROM `clientes_preferenciales` "+
            "WHERE `id_lider` = ? AND `active_row` = ?;";

			statement = Connector.getConnector().getStatement(sqlStmnt);
			statement.setInt(1, leaderId);
			statement.setBoolean(2, this.activeRow);
        }
        else
        {
            throw new Exception("Leader id is null");
		}

		ResultSet resultSet = statement.executeQuery();

		SubordinatedClient subClient;
		while (resultSet.next())
		{
            subClient = new SubordinatedClient(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(4));
            
            subClient.setDni(resultSet.getLong(2));
            subClient.setLocation(resultSet.getString(5));
            Date date = resultSet.getDate(6);
            if(!resultSet.wasNull())
            {
                subClient.setBirthDate(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            }
            subClient.setEmail(resultSet.getString(7));
            subClient.setPhoneNumber(resultSet.getLong(8));
            
			//fk ids
			subClient.setLeaderId(leaderId);

            //Associations
            subClient.setOrders(new VitnikSearchableList<Order>(OrderOperator.getOperator().findAll(subClient.getId(), null)));
            subClient.setDevolutions(new VitnikSearchableList<Devolution>(DevolutionOperator.getOperator().findAll(subClient.getId(), null)));
            subClient.setRepurchases(new VitnikSearchableList<Repurchase>(RepurchaseOperator.getOperator().findAll(subClient.getId(), null)));
            subClient.setPayments(new VitnikSearchableList<Payment>(PaymentOperator.getOperator().findAll(subClient.getId(), null)));
            subClient.setBalances(new VitnikSearchableList<Balance>(BalanceOperator.getOperator().findAll(subClient.getId(), null)));
            
			ret.add(subClient);
		}

		statement.close();
		
		if(ret.size() == 0)
            ret = null;
		
        return ret;
    }

    @Override
    public PreferentialClient findShort(Integer id) throws Exception
    {
        SubordinatedClient ret = null;
        String sqlStmnt =
        "SELECT `dni`, `nombre`, `apellido`, `lugar`, `fecha_nac`, `email`, `tel` "+
        "FROM `clientes_preferenciales` "+
        "WHERE `id_cp` = ? AND `id_lider` IS NOT NULL AND `es_lider` = ? AND `active_row` = ?;";

        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        if(id != null)
        {
            statement.setInt(1, id);
        }
        else
        {
            throw new Exception("SubordinatedClient id is null");
        }

        statement.setBoolean(2, false);
        statement.setBoolean(3, this.activeRow);

        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next())
        {
            ret = new SubordinatedClient(id, resultSet.getString(2), resultSet.getString(3));
            
            ret.setDni(resultSet.getLong(1));
            ret.setLocation(resultSet.getString(4));
            Date date = resultSet.getDate(5);
            if(!resultSet.wasNull())
            {
                ret.setBirthDate(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            }
            ret.setEmail(resultSet.getString(6));
            ret.setPhoneNumber(resultSet.getLong(7));
        }

        statement.close();
            
        return ret;
    }

    @Override
    public PreferentialClient find(Integer id) throws Exception
    {
        SubordinatedClient ret = null;
        
        String sqlStmnt =
        "SELECT `id_cp`, `id_lider`, `dni`, `nombre`, `apellido`, `lugar`, `fecha_nac`, `email`, `tel`"+
        "FROM `clientes_preferenciales` "+
        "WHERE `id_cp` = ? AND `id_lider` IS NOT NULL AND `active_row` = ?;";

        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        statement.setInt(1, id);
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
            ret = new SubordinatedClient(resultSet.getInt(1), resultSet.getString(4), resultSet.getString(5));
            
            ret.setDni(resultSet.getLong(3));
            ret.setLocation(resultSet.getString(6));
            Date date = resultSet.getDate(7);
            if(!resultSet.wasNull())
            {
                ret.setBirthDate(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            }
            ret.setEmail(resultSet.getString(8));
            ret.setPhoneNumber(resultSet.getLong(9));
            
			//fk ids
			ret.setLeaderId(resultSet.getInt(2));

            //Associations
            ret.setOrders(new VitnikSearchableList<Order>(OrderOperator.getOperator().findAll(ret.getId(), null)));
            ret.setDevolutions(new VitnikSearchableList<Devolution>(DevolutionOperator.getOperator().findAll(ret.getId(), null)));
            ret.setRepurchases(new VitnikSearchableList<Repurchase>(RepurchaseOperator.getOperator().findAll(ret.getId(), null)));
            ret.setPayments(new VitnikSearchableList<Payment>(PaymentOperator.getOperator().findAll(ret.getId(), null)));
            ret.setBalances(new VitnikSearchableList<Balance>(BalanceOperator.getOperator().findAll(ret.getId(), null)));
        }
        
        statement.close();
        
        return ret;
    }

    @Override
    public PreferentialClient find(Integer id, Integer campNumber) throws Exception
    {
        SubordinatedClient ret = null;
        
        String sqlStmnt =
        "SELECT `id_cp`, `id_lider`, `dni`, `nombre`, `apellido`, `lugar`, `fecha_nac`, `email`, `tel`"+
        "FROM `clientes_preferenciales` "+
        "WHERE `id_cp` = ? AND `id_lider` IS NOT NULL AND `active_row` = ?;";

        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        statement.setInt(1, id);
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
            ret = new SubordinatedClient(resultSet.getInt(1), resultSet.getString(4), resultSet.getString(5));
            
            ret.setDni(resultSet.getLong(3));
            ret.setLocation(resultSet.getString(6));
            Date date = resultSet.getDate(7);
            if(!resultSet.wasNull())
            {
                ret.setBirthDate(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            }
            ret.setEmail(resultSet.getString(8));
            ret.setPhoneNumber(resultSet.getLong(9));
            
			//fk ids
			ret.setLeaderId(resultSet.getInt(2));

            //Associations
            ret.setOrders(new VitnikSearchableList<Order>(OrderOperator.getOperator().findAll(ret.getId(), campNumber)));
            ret.setDevolutions(new VitnikSearchableList<Devolution>(DevolutionOperator.getOperator().findAll(ret.getId(), campNumber)));
            ret.setRepurchases(new VitnikSearchableList<Repurchase>(RepurchaseOperator.getOperator().findAll(ret.getId(), campNumber)));
            ret.setPayments(new VitnikSearchableList<Payment>(PaymentOperator.getOperator().findAll(ret.getId(), campNumber)));
            ret.setBalances(new VitnikSearchableList<Balance>(BalanceOperator.getOperator().findAll(ret.getId(), campNumber)));
        }
        
        statement.close();
        
        return ret;
    }

    @Override
    public Integer insert(PreferentialClient cp) throws Exception
    {
        Integer returnCode = null;

        String sqlStmnt = "INSERT INTO `clientes_preferenciales`(`id_cp`, `dni`, `nombre`, `apellido`, `lugar`, `fecha_nac`, "+
            "`email`, `tel`, `id_lider`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            
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

        statement.setInt(9, ((SubordinatedClient)cp).getLeader().getId());

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