package vitniksys.backend.util;

/**
 * A "OrderObtainer" object performs a process to obtain the incoming orders.
 */
public abstract class OrderObtainer implements Runnable
{  
    @Override
    public void run()
    {
        getInfo();
    }

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
    protected abstract void getInfo();
}