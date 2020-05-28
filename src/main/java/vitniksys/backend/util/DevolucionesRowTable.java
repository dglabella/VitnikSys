package vitniksys.backend.util;

import java.sql.Timestamp;
import vitniksys.backend.enums.Motivo;
import vitniksys.backend.enums.TipoArt;

public class DevolucionesRowTable 
{
    private int codDev;
    private int nroEnvio;
    private int codEjemplar;
    private Float precio;
    private Float precioCom;
    private String nombre;
    private TipoArt tipo;
    private Motivo motivo;
    private Timestamp fechaDev;

    public DevolucionesRowTable( int codDev, int nroEnvio, int codEjemplar, Float precio, 
        Float precioCom, String nombre, TipoArt tipo, Motivo motivo, Timestamp fechaDev)
    {
        this.codDev = codDev;
        this.nroEnvio = nroEnvio;
        this.codEjemplar = codEjemplar;
        this.precio = precio;
        this.precioCom = precioCom;
        this.nombre = nombre;
        this.tipo = tipo;
        this.motivo = motivo;
        this.fechaDev = fechaDev;
    }

    //Getters && Setters
    public int getCodDev()
    {
        return this.codDev;
    }

    public void setCodDev(int codDev)
    {
        this.codDev = codDev;
    }

    public int getNroEnvio()
    {
        return this.nroEnvio;
    }

    public void setNroEnvio(int nroEnvio)
    {
        this.nroEnvio = nroEnvio;
    }

    public int getCodEjemplar()
    {
        return this.codEjemplar;
    }

    public void setCodEjemplar(int codEjemplar)
    {
        this.codEjemplar = codEjemplar;
    }

    public Float getPrecio()
    {
        return this.precio;
    }

    public void setPrecio(Float precio)
    {
        this.precio = precio;
    }

    public Float getPrecioCom()
    {
        return this.precioCom;
    }

    public void setPrecioCom(Float precioCom)
    {
        this.precioCom = precioCom;
    }

    public String getNombre()
    {
        return this.nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public TipoArt getTipo()
    {
        return this.tipo;
    }

    public void setTipo(TipoArt tipo)
    {
        this.tipo = tipo;
    }

    public Motivo getMotivo()
    {
        return this.motivo;
    }

    public void setMotivo(Motivo motivo)
    {
        this.motivo = motivo;
    }

    public Timestamp getFechaDev()
    {
        return this.fechaDev;
    }

    public void setFechaDev(Timestamp fechaDev)
    {
        this.fechaDev = fechaDev;
    }
}