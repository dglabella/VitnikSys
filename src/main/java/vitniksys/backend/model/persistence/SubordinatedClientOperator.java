package vitniksys.backend.model.persistence;

import java.sql.Date;
import java.sql.Types;
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
        int returnCode;

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
}