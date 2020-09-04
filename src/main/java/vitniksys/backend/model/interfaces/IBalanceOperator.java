package vitniksys.backend.model.interfaces;

import vitniksys.backend.model.entities.Balance;

public interface IBalanceOperator extends CrudOperator<Balance>
{
    Balance find(int id) throws Exception;

    int delete(int id) throws Exception;
}