package vitniksys.backend.model.entities;

import java.util.List;
import java.sql.Timestamp;
import vitniksys.backend.model.enums.Mes;

public class Camp
{
    //Entity properties
    private int number;
    private String name;
    private String alias;
    private Mes month;
    private int year;
    private Timestamp registrationTime;

    //Domain Associations
    private Catalogo catalogo;
    private List<Pago> pagos;
    private List<Pedido> pedidos;
    private List<Recompra> recompras;
    private List<Devolucion> devoluciones;
    private List<Saldo> saldos;

    //Others
    private boolean active;


    public Camp(int number)
    {
        this.number = number;
    }

    public Camp(int number, Mes month, int year)
    {
        this.number = number;
        this.month = month;
        this.year = year;
        this.name = this.month.toString()+this.year;
    }

    //Getters && Setters
    /**
     * 
     * @return return the BD table key (column name: nro_camp).
     */
    public int getNumber()
    {
        return this.number;
    }

    /**
     * 
     * @param number set the BD table key (column name: nro_camp).
     */
    public void setNumber(int number)
    {
        this.number = number;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAlias()
    {
        return this.alias;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public Mes getMonth()
    {
        return this.month;
    }

    public void setMonth(Mes month)
    {
        this.month = month;
    }

    public int getYear()
    {
        return this.year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public Timestamp getRegistrationTime()
    {
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

    public Catalogo getCatalogo()
    {
        return this.catalogo;
    }

    public void setCatalogo(Catalogo catalogo)
    {
        this.catalogo = catalogo;
    }

    public List<Pago> getPagos()
    {
        return this.pagos;
    }

    public void setPagos(List<Pago> pagos)
    {
        this.pagos = pagos;
    }

    public List<Pedido> getPedidos()
    {
        return this.pedidos;
    }

    public void setPedidos(List<Pedido> pedidos)
    {
        this.pedidos = pedidos;
    }

    public List<Recompra> getRecompras()
    {
        return this.recompras;
    }

    public void setRecompras(List<Recompra> recompras)
    {
        this.recompras = recompras;
    }

    public List<Devolucion> getDevoluciones()
    {
        return this.devoluciones;
    }

    public void setDevoluciones(List<Devolucion> devoluciones)
    {
        this.devoluciones = devoluciones;
    }

    public List<Saldo> getSaldos()
    {
        return this.saldos;
    }

    public void setSaldos(List<Saldo> saldos)
    {
        this.saldos = saldos;
    }
}