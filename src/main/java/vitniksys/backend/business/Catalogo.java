package vitniksys.backend.business;

public class Catalogo
{
    private int codigo;
    private int stockInicial;
    private float precio;
    private String link;
    private int stock;

    public Catalogo(int codigo, int stockInicial, float precio)
    {
        this.codigo = codigo;
        this.stockInicial = stockInicial;
        //Inicialmente, stockInicial y stock deberian ser iguales
        this.stock = stockInicial;
        this.precio = precio;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getStockInicial() {
        return this.stockInicial;
    }

    public void setStockInicial(int stockInicial) {
        this.stockInicial = stockInicial;
    }

    public float getPrecio() {
        return this.precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}