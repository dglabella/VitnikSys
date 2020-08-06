package vitniksys.backend.model.interfaces;

import vitniksys.backend.model.entities.Camp;

public interface ICampOperator extends CrudOperator<Camp>
{
    public Camp find(int id) throws Exception;

    public int delete(int id) throws Exception;
}