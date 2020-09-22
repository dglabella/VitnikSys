package vitniksys.backend.model.persistence;

import java.sql.PreparedStatement;
import vitniksys.backend.model.entities.SubordinatedClient;
import vitniksys.backend.model.entities.PreferentialClient;

public class SubordinatedClientOperator extends PreferentialClientOperator
{
    private static SubordinatedClientOperator operator;

    private SubordinatedClientOperator()
    {
        //Empty Constructor
    }

    public static SubordinatedClientOperator getOperator()
    {
        if(SubordinatedClientOperator.operator == null)
            SubordinatedClientOperator.operator = new SubordinatedClientOperator();

        return SubordinatedClientOperator.operator;
    }

    @Override
    public int insert(PreferentialClient cp) throws Exception
    {
        int returnCode = super.insert(cp);

        String sqlStmnt = "UPDATE `clientes_preferenciales` SET `id_lider`= ? WHERE `id_cp` = ?;";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);
        statement.setInt(1, ((SubordinatedClient)cp).getLeader().getId());
        statement.setInt(2, cp.getId());

        returnCode = returnCode * statement.executeUpdate();
        statement.close();

        sqlStmnt = "UPDATE `clientes_preferenciales` SET `es_lider`= ? WHERE `id_cp` = ?;";
        statement = Connector.getConnector().getStatement(sqlStmnt);
        statement.setBoolean(1, true);
        statement.setInt(2, ((SubordinatedClient)cp).getLeader().getId());

        returnCode = returnCode * statement.executeUpdate();
        statement.close();

        return returnCode;
    }
}