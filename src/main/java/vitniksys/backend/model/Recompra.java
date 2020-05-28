package vitniksys.backend.model;

import java.sql.Timestamp;

public class Recompra
{
   //Entity properties
   private int code;
   private float cost;
   private Timestamp registrationTime;

   //Domain Associations
   private ClientePreferencial clientePreferencial;
   private Camp camp;
   private ArticuloDevuelto articuloDevuelto;

   //Others
   private boolean active;

   public Recompra(int code, float cost)
   {
       this.code = code;
       this.cost = cost;
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

   public float getCost()
   {
       return this.cost;
   }

   public void setCost(float cost)
   {
       this.cost = cost;
   }

   public Timestamp getRegistrationTime()
   {
       return this.registrationTime;
   }

   public void setRegistrationTime(Timestamp registrationTime)
   {
       this.registrationTime = registrationTime;
   }

   public ClientePreferencial getClientePreferencial()
   {
       return this.clientePreferencial;
   }

   public void setClientePreferencial(ClientePreferencial clientePreferencial)
   {
       this.clientePreferencial = clientePreferencial;
   }

   public Camp getCamp()
   {
       return this.camp;
   }

   public void setCamp(Camp camp)
   {
       this.camp = camp;
   }

   public ArticuloDevuelto getArticuloDevuelto()
   {
       return this.articuloDevuelto;
   }

   public void setArticuloDevuelto(ArticuloDevuelto articuloDevuelto)
   {
       this.articuloDevuelto = articuloDevuelto;
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