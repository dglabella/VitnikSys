package vitniksys.backend.auxiliary;

import vitniksys.backend.business.Motivo;

public class FilaTablaStockDevs {

    private int _codEjemplar;
    private String _nombre;
    private float _precio;
    private Motivo _motivo;

    public FilaTablaStockDevs(int codEjemplar, float precio, String nombre, Motivo motivo) {
        _codEjemplar = codEjemplar;
        _nombre = nombre;
        _precio = precio;
        _motivo = motivo;
    }

    //------------------ Getting && Setters ------------------

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String nombre) {
        this._nombre = nombre;
    }

    public float getPrecio() {
        return _precio;
    }

    public void setPrecio(float precio) {
        this._precio = precio;
    }

    public int getCodEjemplar() {
        return _codEjemplar;
    }

    public void setCodEjemplar(int codEjemplar) {
        this._codEjemplar = codEjemplar;
    }

    public Motivo getMotivo() {
        return _motivo;
    }

    public void setMotivo(Motivo motivo) {
        this._motivo = motivo;
    }
}