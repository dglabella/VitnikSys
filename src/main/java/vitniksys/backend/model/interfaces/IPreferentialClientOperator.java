package vitniksys.backend.model.interfaces;

import vitniksys.backend.model.entities.PreferentialClient;

public interface IPreferentialClientOperator extends CrudOperator<PreferentialClient>
{
    PreferentialClient find(Integer id) throws Exception;

    PreferentialClient find(Integer id, Integer campNumber) throws Exception;

    Integer delete(Integer id) throws Exception;

    Integer registerOrders(PreferentialClient cp) throws Exception;
}