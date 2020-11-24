package vitniksys.backend.model.interfaces;

import java.util.List;
import vitniksys.backend.model.entities.Order;

public interface IOrderOperator extends CrudOperator<Order>
{
    Order find(int id) throws Exception;

    List<Order> findAll(Integer campNumb, Integer prefClientId) throws Exception;

    int delete(int id) throws Exception;
}