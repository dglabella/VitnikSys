package vitniksys.backend.util;

import vitniksys.backend.model.enums.Reason;

public class StockDevsRowTable
{
    private int codEjemplar;
    private String nombre;
    private float precio;
    private Reason motivo;

    public StockDevsRowTable(int codEjemplar, float precio, String nombre, Reason motivo)
    {
        this.codEjemplar = codEjemplar;
        this.nombre = nombre;
        this.precio = precio;
        this.motivo = motivo;
    }

    //Getting && Setters
    public int getCodEjemplar()
    {
        return this.codEjemplar;
    }

    public void setCodEjemplar(int codEjemplar)
    {
        this.codEjemplar = codEjemplar;
    }

    public String getNombre()
    {
        return this.nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public float getPrecio()
    {
        return this.precio;
    }

    public void setPrecio(float precio)
    {
        this.precio = precio;
    }

    public Reason getMotivo()
    {
        return this.motivo;
    }

    public void setMotivo(Reason motivo)
    {
        this.motivo = motivo;
    }
}