package vitniksys.backend.model;

public class Comision
{
    //Entity properties
    private int actualQuantity;
    private int lvl1Quantity;
    private int lvl2Quantity;
    private int lvl3Quantity;
    private int lvl4Quantity;
    private int lvl1Factor;
    private int lvl2Factor;
    private int lvl3Factor;
    private int lvl4Factor;

    //Domain Associations
    private Lider lider;
    private Camp camp;

    //Others
    private int isActive;

    public Comision(int actualQuantity, int lvl1Quantity, int lvl2Quantity, int lvl3Quantity,
        int lvl4Quantity, int lvl1Factor, int lvl2Factor, int lvl3Factor, int lvl4Factor)
    {
        this.actualQuantity = actualQuantity;
        this.lvl1Quantity = lvl1Quantity;
        this.lvl2Quantity = lvl2Quantity;
        this.lvl3Quantity = lvl3Quantity;
        this.lvl4Quantity = lvl4Quantity;
        this.lvl1Factor = lvl1Factor;
        this.lvl2Factor = lvl2Factor;
        this.lvl3Factor = lvl3Factor;
        this.lvl4Factor = lvl4Factor;
    }

    //Getters && Setters
    public int getActualQuantity()
    {
        return this.actualQuantity;
    }

    public void setActualQuantity(int actualQuantity)
    {
        this.actualQuantity = actualQuantity;
    }

    public int getLvl1Quantity()
    {
        return this.lvl1Quantity;
    }

    public void setLvl1Quantity(int lvl1Quantity)
    {
        this.lvl1Quantity = lvl1Quantity;
    }

    public int getLvl2Quantity()
    {
        return this.lvl2Quantity;
    }

    public void setLvl2Quantity(int lvl2Quantity)
    {
        this.lvl2Quantity = lvl2Quantity;
    }

    public int getLvl3Quantity()
    {
        return this.lvl3Quantity;
    }

    public void setLvl3Quantity(int lvl3Quantity)
    {
        this.lvl3Quantity = lvl3Quantity;
    }

    public int getLvl4Quantity()
    {
        return this.lvl4Quantity;
    }

    public void setLvl4Quantity(int lvl4Quantity)
    {
        this.lvl4Quantity = lvl4Quantity;
    }

    public int getLvl1Factor()
    {
        return this.lvl1Factor;
    }

    public void setLvl1Factor(int lvl1Factor)
    {
        this.lvl1Factor = lvl1Factor;
    }

    public int getLvl2Factor()
    {
        return this.lvl2Factor;
    }

    public void setLvl2Factor(int lvl2Factor)
    {
        this.lvl2Factor = lvl2Factor;
    }

    public int getLvl3Factor()
    {
        return this.lvl3Factor;
    }

    public void setLvl3Factor(int lvl3Factor)
    {
        this.lvl3Factor = lvl3Factor;
    }

    public int getLvl4Factor()
    {
        return this.lvl4Factor;
    }

    public void setLvl4Factor(int lvl4Factor)
    {
        this.lvl4Factor = lvl4Factor;
    }

    public int getIsActive()
    {
        return this.isActive;
    }

    public void setIsActive(int isActive)
    {
        this.isActive = isActive;
    }

    /**
     * 
     * @return return the corresponding  "lider" whose id identifies, in part, to this "comision".
     * The BD table key (column name: id_cp).
     */
    public Lider getLider()
    {
        return this.lider;
    }

    /**
     * 
     * @param lider set the corresponding  "lider" whose id identifies, in part, to this "comision".
     * The BD table key (column name: id_cp).
     */
    public void setLider(Lider lider)
    {
        this.lider = lider;
    }

    /**
     * 
     * @return return the corresponding "camp" whose number identifies, in part, to this "comision".
     * The BD table key (column name: nro_camp).
     */
    public Camp getCamp()
    {
        return this.camp;
    }

    /**
     * 
     * @param camp set the corresponding "camp" whose number identifies, in part, to this "comision".
     * The BD table key (column name: nro_camp).
     */
    public void setCamp(Camp camp)
    {
        this.camp = camp;
    }
}