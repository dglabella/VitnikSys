package vitniksys.backend.interfaces;

import vitniksys.backend.model.ClientePreferencial;

public interface IClientePreferencialOperator extends CrudOperator<ClientePreferencial>
{
    public ClientePreferencial find(int id);

    public int delete(int id);
}