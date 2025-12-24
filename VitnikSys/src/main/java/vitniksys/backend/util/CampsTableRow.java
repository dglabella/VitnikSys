package vitniksys.backend.util;

import java.util.List;
import java.time.Month;
import java.util.Iterator;
import java.util.ArrayList;

import vitniksys.backend.model.entities.Balance;
import vitniksys.backend.model.entities.Campaign;

public class CampsTableRow
{
    private Integer idCamp;
    private Month month;
    private Integer year;
    private String alias;
    private Float balance;
    private Integer catalogueCode;

    public static List<CampsTableRow> generateRows(List<Campaign> camps)
    {
        List<CampsTableRow> ret = new ArrayList<>();

        if(camps != null)
        {
            Iterator<Campaign> campsIt = camps.iterator();
            Iterator<Balance> balancesIt;
            Campaign camp;
            CampsTableRow row;

            while(campsIt.hasNext())
            {
                float balance = 0;
                camp = campsIt.next();
                row = new CampsTableRow();

                row.setIdCamp(camp.getNumber());
                row.setMonth(camp.getEnumMonth());
                row.setYear(camp.getYear());
                row.setAlias(camp.getAlias());
                row.setCatalogueCode(camp.getCatalogueCode());

                balancesIt = camp.getBalances().iterator();
                while(balancesIt.hasNext())
                {
                    balance += balancesIt.next().getBalance();
                }
                row.setBalance(balance);

                ret.add(row);
            }
        }

        if(ret.size() == 0)
            ret = null;

        return ret;
    }

    public Integer getIdCamp()
    {
        return this.idCamp;
    }

    public void setIdCamp(Integer idCamp)
    {
        this.idCamp = idCamp;
    }

    public Month getMonth()
    {
        return this.month;
    }

    public void setMonth(Month month)
    {
        this.month = month;
    }

    public Integer getYear()
    {
        return this.year;
    }

    public void setYear(Integer year)
    {
        this.year = year;
    }

    public String getAlias()
    {
        return this.alias;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public Float getBalance()
    {
        return this.balance;
    }

    public void setBalance(Float balance)
    {
        this.balance = balance;
    }

    public Integer getCatalogueCode()
    {
        return this.catalogueCode;
    }

    public void setCatalogueCode(Integer catalogue)
    {
        this.catalogueCode = catalogue;
    }
}