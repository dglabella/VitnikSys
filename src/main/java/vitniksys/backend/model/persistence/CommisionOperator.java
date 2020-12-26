package vitniksys.backend.model.persistence;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import vitniksys.backend.model.entities.Commission;
import vitniksys.backend.model.interfaces.ICommisionOperator;

public class CommisionOperator implements ICommisionOperator
{
    private static CommisionOperator operator;
	private boolean activeRow;

	private CommisionOperator()
	{
		this.activeRow = true;
	}

	public static CommisionOperator getOperator()
	{
		if (CommisionOperator.operator == null)
            CommisionOperator.operator = new CommisionOperator();

		return CommisionOperator.operator;
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
    public CommisionOperator setActiveRow(boolean activeRow)
    {
		this.activeRow = activeRow;
		return CommisionOperator.operator;
    }

    @Override
    public int insert(Commission entity) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int insertMany(List<Commission> list) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(Commission entity) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Commission> findAll() throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Commission> findAll(Integer prefClientId, Integer campNumb) throws Exception
    {
        List<Commission> ret = new ArrayList<>();
		String sqlStmnt = null;
        PreparedStatement statement = null;

        if(prefClientId != null && campNumb != null)
        {
            sqlStmnt =
			"SELECT `id_cp`, `nro_camp`, `cant_actual`, `cant_1`, `cant_2`, `cant_3`, `cant_4`, `nivel_1`, `nivel_2`, `nivel_3`, `nivel_4` "+
            "FROM `comisiones` "+
            "WHERE `id_cp` = ? AND `nro_camp` = ? AND `active_row` = ?;";

			statement = Connector.getConnector().getStatement(sqlStmnt);
			statement.setInt(1, prefClientId);
			statement.setInt(2, campNumb);
			statement.setBoolean(3, this.activeRow);
        }
        else if(prefClientId != null && campNumb == null)
        {
            sqlStmnt =
            "SELECT `id_cp`, `nro_camp`, `cant_actual`, `cant_1`, `cant_2`, `cant_3`, `cant_4`, `nivel_1`, `nivel_2`, `nivel_3`, `nivel_4` "+
            "FROM `comisiones` "+
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

		Commission commission;
		while (resultSet.next())
		{
			commission = new Commission(
                resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7), 
                resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11));
			
			//fk ids
			commission.setLeaderId(resultSet.getInt(1));
			commission.setCampNumber(resultSet.getInt(2));

			//Associations
			
			ret.add(commission);
		}

		statement.close();
		
		if(ret.size() == 0)
            ret = null;
		
        return ret;
    }

    @Override
    public Commission find(Integer prefClientId, Integer campNumber) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int delete(Integer prefClientId, Integer campNumber) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }
}