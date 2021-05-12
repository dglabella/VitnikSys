package vitniksys.backend.model.persistence;

import vitniksys.App;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Connector
{
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    //private static final String URL = "jdbc:mysql://localhost:3306/vitniksanluis?serverTimezone=UTC";
    //private static final String URL = "jdbc:mysql://localhost:3306/vitniksanluis?serverTimezone=America/Argentina/Buenos_Aires";
    //private static final String URL = "jdbc:mysql://localhost:3308/vitniksanluis?serverTimezone=America/Argentina/Buenos_Aires";
    //private static final String URL = "jdbc:mysql://localhost:3308/vitniksanluis_test?serverTimezone=America/Argentina/Buenos_Aires";
    private static String DRIVER_PREFIX = "jdbc:mysql://";
    private static String IP;
    private static String PORT;
    private static String DATABASE;
    private static String OPT;
    private static String URL;
    private static String USER;
    private static String PASS;

    private static Connector connector = null;
    private static Connection connection = null;

    private Connector() throws ClassNotFoundException, SQLException
    {
        Connector.readConfigFile(App.ConstraitConstants.CONFIG_FILE_LOCATION);
        Class.forName(DRIVER);
        Connector.connection = DriverManager.getConnection(URL, USER, PASS);
    }

    private static void readConfigFile(String file)
    {
        String line;
        String [] splitedLine = null;
        Scanner inputStream;

        try
        {
            inputStream = new Scanner(file);

            //Gathering all the lines in the file into primary memory (detailFileRows).
            boolean endTagReached = false;
            while(inputStream.hasNext() && !endTagReached)
            {
                line = inputStream.next();
                if (line.equals(App.ConstraitConstants.CONFIG_FILE_CONNECTION_SECTION))
                {
                    //move to the next line
                    line = inputStream.next();

                    while(!line.equals(App.ConstraitConstants.CONFIG_FILE_END_SECTION))
                    {
                        splitedLine = line.split(App.ConstraitConstants.CONFIG_FILE_DATA_SEPARATOR);

                        switch (splitedLine[App.ConstraitConstants.CONFIG_FILE_TAG_SIDE])
                        {
                            case App.ConstraitConstants.CONFIG_FILE_IP_TAG:
                                Connector.IP = splitedLine[App.ConstraitConstants.CONFIG_FILE_DATA_SIDE];
                            break;
                            case App.ConstraitConstants.CONFIG_FILE_PORT_TAG:
                                Connector.PORT = splitedLine[App.ConstraitConstants.CONFIG_FILE_DATA_SIDE];
                            break;
                            case App.ConstraitConstants.CONFIG_FILE_DATABASE_TAG:
                                Connector.DATABASE = splitedLine[App.ConstraitConstants.CONFIG_FILE_DATA_SIDE];
                            break;
                            case App.ConstraitConstants.CONFIG_FILE_OPT_TAG:
                                Connector.OPT = splitedLine[App.ConstraitConstants.CONFIG_FILE_DATA_SIDE];
                            break;
                            case App.ConstraitConstants.CONFIG_FILE_USER_TAG:
                                Connector.USER = splitedLine[App.ConstraitConstants.CONFIG_FILE_DATA_SIDE];
                            break;
                            case App.ConstraitConstants.CONFIG_FILE_PASS_TAG:
                                Connector.PASS = splitedLine[App.ConstraitConstants.CONFIG_FILE_DATA_SIDE];
                            break;
                        }
                        line = inputStream.next();
                    }
                    endTagReached = true;
                    Connector.URL = DRIVER_PREFIX+IP+":"+PORT+"/"+DATABASE;
                    if (OPT != null)
                    {
                        Connector.URL += "?"+OPT;
                    }
                    System.out.println("URL = "+Connector.URL);
                }
            }
            inputStream.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public static Connector getConnector()
    {
        try
        {
            if (Connector.connector == null)
            {
                Connector.connector = new Connector();
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        
        return Connector.connector;
    }

    public void closeConnection()
    {
        try
        {
            Connector.connection.close();
            Connector.connector = null;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void startTransaction()
    {
        try
        {
            Connector.connection.setAutoCommit(false);   
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void commit()
    {
        try
        {
            Connector.connection.commit();   
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void rollBack()
    {
        try
        {
            Connector.connection.rollback();   
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void endTransaction()
    {
        try
        {
            Connector.connection.setAutoCommit(true);    
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public PreparedStatement getStatement(String SQLstatement)
    {
        PreparedStatement statement = null;

        try
        {
            statement = Connector.connection.prepareStatement(SQLstatement);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        return statement;
    }

    public PreparedStatement getStatement(String SQLstatement, int returnGenKeys) throws SQLException
    {
        PreparedStatement statement = null;

        try
        {
            statement = Connector.connection.prepareStatement(SQLstatement, returnGenKeys);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        return statement;
    }
}