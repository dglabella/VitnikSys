package vitniksys.backend.model.entities;

import vitniksys.backend.model.enums.Motivo;
import vitniksys.backend.model.enums.TipoArt;

public class ArticuloDevuelto extends Articulo
{
    //Entity properties
    private int unitCode; //Table id = ejemplar
    private Motivo reason;
    private boolean repurchased;

    //Domain Associations
    

    //Others
    private boolean active;

    public ArticuloDevuelto(String id, String name, TipoArt type, float unitPrice, 
        int unitCode, Motivo reason, boolean repurchased)
    {
        super(id, name, type, unitPrice);
        this.unitCode = unitCode;
        this.reason = reason;
        this.repurchased = repurchased;
    }
    
    //Getters && Setters
    /**
     * 
     * @return return the BD table key (column name: ejemplar).
     */
    public int getUnitCode()
    {
        return this.unitCode;
    }

    /**
     * 
     * @param unitCode set the BD table key (column name: ejemplar).
     */
    public void setUnitCode(int unitCode)
    {
        this.unitCode = unitCode;
    }

    public Motivo getReason()
    {
        return this.reason;
    }

    public void setReason(Motivo reason)
    {
        this.reason = reason;
    }

    public boolean isRepurchased()
    {
        return this.repurchased;
    }

    public void setRepurchased(boolean repurchased)
    {
        this.repurchased = repurchased;
    }

    public boolean isActive()
    {
        return this.active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }
}