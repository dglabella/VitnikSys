package vitniksys.backend.model.entities;

public class Commission extends VitnikSearchableEntity
{
    //Entity properties
    private int actualQuantity;
    private int actualRate;
    private int minQuantity;
    private int lvl1Quantity;
    private int lvl2Quantity;
    private int lvl3Quantity;
    private int lvl1Factor;
    private int lvl2Factor;
    private int lvl3Factor;
    private int lvl4Factor;
    private int fpFactor;
    private int otherFactor;


    //Domain Associations
    private Leader leader;
    private Campaign campaign;

    //Others
    private int isActive;

    public Commission(int prefClientId, int campNumber)
    {
        this.setPrefClientId(prefClientId);
        this.setCampNumber(campNumber);
    }

    public Commission(int actualQuantity, int actualRate, int minQuantity, int lvl1Quantity, int lvl2Quantity, int lvl3Quantity, 
        int lvl1Factor, int lvl2Factor, int lvl3Factor, int lvl4Factor, int fpFactor, int otherFactor)
    {
        this.actualQuantity = actualQuantity;
        this.actualRate = actualRate;
        this.minQuantity = minQuantity;
        this.lvl1Quantity = lvl1Quantity;
        this.lvl2Quantity = lvl2Quantity;
        this.lvl3Quantity = lvl3Quantity;
        
        this.lvl1Factor = lvl1Factor;
        this.lvl2Factor = lvl2Factor;
        this.lvl3Factor = lvl3Factor;
        this.lvl4Factor = lvl4Factor;

        this.fpFactor = fpFactor;
        this.otherFactor = otherFactor;
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

    public int getActualRate()
    {
        return this.actualRate;
    }

    public void setActualRate(int actualRate)
    {
        this.actualRate = actualRate;
    }

    public int getMinQuantity()
    {
        return this.minQuantity;
    }

    public void setMinQuantity(int minQuantity)
    {
        this.minQuantity = minQuantity;
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

    public int getFpFactor()
    {
        return this.fpFactor;
    }

    public void setFpFactor(int fpFactor)
    {
        this.fpFactor = fpFactor;
    }

    public int getOtherFactor()
    {
        return this.otherFactor;
    }

    public void setOtherFactor(int otherFactor)
    {
        this.otherFactor = otherFactor;
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
     * @return return the corresponding leader whose id identifies, in part, to this commission.
     * The BD table key (column name: id_cp).
     */
    public Leader getLeader()
    {
        return this.leader;
    }

    /**
     * 
     * @param leader set the corresponding leader whose id identifies, in part, to this commision.
     * The BD table key (column name: id_cp).
     */
    public void setLeader(Leader leader)
    {
        this.leader = leader;
    }

    /**
     * 
     * @return return the corresponding campaign whose number identifies, in part, to this commision.
     * The BD table key (column name: nro_camp).
     */
    public Campaign getCampaign()
    {
        return this.campaign;
    }

    /**
     * 
     * @param campaign set the corresponding campaign whose number identifies, in part, to this commision.
     * The BD table key (column name: nro_camp).
     */
    public void setCamp(Campaign campaign)
    {
        this.campaign = campaign;
    }
}