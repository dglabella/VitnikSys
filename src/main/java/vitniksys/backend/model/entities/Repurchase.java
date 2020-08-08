package vitniksys.backend.model.entities;

import java.sql.Timestamp;

public class Repurchase
{
   //Entity properties
   private int code;
   private float cost;
   private Timestamp registrationTime;

   //Domain Associations
   private PreferentialClient preferentialClient;
   private Campaign campaign;
   private ReturnedArticle returnedArticle;

   //Others
   private boolean active;
   
   public Repurchase(int code, float cost)
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

   public PreferentialClient getPreferentialClient()
   {
       return this.preferentialClient;
   }
   
   public void setPreferentialClient(PreferentialClient preferentialClient)
   {
       this.preferentialClient = preferentialClient;
   }
   
   public Campaign getCampaign()
   {
       return this.campaign;
   }

   public void setCampaign(Campaign campaign)
   {
       this.campaign = campaign;
   }

   public ReturnedArticle getReturnedArticle()
   {
       return this.returnedArticle;
   }
   
   public void setReturnedArticle(ReturnedArticle returnedArticle)
   {
       this.returnedArticle = returnedArticle;
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