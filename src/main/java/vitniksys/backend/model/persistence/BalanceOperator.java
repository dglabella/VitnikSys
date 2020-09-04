package vitniksys.backend.model.persistence;

import java.util.List;
import java.sql.PreparedStatement;
import vitniksys.backend.model.entities.Balance;
import vitniksys.backend.model.interfaces.IBalanceOperator;

public class BalanceOperator implements IBalanceOperator
{
    private static BalanceOperator operator;

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

    @Override
    public int insert(Balance balance) throws Exception
    {
        int returnCode;
        String sqlStmnt = "INSERT INTO `saldos`(`id_cp`, `nro_camp`) VALUES (?, ?)";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        statement.setInt(1, balance.getClient().getId());
        statement.setInt(2, balance.getCampaign().getNumber());

        returnCode = statement.executeUpdate();
        statement.close();
        return returnCode;
    }

    @Override
    public int update(Balance e) throws Exception
    {
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