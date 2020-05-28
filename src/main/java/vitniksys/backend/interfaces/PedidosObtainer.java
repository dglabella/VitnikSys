package vitniksys.backend.interfaces;

import java.util.List;
import vitniksys.backend.model.Pedido;

/**
 * A "PedidosObtainer" object performs a process to obtain the incoming "pedidos".
 */
public interface PedidosObtainer
{
    /**
     * This method creates a thread to run the gathering information process to
     * obtain the incoming "pedidos".
     */
    public void startGatheringInfo();

    /**
     * This method should be called once the "startGatheringInfo" method is finished.
     * The "startGatheringInfo" method start the gathering information process to
     * obtain the incoming "pedidos".
     * @return A list with all the incoming "pedidos".
     */
    public List<Pedido> getInfo();  
}