package vitniksys.backend.interfaces;

import vitniksys.backend.model.ClientePreferencial;

public interface IClientePreferencialOperator extends CrudOperator<ClientePreferencial>
{
    public ClientePreferencial find(int id) throws Exception;

    public int delete(int id) throws Exception;
}