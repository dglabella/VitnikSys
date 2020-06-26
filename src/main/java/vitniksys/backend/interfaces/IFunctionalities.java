package vitniksys.backend.interfaces;

import java.util.List;
import vitniksys.backend.model.*;
import java.util.concurrent.Future;
import vitniksys.backend.util.PedidosObtainer;

/**
*This interface implements the facade pattern design.
*View controllers should use methods in this interface whenever it 
*tries to Trigger some vitniksys functionalities (vitnik uses cases)
*/
public interface IFunctionalities
{
    /**
     * 
     * @param pedidosObtainer The object that performs a process to obtain the incoming "pedidos".
     * @throws Exception Any exception thrown by java.util.concurrent.Executors
     * and java.util.concurrent.ExecutorService
     */
    public void obtenerPedidos(PedidosObtainer pedidosObtainer) throws Exception;

    public Future<List<ClientePreferencial>> getCustomersWithNewOrders();

    public int agregarPedidos(List<ClientePreferencial> clientes) throws Exception;
    
    public int registrarDevoluciones(List<Devolucion> devoluciones)  throws Exception;

    public int registrarRecompras(List<Recompra> recompras) throws Exception;

    public int registrarCliente(ClientePreferencial clientesPreferenciales) throws Exception;

    public Camp consultarUltimaCamp() throws Exception;

    public Camp consultarCamp() throws Exception;
}