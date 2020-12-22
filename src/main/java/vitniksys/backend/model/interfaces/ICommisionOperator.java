package vitniksys.backend.model.interfaces;

import java.util.List;
import vitniksys.backend.model.entities.Commission;

public interface ICommisionOperator extends CrudOperator<Commission>
{
    List<Commission> findAll(Integer prefClientId, Integer campNumb) throws Exception;

    Commission find(Integer prefClientId, Integer campNumber) throws Exception;

    int delete(Integer prefClientId, Integer campNumber) throws Exception;
}