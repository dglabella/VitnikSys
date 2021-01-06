package vitniksys.backend.util;

import java.util.ArrayList;
import java.util.Iterator;

import vitniksys.backend.model.entities.Balance;

public class BalanceList extends ArrayList<Balance>
{
    public BalanceList()
    {
        super();
    }

    /**
     * This method determines if the balance with this preferential client id
     * and campaign number exist in this list.
     * @param prefClientId The preferential client id.
     * @param campNumb The campaign number.
     * @return True if the balance is in this list, otherwise return false.
     * @throws Exception If both params are null.
     */
    public boolean exist(Integer prefClientId, Integer campNumb)
    {
        int i = 0;
        boolean ret = false;
        try
        {
            if(prefClientId != null && campNumb != null)
            {
                while(i < this.size() && this.get(i).getPrefClientId() != prefClientId && this.get(i).getCampNumber() != campNumb)
                    i++;
            }
            else if(prefClientId != null && campNumb == null)
            {
                throw new Exception("Operation not supported yet");
            }
            else if(prefClientId == null && campNumb != null)
            {
                throw new Exception("Operation not supported yet");
            }
            else
            {
                throw new Exception("Both campaign number and preferential client id are null");
            }   
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            if(i < this.size())
                ret = true;
        }

        return ret;
    }

    /**
     * This method locates the balance with a certain preferential client id 
     * and a certain campaign number.
     * @param prefClientId The preferential client id.
     * @param campNumb The campaign number.
     * @return True if the balance is in this list, otherwise return false.
     * @throws Exception If both params are null.
     * @return The position in the list where the balance is located.
     * If null is returned, no balance with those params is in the list.
     */
    public Integer locate(Integer prefClientId, Integer campNumb)
    {
        int i = 0;
        Integer ret = null;
        try
        {
            if(prefClientId != null && campNumb != null)
            {
                while(i < this.size() && !this.get(i).getPrefClientId().equals(prefClientId))
                    i++;
                
                while(i < this.size() && !this.get(i).getCampNumber().equals(campNumb))
                    i++;
            }
            else if(prefClientId != null && campNumb == null)
            {
                throw new Exception("Operation not supported yet");
            }
            else if(prefClientId == null && campNumb != null)
            {
                throw new Exception("Operation not supported yet");
            }
            else
            {
                throw new Exception("Both campaign number and preferential client id are null");
            }   
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            if(i < this.size())
                ret = i;
        }

        return ret;
    }

    @Override
    public String toString()
    {
        String ret = "";
        Iterator<Balance> it = this.iterator();

        while(it.hasNext())
            ret = ret + "\n" + it.next().toString();
        
        return ret;
    }
}