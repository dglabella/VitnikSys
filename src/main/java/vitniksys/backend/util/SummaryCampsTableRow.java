package vitniksys.backend.util;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import vitniksys.backend.model.entities.Balance;
import vitniksys.backend.model.entities.PreferentialClient;

public class SummaryCampsTableRow
{
    private String camp;
    private Float campBalance;

    public static List<SummaryCampsTableRow> generateRows(PreferentialClient prefClient)
    {
        List<SummaryCampsTableRow> ret = new ArrayList<>();

        Balance balance;
        SummaryCampsTableRow row;
        Iterator<Balance> balancesIt = prefClient.getBalances().iterator();
        while(balancesIt.hasNext())
        {
            balance = balancesIt.next();
            row = new SummaryCampsTableRow();
            
            row.setCamp(balance.getCampaign().toString());
            row.setCampBalance(balance.getBalance());

            ret.add(row);
        }

        return ret;
    }

    public String getCamp()
    {
        return this.camp;
    }

    public void setCamp(String camp)
    {
        this.camp = camp;
    }

    public Float getCampBalance()
    {
        return this.campBalance;
    }

    public void setCampBalance(Float campBalance)
    {
        this.campBalance = campBalance;
    }
}