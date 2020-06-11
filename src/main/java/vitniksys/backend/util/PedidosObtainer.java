package vitniksys.backend.util;

import java.util.List;
import java.util.concurrent.Callable;
import vitniksys.backend.model.Pedido;

/**
 * A "PedidosObtainer" object performs a process to obtain the incoming "pedidos".
 */
public abstract class PedidosObtainer implements Callable<List<Pedido>>
{
    /**
     * This method should not be called directly, instead the object that call
     * this method, should be submited to an java.util.concurrent.ExecutorService object.
     * A PedidosObtainer object should be used like: 
     * ExecutorService.newSingleThreadExecutor().submit(pedidosObtainer);
     * If this method is called directly, if the information to obtain is much bigger
     * than usual, can freeze the System execution.
     * @return A list with all the incoming "pedidos".
     * @see ExecutorService.newSingleThreadExecutor().submit()
     */
    protected abstract List<Pedido> getInfo();

    @Override
    public List<Pedido> call()
    {
        return getInfo();
    }
}