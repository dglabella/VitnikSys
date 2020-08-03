package vitniksys.backend.model.entities;

import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.time.LocalDate;
import vitniksys.backend.model.persistence.ClientePreferencialOperator;

public abstract class ClientePreferencial
{
    //Entity properties
    private int id;
    private long dni;
    private String name;
    private String lastName;
    private String location;
    private LocalDate birthdate;
    private String email;
    private long phoneNumber;
    private Timestamp registrationTime;

    //Domain Associations
    private List<Pedido> pedidos;
    private List<Devolucion> devoluciones;
    private List<Recompra> recompras;
    private List<Pago> pagos;
    private List<Saldo> saldos;

    //Others
    private boolean active;

    //incomingOrders is supposed to be used for
    //registrate all new orders from this cp
    private List<Pedido> incomingOrders;

    public ClientePreferencial(int id)
    {
        this.id = id;
    }

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

    public long getDni()
    {
        return this.dni;
    }

    public void setDni(long dni)
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

    public long getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber)
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

    public List<Pedido> getIncomingOrders()
    {
        if(this.incomingOrders == null)
            this.incomingOrders =  new ArrayList<>();
            
        return this.incomingOrders;
    }

    public void setIncomingOrders(List<Pedido> incomingOrders)
    {
        this.incomingOrders = incomingOrders;
    }

    public List<Pedido> getPedidos()
    {
        if(this.pedidos == null)
            this.pedidos = new ArrayList<>();
                
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

    @Override
    public String toString()
    {
        return "Id: "+this.id+" -- Name: "+this.name+" -- LastName: "+this.lastName;
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