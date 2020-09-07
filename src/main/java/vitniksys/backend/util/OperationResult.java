package vitniksys.backend.util;

public class OperationResult
{
    public static final String DEFAULT_SUCCES_MESSAGE = "The operation was succesfully done!";
    public static final String DEFAULT_ERROR_MESSAGE = "An Error has ocurred!. For more information, click on \"Show details\".";
    
    public static final String DEFAULT_SUCCES_TITLE = "SUCCES";
    public static final String DEFAULT_ERROR_TITLE = "ERROR";

    public static final int SUCCES = 1;
    public static final int ERROR = -1;

    private int code;
    private String shortMessage;
    private String description;
    private Exception exception;

    public static Exception forcedException()
    {
        return new Exception("Forced exception thrown");
    }

    public int getCode()
    {
        return this.code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getShortMessage()
    {
        return this.shortMessage;
    }

    public void setShortMessage(String shortMessage)
    {
        this.shortMessage = shortMessage;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Exception getException()
    {
        return this.exception;
    }

    public void setException(Exception exception)
    {
        this.exception = exception;
    }
}