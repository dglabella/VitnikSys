package vitniksys.backend.model.persistence;

import java.util.List;
import java.util.Iterator;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import vitniksys.backend.model.entities.Balance;
import vitniksys.backend.model.interfaces.IBalanceOperator;

public class BalanceOperator implements IBalanceOperator
{
    private static BalanceOperator operator;
    
    private boolean activeRow;

    private BalanceOperator()
    {
        this.activeRow = true;
    }

    public static BalanceOperator getOperator()
    {
        if (BalanceOperator.operator == null)
            BalanceOperator.operator = new BalanceOperator();

        return BalanceOperator.operator;
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
    public BalanceOperator setActiveRow(boolean activeRow)
    {
        this.activeRow = activeRow;
        return BalanceOperator.operator;
    }

    @Override
    public Integer insert(Balance balance) throws Exception
    {
        Integer returnCode = null;
        String sqlStmnt =
        "INSERT INTO `saldos`(`id_cp`, `nro_camp`) "
        +"VALUES (?, ?);";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        statement.setInt(1, balance.getPrefClientId());
        statement.setInt(2, balance.getCampNumber());

        returnCode = statement.executeUpdate();
        statement.close();
        return returnCode;
    }

    @Override
    public Integer insertMany(List<Balance> list) throws Exception
    {
        Integer returnCode = 0;
        String sqlStmnt =
        "INSERT INTO `saldos`(`id_cp`, `nro_camp`) "+
        "VALUES (?, ?);";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        Balance balance;
        Iterator<Balance> listIterator = list.iterator();

        while(listIterator.hasNext())
        {
            balance = listIterator.next();

            statement.setInt(1, balance.getPrefClientId());
            statement.setInt(2, balance.getCampNumber());

            returnCode += statement.executeUpdate();
        }

        statement.close();
        return returnCode;
    }

    @Override
    public Integer update(Balance balance) throws Exception
    {
        Integer returnCode = null;

        String sqlStmnt = 
        "UPDATE `saldos` SET `pedidos`= `pedidos`+ ?, `catalogos`= `catalogos`+ ?, "+
        "`recompras`= `recompras`+ ?, `pagos`= `pagos`+ ?, `devoluciones`= `devoluciones`+ ?, "+
        "`comision`= `comision`+ ?, `balance`= -`pedidos`-`catalogos`-`recompras`+`pagos`+`devoluciones`+`comision` "+
        "WHERE `id_cp` = ? AND `nro_camp` = ? AND `active_row` = ?;";

        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        statement.setFloat(1, balance.getTotalInOrders());
        statement.setFloat(2, balance.getTotalInCatalogues());
        statement.setFloat(3, balance.getTotalInRepurchases());
        statement.setFloat(4, balance.getTotalInPayments());
        statement.setFloat(5, balance.getTotalInDevolutions());
        statement.setFloat(6, balance.getTotalInCommission());
        statement.setInt(7, balance.getPrefClientId());
        statement.setInt(8, balance.getCampNumber());
        statement.setBoolean(9, this.activeRow);

        returnCode = statement.executeUpdate();
        statement.close();
        
        return returnCode;
    }

    @Override
    public Integer correctCommission(Integer prefClientId, Integer campNumb, Float totalInCommission) throws Exception
    {
        Integer returnCode = null;
        String sqlStmnt = 
        "UPDATE `saldos` "+
        "SET `balance`= `balance`-`comision`, `comision`= ?, `balance`= `balance`+`comision` "+
        "WHERE `id_cp` = ? AND `nro_camp` = ? AND `active_row` = ?;";

        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        statement.setFloat(1,totalInCommission);
        statement.setFloat(2, prefClientId);
        statement.setFloat(3, campNumb);
        statement.setBoolean(4, this.activeRow);
        
        returnCode = statement.executeUpdate();
        statement.close();

        return returnCode;
    }
    
    @Override
    public List<Balance> findAll() throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Balance> findAll(Integer prefClientId, Integer campNumb) throws Exception
    {
        List<Balance> ret = new ArrayList<>();
        String sqlStmnt = null;
        PreparedStatement statement = null;

        if(prefClientId != null && campNumb != null)
        {
            sqlStmnt = 
            "SELECT `id_cp`, `nro_camp`, `balance`, `pedidos`, `catalogos`, `recompras`, `pagos`, `devoluciones`, `comision` "+
            "FROM `saldos` "+
            "WHERE `id_cp` = ? AND `nro_camp` = ? AND `active_row` = ? "+
            "ORDER BY `saldos`.`nro_camp` DESC;";

            statement = Connector.getConnector().getStatement(sqlStmnt);
            statement.setInt(1, prefClientId);
            statement.setInt(2, campNumb);
            statement.setBoolean(3, this.activeRow);
        }
        else if(prefClientId != null && campNumb == null)
        {
			sqlStmnt = 
            "SELECT `id_cp`, `nro_camp`, `balance`, `pedidos`, `catalogos`, `recompras`, `pagos`, `devoluciones`, `comision` "+
            "FROM `saldos` "+
            "WHERE `id_cp` = ? AND `active_row` = ? "+
            "ORDER BY `saldos`.`nro_camp` DESC;";

            statement = Connector.getConnector().getStatement(sqlStmnt);
            statement.setInt(1, prefClientId);
            statement.setBoolean(2, this.activeRow);
        }
        else if(prefClientId == null && campNumb != null)
        {
            // Select devs with camp numb x
        }
        else
        {
            throw new Exception("Both campaign number and preferential client id are null");
		}

		ResultSet resultSet = statement.executeQuery();

        Balance balance;
		while (resultSet.next())
		{
            balance = new Balance(resultSet.getFloat(4), resultSet.getFloat(5), resultSet.getFloat(6), resultSet.getFloat(7), resultSet.getFloat(8), resultSet.getFloat(9));
            balance.setBalance(resultSet.getFloat(3));

            //fk ids
            balance.setPrefClientId(resultSet.getInt(1));
            balance.setCampNumber(resultSet.getInt(2));

            //Associations
            

			ret.add(balance);
		}

		statement.close();
		
		if(ret.size() == 0)
            ret = null;
		
        return ret;
    }

    @Override
    public Balance find(int id) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer delete(int id) throws Exception
    {
        // TODO Auto-generated method stub0
        return 0;
    }
}