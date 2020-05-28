package vitniksys.backend.model;

import java.sql.Timestamp;
import vitniksys.backend.enums.Banco;
import vitniksys.backend.enums.Estado;
import vitniksys.backend.enums.ItemPago;
import vitniksys.backend.enums.FormaPago;

public class Pago
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
    private ClientePreferencial cliente;
    private Camp camp;

    //Others
    private boolean active;

    public Pago(int code, String descriptor, float amount, Timestamp registrationTime)
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

    public ClientePreferencial getCliente()
    {
        return this.cliente;
    }

    public void setCliente(ClientePreferencial cliente)
    {
        this.cliente = cliente;
    }

    public Camp getCamp()
    {
        return this.camp;
    }

    public void setCamp(Camp camp)
    {
        this.camp = camp;
    }
}