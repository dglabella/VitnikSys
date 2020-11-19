package vitniksys.backend.model.entities;

import java.sql.Timestamp;
import vitniksys.backend.model.enums.Bank;
import vitniksys.backend.model.enums.PayItem;
import vitniksys.backend.model.enums.PayType;
import vitniksys.backend.model.enums.PayStatus;

public class Payment
{
    //Entity properties
    private int code;
    private String descriptor;
    private float amount;
    private PayItem item;
    private PayType paymentMethod;
    private Bank bank;
    private PayStatus paymentStatus;
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

    public PayItem getItem()
    {
        return this.item;
    }

    public void setItem(PayItem item)
    {
        this.item = item;
    }

    public PayType getPaymentMethod()
    {
        return this.paymentMethod;
    }

    public void setPaymentMethod(PayType paymentMethod)
    {
        this.paymentMethod = paymentMethod;
    }

    public Bank getBank()
    {
        return this.bank;
    }

    public void setBank(Bank bank)
    {
        this.bank = bank;
    }

    public PayStatus getPaymentStatus()
    {
        return this.paymentStatus;
    }

    public void setPaymentStatus(PayStatus paymentStatus)
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