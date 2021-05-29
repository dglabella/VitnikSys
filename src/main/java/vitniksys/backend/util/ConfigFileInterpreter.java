package vitniksys.backend.util;

import java.io.File;
import vitniksys.App;
import java.util.Scanner;

public class ConfigFileInterpreter extends FileInterpreter
{
    public static final String CONFIG_FILE_LOCATION = App.class.getResource("../configs/config.txt").getPath();

    private static final String CONFIG_FILE_CONNECTION_SECTION = "[CONNECTION]";
    private static final String CONFIG_FILE_BACKUP_SECTION = "[BACKUP]";
    private static final String CONFIG_FILE_END_SECTION = "[END]";
    private static final String CONFIG_FILE_DATA_SEPARATOR = ":";
    private static final int CONFIG_FILE_TAG_SIDE = 0;
    private static final int CONFIG_FILE_DATA_SIDE = 1;

    private static final String CONFIG_FILE_IP_TAG = "ip";
    private static final String CONFIG_FILE_PORT_TAG = "port";
    private static final String CONFIG_FILE_DATABASE_TAG = "database";
    private static final String CONFIG_FILE_OPT_TAG = "options";
    private static final String CONFIG_FILE_USER_TAG = "user";
    private static final String CONFIG_FILE_PASS_TAG = "pass";

    private static String DRIVER_PREFIX = "jdbc:mysql://";

    private String connectionUrl;
    private String connectionUser;
    private String connectionPass;

    public ConfigFileInterpreter(File file)
    {
        super(file, null);
    }

    public String getConnectionUrl()
    {
        return this.connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl)
    {
        this.connectionUrl = connectionUrl;
    }

    public String getConnectionUser()
    {
        return this.connectionUser;
    }

    public void setConnectionUser(String connectionUser)
    {
        this.connectionUser = connectionUser;
    }

    public String getConnectionPass()
    {
        return this.connectionPass;
    }

    public void setConnectionPass(String connectionPass)
    {
        this.connectionPass = connectionPass;
    }

    private void readConnectionSection()
    {
        String line;
        String [] splitedLine = null;
        Scanner inputStream;

        String ip = null;
        String port = null;
        String dataBase = null;
        String opt = null;

        try
        {
            inputStream = new Scanner(this.getFile());

            //Gathering all the lines in the file into primary memory (detailFileRows).
            boolean endTagReached = false;
            while(inputStream.hasNext() && !endTagReached)
            {
                line = inputStream.next();
                if (line.equals(ConfigFileInterpreter.CONFIG_FILE_CONNECTION_SECTION))
                {
                    //move to the next line
                    line = inputStream.next();

                    while(!line.equals(ConfigFileInterpreter.CONFIG_FILE_END_SECTION))
                    {
                        splitedLine = line.split(ConfigFileInterpreter.CONFIG_FILE_DATA_SEPARATOR);

                        switch (splitedLine[ConfigFileInterpreter.CONFIG_FILE_TAG_SIDE])
                        {
                            case ConfigFileInterpreter.CONFIG_FILE_IP_TAG:
                                ip = splitedLine[ConfigFileInterpreter.CONFIG_FILE_DATA_SIDE];
                            break;
                            case ConfigFileInterpreter.CONFIG_FILE_PORT_TAG:
                                port = splitedLine[ConfigFileInterpreter.CONFIG_FILE_DATA_SIDE];
                            break;
                            case ConfigFileInterpreter.CONFIG_FILE_DATABASE_TAG:
                                dataBase = splitedLine[ConfigFileInterpreter.CONFIG_FILE_DATA_SIDE];
                            break;
                            case ConfigFileInterpreter.CONFIG_FILE_OPT_TAG:
                                opt = splitedLine[ConfigFileInterpreter.CONFIG_FILE_DATA_SIDE];
                            break;
                            case ConfigFileInterpreter.CONFIG_FILE_USER_TAG:
                                this.connectionUser = splitedLine[ConfigFileInterpreter.CONFIG_FILE_DATA_SIDE];
                            break;
                            case ConfigFileInterpreter.CONFIG_FILE_PASS_TAG:
                                this.connectionPass = splitedLine[ConfigFileInterpreter.CONFIG_FILE_DATA_SIDE];
                            break;
                        }
                        line = inputStream.next();
                    }
                    endTagReached = true;
                    this.connectionUrl = DRIVER_PREFIX+ip+":"+port+"/"+dataBase;
                    if (opt != null)
                    {
                        this.connectionUrl += "?"+opt;
                    }
                    System.out.println("URL = "+this.connectionUrl);
                }
            }
            inputStream.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private void readBackUpSection()
    {

    }

    @Override
    public void interpret() throws Exception
    {
        this.readConnectionSection();
        this.readBackUpSection();
    }
}
