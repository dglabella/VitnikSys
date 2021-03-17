package vitniksys.backend.util;

public class CpLoadFileRow
{
    private Integer clientId;
    private String lastName;
    private String name;
    private Integer dni;

    public CpLoadFileRow(String clientId, String lastName, String name, String dni)
    {
        this.clientId = Integer.parseInt(clientId);
        this.lastName = lastName;
        this.name = name;
        this.dni = Integer.parseInt(dni);
    }

    public Integer getClientId()
    {
        return this.clientId;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public String getName()
    {
        return this.name;
    }

    public Integer getDni()
    {
        return this.dni;
    }
}