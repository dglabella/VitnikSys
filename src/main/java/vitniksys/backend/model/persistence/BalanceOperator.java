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
        // Empty constructor
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
    public void setActiveRow(Boolean activeRow)
    {
        this.activeRow = activeRow;
    }

    @Override
    public int insert(Balance balance) throws Exception
    {
        int returnCode = 0;
        String sqlStmnt = "INSERT INTO `saldos`(`id_cp`, `nro_camp`) VALUES (?, ?)";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        statement.setInt(1, balance.getClient().getId());
        statement.setInt(2, balance.getCampaign().getNumber());

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

            statement.setInt(1, balance.getClient().getId());
            statement.setInt(2, balance.getCampaign().getNumber());

            returnCode += statement.executeUpdate();
        }

        statement.close();
        return returnCode;
    }

    @Override
    public int update(Balance balance) throws Exception
    {
        //String updateSQLStatement = "UPDATE `saldos` SET `pedidos_comisionables`= `pedidos_comisionables`+100, `pedidos_no_comisionables`= `pedidos_no_comisionables`+200, `catalogos`= `catalogos`+300, `recompras`= `recompras`+400, `pagos`= `pagos`+500, `devoluciones`= `devoluciones`+600, `comision`= `comision`+700, `balance`= -`pedidos_comisionables`-`pedidos_no_comisionables`-`catalogos`-`recompras`+`pagos`+`devoluciones`+`comision`WHERE `id_cp` = 555 AND `nro_camp` = 221 AND `active_row` = True;";
        // TODO Auto-generated method stub
        return 0;
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