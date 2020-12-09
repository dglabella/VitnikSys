package vitniksys.backend.model.interfaces;

import java.util.List;
import vitniksys.backend.model.entities.Balance;

public interface IBalanceOperator extends CrudOperator<Balance>
{
    List<Balance> findAll(Integer prefClientId, Integer campNumb) throws Exception;

    Balance find(int id) throws Exception;

    int delete(int id) throws Exception;
}