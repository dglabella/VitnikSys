package vitniksys.backend.model.entities;

public class Balance
{
    //Entity properties
    private float balance;
    private float totalInOrdersCom;
    private float totalInOrdersNonCom;
    private float totalInCatalogues;
    private float totalInRepurchases;
    private float totalInPayments;
    private float totalInDevolutions;
    private float totalInCommission;

    //Domain Associations
    private PreferentialClient client;
    private Campaign campaign;

    //Others
    private boolean active;

    public Balance()
    {
        //Default Balance
    }

    public Balance(Float totalInOrdersCom, Float totalInOrdersNonCom, Float totalInCatalogues, 
    Float totalInRepurchases, Float totalInPayments, Float totalInDevolutions, Float totalInCommission)
    {
        this.totalInOrdersCom = totalInOrdersCom;
        this.totalInOrdersNonCom = totalInOrdersNonCom;
        this.totalInCatalogues = totalInCatalogues;
        this.totalInRepurchases = totalInRepurchases;
        this.totalInPayments = totalInPayments;
        this.totalInDevolutions = totalInDevolutions;
        this.totalInCommission = totalInCommission;
    }

    //Getters && Setters
    public Float getBalance()
    {
        return this.balance;
    }

    public void setBalance(Float balance)
    {
        this.balance = balance;
    }

    public Float getTotalInOrdersCom()
    {   
        return this.totalInOrdersCom;
    }

    public void setTotalInOrdersCom(Float totalInOrdersCom)
    {
        this.totalInOrdersCom = totalInOrdersCom;
    }

    public Float getTotalInOrdersNonCom()
    {
        return this.totalInOrdersNonCom;
    }

    public void setTotalInOrdersNonCom(Float totalInOrdersNonCom)
    {
        this.totalInOrdersNonCom = totalInOrdersNonCom;
    }

    public Float getTotalInCatalogues()
    {
        return this.totalInCatalogues;
    }

    public void setTotalInCatalogues(Float totalInCatalogues)
    {
        this.totalInCatalogues = totalInCatalogues;
    }

    public Float getTotalInRepurchases()
    {
        return this.totalInRepurchases;
    }

    public void setTotalInRepurchases(Float totalInRepurchases)
    {
        this.totalInRepurchases = totalInRepurchases;
    }

    public Float getTotalInPayments()
    {
        return this.totalInPayments;
    }

    public void setTotalInPayments(Float totalInPayments)
    {
        this.totalInPayments = totalInPayments;
    }

    public Float getTotalInDevolutions()
    {
        return this.totalInDevolutions;
    }

    public void setTotalInDevolutions(Float totalInDevolutions)
    {
        this.totalInDevolutions = totalInDevolutions;
    }

    public Float getTotalInCommission()
    {
        return this.totalInCommission;
    }

    public void setTotalInCommission(Float totalInCommission)
    {
        this.totalInCommission = totalInCommission;
    }

    /**
     * 
     * @return return the corresponding client whose id identifies, in part, to this balance.
     * The BD table key (column name: id_cp).
     */
    public PreferentialClient getClient()
    {
        return this.client;
    }

    /**
     * 
     * @param client set the corresponding client whose id identifies, in part, to this balance.
     * The BD table key (column name: id_cp).
     */
    public void setClient(PreferentialClient client)
    {
        this.client = client;
    }

    /**
     * 
     * @return return the corresponding campaign whose number identifies, in part, to this balance.
     * The BD table key (column name: nro_camp).
     */
    public Campaign getCampaign()
    {
        return this.campaign;
    }

    /**
     * 
     * @param camp set the corresponding campaign whose number identifies, in part, to this balance.
     * The BD table key (column name: nro_camp).
     */
    public void setCamp(Campaign campaign)
    {
        this.campaign = campaign;
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