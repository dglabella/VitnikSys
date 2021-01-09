package vitniksys.backend.model.persistence;

import java.sql.Date;
import java.util.List;
import java.sql.Types;
import java.time.ZoneId;
import java.time.Instant;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import vitniksys.backend.model.entities.Order;
import vitniksys.backend.model.entities.Payment;
import vitniksys.backend.model.entities.Balance;
import vitniksys.backend.model.entities.BaseClient;
import vitniksys.backend.model.entities.Devolution;
import vitniksys.backend.util.VitnikSearchableList;
import vitniksys.backend.model.entities.Repurchase;
import vitniksys.backend.model.entities.Observation;
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
    public BaseClientOperator setActiveRow(boolean activeRow)
    {
        this.activeRow = activeRow;
        return BaseClientOperator.operator;
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
        statement.setBoolean(1, false);
        statement.setBoolean(2, this.activeRow);
        ResultSet resultSet = statement.executeQuery();

        BaseClient baseClient;
        while(resultSet.next())
        {
            baseClient = new BaseClient(resultSet.getInt(1), resultSet.getString(4), resultSet.getString(5));
            
            baseClient.setDni(resultSet.getLong(3));
            baseClient.setLocation(resultSet.getString(6));
            Date date = resultSet.getDate(7);
            if(!resultSet.wasNull())
            {
                baseClient.setBirthDate(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            }
            baseClient.setEmail(resultSet.getString(8));
            baseClient.setPhoneNumber(resultSet.getLong(9));

            ret.add(baseClient);
        }

        statement.close();
  
        if(ret.size() == 0)
            ret = null;

        return ret;
    }
    
    @Override
    public BaseClient find(Integer id) throws Exception
    {
        BaseClient ret = null;
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
            throw new Exception("Base client id is null");
        }

        statement.setBoolean(2, false);
        statement.setBoolean(3, this.activeRow);

        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next())
        {
            ret = new BaseClient(resultSet.getInt(1), resultSet.getString(4), resultSet.getString(5));
            
            ret.setDni(resultSet.getLong(3));
            ret.setLocation(resultSet.getString(6));
            Date date = resultSet.getDate(7);
            if(!resultSet.wasNull())
            {
                ret.setBirthDate(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            }
            ret.setEmail(resultSet.getString(8));
            ret.setPhoneNumber(resultSet.getLong(9));

            ret.setOrders(new VitnikSearchableList<Order>(OrderOperator.getOperator().findAll(ret.getId(), null)));
            ret.setDevolutions(new VitnikSearchableList<Devolution>(DevolutionOperator.getOperator().findAll(ret.getId(), null)));
            ret.setRepurchases(new VitnikSearchableList<Repurchase>(RepurchaseOperator.getOperator().findAll(ret.getId(), null)));
            ret.setPayments(new VitnikSearchableList<Payment>(PaymentOperator.getOperator().findAll(ret.getId(), null)));
            ret.setBalances(new VitnikSearchableList<Balance>(BalanceOperator.getOperator().findAll(ret.getId(), null)));

            ret.setObservations(new VitnikSearchableList<Observation>(ObservationOperator.getOperator().findAll(ret.getId(), null)));
            ret.setCatalogueDeliveries(CatalogueOperator.getOperator().findCatalogueDeliveries(ret.getId(), null));
        }

        statement.close();
            
        return ret;
    }

    @Override
    public int insert(PreferentialClient cp) throws Exception
    {
        int returnCode;
        String sqlStmnt =
        "INSERT INTO `clientes_preferenciales`(`id_cp`, `dni`, `nombre`, `apellido`, `lugar`, `fecha_nac`, `email`, `tel`) "+
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            
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