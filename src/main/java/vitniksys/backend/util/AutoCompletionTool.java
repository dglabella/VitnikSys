package vitniksys.backend.util;

import java.util.List;
import java.util.function.Predicate;
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

    public AutoCompletionTool(TextField textField, List<String> suggestions, ListView<String> suggestionsList)
    {
        this.textField = textField;
        this.suggestions = FXCollections.observableArrayList(suggestions);
        this.suggestionsList = suggestionsList;
        

        //ObservableList<String> data = FXCollections.observableArrayList();
        //IntStream.range(0, 1000).mapToObj(Integer::toString).forEach(data::add);

        FilteredList<String> filteredData = new FilteredList<>(this.suggestions, s -> true);//, s -> true

        this.textField.textProperty().addListener(obs->
        {
            String filter = textField.getText(); 

            filteredData.setPredicate(new Predicate<String>()
            {
                @Override
                public boolean test(String t)
                {
                    boolean ret = false;
                    if(filter == null || filter.length() == 0) {
                        filteredData.setPredicate(s -> true);
                    }
                    else {
                        filteredData.setPredicate(s -> s.contains(filter));
                    }
                    return ret;
                }
            });
        });
        
       this.suggestionsList.setItems(this.suggestions);//new ListView<>(filteredData);





       ObservableList<String> data = FXCollections.observableArrayList();
        IntStream.range(0, 1000).mapToObj(Integer::toString).forEach(data::add);

        FilteredList<String> filteredData = new FilteredList<>(data, s -> true);

        TextField filterInput = new TextField();
        filterInput.textProperty().addListener(obs->{
            String filter = filterInput.getText(); 
            if(filter == null || filter.length() == 0) {
                filteredData.setPredicate(s -> true);
            }
            else {
                filteredData.setPredicate(s -> s.contains(filter));
            }
        });


        BorderPane content = new BorderPane(new ListView<>(filteredData));
        content.setBottom(filterInput);
    }
}