package vitniksys.backend.auxiliary;

import java.sql.Timestamp;
import vitniksys.backend.business.Motivo;
import vitniksys.backend.business.TipoArt;

public class FilaTablaDevol {

    private int _codDev;
    private int _nroEnvio;
    private int _codEjemplar;
    private Float _precio;
    private Float _precioCom;
    private String _nombre;
    private TipoArt _tipo;
    private Motivo _motivo;
    private Timestamp _fechaDev;

    public FilaTablaDevol( int codDev, int nroEnvio, int codEjemplar, Float precio, Float precioCom, String nombre, TipoArt tipo, Motivo motivo, Timestamp fechaDev) {
        _codDev = codDev;
        _nroEnvio = nroEnvio;
        _codEjemplar = codEjemplar;
        _precio = precio;
        _precioCom = precioCom;
        _nombre = nombre;
        _tipo = tipo;
        _motivo = motivo;
        _fechaDev = fechaDev;
    }

    // ---------------- Getters && Setters ----------------
    public int getCodDev(){ return _codDev; }

    public void setCodDev(int cod){ _codDev = cod; }

    public int getNroEnvio(){ return _nroEnvio; }

    public void setNroEvio(int nroEnvio){ _nroEnvio = nroEnvio; }

    public int getCodEjemplar(){ return _codEjemplar; }

    public void setCodEjemplar(int cod){ _codEjemplar = cod; }

    public float getPrecio(){ return _precio; }

    public void setPrecio(float precio){ _precio = precio; }

    public float getPrecioCom(){ return _precioCom; }

    public void setPrecioCom(float precioCom){ _precioCom = precioCom; }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String nombre) { _nombre = nombre; }

    public TipoArt getTipo(){ return _tipo; }

    public void setTipo(TipoArt tipo){ _tipo = tipo; }

    public Motivo getMotivo() { return _motivo; }

    public void setMotivo(Motivo motivo) { _motivo = motivo; }

    public Timestamp getFechaDev() { return _fechaDev; }

    public void setFechaDev(Timestamp fechaDev) { _fechaDev = fechaDev; }
}