package vitniksys.backend.model.interfaces;

import java.util.List;
import vitniksys.backend.model.entities.Order;

public interface IOrderOperator extends CrudOperator<Order>
{
    List<Order> findAll(Integer prefClientId, Integer campNumb) throws Exception;

    Integer updateAllForCommission(List<Order> orders) throws Exception;

    Integer registerWithdrawals(List<Order> orders) throws Exception;

    Integer incrementForDevolution(Integer orderId) throws Exception;

    Order find(int id) throws Exception;

    Integer delete(int id) throws Exception;
}