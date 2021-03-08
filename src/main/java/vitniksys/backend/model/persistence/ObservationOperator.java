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
    public Integer insert(Observation observation) throws Exception
    {
        Integer returnCode = null;
        String sqlStmnt =
        "INSERT INTO `observaciones`(`id_cp`, `nro_camp`, `observacion`) "+
        "VALUES (?, ?, ?) "+
        "ON DUPLICATE KEY UPDATE `observacion` = ?;";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        statement.setInt(1, observation.getPrefClientId());
        statement.setInt(2, observation.getCampNumber());
        statement.setString(3, observation.getObservation());
        statement.setString(4, observation.getObservation());

        returnCode = statement.executeUpdate();
        statement.close();
  
        return returnCode;
    }

    @Override
    public Integer insertMany(List<Observation> list) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Integer update(Observation entity) throws Exception
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
        
        String sqlStmnt = null;
        PreparedStatement statement = null;

        if(prefClientId != null && campNumb != null)
        {
            sqlStmnt = 
            "SELECT `id_cp`, `nro_camp`, `observacion` "+
            "FROM `observaciones` "+
            "WHERE `id_cp` = ? AND `nro_camp` = ? AND `active_row` = ?;";

            statement = Connector.getConnector().getStatement(sqlStmnt);
            statement.setInt(1, prefClientId);
            statement.setInt(2, campNumb);
            statement.setBoolean(3, this.activeRow);
        }
        else if(prefClientId != null && campNumb == null)
        {
			sqlStmnt = 
            "SELECT `id_cp`, `nro_camp`, `observacion` "+
            "FROM `observaciones` "+
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

        Observation observation;
		while (resultSet.next())
		{
            observation = new Observation(resultSet.getString(3));

            //fk ids
            observation.setPrefClientId(resultSet.getInt(1));
            observation.setCampNumber(resultSet.getInt(2));

            //Associations
            

			ret.add(observation);
        }
        
		statement.close();
		
		if(ret.size() == 0)
            ret = null;
		
        return ret;
    }

    @Override
    public Observation find(Integer prefClientId, Integer campNumber) throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer delete(Integer prefClientId, Integer campNumber) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }
}