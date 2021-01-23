package vitniksys.backend.model.interfaces;

import java.util.List;
import vitniksys.backend.model.entities.Order;

public interface IOrderOperator extends CrudOperator<Order>
{
    List<Order> findAll(Integer prefClientId, Integer campNumb) throws Exception;

    int updateAll(List<Order> orders) throws Exception;

    Order find(int id) throws Exception;

    int delete(int id) throws Exception;
}