package vitniksys.backend.model.entities;

import java.sql.Timestamp;
import vitniksys.backend.model.enums.Banco;
import vitniksys.backend.model.enums.Estado;
import vitniksys.backend.model.enums.ItemPago;
import vitniksys.backend.model.enums.FormaPago;

public class Payment
{
    //Entity properties
    private int code;
    private String descriptor;
    private float amount;
    private ItemPago item;
    private FormaPago paymentMethod;
    private Banco bank;
    private Estado paymentStatus;
    private Timestamp registrationTime;

    //Domain Associations
    private PreferentialClient client;
    private Campaign campaign;

    //Others
    private boolean active;

    public Payment(int code, String descriptor, float amount, Timestamp registrationTime)
    {
        this.code = code;
        this.descriptor = descriptor;
        this.amount = amount;
        this.registrationTime = registrationTime;
    }

    //Getters && Setters
    /**
     * 
     * @return return the BD table key (column name: cod).
     */
    public int getCode()
    {
        return this.code;
    }

    /**
     * 
     * @param code set the BD table key (column name: cod).
     */
    public void setCode(int code)
    {
        this.code = code;
    }

    public String getDescriptor()
    {
        return this.descriptor;
    }

    public void setDescriptor(String descriptor)
    {
        this.descriptor = descriptor;
    }

    public float getAmount()
    {
        return this.amount;
    }

    public void setAmount(float amount)
    {
        this.amount = amount;
    }

    public ItemPago getItem()
    {
        return this.item;
    }

    public void setItem(ItemPago item)
    {
        this.item = item;
    }

    public FormaPago getPaymentMethod()
    {
        return this.paymentMethod;
    }

    public void setPaymentMethod(FormaPago paymentMethod)
    {
        this.paymentMethod = paymentMethod;
    }

    public Banco getBank()
    {
        return this.bank;
    }

    public void setBank(Banco bank)
    {
        this.bank = bank;
    }

    public Estado getPaymentStatus()
    {
        return this.paymentStatus;
    }

    public void setPaymentStatus(Estado paymentStatus)
    {
        this.paymentStatus = paymentStatus;
    }

    public Timestamp getRegistrationTime()
    {
        return this.registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime)
    {
        this.registrationTime = registrationTime;
    }

    public boolean isActive()
    {
        return this.active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public PreferentialClient getClient()
    {
        return this.client;
    }

    public void setCliente(PreferentialClient client)
    {
        this.client = client;
    }

    public Campaign getCampaign()
    {
        return this.campaign;
    }

    public void setCamp(Campaign campaign)
    {
        this.campaign = campaign;
    }
}