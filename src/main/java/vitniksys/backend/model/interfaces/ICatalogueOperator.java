package vitniksys.backend.model.interfaces;

import vitniksys.backend.model.entities.Catalogue;

public interface ICatalogueOperator extends CrudOperator<Catalogue>
{
    public Catalogue find(int id);

    public int delete(int id);
}