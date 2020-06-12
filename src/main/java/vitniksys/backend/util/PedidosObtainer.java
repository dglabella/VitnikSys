package vitniksys.backend.util;

import java.util.List;
import java.util.concurrent.Callable;
import vitniksys.backend.model.ClientePreferencial;

/**
 * A "PedidosObtainer" object performs a process to obtain the incoming "pedidos".
 */
public abstract class PedidosObtainer implements Callable<List<ClientePreferencial>>
{
    /**
     * This method should not be called directly, instead the object that call
     * this method, should be submited to an java.util.concurrent.ExecutorService object.
     * A PedidosObtainer object should be used like: 
     * ExecutorService.newSingleThreadExecutor().submit(pedidosObtainer);
     * If this method is called directly, if the information to obtain is much bigger
     * than usual, can freeze the System execution.
     * @return A list with all the the Clients with "incoming Orders".
     * @see ExecutorService.newSingleThreadExecutor().submit()
     */
    protected abstract List<ClientePreferencial> getInfo();

    @Override
    public List<ClientePreferencial> call()
    {
        return getInfo();
    }
}