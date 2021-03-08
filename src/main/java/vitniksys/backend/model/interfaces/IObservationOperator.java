package vitniksys.backend.model.interfaces;

import java.util.List;
import vitniksys.backend.model.entities.Observation;

public interface IObservationOperator extends CrudOperator<Observation>
{
    List<Observation> findAll(Integer prefClientId, Integer campNumb) throws Exception;

    Observation find(Integer prefClientId, Integer campNumb) throws Exception;

    Integer delete(Integer prefClientId, Integer campNumb) throws Exception;
}