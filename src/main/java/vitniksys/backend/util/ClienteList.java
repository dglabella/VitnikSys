package vitniksys.backend.util;

import java.util.List;
import java.util.ArrayList;
import vitniksys.backend.model.ClientePreferencial;

public class ClienteList extends List<ClientePreferencial>
{
    private ArrayList<ClientePreferencial> clientes;

    public ClienteList ()
    {
        this.clientes = new ArrayList<>();
    }

    /**
     * 
     * @return the encapsulated ArrayList<ClientePreferencial>.
     */
    public ArrayList<ClientePreferencial> getClientes()
    {
        return this.clientes;
    }

   /**
    * 
    * @param clientes set the ArrayList<ClientePreferencial> to the encapsulated field.
    */ 
    public void setClientes(ArrayList<ClientePreferencial> clientes)
    {
        this.clientes = clientes;
    }
    /**
     * This method determines if the ClientePreferencial with this id
     * is in the list.
     * @param id the ClientePreferencial id
     * @return true if id is in this list, otherwise return false.
     */
    public boolean belongs(int id)
    {
        int i = 0;
        boolean ret = false;

        while(this.clientes.size() < i && this.clientes.get(i).getId() != id)
            i++;

        if(i <= this.clientes.size())
            ret = true;

        return ret;
    }
}