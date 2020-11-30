package vitniksys.backend.model.persistence;

import java.util.Iterator;
import java.util.List;
import java.sql.PreparedStatement;
import vitniksys.backend.model.entities.Balance;
import vitniksys.backend.model.interfaces.IBalanceOperator;

public class BalanceOperator implements IBalanceOperator
{
    private static BalanceOperator operator;
    
    private Boolean activeRow;

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
    public BalanceOperator setActiveRow(Boolean activeRow)
    {
        this.activeRow = activeRow;
        return BalanceOperator.operator;
    }

    @Override
    public int insert(Balance balance) throws Exception
    {
        int returnCode = 0;
        String sqlStmnt = "INSERT INTO `saldos`(`id_cp`, `nro_camp`) VALUES (?, ?)";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        statement.setInt(1, balance.getPrefClientId());
        statement.setInt(2, balance.getCampNumber());

        returnCode += statement.executeUpdate();
        statement.close();
        return returnCode;
    }

    @Override
    public int insertMany(List<Balance> list) throws Exception
    {
        int returnCode = 0;
        String sqlStmnt = "INSERT INTO `saldos`(`id_cp`, `nro_camp`) VALUES (?, ?)";
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
    public int update(Balance balance) throws Exception
    {
        int returnCode = 0;
        String sqlStmnt = "UPDATE `saldos` SET `pedidos_comisionables`= `pedidos_comisionables`+ ?, "+
        "`pedidos_no_comisionables`= `pedidos_no_comisionables`+ ?, `catalogos`= `catalogos`+ ?, "+
        "`recompras`= `recompras`+ ?, `pagos`= `pagos`+ ?, `devoluciones`= `devoluciones`+ ?, "+
        "`comision`= `comision`+ ?, `balance`= -`pedidos_comisionables`-`pedidos_no_comisionables`-`catalogos`-`recompras`+`pagos`+`devoluciones`+`comision` "+
        "WHERE `id_cp` = ? AND `nro_camp` = ? AND `active_row` = ?;";

        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        statement.setFloat(1, balance.getTotalInOrdersCom());
        statement.setFloat(2, balance.getTotalInOrdersNonCom());
        statement.setFloat(3, balance.getTotalInCatalogues());
        statement.setFloat(4, balance.getTotalInRepurchases());
        statement.setFloat(5, balance.getTotalInPayments());
        statement.setFloat(6, balance.getTotalInDevolutions());
        statement.setFloat(7, balance.getTotalInCommission()) ;
        statement.setInt(8, balance.getPrefClientId());
        statement.setInt(9, balance.getCampNumber());
        statement.setBoolean(10, this.activeRow);

        returnCode += statement.executeUpdate();
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
    public Balance find(int id) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int delete(int id) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }
}