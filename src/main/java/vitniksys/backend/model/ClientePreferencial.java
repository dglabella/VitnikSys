package vitniksys.backend.model;

import java.util.List;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;

public abstract class ClientePreferencial
{
    //Entity properties
    private int id;
    private Long dni;
    private String name;
    private String lastName;
    private String location;
    private LocalDate birthdate;
    private String email;
    private Long phoneNumber;
    private Timestamp registrationTime;
    //Associations
    private List<Pedido> pedidos;
    private List<Devolucion> devoluciones;
    private List<Recompra> recompras;
    private List<Pago> pagos;
    private List<EntregaDeCatalogo> entregaDeCatalogos;
    private List<Saldo> Saldos;
}