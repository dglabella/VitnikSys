package vitniksys.backend.model.persistence;

import java.util.List;

import vitniksys.backend.model.entities.Order;
import vitniksys.backend.model.interfaces.IOrderOperator;

public class OrderOperator implements IOrderOperator
{
	private static OrderOperator operator;
	private Boolean activeRow;

	private OrderOperator()
	{
		// Empty constructor
	}

	public static OrderOperator getOperator()
	{
		if (OrderOperator.operator == null)
			OrderOperator.operator = new OrderOperator();

		return OrderOperator.operator;
	}

	/**
     * Get the flag state with which the DAO operator performs a CRUD operation.
     * Ignore this if it not exist an implementation for active or inactive rows in
     * your Data Base.
     * Default value: true.
     * @return The state of the entity.
     */
    public Boolean isActiveRow()
    {
        return this.activeRow;
    }

    /**
     * Change the flag state with which the DAO operator performs a CRUD operation.
     * Ignore this if it not exist an implementation for active or inactive rows in
     * your Data Base.
     * Default value: true.
     * @param activeRow the value for the operation.
     */
    public void setActiveRow(Boolean activeRow)
    {
        this.activeRow = activeRow;
    }

	@Override
	public int insert(Order e) throws Exception
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Order e) throws Exception
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Order> findAll() throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order find(int id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int id)
	{
		// TODO Auto-generated method stub
		return 0;
	}
}