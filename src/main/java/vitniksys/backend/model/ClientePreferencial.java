package vitniksys.backend.model;

import java.util.List;
import java.sql.Timestamp;
import java.time.LocalDate;
import vitniksys.backend.persistence.ClientePreferencialOperator;

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

    //Domain Associations
    private List<Pedido> pedidos;
    private List<Devolucion> devoluciones;
    private List<Recompra> recompras;
    private List<Pago> pagos;
    private List<Saldo> saldos;
    private List<EntregaCatalogo> entregaCatalogos;

    //Others
    private boolean active;
    private List<Pedido> pedidosEntrantes;

    public ClientePreferencial(int id, String name, String lastName)
    {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }

    //Getters && Setters
    /**
     * 
     * @return return the BD table key (column name: id_cp).
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * 
     * @param id set the BD table key (column name: id).
     */
    public void setId(int id)
    {
        this.id = id;
    }

    public Long getDni()
    {
        return this.dni;
    }

    public void setDni(Long dni)
    {
        this.dni = dni;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public LocalDate getBirthdate()
    {
        return this.birthdate;
    }

    public void setBirthdate(LocalDate birthdate)
    {
        this.birthdate = birthdate;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public Timestamp getRegistrationTime() {
        return this.registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime)
    {
        this.registrationTime = registrationTime;
    }

    public boolean isActive()
    {
        return this.active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public List<Pedido> getPedidos()
    {
        return this.pedidos;
    }

    public void setPedidos(List<Pedido> pedidos)
    {
        this.pedidos = pedidos;
    }

    public List<Devolucion> getDevoluciones()
    {
        return this.devoluciones;
    }

    public void setDevoluciones(List<Devolucion> devoluciones)
    {
        this.devoluciones = devoluciones;
    }

    public List<Recompra> getRecompras()
    {
        return this.recompras;
    }

    public void setRecompras(List<Recompra> recompras)
    {
        this.recompras = recompras;
    }

    public List<Pago> getPagos()
    {
        return this.pagos;
    }

    public void setPagos(List<Pago> pagos)
    {
        this.pagos = pagos;
    }

    public List<Saldo> getSaldos()
    {
        return this.saldos;
    }

    public void setSaldos(List<Saldo> saldos)
    {
        this.saldos = saldos;
    }

    public List<EntregaCatalogo> getEntregaCatalogos()
    {
        return this.entregaCatalogos;
    }

    public void setEntregaCatalogos(List<EntregaCatalogo> entregaCatalogos)
    {
        this.entregaCatalogos = entregaCatalogos;
    }    

    /**
     * This method supplies an easy way to get the Data Acces Object (DAO).
     * Using polymorphism, this method delegates creating a CRUD Operator.
     * This method is supposed to be only available in objects that are
     * instances of a subclass.
     * Call this method to obtain the DAO for this object. 
     * @return the DAO operator.
     */
    public abstract ClientePreferencialOperator operator();
}