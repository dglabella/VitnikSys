package vitniksys.backend.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.scene.layout.Pane;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

public class CustomAlert extends Alert
{
    private static final String DEFAULT_SUCCES_MESSAGE = "The operation was succesfully done!";
    private static final String DEFAULT_ERROR_MESSAGE = "An Error has ocurred!. For more information, click on \"Show details\".";

    private static final String DEFAULT_SUCCES_TITLE = "SUCCES";
    private static final String DEFAULT_ERROR_TITLE = "ERROR";

    public CustomAlert()
    {
        super(AlertType.INFORMATION);
        this.setTitle(DEFAULT_SUCCES_TITLE);
        this.setHeaderText(DEFAULT_SUCCES_MESSAGE);
    }

    public CustomAlert(AlertType type, String message)
    {
        super(type);
        this.setTitle("Message");
        this.setHeaderText(message);
    }

    public void defaultShow(OperationResult operationResult)
    {
        if(operationResult.getCode() == OperationResult.ERROR)
        {
            this.setAlertType(AlertType.ERROR);
            this.setTitle(DEFAULT_ERROR_TITLE);
            this.setHeaderText(DEFAULT_ERROR_MESSAGE);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            operationResult.getException().printStackTrace(pw);
            String stackTraceMessage = sw.toString(); // stack trace as a string

            TextArea textArea = new TextArea(stackTraceMessage);
            textArea.setEditable(false);
            textArea.setWrapText(true);
            //textArea.setMaxWidth(Double.MAX_VALUE);
            //textArea.setMaxHeight(Double.MAX_VALUE);

            Pane pane = new Pane();
            //pane.setMaxWidth(Double.MAX_VALUE);
            pane.getChildren().add(textArea);
            this.getDialogPane().setExpandableContent(pane);
        }
        this.showAndWait();   
    }

    public void customShow(OperationResult operationResult)
    {
        this.setHeaderText(operationResult.getShortMessage());

        if(operationResult.getCode() == OperationResult.ERROR)
        {
            this.setAlertType(AlertType.ERROR);
            this.setTitle(DEFAULT_ERROR_TITLE);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            operationResult.getException().printStackTrace(pw);
            String stackTraceMessage = sw.toString(); // stack trace as a string

            TextArea textArea = new TextArea(operationResult.getDescription()+"\n\n"+stackTraceMessage);
            textArea.setEditable(false);
            textArea.setWrapText(true);
            //textArea.setMaxWidth(Double.MAX_VALUE);
            //textArea.setMaxHeight(Double.MAX_VALUE);

            Pane pane = new Pane();
            //pane.setMaxWidth(Double.MAX_VALUE);
            pane.getChildren().add(textArea);
            this.getDialogPane().setExpandableContent(pane);
        }
        this.showAndWait();   
    }
}