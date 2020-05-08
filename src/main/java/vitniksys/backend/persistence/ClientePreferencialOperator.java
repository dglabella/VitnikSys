package vitniksys.backend.persistence;

import java.util.List;

import vitniksys.backend.interfaces.IClientePreferencialOperator;
import vitniksys.backend.model.ClientePreferencial;

public class ClientePreferencialOperator implements IClientePreferencialOperator
{      
    @Override
    public int update(ClientePreferencial cp) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int insert(ClientePreferencial cp) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<ClientePreferencial> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ClientePreferencial find(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int delete(int id) {
        // TODO Auto-generated method stub
        return 0;
    }
}