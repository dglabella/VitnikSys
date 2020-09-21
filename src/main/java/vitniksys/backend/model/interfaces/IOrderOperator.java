package vitniksys.backend.model.interfaces;

import vitniksys.backend.model.entities.Order;

public interface IOrderOperator extends CrudOperator<Order>
{
    Order find(int id) throws Exception;

    int delete(int id) throws Exception;
}