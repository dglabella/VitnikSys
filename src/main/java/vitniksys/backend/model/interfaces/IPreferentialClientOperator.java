package vitniksys.backend.model.interfaces;

import vitniksys.backend.model.entities.PreferentialClient;

public interface IPreferentialClientOperator extends CrudOperator<PreferentialClient>
{
    public PreferentialClient find(int id) throws Exception;

    public int delete(int id) throws Exception;
}