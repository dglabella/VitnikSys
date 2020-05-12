package vitniksys.backend.interfaces;

import java.util.List;
import vitniksys.backend.model.*;

public interface IFunctionalitiesFacade
{
    public List<ClientePreferencial> obtenerPedidos(PedidosObtainer pedidosObtainer) throws Exception;

    public int agregarPedidos(List<ClientePreferencial> clientes) throws Exception;
    
    public int registrarDevoluciones(List<Devolucion> devoluciones)  throws Exception;

    public int registrarRecompras(List<Recompra> recompras) throws Exception;

    public int registrarVendedor(ClientePreferencial clientesPreferenciales) throws Exception;

    public Camp consultarUltimaCamp() throws Exception;

    public Camp consultarCamp() throws Exception;
}