package vitniksys.backend.model.persistence;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import vitniksys.backend.model.entities.Observation;
import vitniksys.backend.model.interfaces.IObservationOperator;

public class ObservationOperator implements IObservationOperator
{
    private static ObservationOperator operator;
	private boolean activeRow;

	private ObservationOperator()
	{
		this.activeRow = true;
	}

	public static ObservationOperator getOperator()
	{
		if (ObservationOperator.operator == null)
        ObservationOperator.operator = new ObservationOperator();

		return ObservationOperator.operator;
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
    public ObservationOperator setActiveRow(boolean activeRow)
    {
		this.activeRow = activeRow;
		return ObservationOperator.operator;
    }

    @Override
    public int insert(Observation entity) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int insertMany(List<Observation> list) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(Observation entity) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Observation> findAll() throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Observation> findAll(Integer prefClientId, Integer campNumb) throws Exception
    {
        List<Observation> ret = new ArrayList<>();
        /*
        String sqlStmnt = null;
        PreparedStatement statement = null;

        if(prefClientId != null && campNumb != null)
        {
            sqlStmnt = 
            "";

            statement = Connector.getConnector().getStatement(sqlStmnt);
            statement.setInt(1, prefClientId);
            statement.setInt(2, campNumb);
            statement.setBoolean(3, this.activeRow);
        }
        else if(prefClientId != null && campNumb == null)
        {
			sqlStmnt = 
            "";

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

        Observation observation;
		while (resultSet.next())
		{
            observation = new Balance(resultSet.getFloat(4), resultSet.getFloat(5), resultSet.getFloat(6), resultSet.getFloat(7), resultSet.getFloat(8), resultSet.getFloat(9), resultSet.getFloat(10));
            observation.setBalance(resultSet.getFloat(3));

            //fk ids
            observation.setPrefClientId(resultSet.getInt(1));
            observation.setCampNumber(resultSet.getInt(2));

            //Associations
            

			ret.add(observation);
        }
        
		statement.close();
		
		if(ret.size() == 0)
            ret = null;

        */
		
        return ret;
    }

    @Override
    public Observation find(int id) throws Exception
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