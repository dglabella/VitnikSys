package vitniksys.backend.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.scene.layout.Pane;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

public class ExceptionAlert extends Alert
{
    public ExceptionAlert(AlertType type, Exception ex)
    {
        super(type);
        this.setTitle("Exception");
        this.setHeaderText("An exception has ocurred");
        this.setContentText("Exception catched:");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
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
}