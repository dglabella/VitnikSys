package vitniksys.backend.util;

public interface IFileInterpreter
{
    static String SEPARATOR = ";";

    /**
     * Interprets the file using the implementation 
     * corresponding to the class that is instantiated.
     */
    void interpret() throws Exception;
}