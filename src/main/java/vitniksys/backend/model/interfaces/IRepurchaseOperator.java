package vitniksys.backend.model.interfaces;

import java.util.List;

import vitniksys.backend.model.entities.Repurchase;

public interface IRepurchaseOperator extends CrudOperator<Repurchase>
{
    List<Repurchase> findAll(Integer prefClientId, Integer campNumb) throws Exception;

    Repurchase find(Integer id) throws Exception;

    Integer delete(Integer id) throws Exception;

    Integer updateAll(List<Repurchase> repurchases) throws Exception;

    Integer setCommissionable(Integer id, boolean commissionable) throws Exception;

    Integer setReturned(Integer id) throws Exception;
}