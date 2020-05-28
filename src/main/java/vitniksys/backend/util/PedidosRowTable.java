package vitniksys.backend.util;

import java.sql.Timestamp;
import vitniksys.backend.enums.TipoArt;

public class PedidosRowTable
{
    private int codigoEjemplar;
    private int nroEnvio;
    private float precio;
    private float precioCom;
    private String nombre;
    private TipoArt tipo;
    private String letra;
    private Timestamp fechaRetiro;
    private boolean comisiona;

    public PedidosRowTable(int nroEnvio, int codigoEjemplar, float precio, float precioCom, 
        String nombre, TipoArt tipo, String letra, Timestamp fechaRetiro, boolean comisiona)
    {
        this.nroEnvio = nroEnvio;
        this.codigoEjemplar = codigoEjemplar;
        this.precio = precio;
        this.precioCom = precioCom;
        this.nombre = nombre;
        this.tipo = tipo;
        this.letra = letra;
        this.fechaRetiro = fechaRetiro;
        this.comisiona = comisiona;
    }

    //Getters && Setters
    public int getCodigoEjemplar()
    {
        return this.codigoEjemplar;
    }

    public void setCodigoEjemplar(int codigoEjemplar)
    {
        this.codigoEjemplar = codigoEjemplar;
    }

    public int getNroEnvio()
    {
        return this.nroEnvio;
    }

    public void setNroEnvio(int nroEnvio)
    {
        this.nroEnvio = nroEnvio;
    }

    public float getPrecio()
    {
        return this.precio;
    }

    public void setPrecio(float precio)
    {
        this.precio = precio;
    }

    public float getPrecioCom()
    {
        return this.precioCom;
    }

    public void setPrecioCom(float precioCom)
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

    public String getLetra()
    {
        return this.letra;
    }

    public void setLetra(String letra)
    {
        this.letra = letra;
    }

    public Timestamp getFechaRetiro()
    {
        return this.fechaRetiro;
    }

    public void setFechaRetiro(Timestamp fechaRetiro)
    {
        this.fechaRetiro = fechaRetiro;
    }

    public boolean isComisiona()
    {
        return this.comisiona;
    }

    public void setComisiona(boolean comisiona)
    {
        this.comisiona = comisiona;
    }
}