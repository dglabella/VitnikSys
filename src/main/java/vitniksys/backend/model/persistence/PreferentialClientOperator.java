package vitniksys.backend.model.persistence;

import java.sql.Date;
import java.util.List;
import java.sql.Types;
import java.sql.PreparedStatement;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.backend.model.interfaces.IPreferentialClientOperator;

public abstract class PreferentialClientOperator implements IPreferentialClientOperator
{      
    @Override
    public int update(PreferentialClient cp)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int insert(PreferentialClient cp) throws Exception
    {
        int returnCode;
        String sqlStmnt = "INSERT INTO `clientes_preferenciales`(`id_cp`, `dni`, `nombre`, `apellido`, `lugar`, `fecha_nac`, `email`, `tel`) VALUES "+
        "(?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);
        statement.setInt(1, cp.getId());

        if(cp.getDni()!=0)
            statement.setLong(2, cp.getDni());
        else
            statement.setNull(2, Types.BIGINT);
        
        statement.setString(3, cp.getName());
        statement.setString(4, cp.getLastName());

        if(cp.getLocation() != null && (!cp.getLocation().isEmpty() || !cp.getLocation().isBlank()))
            statement.setString(5, cp.getLocation());
        else
            statement.setNull(5, Types.VARCHAR);

        if(cp.getBirthdate() != null)
            statement.setDate(6, Date.valueOf(cp.getBirthdate()));
        else
            statement.setNull(6, Types.DATE);

        if(cp.getEmail() != null && (!cp.getEmail().isEmpty() || !cp.getEmail().isBlank()))
            statement.setString(7, cp.getEmail());
        else
            statement.setNull(7, Types.VARCHAR);

        if(cp.getPhoneNumber()!=0)
            statement.setLong(8, cp.getPhoneNumber());
        else
            statement.setNull(8, Types.BIGINT);

        returnCode = statement.executeUpdate();
        statement.close();

        //BALANCE REGISTRATION
        sqlStmnt = "INSERT INTO `saldos`(`id_cp`, `nro_camp`) VALUES (?, ?)";
        statement = Connector.getConnector().getStatement(sqlStmnt);
        statement.setInt(1, cp.getId());

        
        
        return returnCode;
    }
    
    @Override
    public List<PreferentialClient> findAll() throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PreferentialClient find(int id) throws Exception
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

    public abstract int registerOrders(PreferentialClient cp) throws Exception;
}