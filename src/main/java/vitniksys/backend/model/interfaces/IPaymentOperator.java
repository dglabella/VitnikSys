package vitniksys.backend.model.interfaces;

import java.util.List;

import vitniksys.backend.model.entities.Payment;

public interface IPaymentOperator extends CrudOperator<Payment>
{
    List<Payment> findAll(Integer prefClientId, Integer campNumb) throws Exception;

    Payment find(int id) throws Exception;

    Integer delete(int id) throws Exception;
}