package vitniksys.backend.auxiliary;

public class FilaDetalle {
    
    private int _cplider;
    private int _cp;
    private int _nroEnvio;
    private String _letra;
    private String _codigoBarra;
    private String _nombre;
    private int _cant;
    private float _precioUnitario;
    private float _descCP;
    private float _precio;
    private float _comisionAgente;
    private float _precioFinal;
    private int _nroCamp;
    private String _obsArticulo;

    public FilaDetalle(){}

    public FilaDetalle(int cp, int nroEnvio, String letra, String codigoBarra, String nombre,
                       int cant, float precioUnitario, float descCP, float precio, float comisionAgente, 
                       float precioFinal, int nroCamp, String obsArticulo) {
        _cp = cp;
        _nroEnvio = nroEnvio;
        _letra = letra;
        _codigoBarra = codigoBarra;
        _nombre = nombre;
        _cant = cant;
        _precioUnitario = precioUnitario;
        _descCP = descCP;
        _precio = precio;
        _comisionAgente = comisionAgente;
        _precioFinal = precioFinal;
        _nroCamp = nroCamp;
        _obsArticulo = obsArticulo;
    }

    // ------------ Getters && Setters ------------
    
    public int getCplider() {
        return _cplider;
    }

    public FilaDetalle setCplider(int cplider) {
        _cplider = cplider;
        return this;
    }

    public int getCp() {
        return _cp;
    }

    public void setCp(int cp) { _cp = cp; }

    public int getNroEnvio() {
        return _nroEnvio;
    }

    public void setNroEnvio(int nroEnvio) { _nroEnvio = nroEnvio; }

    public String getLetra() {
        return _letra;
    }

    public void setLetra(String letra) {
        _letra = letra;
    }

    public String getCodigoBarra() {
        return _codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        _codigoBarra = codigoBarra;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String nombre) {
        _nombre = nombre;
    }

    public int getCant() {
        return _cant;
    }

    public void setCant(int cant) {
        _cant = cant;
    }

    public float getPrecioUnitario() {
        return _precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        _precioUnitario = precioUnitario;
    }

    public float getDescCP() {
        return _descCP;
    }

    public void setDescCP(float descCP) {
        _descCP = descCP;
    }

    public float getPrecio() {
        return _precio;
    }

    public void setPrecio(float precio) {
        _precio = precio;
    }

    public float getComisionAgente() {
        return _comisionAgente;
    }

    public void setComisionAgente(float comisionAgente) {
        _comisionAgente = comisionAgente;
    }

    public float getPrecioFinal() {
        return _precioFinal;
    }

    public void setPrecioFinal(float precioFinal) {
        _precioFinal = precioFinal;
    }

    public int getNroCamp() {
        return _nroCamp;
    }

    public void setNroCamp(int nroCamp) {
        _nroCamp = nroCamp;
    }

    public String getObsArticulo() {
        return _obsArticulo;
    }

    public void setObsArticulo(String obsArticulo) {
        _obsArticulo = obsArticulo;
    }
}