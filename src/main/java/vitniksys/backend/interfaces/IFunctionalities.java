package vitniksys.backend.interfaces;

import java.util.List;
import vitniksys.backend.model.*;

/**
*This interface implements the facade pattern design.
*View controllers should use methods in this interface whenever it 
*tries to Trigger some vitniksys functionalities (vitnik uses cases)
*/
public interface IFunctionalities
{
    public List<ClientePreferencial> obtenerPedidos(PedidosObtainer pedidosObtainer) throws Exception;

    public int agregarPedidos(List<ClientePreferencial> clientes) throws Exception;
    
    public int registrarDevoluciones(List<Devolucion> devoluciones)  throws Exception;

    public int registrarRecompras(List<Recompra> recompras) throws Exception;

    public int registrarVendedor(ClientePreferencial clientesPreferenciales) throws Exception;

    public Camp consultarUltimaCamp() throws Exception;

    public Camp consultarCamp() throws Exception;
}