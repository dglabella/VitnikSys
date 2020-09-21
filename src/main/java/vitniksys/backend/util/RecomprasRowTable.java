package vitniksys.backend.util;

import java.sql.Timestamp;
import vitniksys.backend.model.enums.ArticleType;

public class RecomprasRowTable
{
    private int codDev;
    private int nroEnvio;
    private int codEjemplar;
    private float precio;
    private float precioRec;
    private String nombre;
    private ArticleType tipo;
    private Timestamp fechaRec;

    public RecomprasRowTable(int codDev, int nroEnvio, int codEjemplar, float precio, 
        float precioRec, String nombre, ArticleType tipo, Timestamp fechaRec)
    {
        this.codDev = codDev;
        this.nroEnvio = nroEnvio;
        this.codEjemplar = codEjemplar;
        this.precio = precio;
        this.precioRec = precioRec;
        this.nombre = nombre;
        this.tipo = tipo;
        this.fechaRec = fechaRec;
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

    public float getPrecio()
    {
        return this.precio;
    }

    public void setPrecio(float precio)
    {
        this.precio = precio;
    }

    public float getPrecioRec()
    {
        return this.precioRec;
    }

    public void setPrecioRec(float precioRec)
    {
        this.precioRec = precioRec;
    }

    public String getNombre()
    {
        return this.nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public ArticleType getTipo()
    {
        return this.tipo;
    }

    public void setTipo(ArticleType tipo)
    {
        this.tipo = tipo;
    }

    public Timestamp getFechaRec()
    {
        return this.fechaRec;
    }

    public void setFechaRec(Timestamp fechaRec)
    {
        this.fechaRec = fechaRec;
    }
}