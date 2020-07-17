package vitniksys.backend.model.interfaces;

import vitniksys.backend.model.entities.Catalogo;

public interface ICatalogoOperator extends CrudOperator<Catalogo>
{
    public Catalogo find(int codigo);

    public int delete(int codigo);
}