package vitniksys.backend.util;

import java.util.ArrayList;
import vitniksys.backend.model.entities.PreferentialClient;

public class ClientList extends ArrayList<PreferentialClient>
{

    public ClientList ()
    {
        super();
    }

    /**
     * This method determines if the Preferential Client with this id
     * exist in this list.
     * @param id the Preferential Client id.
     * @return true if id is in this list, otherwise return false.
     */
    public boolean exist(int id)
    {
        int i = 0;
        boolean ret = false;
        
        while(i < this.size() && this.get(i).getId() != id)
            i++;

        if(i < this.size())
            ret = true;

        return ret;
    }

    /**
     * This method locates the Preferential Client with this id
     * @param id the Preferential Client id.
     * @return the position in the list where the Preferential Client is located.
     * If -1 is returned, no Preferential Client with this id is in the list.
     */
    public int locate(int id)
    {
        int i = 0;
        int ret = -1;
        
        while(i < this.size() && this.get(i).getId() != id)
            i++;

        if(i < this.size())
            ret = i;

        return ret;
    }
}