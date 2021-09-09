package vitniksys.backend.util;

import vitniksys.App;
import java.util.Scanner;
import javafx.scene.control.Alert.AlertType;

public class ConfigFileInterpreter extends FileInterpreter {
    public static final String CONFIG_FILE_LOCATION =
            App.class.getResource("../configs/config.txt").getPath();

    public static final String CONFIG_FILE_DEFAULT_PREF_CLIENTS_REPORTS_STORAGE_PATH = App.class.getResource("../storage/reports/").getPath();
    public static final String CONFIG_FILE_DEFAULT_PREF_CLIENTS_REPORTS_STORAGE_PATH_TAG = "default";

    private static final String CONFIG_FILE_CONNECTION_SECTION = "[CONNECTION]";
    private static final String CONFIG_FILE_BACKUP_SECTION = "[BACKUP]";
    private static final String CONFIG_FILE_DETAIL_FILE_SECTION = "[DETAIL_FILE]";
    private static final String CONFIG_FILE_FILE_STORAGE_SECTION = "[FILE_STORAGE]";
    private static final String CONFIG_FILE_END_SECTION = "[END]";
    private static final String CONFIG_FILE_DATA_SEPARATOR = ":";
    private static final int CONFIG_FILE_TAG_SIDE = 0;
    private static final int CONFIG_FILE_DATA_SIDE = 1;

    private static ConfigFileInterpreter configFileInterpreter;

    // about connection
    private static final String CONFIG_FILE_IP_TAG = "ip";
    private static final String CONFIG_FILE_PORT_TAG = "port";
    private static final String CONFIG_FILE_DATABASE_TAG = "database";
    private static final String CONFIG_FILE_OPT_TAG = "options";
    private static final String CONFIG_FILE_USER_TAG = "user";
    private static final String CONFIG_FILE_PASS_TAG = "pass";

    // about backup
    private static final String CONFIG_FILE_PATH_TAG = "path";

    // about detail file
    private static final String CONFIG_FILE_FIRST_ROWS_SKIPPED_TAG = "first_rows_skipped";

    // about file storage
    private static final String CONFIG_FILE_PREF_CLIENTS_REPORTS_STORAGE_PATH_TAG = "pref_clients_reports_storage_path";

    private static String DRIVER_PREFIX = "jdbc:mysql://";

    private static String connectionUrl;
    private static String connectionUser;
    private static String connectionPass;

    private static String path;

    private static Integer firstRowsSkipped;

    private static String prefClientsReportsStoragePath;


    private ConfigFileInterpreter(String filePath) {
        super(filePath, null);
    }

    public static String getConnectionUrl() {
        return ConfigFileInterpreter.connectionUrl;
    }

    public static void setConnectionUrl(String connectionUrl) {
        ConfigFileInterpreter.connectionUrl = connectionUrl;
    }

    public static String getConnectionUser() {
        return ConfigFileInterpreter.connectionUser;
    }

    public static void setConnectionUser(String connectionUser) {
        ConfigFileInterpreter.connectionUser = connectionUser;
    }

    public static String getConnectionPass() {
        return ConfigFileInterpreter.connectionPass;
    }

    public static void setConnectionPass(String connectionPass) {
        ConfigFileInterpreter.connectionPass = connectionPass;
    }

    public static String getPath() {
        return ConfigFileInterpreter.path;
    }

    public static void setPath(String path) {
        ConfigFileInterpreter.path = path;
    }

    public static Integer getFirstRowsSkipped() {
        return ConfigFileInterpreter.firstRowsSkipped;
    }

    public static void setFirstRowsSkipped(Integer firstRowsSkipped) {
        ConfigFileInterpreter.firstRowsSkipped = firstRowsSkipped;
    }

    public static String getPrefClientsReportsStoragePath() {
        return ConfigFileInterpreter.prefClientsReportsStoragePath;
    }

    public static void setPrefClientsReportsStoragePath(String prefClientsReportsStoragePath) {
        ConfigFileInterpreter.prefClientsReportsStoragePath = prefClientsReportsStoragePath;
    }

    public static ConfigFileInterpreter getInstance(String filePath) {
        if (ConfigFileInterpreter.configFileInterpreter == null) {
            ConfigFileInterpreter.configFileInterpreter = new ConfigFileInterpreter(filePath);
        }

        return ConfigFileInterpreter.configFileInterpreter;
    }

    private String reconstructDataSide(String[] dataSide){
        String ret = "";
        for(int i = CONFIG_FILE_DATA_SIDE; i < dataSide.length-1; i++){
            ret += dataSide[i]+ConfigFileInterpreter.CONFIG_FILE_DATA_SEPARATOR;
        }
        ret += dataSide[dataSide.length-1];
        return ret;
    }

    private void readConnectionSection() {
        String line;
        String[] splitedLine = null;
        String dataSide = null;

        String ip = null;
        String port = null;
        String dataBase = null;
        String opt = null;

        Scanner inputStream;

        try {
            inputStream = new Scanner(this.getFile());
            // Gathering all the lines in the file into primary memory (detailFileRows).
            boolean endTagReached = false;
            while (inputStream.hasNext() && !endTagReached) {
                line = inputStream.next();
                if (line.equals(ConfigFileInterpreter.CONFIG_FILE_CONNECTION_SECTION)) {
                    // move to the next line
                    line = inputStream.next();
                    while (!line.equals(ConfigFileInterpreter.CONFIG_FILE_END_SECTION)) {
                        splitedLine = line.split(ConfigFileInterpreter.CONFIG_FILE_DATA_SEPARATOR);
                        dataSide = this.reconstructDataSide(splitedLine);

                        switch (splitedLine[ConfigFileInterpreter.CONFIG_FILE_TAG_SIDE]) {
                            case ConfigFileInterpreter.CONFIG_FILE_IP_TAG:
                                //ip = splitedLine[ConfigFileInterpreter.CONFIG_FILE_DATA_SIDE];
                                ip = dataSide;
                                break;
                            case ConfigFileInterpreter.CONFIG_FILE_PORT_TAG:
                                //port = splitedLine[ConfigFileInterpreter.CONFIG_FILE_DATA_SIDE];
                                port = dataSide;
                                break;
                            case ConfigFileInterpreter.CONFIG_FILE_DATABASE_TAG:
                                //dataBase = splitedLine[ConfigFileInterpreter.CONFIG_FILE_DATA_SIDE];
                                dataBase = dataSide;
                                break;
                            case ConfigFileInterpreter.CONFIG_FILE_OPT_TAG:
                                //opt = splitedLine[ConfigFileInterpreter.CONFIG_FILE_DATA_SIDE];
                                opt = dataSide;
                                break;
                            case ConfigFileInterpreter.CONFIG_FILE_USER_TAG:
                                //ConfigFileInterpreter.connectionUser = splitedLine[ConfigFileInterpreter.CONFIG_FILE_DATA_SIDE];
                                ConfigFileInterpreter.connectionUser = dataSide;
                                break;
                            case ConfigFileInterpreter.CONFIG_FILE_PASS_TAG:
                                //ConfigFileInterpreter.connectionPass = splitedLine[ConfigFileInterpreter.CONFIG_FILE_DATA_SIDE];
                                ConfigFileInterpreter.connectionPass = dataSide;
                                break;
                        }
                        line = inputStream.next();
                    }
                    endTagReached = true;
                    ConfigFileInterpreter.connectionUrl =
                            DRIVER_PREFIX + ip + ":" + port + "/" + dataBase;
                    if (opt != null) {
                        ConfigFileInterpreter.connectionUrl += "?" + opt;
                    }
                    // System.out.println("URL = "+ConfigFileInterpreter.connectionUrl);
                }
            }

            if (!inputStream.hasNext() && !endTagReached) {
                new CustomAlert(AlertType.ERROR, "ERROR",
                        "El archivo de configuraciones no define una sección de conexión")
                                .customShow();
            }

            inputStream.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void readBackUpSection() {
        String line;
        String[] splitedLine = null;
        String dataSide = null;

        Scanner inputStream;

        try {
            inputStream = new Scanner(this.getFile());
            // Gathering all the lines in the file into primary memory (detailFileRows).
            boolean endTagReached = false;
            while (inputStream.hasNext() && !endTagReached) {
                line = inputStream.next();
                if (line.equals(ConfigFileInterpreter.CONFIG_FILE_BACKUP_SECTION)) {
                    // move to the next line
                    line = inputStream.next();
                    while (!line.equals(ConfigFileInterpreter.CONFIG_FILE_END_SECTION)) {
                        splitedLine = line.split(ConfigFileInterpreter.CONFIG_FILE_DATA_SEPARATOR);
                        dataSide = this.reconstructDataSide(splitedLine);

                        switch (splitedLine[ConfigFileInterpreter.CONFIG_FILE_TAG_SIDE]) {
                            case ConfigFileInterpreter.CONFIG_FILE_PATH_TAG:
                                //ConfigFileInterpreter.path = splitedLine[ConfigFileInterpreter.CONFIG_FILE_DATA_SIDE];
                                ConfigFileInterpreter.path = dataSide;
                                break;
                        }
                        line = inputStream.next();
                    }
                    endTagReached = true;
                }
            }

            if (!inputStream.hasNext() && !endTagReached) {
                new CustomAlert(AlertType.ERROR, "ERROR",
                        "El archivo de configuraciones no define una sección de backup")
                                .customShow();
            }

            inputStream.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void readDetailFileSection() {
        String line;
        String[] splitedLine = null;
        String dataSide = null;

        Scanner inputStream;

        try {
            inputStream = new Scanner(this.getFile());
            // Gathering all the lines in the file into primary memory (detailFileRows).
            boolean endTagReached = false;
            while (inputStream.hasNext() && !endTagReached) {
                line = inputStream.next();
                if (line.equals(ConfigFileInterpreter.CONFIG_FILE_DETAIL_FILE_SECTION)) {
                    // move to the next line
                    line = inputStream.next();

                    while (!line.equals(ConfigFileInterpreter.CONFIG_FILE_END_SECTION)) {
                        splitedLine = line.split(ConfigFileInterpreter.CONFIG_FILE_DATA_SEPARATOR);
                        dataSide = this.reconstructDataSide(splitedLine);

                        switch (splitedLine[ConfigFileInterpreter.CONFIG_FILE_TAG_SIDE]) {
                            case ConfigFileInterpreter.CONFIG_FILE_FIRST_ROWS_SKIPPED_TAG:
                                //ConfigFileInterpreter.firstRowsSkipped = Integer.parseInt(splitedLine[ConfigFileInterpreter.CONFIG_FILE_DATA_SIDE]);
                                ConfigFileInterpreter.firstRowsSkipped = Integer.parseInt(dataSide);
                                break;
                        }
                        line = inputStream.next();
                    }
                    endTagReached = true;
                }
            }

            if (!inputStream.hasNext() && !endTagReached) {
                new CustomAlert(AlertType.ERROR, "ERROR",
                        "El archivo de configuraciones no define una sección de archivo detalle")
                                .customShow();
            }

            inputStream.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void readFileStorageSection() {
        String line;
        String[] splitedLine = null;
        String dataSide;

        Scanner inputStream;

        try {
            inputStream = new Scanner(this.getFile());
            // Gathering all the lines in the file into primary memory (detailFileRows).
            boolean endTagReached = false;
            while (inputStream.hasNext() && !endTagReached) {
                line = inputStream.next();
                if (line.equals(ConfigFileInterpreter.CONFIG_FILE_FILE_STORAGE_SECTION)) {
                    // move to the next line
                    line = inputStream.next();
                    while (!line.equals(ConfigFileInterpreter.CONFIG_FILE_END_SECTION)) {
                        splitedLine = line.split(ConfigFileInterpreter.CONFIG_FILE_DATA_SEPARATOR);
                        dataSide = this.reconstructDataSide(splitedLine);

                        switch (splitedLine[ConfigFileInterpreter.CONFIG_FILE_TAG_SIDE]) {
                            case ConfigFileInterpreter.CONFIG_FILE_PREF_CLIENTS_REPORTS_STORAGE_PATH_TAG:
                                //ConfigFileInterpreter.prefClientsReportsStoragePath = splitedLine[ConfigFileInterpreter.CONFIG_FILE_DATA_SIDE];
                                ConfigFileInterpreter.prefClientsReportsStoragePath = dataSide;
                                break;
                        }
                        line = inputStream.next();
                    }
                    endTagReached = true;
                }
            }

            if (!inputStream.hasNext() && !endTagReached) {
                new CustomAlert(AlertType.ERROR, "ERROR",
                        "El archivo de configuraciones no define una sección de almacenamiento")
                                .customShow();
            }

            inputStream.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void interpret() throws Exception {
        this.readConnectionSection();
        this.readBackUpSection();
        this.readDetailFileSection();
        this.readFileStorageSection();
    }
}
