package vitniksys.backend.model.interfaces;

import vitniksys.backend.model.entities.Order;

public interface IOrderOperator extends CrudOperator<Order>
{
    public Order find(int id) throws Exception;

    public int delete(int id) throws Exception;
}