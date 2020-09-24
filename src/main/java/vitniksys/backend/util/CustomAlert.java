package vitniksys.backend.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.scene.layout.Pane;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.ButtonType;

public class CustomAlert extends Alert
{
    public static final String DEFAULT_SUCCES_HEADER = "The operation was succesfully done!";
    public static final String DEFAULT_ERROR_HEADER = "An Error has ocurred!. For more information, click on \"Show details\".";
    public static final String DEFAULT_WORKING_ON_HEADER = "Working on a process. Please wait...";
    
    public static final String DEFAULT_SUCCES_TITLE = "SUCCES";
    public static final String DEFAULT_WORKING_ON_TITLE = "WORKING ON";
    public static final String DEFAULT_ERROR_TITLE = "ERROR";

    public static final String DEFAULT_DESCRIPTION = "No message to show";

    private String description;
    private Exception exception;

    public CustomAlert()
    {
        super(AlertType.INFORMATION);
        this.setTitle(DEFAULT_SUCCES_TITLE);
        this.setHeaderText(DEFAULT_SUCCES_HEADER);
        this.setResizable(false);
    }

    public CustomAlert(AlertType alertType, String title, String headerText)
    {
        super(alertType);
        this.setTitle(title);
        this.setHeaderText(headerText);
        this.setResizable(false);
    }

    public CustomAlert(AlertType alertType, String title, String headerText, String description, Exception exception)
    {
        super(alertType);
        this.setTitle(title);
        this.setHeaderText(headerText);
        this.description = description;
        this.exception = CustomAlert.irrelevantException();
        this.setResizable(false);
    }

    public static Exception irrelevantException()
    {
        return new Exception("Irrelevant exception");
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

    public void customShow()
    {
        if(getException() != null)
        {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            getException().printStackTrace(pw);
            String stackTraceMessage = sw.toString(); //stack trace as a string
            TextArea textArea = new TextArea((getDescription()!=null?getDescription()+"\n\n":"")+stackTraceMessage);
            textArea.setEditable(false);
            textArea.setWrapText(true);
            //textArea.setMaxWidth(Double.MAX_VALUE);
            //textArea.setMaxHeight(Double.MAX_VALUE);

            Pane pane = new Pane();
            //pane.setMaxWidth(Double.MAX_VALUE);
            pane.getChildren().add(textArea);
            getDialogPane().setExpandableContent(pane);
        }        

        if(getAlertType() != AlertType.NONE)
        {
            showAndWait();
        }
        else
        {
            show();
        }
    }

    public void customClose()
    {
        this.setResult(ButtonType.CLOSE);
    }
}