package vitniksys.backend.model.interfaces;

import java.util.List;
import vitniksys.backend.model.entities.Balance;

public interface IBalanceOperator extends CrudOperator<Balance> {
    List<Balance> findAll(Integer prefClientId, Integer campNumb) throws Exception;

    Balance find(int id) throws Exception;

    Integer delete(int id) throws Exception;

    Integer updateNotCumulative(Balance balance) throws Exception;

    Integer correctCommission(Integer balanceId, Integer campNumb, Float totalInCommission)
            throws Exception;
}
