package vitniksys.backend.model.persistence;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;
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
        List<Payment> ret = new ArrayList<>();
        String sqlStmnt = null;
        PreparedStatement statement = null;

        if(prefClientId != null && campNumb != null)
        {
            sqlStmnt = 
            "SELECT `cod`, `id_cp`, `nro_camp`, `descriptor`, `monto`, `item`, `forma`, `banco`, `estado`, `fecha_registro`"+
            "FROM `pagos`"+
            "WHERE `id_cp` = ? AND `nro_camp` = ? AND `active_row` = ?;";

            statement = Connector.getConnector().getStatement(sqlStmnt);
            statement.setInt(1, prefClientId);
            statement.setInt(2, campNumb);
            statement.setBoolean(3, this.activeRow);
        }
        else if(prefClientId != null && campNumb == null)
        {
			sqlStmnt = 
            "SELECT `cod`, `id_cp`, `nro_camp`, `descriptor`, `monto`, `item`, `forma`, `banco`, `estado`, `fecha_registro`"+
            "FROM `pagos`"+
            "WHERE `id_cp` = ? AND `active_row` = ?;";

            statement = Connector.getConnector().getStatement(sqlStmnt);
            statement.setInt(1, prefClientId);
            statement.setBoolean(2, this.activeRow);
        }
        else if(prefClientId == null && campNumb != null)
        {
            // Select devs with camp numb x
        }
        else
        {
            throw new Exception("Both campaign number and preferential client id are null");
		}

		ResultSet resultSet = statement.executeQuery();

        Payment payment;
		while (resultSet.next())
		{
            payment = new Payment(resultSet.getInt(1), resultSet.getString(4), resultSet.getFloat(4), resultSet.getTimestamp(10));

            //fk ids            
            payment.setPrefClientId(resultSet.getInt(2));
            payment.setCampNumber(resultSet.getInt(3));

            //Associations
            
			
			ret.add(payment);
		}

		statement.close();
		
		if(ret.size() == 0)
            ret = null;
		
        return ret;
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