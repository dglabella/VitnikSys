package vitniksys.backend.model.interfaces;

import vitniksys.backend.model.entities.Catalogue;

public interface ICatalogueOperator extends CrudOperator<Catalogue>
{
    public Catalogue find(int id) throws Exception;

    public int delete(int id) throws Exception;
}