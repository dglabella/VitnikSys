package vitniksys.backend.model.interfaces;

import vitniksys.backend.model.entities.Catalogue;

public interface ICatalogueOperator extends CrudOperator<Catalogue>
{
    Catalogue find(int id) throws Exception;

    int delete(int id) throws Exception;
}