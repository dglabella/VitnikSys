package vitniksys.backend.util;

import java.sql.Timestamp;
import vitniksys.backend.model.TipoArt;

public class FilaTablaPedidos {

    private int _codigoEjemplar;
    private int _nroEnvio;
    private float _precio;
    private float _precioCom;
    private String _nombre;
    private TipoArt _tipo;
    private String _letra;
    private Timestamp _fechaRetiro;
    private boolean _comisiona;

    public FilaTablaPedidos(int nroEnvio, int codigoEjemplar, float precio, float precioCom, String nombre, TipoArt tipo, String letra, Timestamp fechaRetiro, boolean comisiona) {
        _nroEnvio = nroEnvio;
        _codigoEjemplar = codigoEjemplar;
        _precio = precio;
        _precioCom = precioCom;
        _nombre = nombre;
        _tipo = tipo;
        _letra = letra;
        _fechaRetiro = fechaRetiro;
        _comisiona = comisiona;
    }
    
    // ------------ Getters && Setters ------------

    public int getCodigoEjemplar(){ return  _codigoEjemplar; }

    public void setCodigoEjemplar(int cod){ _codigoEjemplar = cod; }

    public String getLetra() {
        return _letra;
    }

    public void setLetra(String letra) {
        this._letra = letra;
    }

    public int getNroEnvio() {
        return _nroEnvio;
    }

    public void setNroEnvio(int nroEnvio) {
        this._nroEnvio = nroEnvio;
    }

    public float getPrecio() {
        return _precio;
    }

    public void setPrecio(float precio) {
        this._precio = precio;
    }

    public float getPrecioCom() { return _precioCom; }

    public void setPrecioCom(float precioCom) { _precioCom = precioCom; }

    public TipoArt getTipo() {
        return _tipo;
    }

    public void setTipo(TipoArt tipo) {
        this._tipo = tipo;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String nombre) {
        this._nombre = nombre;
    }

    public Timestamp getFechaRetiro() {
        return _fechaRetiro;
    }

    public void setFechaRetiro(Timestamp fechaRetiro) {
        this._fechaRetiro = fechaRetiro;
    }

    public boolean getComisiona(){ return _comisiona; }

    public void setComisiona(boolean comisiona){ _comisiona = comisiona; }
}