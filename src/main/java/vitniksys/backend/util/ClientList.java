package vitniksys.backend.util;

import java.util.List;
import java.util.ArrayList;
import vitniksys.backend.model.ClientePreferencial;

public class ClientList extends List<ClientePreferencial>
{
    private ArrayList<ClientePreferencial> clients;

    public ClientList ()
    {
        this.clients = new ArrayList<>();
    }

    /**
     * 
     * @return the encapsulated ArrayList<ClientePreferencial>.
     */
    public ArrayList<ClientePreferencial> getClientes()
    {
        return this.clients;
    }

   /**
    * 
    * @param clientes set the ArrayList<ClientePreferencial> to the encapsulated field.
    */ 
    public void setClientes(ArrayList<ClientePreferencial> clientes)
    {
        this.clients = clientes;
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

        while(this.clients.size() < i && this.clients.get(i).getId() != id)
            i++;

        if(i <= this.clients.size())
            ret = true;

        return ret;
    }
}