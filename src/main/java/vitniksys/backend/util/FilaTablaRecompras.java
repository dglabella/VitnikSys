package vitniksys.backend.util;

import java.sql.Timestamp;
import vitniksys.backend.model.TipoArt;

public class FilaTablaRecompras {

    private int _codDev;
    private int _nroEnvio;
    private int _codEjemplar;
    private float _precio;
    private float _precioRec;
    private String _nombre;
    private TipoArt _tipo;
    private Timestamp _fechaRec;

    public FilaTablaRecompras(int codDev, int nroEnvio, int codEjemplar, float precio, float precioRec, String nombre, TipoArt tipo, Timestamp fechaRec) {
        _codDev = codDev;
        _nroEnvio = nroEnvio;
        _codEjemplar = codEjemplar;
        _precio = precio;
        _precioRec = precioRec;
        _nombre = nombre;
        _tipo = tipo;
        _fechaRec = fechaRec;
    }

    //------------------ Getting && Setters ------------------

    public int getCodDev(){ return _codDev; }

    public void setCodDev(int codDev){ _codDev = codDev; }

    public int getNroEnvio(){ return _nroEnvio; }

    public void setNroEnvio(int nroEnvio){ _nroEnvio = nroEnvio; }

    public int getCodEjemplar() {
        return _codEjemplar;
    }

    public void setCodEjemplar(int codEjemplar) { _codEjemplar = codEjemplar; }

    public float getPrecio() {
        return _precio;
    }

    public void setPrecio(float precio) { _precio = precio; }

    public float getPrecioRec() {
        return _precioRec;
    }

    public void setPrecioRec(float precioRec) { _precioRec = precioRec; }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String nombre) {
        _nombre = nombre;
    }

    public TipoArt getTipo(){ return _tipo; }

    public void setTipo(TipoArt tipo){ _tipo = tipo; }

    public Timestamp getFechaRec(){ return _fechaRec; }

    public void setFechaRec(Timestamp fechaRec){ _fechaRec = fechaRec; }
}