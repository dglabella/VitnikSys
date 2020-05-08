package vitniksys.backend.persistence;

import java.util.List;
import vitniksys.backend.model.Pedido;
import vitniksys.backend.interfaces.IPedidoOperator;

public class PedidoOperator implements IPedidoOperator
{       
    @Override
    public int update(Pedido mo) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int insert(Pedido mo) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Pedido> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pedido find(int codigo, int nroCamp) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int delete(int codigo, int nroCamp) {
        // TODO Auto-generated method stub
        return 0;
    }
}