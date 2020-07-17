package vitniksys.backend.persistence;

import java.sql.Date;
import java.sql.Types;
import java.sql.PreparedStatement;
import vitniksys.backend.model.ClienteSubordinado;
import vitniksys.backend.model.ClientePreferencial;

public class ClienteSubordinadoOperator extends ClientePreferencialOperator
{
    private static ClienteSubordinadoOperator operator;

    private ClienteSubordinadoOperator()
    {
        //Empty Constructor
    }

    public static ClienteSubordinadoOperator getOperator()
    {
        if(ClienteSubordinadoOperator.operator == null)
            ClienteSubordinadoOperator.operator = new ClienteSubordinadoOperator();

        return ClienteSubordinadoOperator.operator;
    }

    @Override
    public int insert(ClientePreferencial cp) throws Exception
    {
        int returnCode = super.insert(cp);

        String sqlStmnt = "UPDATE `clientes_preferenciales` SET `id_lider`= ? WHERE `id_cp` = ?;";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);
        statement.setInt(1, ((ClienteSubordinado)cp).getLider().getId());
        statement.setInt(2, cp.getId());

        returnCode = returnCode * statement.executeUpdate();
        statement.close();

        sqlStmnt = "UPDATE `clientes_preferenciales` SET `es_lider`= ? WHERE `id_cp` = ?;";
        statement = Connector.getConnector().getStatement(sqlStmnt);
        statement.setBoolean(1, true);
        statement.setInt(2, ((ClienteSubordinado)cp).getLider().getId());

        returnCode = returnCode * statement.executeUpdate();
        statement.close();

        return returnCode;
    }

    @Override
    public int registerOrders(ClientePreferencial cp)
    {
        System.out.println("Agregando pedidos Cliente Preferencial Subordinado");
        return 0;
    }
}