package vitniksys.backend.util;

import java.sql.Timestamp;
import vitniksys.backend.model.enums.Bank;
import vitniksys.backend.model.enums.PayItem;
import vitniksys.backend.model.enums.PayType;
import vitniksys.backend.model.enums.PayStatus;

public class PagosRowTable
{    
    private Timestamp fecha;
    private int codPago;
    private String id;
    private float monto;
    private PayItem item;
    private PayType tipo;
    private PayStatus estado;
    private Bank banco;

    public PagosRowTable(int codPago, String id, float monto, PayItem item, 
        PayType tipo, PayStatus estado, Bank banco, Timestamp fecha)
    {
        this.fecha = fecha;
        this.codPago = codPago;
        this.id = id;
        this.monto = monto;
        this.item = item;
        this.tipo = tipo;
        this.estado = estado;
        this.banco = banco;
    }
    
    //Getters && Setters
    public Timestamp getFecha()
    {
        return this.fecha;
    }

    public void setFecha(Timestamp fecha)
    {
        this.fecha = fecha;
    }

    public int getCodPago()
    {
        return this.codPago;
    }

    public void setCodPago(int codPago)
    {
        this.codPago = codPago;
    }

    public String getId()
    {
        return this.id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public float getMonto()
    {
        return this.monto;
    }

    public void setMonto(float monto)
    {
        this.monto = monto;
    }

    public PayItem getItem()
    {
        return this.item;
    }

    public void setItem(PayItem item)
    {
        this.item = item;
    }

    public PayType getTipo()
    {
        return this.tipo;
    }

    public void setTipo(PayType tipo)
    {
        this.tipo = tipo;
    }

    public PayStatus getEstado()
    {
        return this.estado;
    }

    public void setEstado(PayStatus estado)
    {
        this.estado = estado;
    }

    public Bank getBanco()
    {
        return this.banco;
    }

    public void setBanco(Bank banco)
    {
        this.banco = banco;
    } 
}