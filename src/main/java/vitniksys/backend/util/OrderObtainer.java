package vitniksys.backend.util;

import java.util.List;
import vitniksys.backend.model.entities.PreferentialClient;

/**
 * A "OrderObtainer" object performs a process to obtain the incoming orders.
 */
public interface OrderObtainer
{
    /**
     * Get a list of those preferential clientes who have placed incoming orders.
     * @return A list with all the the Clients with "incoming Orders".
     */
    List<PreferentialClient> getOrderMakers();
}