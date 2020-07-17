package vitniksys.backend.util;

public class OperationResult
{
    public static final int SUCCES = 1;
    public static final int ERROR = -1;

    private int code;

    private Exception exception;

    public int getCode()
    {
        return this.code;
    }

    public void setCode(int code)
    {
        this.code = code;
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