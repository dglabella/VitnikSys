package vitniksys.backend.model.interfaces;

import vitniksys.backend.model.entities.Pedido;

public interface IPedidoOperator extends CrudOperator<Pedido>
{
    public Pedido find(int codigo, int nroCamp);

    public int delete(int codigo, int nroCamp);
}