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

    public Balance(float balance, float totalInOrdersCom, float totalInOrdersNonCom, float totalInCatalogues, 
    float totalInRepurchases, float totalInPayments, float totalInDevolutions, float totalInCommission)
    {
        this.balance = balance;
        this.totalInOrdersCom = totalInOrdersCom;
        this.totalInOrdersNonCom = totalInOrdersNonCom;
        this.totalInCatalogues = totalInCatalogues;
        this.totalInRepurchases = totalInRepurchases;
        this.totalInPayments = totalInPayments;
        this.totalInDevolutions = totalInDevolutions;
        this.totalInCommission = totalInCommission;
    }

    //Getters && Setters
    public float getBalance()
    {
        return this.balance;
    }

    public void setBalance(float balance)
    {
        this.balance = balance;
    }

    public float getTotalInOrdersCom()
    {
        return this.totalInOrdersCom;
    }

    public void setTotalInOrdersCom(float totalInOrdersCom)
    {
        this.totalInOrdersCom = totalInOrdersCom;
    }

    public float getTotalInOrdersNonCom()
    {
        return this.totalInOrdersNonCom;
    }

    public void setTotalInOrdersNonCom(float totalInOrdersNonCom)
    {
        this.totalInOrdersNonCom = totalInOrdersNonCom;
    }

    public float getTotalInCatalogues()
    {
        return this.totalInCatalogues;
    }

    public void setTotalInCatalogues(float totalInCatalogues)
    {
        this.totalInCatalogues = totalInCatalogues;
    }

    public float getTotalInRepurchases()
    {
        return this.totalInRepurchases;
    }

    public void setTotalInRepurchases(float totalInRepurchases)
    {
        this.totalInRepurchases = totalInRepurchases;
    }

    public float getTotalInPayments()
    {
        return this.totalInPayments;
    }

    public void setTotalInPayments(float totalInPayments)
    {
        this.totalInPayments = totalInPayments;
    }

    public float getTotalInDevolutions()
    {
        return this.totalInDevolutions;
    }

    public void setTotalInDevolutions(float totalInDevolutions)
    {
        this.totalInDevolutions = totalInDevolutions;
    }

    public float getTotalInCommission()
    {
        return this.totalInCommission;
    }

    public void setTotalInCommission(float totalInCommission)
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