package vitniksys.backend.model.interfaces;

import java.util.List;
import vitniksys.backend.model.entities.Devolution;

public interface IDevolutionOperator extends CrudOperator<Devolution>
{
    Devolution find(int id) throws Exception;

    List<Devolution> findAll(Integer campNumb, Integer prefClientId) throws Exception;

    int delete(int id) throws Exception;
}