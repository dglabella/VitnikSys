package vitniksys.backend.model.interfaces;

import java.util.List;
import vitniksys.backend.model.entities.Commission;

public interface ICommissionOperator extends CrudOperator<Commission>
{
    List<Commission> findAll(Integer prefClientId, Integer campNumb) throws Exception;

    Commission find(Integer prefClientId, Integer campNumber) throws Exception;

    Integer delete(Integer prefClientId, Integer campNumber) throws Exception;
}