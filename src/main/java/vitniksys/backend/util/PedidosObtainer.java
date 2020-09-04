package vitniksys.backend.util;

import vitniksys.frontend.views.CampQueryRegisterView;

/**
 * A "PedidosObtainer" object performs a process to obtain the incoming "pedidos".
 */
public abstract class PedidosObtainer implements Runnable
{
    private CampQueryRegisterView campQueryRegisterView;

    protected PedidosObtainer(CampQueryRegisterView campQueryRegisterView)
    {
        this.campQueryRegisterView = campQueryRegisterView;
    }

    
    public CampQueryRegisterView getCampQueryRegisterView()
    {
        return this.campQueryRegisterView;
    }

    public void setCampQueryRegisterView(CampQueryRegisterView campQueryRegisterView)
    {
        this.campQueryRegisterView = campQueryRegisterView;
    }

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