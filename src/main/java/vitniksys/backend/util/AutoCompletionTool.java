package vitniksys.backend.util;

import java.util.List;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

public class AutoCompletionTool
{
    private TextField textField;
    private ObservableList<String> suggestions;
    private ListView<String> suggestionsList;

    private FilteredList<String> filteredData;

    public AutoCompletionTool()
    {
        this.suggestions = FXCollections.observableArrayList();
        this.filteredData = new FilteredList<>(this.suggestions, s -> true);
    }

    public AutoCompletionTool(TextField textField, List<String> suggestions, ListView<String> suggestionsList)
    {
        this.textField = textField;
        this.suggestions = FXCollections.observableArrayList(suggestions);
        this.suggestionsList = suggestionsList;
        this.suggestionsList.setVisible(false);

        this.suggestionsList.getSelectionModel().selectedItemProperty().addListener(obs ->
        {
            this.textField.setText(this.suggestionsList.getSelectionModel().getSelectedItem());
        });

        FilteredList<String> filteredData = new FilteredList<>(this.suggestions, s -> true);
        this.textField.textProperty().addListener(obs->
        {
            this.suggestionsList.setVisible(true);

            String filter = this.textField.getText();
            if(filter == null || filter.length() == 0)
            {
                filteredData.setPredicate(s -> true);
            }
            else
            {
                filteredData.setPredicate(s -> s.contains(filter) || s.toLowerCase().contains(filter));
            }
        });
        this.suggestionsList.setItems(filteredData);
    }

    public TextField getTextField()
    {
        return this.textField;
    }

    public void setTextField(TextField textField)
    {
        this.textField = textField;
    }

    public ObservableList<String> getSuggestions()
    {
        return this.suggestions;
    }

    public void setSuggestions(ObservableList<String> suggestions)
    {
        this.suggestions = suggestions;
    }

    public ListView<String> getSuggestionsList()
    {
        return this.suggestionsList;
    }

    public void setSuggestionsList(ListView<String> suggestionsList)
    {
        this.suggestionsList = suggestionsList;
    }

    public void searchMatch()
    {
        FilteredList<String> filteredData = new FilteredList<>(this.suggestions, s -> true);

        this.textField.textProperty().addListener(obs->
        {
            String filter = this.textField.getText();
            if(filter == null || filter.length() == 0)
            {
                filteredData.setPredicate(s -> true);
            }
            else
            {
                filteredData.setPredicate(s -> s.contains(filter));
            }
        });
        this.suggestionsList.setItems(filteredData);   
    }
}