package vitniksys.backend.util;

public class DetalleRow
{    
    private int cplider;
    private int cp;
    private int nroEnvio;
    private String letra;
    private String codigoBarra;
    private String nombre;
    private int cant;
    private float precioUnitario;
    private float descCP;
    private float precio;
    private float comisionAgente;
    private float precioFinal;
    private int nroCamp;
    private String obsArticulo;

    public DetalleRow()
    {
        //Empty construsctor
    }

    public DetalleRow(int cp, int nroEnvio, String letra, String codigoBarra, String nombre,
                       int cant, float precioUnitario, float descCP, float precio, float comisionAgente, 
                       float precioFinal, int nroCamp, String obsArticulo)
    {
        this.cp = cp;
        this.nroEnvio = nroEnvio;
        this.letra = letra;
        this.codigoBarra = codigoBarra;
        this.nombre = nombre;
        this.cant = cant;
        this.precioUnitario = precioUnitario;
        this.descCP = descCP;
        this.precio = precio;
        this.comisionAgente = comisionAgente;
        this.precioFinal = precioFinal;
        this.nroCamp = nroCamp;
        this.obsArticulo = obsArticulo;
    }

    //Getters && Setters
    public int getCplider()
    {
        return this.cplider;
    }

    public void setCplider(int cplider)
    {
        this.cplider = cplider;
    }

    public int getCp()
    {
        return this.cp;
    }

    public void setCp(int cp)
    {
        this.cp = cp;
    }

    public int getNroEnvio()
    {
        return this.nroEnvio;
    }

    public void setNroEnvio(int nroEnvio)
    {
        this.nroEnvio = nroEnvio;
    }

    public String getLetra()
    {
        return this.letra;
    }

    public void setLetra(String letra)
    {
        this.letra = letra;
    }

    public String getCodigoBarra()
    {
        return this.codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra)
    {
        this.codigoBarra = codigoBarra;
    }

    public String getNombre()
    {
        return this.nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public int getCant()
    {
        return this.cant;
    }

    public void setCant(int cant)
    {
        this.cant = cant;
    }

    public float getPrecioUnitario()
    {
        return this.precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario)
    {
        this.precioUnitario = precioUnitario;
    }

    public float getDescCP()
    {
        return this.descCP;
    }

    public void setDescCP(float descCP)
    {
        this.descCP = descCP;
    }

    public float getPrecio(){
        return this.precio;
    }

    public void setPrecio(float precio)
    {
        this.precio = precio;
    }

    public float getComisionAgente()
    {
        return this.comisionAgente;
    }

    public void setComisionAgente(float comisionAgente)
    {
        this.comisionAgente = comisionAgente;
    }

    public float getPrecioFinal()
    {
        return this.precioFinal;
    }

    public void setPrecioFinal(float precioFinal)
    {
        this.precioFinal = precioFinal;
    }

    public int getNroCamp()
    {
        return this.nroCamp;
    }

    public void setNroCamp(int nroCamp)
    {
        this.nroCamp = nroCamp;
    }

    public String getObsArticulo()
    {
        return this.obsArticulo;
    }

    public void setObsArticulo(String obsArticulo)
    {
        this.obsArticulo = obsArticulo;
    }
}