package vitniksys.backend.util;

import java.sql.Timestamp;
import vitniksys.backend.model.enums.Banco;
import vitniksys.backend.model.enums.Estado;
import vitniksys.backend.model.enums.ItemPago;
import vitniksys.backend.model.enums.FormaPago;

public class PagosRowTable
{    
    private Timestamp fecha;
    private int codPago;
    private String id;
    private float monto;
    private ItemPago item;
    private FormaPago tipo;
    private Estado estado;
    private Banco banco;

    public PagosRowTable(int codPago, String id, float monto, ItemPago item, 
        FormaPago tipo, Estado estado, Banco banco, Timestamp fecha)
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

    public ItemPago getItem()
    {
        return this.item;
    }

    public void setItem(ItemPago item)
    {
        this.item = item;
    }

    public FormaPago getTipo()
    {
        return this.tipo;
    }

    public void setTipo(FormaPago tipo)
    {
        this.tipo = tipo;
    }

    public Estado getEstado()
    {
        return this.estado;
    }

    public void setEstado(Estado estado)
    {
        this.estado = estado;
    }

    public Banco getBanco()
    {
        return this.banco;
    }

    public void setBanco(Banco banco)
    {
        this.banco = banco;
    } 
}