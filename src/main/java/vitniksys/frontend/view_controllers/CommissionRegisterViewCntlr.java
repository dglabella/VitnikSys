package vitniksys.frontend.view_controllers;

import java.net.URL;
import java.util.List;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.TextField;
import javafx.beans.value.ObservableValue;
import vitniksys.backend.model.entities.Order;
import javafx.scene.control.SpinnerValueFactory;
import vitniksys.backend.model.entities.Campaign;
import vitniksys.backend.model.entities.Commission;
import vitniksys.backend.model.services.CommissionService;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.frontend.views_subscriber.CommissionServiceSubscriber;

public class CommissionRegisterViewCntlr extends ViewCntlr implements CommissionServiceSubscriber
{
    private PreferentialClient prefClient;
    private Campaign camp;
    private List<Order> orders;
    private Commission commission;

    @FXML private Label idCpNameLastName;
    @FXML private Label invalidPrice;
    @FXML private Label invalidLink;
    @FXML private Label actualQuantity;
    @FXML private Label actualRate;

    @FXML private JFXButton update;
    @FXML private Slider lvl1RateSlider;
    @FXML private TextField lvl1RateField;
    @FXML private Spinner<Integer> lvl1Quantity;
    @FXML private Slider lvl2RateSlider;
    @FXML private TextField lvl2RateField;
    @FXML private Spinner<Integer> lvl2Quantity;
    @FXML private Slider lvl3RateSlider;
    @FXML private TextField lvl3RateField;
    @FXML private Spinner<Integer> lvl3Quantity;
    @FXML private Slider lvl4RateSlider;
    @FXML private TextField lvl4RateField;
    @FXML private Spinner<Integer> lvl4Quantity;
    @FXML private Spinner<Integer> minQuantity;

    @FXML
    private void updateButtonPressed()
    {
        try
        {
            ((CommissionService)this.getService(0)).modifyCommission(new Commission
            (
                actualQuantity, actualRate, minQuantity, lvl1Quantity, lvl2Quantity, lvl3Quantity, lvl4Quantity, lvl1Factor, lvl2Factor, lvl3Factor, lvl4Factor
            ));
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    @FXML
    private void lvl1RateSliderOnMouseClicked(MouseEvent event)
    {
        if(event.getClickCount() == 2)
        {
            this.lvl1RateSlider.setValue(this.commission.getLvl1Factor());
        }
    }

    @FXML
    private void lvl2RateSliderOnMouseClicked(MouseEvent event)
    {
        if(event.getClickCount() == 2)
        {
            this.lvl2RateSlider.setValue(this.commission.getLvl2Factor());
        }
    }

    @FXML
    private void lvl3RateSliderOnMouseClicked(MouseEvent event)
    {
        if(event.getClickCount() == 2)
        {
            this.lvl3RateSlider.setValue(this.commission.getLvl3Factor());
        }
    }

    @FXML
    private void lvl4RateSliderOnMouseClicked(MouseEvent event)
    {
        if(event.getClickCount() == 2)
        {
            this.lvl4RateSlider.setValue(this.commission.getLvl4Factor());
        }
    }

    @Override
    protected void manualInitialize()
    {
        this.idCpNameLastName.setText(this.prefClient.getId()+" - "+this.prefClient.getName()+" "+this.prefClient.getLastName());

        if(this.commission == null)
        {
            try
            {
                ((CommissionService)this.getService(0)).createDefaultCommission(this.orders);
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
        }
        else
        {
            this.showCommission(this.commission);
        }
    }

    protected void loadPrefClient(PreferentialClient prefClient)
    {
        this.prefClient = prefClient;
    }

    protected void loadCamp(Campaign camp)
    {
        this.camp = camp;
    }

    public void loadCommission(Commission commission)
    {
        this.commission = commission;
	}
    
    public void loadOrders(List<Order> orders)
    {
        this.orders = orders;
	}

    @Override
    public void customInitialize(URL location, ResourceBundle resources) throws Exception
    {
        this.minQuantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));
        this.lvl1Quantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));
        this.lvl2Quantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));
        this.lvl3Quantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));
        this.lvl4Quantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));

        this.lvl1RateSlider.setMax(50);
        this.lvl1RateSlider.setBlockIncrement(1);
        this.lvl1RateSlider.valueProperty().addListener
        (
            (ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> 
            {
                this.lvl1RateField.setText(""+newValue.intValue());   
            }
        );

        this.lvl2RateSlider.setMax(50);
        this.lvl2RateSlider.setBlockIncrement(1);
        this.lvl2RateSlider.valueProperty().addListener
        (
            (ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> 
            {
                this.lvl2RateField.setText(""+newValue.intValue());   
            }
        );

        this.lvl3RateSlider.setMax(50);
        this.lvl3RateSlider.setBlockIncrement(1);
        this.lvl3RateSlider.valueProperty().addListener
        (
            (ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> 
            {
                this.lvl3RateField.setText(""+newValue.intValue());   
            }
        );

        this.lvl4RateSlider.setMax(50);
        this.lvl4RateSlider.setBlockIncrement(1);
        this.lvl4RateSlider.valueProperty().addListener
        (
            (ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> 
            {
                this.lvl4RateField.setText(""+newValue.intValue());   
            }
        );
    }

    // ===================================== services subscriber methods =====================================
    @Override
    public void suggestCommisionCreation()
    {
        // TODO Auto-generated method stub
    }

    @Override
    public void showCommission(Commission commission)
    {
        this.actualQuantity.setText(""+commission.getActualQuantity());
        this.actualRate.setText(""+commission.getActualRate());
        this.minQuantity.getValueFactory().setValue(commission.getMinQuantity());
        this.lvl1Quantity.getValueFactory().setValue(commission.getLvl1Quantity());
        this.lvl2Quantity.getValueFactory().setValue(commission.getLvl2Quantity());
        this.lvl3Quantity.getValueFactory().setValue(commission.getLvl3Quantity());
        this.lvl4Quantity.getValueFactory().setValue(commission.getLvl4Quantity());

        this.lvl1RateSlider.setValue(commission.getLvl1Factor());
        this.lvl2RateSlider.setValue(commission.getLvl2Factor());
        this.lvl3RateSlider.setValue(commission.getLvl3Factor());
        this.lvl4RateSlider.setValue(commission.getLvl4Factor());

        this.lvl1RateField.setText(""+commission.getLvl1Factor());
        this.lvl2RateField.setText(""+commission.getLvl2Factor());
        this.lvl3RateField.setText(""+commission.getLvl3Factor());
        this.lvl4RateField.setText(""+commission.getLvl4Factor());
    }
}