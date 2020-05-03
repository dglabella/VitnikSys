package vitniksys.backend.util;

import java.sql.Timestamp;
import vitniksys.backend.model.Banco;
import vitniksys.backend.model.Estado;
import vitniksys.backend.model.ItemPago;
import vitniksys.backend.model.FormaPago;

public class FilaTablaPagos
{    
    private Timestamp _fecha;
    private int _codPago;
    private String _id;
    private float _monto;
    private ItemPago _item;
    private FormaPago _tipo;
    private Estado _estado;
    private Banco _banco;

    public FilaTablaPagos(int codPago, String id, float monto, ItemPago item, FormaPago tipo, Estado estado, Banco banco, Timestamp fecha) {
        _fecha = fecha;
        _codPago = codPago;
        _id = id;
        _monto = monto;
        _item = item;
        _tipo = tipo;
        _estado = estado;
        _banco = banco;
    }
    
    // ------------- Getters && Setters -------------
    
    public Timestamp getFecha() {
        return _fecha;
    }

    public void setFecha(Timestamp fecha) {
        _fecha = fecha;
    }

    public int getCodPago() {
        return _codPago;
    }

    public void setCodPago(int cod) { _codPago = cod; }

    public String getId(){ return _id; }

    public void setId(String id){ _id = id; }

    public float getMonto() {
        return _monto;
    }

    public void setMonto(float monto) {
        _monto = monto;
    }

    public ItemPago getItemPago(){ return _item; }

    public void setItemPago(ItemPago item){ _item = item; }

    public FormaPago getTipo() {
        return _tipo;
    }

    public void setTipo(FormaPago tipo) {
        this._tipo = tipo;
    }

    public Estado getEstado() {
        return _estado;
    }

    public void setEstado(Estado estado) {
        this._estado = estado;
    }

    public Banco getBanco() {
        return _banco;
    }

    public void setBanco(Banco banco) {
        this._banco = banco;
    }
}