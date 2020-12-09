package vitniksys.backend.model.persistence;

import java.util.List;
import vitniksys.backend.model.entities.Payment;
import vitniksys.backend.model.interfaces.IPaymentOperator;

public class PaymentOperator implements IPaymentOperator
{
    private static PaymentOperator operator;
	private boolean activeRow;

	private PaymentOperator()
	{
		this.activeRow = true;
	}

	public static PaymentOperator getOperator()
	{
		if (PaymentOperator.operator == null)
        PaymentOperator.operator = new PaymentOperator();

		return PaymentOperator.operator;
	}

	/**
     * Get the flag state with which the DAO operator performs a CRUD operation.
     * Ignore this if it not exist an implementation for active or inactive rows in
     * your Data Base.
     * Default value: true.
     * @return The state of the entity.
     */
    public boolean isActiveRow()
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
    public PaymentOperator setActiveRow(boolean activeRow)
    {
		this.activeRow = activeRow;
		return PaymentOperator.operator;
    }

    @Override
    public int insert(Payment entity) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int insertMany(List<Payment> list) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(Payment entity) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Payment> findAll() throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Payment> findAll(Integer prefClientId, Integer campNumb) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Payment find(int id) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int delete(int id) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }
}