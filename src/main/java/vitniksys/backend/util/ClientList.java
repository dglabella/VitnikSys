package vitniksys.backend.util;

import java.util.ArrayList;
import vitniksys.backend.model.ClientePreferencial;

public class ClientList extends ArrayList<ClientePreferencial>
{

    public ClientList ()
    {
        super();
    }

    /**
     * This method determines if the Preferential Client with this id
     * is in the list.
     * @param id the Preferential Client id.
     * @return true if id is in this list, otherwise return false.
     */
    public boolean belongs(int id)
    {
        int i = 0;
        boolean ret = false;

        while(this.size() < i && this.get(i).getId() != id)
            i++;

        if(i <= this.size())
            ret = true;

        return ret;
    }
}