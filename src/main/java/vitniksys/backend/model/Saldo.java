package vitniksys.backend.model;

public class Saldo
{
    //Entity properties
    private float balance;
    private float totalInPedidosCom;
    private float totalInPedidosNonCom;
    private float totalInCatalogos;
    private float totalInRecompras;
    private float totalInPagos;
    private float totalInDevoluciones;
    private float totalInComision;

    //Domain Associations
    private ClientePreferencial cliente;
    private Camp camp;

    //Others
    private boolean active;

    public Saldo(float balance, float totalInPedidosCom, float totalInPedidosNonCom, float totalInCatalogos, 
    float totalInRecompras, float totalInPagos, float totalInDevoluciones, float totalInComision)
    {
        this.balance = balance;
        this.totalInPedidosCom = totalInPedidosCom;
        this.totalInPedidosNonCom = totalInPedidosNonCom;
        this.totalInCatalogos = totalInCatalogos;
        this.totalInRecompras = totalInRecompras;
        this.totalInPagos = totalInPagos;
        this.totalInDevoluciones = totalInDevoluciones;
        this.totalInComision = totalInComision;
    }

    //Getters && Setters
    public float getBalance()
    {
        return this.balance;
    }

    public void setBalance(float balance)
    {
        this.balance = balance;
    }

    public float getTotalInPedidosCom()
    {
        return this.totalInPedidosCom;
    }

    public void setTotalInPedidosCom(float totalInPedidosCom)
    {
        this.totalInPedidosCom = totalInPedidosCom;
    }

    public float getTotalInPedidosNonCom()
    {
        return this.totalInPedidosNonCom;
    }

    public void setTotalInPedidosNonCom(float totalInPedidosNonCom)
    {
        this.totalInPedidosNonCom = totalInPedidosNonCom;
    }

    public float getTotalInCatalogos()
    {
        return this.totalInCatalogos;
    }

    public void setTotalInCatalogos(float totalInCatalogos)
    {
        this.totalInCatalogos = totalInCatalogos;
    }

    public float getTotalInRecompras()
    {
        return this.totalInRecompras;
    }

    public void setTotalInRecompras(float totalInRecompras)
    {
        this.totalInRecompras = totalInRecompras;
    }

    public float getTotalInPagos()
    {
        return this.totalInPagos;
    }

    public void setTotalInPagos(float totalInPagos)
    {
        this.totalInPagos = totalInPagos;
    }

    public float getTotalInDevoluciones()
    {
        return this.totalInDevoluciones;
    }

    public void setTotalInDevoluciones(float totalInDevoluciones)
    {
        this.totalInDevoluciones = totalInDevoluciones;
    }

    public float getTotalInComision()
    {
        return this.totalInComision;
    }

    public void setTotalInComision(float totalInComision)
    {
        this.totalInComision = totalInComision;
    }

    /**
     * 
     * @return return the corresponding  "cliente" whose id identifies, in part, to this "saldo".
     * The BD table key (column name: id_cp).
     */
    public ClientePreferencial getCliente()
    {
        return this.cliente;
    }

    /**
     * 
     * @param cliente set the corresponding  "cliente" whose id identifies, in part, to this "saldo".
     * The BD table key (column name: id_cp).
     */
    public void setCliente(ClientePreferencial cliente)
    {
        this.cliente = cliente;
    }

    /**
     * 
     * @return return the corresponding  "camp" whose number identifies, in part, to this "saldo".
     * The BD table key (column name: nro_camp).
     */
    public Camp getCamp()
    {
        return this.camp;
    }

    /**
     * 
     * @param camp set the corresponding  "camp" whose number identifies, in part, to this "saldo".
     * The BD table key (column name: nro_camp).
     */
    public void setCamp(Camp camp)
    {
        this.camp = camp;
    }

    public boolean isActive()
    {
        return this.active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }
}