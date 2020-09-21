package vitniksys.backend.model.interfaces;

import vitniksys.backend.model.entities.PreferentialClient;

public interface IPreferentialClientOperator extends CrudOperator<PreferentialClient>
{
    PreferentialClient find(int id) throws Exception;

    int delete(int id) throws Exception;

    int registerOrders(PreferentialClient cp) throws Exception;
}