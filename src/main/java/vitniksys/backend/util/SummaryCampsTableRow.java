package vitniksys.backend.util;

import java.util.List;

public class SummaryCampsTableRow
{
    private String camp;
    private Float campBalance;

    public static List<SummaryCampsTableRow> generateRows()
    {
        return null;   
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