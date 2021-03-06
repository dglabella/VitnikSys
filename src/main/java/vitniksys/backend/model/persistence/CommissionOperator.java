package vitniksys.backend.model.persistence;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import vitniksys.backend.model.entities.Commission;
import vitniksys.backend.model.interfaces.ICommissionOperator;

public class CommissionOperator implements ICommissionOperator
{
    private static CommissionOperator operator;
	private boolean activeRow;

	private CommissionOperator()
	{
		this.activeRow = true;
	}

	public static CommissionOperator getOperator()
	{
		if (CommissionOperator.operator == null)
            CommissionOperator.operator = new CommissionOperator();

		return CommissionOperator.operator;
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
    public CommissionOperator setActiveRow(boolean activeRow)
    {
		this.activeRow = activeRow;
		return CommissionOperator.operator;
    }

    @Override
    public Integer insert(Commission commission) throws Exception
    {
        Integer returnCode = 0;
        String sqlStmnt =
        "INSERT INTO `comisiones`(`id_cp`, `nro_camp`, `cant_actual`, `nivel_actual`) "+
        "VALUES (?, ?, ?, ?);";
        PreparedStatement statement = Connector.getInstance().getStatement(sqlStmnt);

        statement.setInt(1, commission.getPrefClientId());
        statement.setInt(2, commission.getCampNumber());
        statement.setInt(3, commission.getActualQuantity());
        statement.setInt(4, commission.getActualRate());

        returnCode = statement.executeUpdate();
        statement.close();
        return returnCode;
    }

    @Override
    public Integer insertMany(List<Commission> list) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Integer update(Commission commission) throws Exception
    {
        Integer returnCode = null;
        String sqlStmnt =
        "UPDATE `comisiones` "+
        "SET `cant_actual`= ?, `nivel_actual`= ?, `cant_min`= ?, `cant_1`= ?, `cant_2`= ?, `cant_3`= ?, `nivel_1`= ?, `nivel_2`= ?, `nivel_3`= ?, `nivel_4`= ?, `nivel_fp`= ?, `nivel_otros`= ? "+
        "WHERE `id_cp` = ? AND `nro_camp` = ? AND `active_row` = ?;";

        PreparedStatement statement = Connector.getInstance().getStatement(sqlStmnt);

        statement.setInt(1, commission.getActualQuantity());
        statement.setInt(2, commission.getActualRate());
        statement.setInt(3, commission.getMinQuantity());
        statement.setInt(4, commission.getLvl1Quantity());
        statement.setInt(5, commission.getLvl2Quantity());
        statement.setInt(6, commission.getLvl3Quantity());
        
        statement.setInt(7, commission.getLvl1Factor());
        statement.setInt(8, commission.getLvl2Factor());
        statement.setInt(9, commission.getLvl3Factor());
        statement.setInt(10, commission.getLvl4Factor());

        statement.setInt(11, commission.getFpFactor()) ;
        statement.setInt(12, commission.getOtherFactor());

        statement.setInt(13, commission.getPrefClientId());
        statement.setInt(14, commission.getCampNumber());
        statement.setBoolean(15, this.activeRow);

        returnCode = statement.executeUpdate();
        statement.close();

        return returnCode;
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
			"SELECT `cant_actual`, `nivel_actual`, `cant_min`, `cant_1`, `cant_2`, `cant_3`, `nivel_1`, `nivel_2`, `nivel_3`, `nivel_4`, `nivel_fp`, `nivel_otros` "+
            "FROM `comisiones` "+
            "WHERE `id_cp` = ? AND `nro_camp` = ? AND `active_row` = ?";

			statement = Connector.getInstance().getStatement(sqlStmnt);
			statement.setInt(1, prefClientId);
			statement.setInt(2, campNumb);
			statement.setBoolean(3, this.activeRow);
        }
        else if(prefClientId != null && campNumb == null)
        {
            sqlStmnt =
			"SELECT `cant_actual`, `nivel_actual`, `cant_min`, `cant_1`, `cant_2`, `cant_3`, `nivel_1`, `nivel_2`, `nivel_3`, `nivel_4`, `nivel_fp`, `nivel_otros` "+
            "FROM `comisiones` "+
            "WHERE `id_cp` = ? AND `active_row` = ?";

			statement = Connector.getInstance().getStatement(sqlStmnt);
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

            commission = new Commission
            (
                resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6), 
                resultSet.getInt(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11), resultSet.getInt(12)
            );
			
			//fk ids
			commission.setPrefClientId(prefClientId);
			commission.setCampNumber(campNumb);

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
        Commission ret = null;
        String sqlStmnt =
        "SELECT `cant_actual`, `nivel_actual`, `cant_min`, `cant_1`, `cant_2`, `cant_3`, `nivel_1`, `nivel_2`, `nivel_3`, `nivel_4`, `nivel_fp`, `nivel_otros` "+
        "FROM `comisiones` "+
        "WHERE `id_cp` = ? AND `nro_camp` = ? AND `active_row` = ?";

        PreparedStatement statement = Connector.getInstance().getStatement(sqlStmnt);

        if(prefClientId != null && campNumber != null)
        {
            statement.setInt(1, prefClientId);
            statement.setInt(2, campNumber);
        }
        else
        {
            throw new Exception("Preferential client id is null or campaign number is null");
        }

        statement.setBoolean(3, this.activeRow);

        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next())
        {
            ret = new Commission
            (
                resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6), 
                resultSet.getInt(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11), resultSet.getInt(12)
            );

            ret.setPrefClientId(prefClientId);
            ret.setCampNumber(campNumber);
        }

        statement.close();
            
        return ret;
    }

    @Override
    public Integer delete(Integer prefClientId, Integer campNumber) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }
}