package vitniksys.backend.model.interfaces;

import java.util.List;
import vitniksys.backend.model.entities.Catalogue;
import vitniksys.backend.model.entities.CatalogueDeliver;

public interface ICatalogueOperator extends CrudOperator<Catalogue>
{
    Catalogue find(int id) throws Exception;

    int delete(int id) throws Exception;

    List<CatalogueDeliver> findCatalogueDeliveries(Integer baseClientId, Integer catalogueId) throws Exception;
}