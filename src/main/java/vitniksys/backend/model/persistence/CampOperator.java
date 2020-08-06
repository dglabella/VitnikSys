package vitniksys.backend.model.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import vitniksys.backend.model.entities.Camp;
import vitniksys.backend.model.enums.Mes;
import vitniksys.backend.model.interfaces.ICampOperator;

public class CampOperator implements ICampOperator {

    @Override
    public int insert(Camp e) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(Camp e) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Camp> findAll() throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Camp find(int id) throws Exception
    {
        Camp ret = null;
        String sqlStmnt = "";
        PreparedStatement statement = Connector.getConnector().getStatement(sqlStmnt);

        //statement.setString(parameterIndex, x);

        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next())
        {
            ret = new Camp(resultSet.getInt(1), Mes.ConvertIntToEnum(resultSet.getInt(3)), resultSet.getInt(4));
            ret.setName(resultSet.getString(2));
            ret.setRegistrationTime(resultSet.getTimestamp(5));
            ret.setCatalogo(CatalogoOperator.getOperator().find(resultSet.getInt(6)));
        }

        statement.close();
        return ret;
    }

    @Override
    public int delete(int id) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }
}