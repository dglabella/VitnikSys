package vitniksys.backend.util;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import vitniksys.backend.model.entities.Balance;
import vitniksys.backend.model.entities.PreferentialClient;

public class SummaryPrefClientTableRow
{
    private Integer id;
    private Long dni;
    private String name;
    private String lastName;
    private Float balance;

    private PreferentialClient prefClient;

    public static List<SummaryPrefClientTableRow> generateRows(List<PreferentialClient> prefClients)
    {
        List<SummaryPrefClientTableRow> ret = new ArrayList<>();

        if(prefClients != null)
        {
            PreferentialClient prefClient;
            SummaryPrefClientTableRow row;
            Iterator<Balance> balancesIt;
            Iterator<PreferentialClient> prefClientsIt = prefClients.iterator();

            while(prefClientsIt.hasNext())
            {
                float totalBalance = 0;
                prefClient = prefClientsIt.next();
            
                row = new SummaryPrefClientTableRow();
                row.setId(prefClient.getId());
                row.setDni(prefClient.getDni());
                row.setName(prefClient.getName());
                row.setLastName(prefClient.getLastName());
                row.setPrefClient(prefClient);

                System.out.println("balances del cp: "+prefClient.getId()+" > "+prefClient.getBalances());

                balancesIt = prefClient.getBalances().iterator();
                while(balancesIt.hasNext())
                {
                    totalBalance += balancesIt.next().getBalance();
                }
                row.setBalance(totalBalance);

                ret.add(row);
            }
        }

        if(ret.size() == 0)
            ret = null;

        return ret;
    }

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Long getDni()
    {
        return this.dni;
    }

    public void setDni(Long dni)
    {
        this.dni = dni;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public Float getBalance()
    {
        return this.balance;
    }

    public void setBalance(Float balance)
    {
        this.balance = balance;
    }

    public PreferentialClient getPrefClient()
    {
        return this.prefClient;
    }

    public void setPrefClient(PreferentialClient prefClient)
    {
        this.prefClient = prefClient;
    }
}