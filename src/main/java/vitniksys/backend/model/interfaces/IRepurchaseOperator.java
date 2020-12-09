package vitniksys.backend.model.interfaces;

import java.util.List;

import vitniksys.backend.model.entities.Repurchase;

public interface IRepurchaseOperator extends CrudOperator<Repurchase>
{
    List<Repurchase> findAll(Integer prefClientId, Integer campNumb) throws Exception;

    Repurchase find(int id) throws Exception;

    int delete(int id) throws Exception;
}