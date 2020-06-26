package vitniksys.backend.persistence;

import java.util.List;
import java.sql.Statement;
import java.sql.Connection;
import vitniksys.backend.model.ClientePreferencial;
import vitniksys.backend.interfaces.IClientePreferencialOperator;

public abstract class ClientePreferencialOperator implements IClientePreferencialOperator
{      
    @Override
    public int update(ClientePreferencial cp)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int insert(ClientePreferencial cp) throws Exception
    {
        int returnCode;
        //String sqlStmnt = "INSERT INTO `clientes_preferenciales`(`id_cp`, `dni`, `nombre`, `apellido`, `lugar`, `fecha_nac`, `email`, `tel`) VALUES"+
        //"("+cp.getId()+","+(cp.getDni() == 0)? null : cp.getDni()+","+cp.getName()+","+cp.getLastName()+","+cp.getLocation().isEmpty()? null : cp.getLocation()+","+cp.getBirthdate()+","+cp.getEmail()+","+cp.getPhoneNumber()+");";

        String sqlStmnt = "INSERT INTO `clientes_preferenciales`(`id_cp`, `dni`, `nombre`, `apellido`, `lugar`, `fecha_nac`, `email`, `tel`) VALUES"+
        "("+cp.getId()+","+cp.getDni()+",'"+cp.getName()+"','"+cp.getLastName()+"','"+cp.getLocation()+"',"+cp.getBirthdate()+",'"+cp.getEmail()+"',"+cp.getPhoneNumber()+");";

        Connection connection = Connector.getConnector().getConnection();
        Statement statement = connection.createStatement();
        returnCode = statement.executeUpdate(sqlStmnt);

        return returnCode;
    }

    @Override
    public List<ClientePreferencial> findAll() throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ClientePreferencial find(int id) throws Exception
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

    public abstract int agregarPedidos(ClientePreferencial cp) throws Exception;
}