package vitniksys.backend.persistence;

import vitniksys.backend.model.Catalogo;

public interface ICatalogoOperator extends CrudOperator<Catalogo>
{
    public Catalogo find(int codigo);

    public int delete(int codigo);
}