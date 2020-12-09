package vitniksys.backend.model.persistence;

import java.util.List;
import java.util.ArrayList;
import vitniksys.backend.model.entities.Repurchase;
import vitniksys.backend.model.interfaces.IRepurchaseOperator;

public class RepurchaseOperator implements IRepurchaseOperator
{
    private static RepurchaseOperator operator;
	private boolean activeRow;

	private RepurchaseOperator()
	{
		this.activeRow = true;
	}

	public static RepurchaseOperator getOperator()
	{
		if (RepurchaseOperator.operator == null)
        RepurchaseOperator.operator = new RepurchaseOperator();

		return RepurchaseOperator.operator;
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
    public RepurchaseOperator setActiveRow(boolean activeRow)
    {
		this.activeRow = activeRow;
		return RepurchaseOperator.operator;
    }

    @Override
    public int insert(Repurchase entity) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int insertMany(List<Repurchase> list) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(Repurchase entity) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Repurchase> findAll() throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Repurchase> findAll(Integer prefClientId, Integer campNumb) throws Exception
    {
        List<Repurchase> ret = new ArrayList<>();

        if(prefClientId != null && campNumb != null)
        {
            // Select devs with camp numb x and pref client id y
        }
        else if(prefClientId != null && campNumb == null)
        {
            // Select devs with pref client id y
        }
        else if(prefClientId == null && campNumb != null)
        {
            // Select devs with camp numb x
        }
        else
        {
            throw new Exception("Both campaign number and preferential client id are null");
        }

        if(ret.size() == 0)
            ret = null;
        
        return ret;
    }

    @Override
    public Repurchase find(int id) throws Exception
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