package vitniksys.frontend.view_controllers;

import java.net.URL;
import vitniksys.App;
import java.util.List;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;
import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ObservableValue;
import vitniksys.backend.model.entities.Order;
import javafx.scene.control.SpinnerValueFactory;
import vitniksys.backend.model.entities.Campaign;
import vitniksys.backend.model.entities.Commission;
import vitniksys.backend.model.entities.Repurchase;
import vitniksys.backend.model.services.CommissionService;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.frontend.views_subscriber.CommissionServiceSubscriber;

public class CommissionRegisterViewCntlr extends ViewCntlr implements CommissionServiceSubscriber
{
    private PreferentialClient prefClient;
    private Campaign camp;
    private List<Order> orders;
    private List<Repurchase> repurchases;
    private Commission commission;

    @FXML private Label idCpNameLastName;
    @FXML private Label invalidPrice;
    @FXML private Label invalidLink;
    @FXML private Label actualQuantity;
    @FXML private Label actualRate;

    @FXML private Spinner<Integer> minQuantity;

    @FXML private Slider lvl1RateSlider;
    @FXML private Spinner<Integer> lvl1RateSpinner;
    @FXML private Spinner<Integer> lvl1Quantity;

    @FXML private Slider lvl2RateSlider;
    @FXML private Spinner<Integer> lvl2RateSpinner;
    @FXML private Spinner<Integer> lvl2Quantity;

    @FXML private Slider lvl3RateSlider;
    @FXML private Spinner<Integer> lvl3RateSpinner;
    @FXML private Spinner<Integer> lvl3Quantity;

    @FXML private Slider lvl4RateSlider;
    @FXML private Spinner<Integer> lvl4RateSpinner;
    @FXML private Spinner<Integer> lvl4Quantity;

    @FXML private JFXButton update;

    @FXML
    private void updateButtonPressed()
    {
        try
        {
            Commission commission = new Commission
            (
                this.commission.getActualQuantity(), this.commission.getActualRate(), this.minQuantity.getValue(), 
                this.lvl1Quantity.getValue(), this.lvl2Quantity.getValue(), this.lvl3Quantity.getValue(), this.lvl4Quantity.getValue(), 
                this.lvl1RateSpinner.getValue(), this.lvl2RateSpinner.getValue(), this.lvl3RateSpinner.getValue(), this.lvl4RateSpinner.getValue()
            );

            commission.setPrefClientId(this.prefClient.getId());
            commission.setCampNumber(this.camp.getNumber());

            ((CommissionService)this.getService(0)).modifyCommission(commission, this.orders, this.repurchases);
            
            //Refill management
            ((ClientManagementViewCntlr)this.getPrevViewCntlr()).fillManagementView();
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
                ((CommissionService)this.getService(0)).createDefaultCommission(this.prefClient, this.orders, this.repurchases);
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

    public void loadRepurchases(List<Repurchase> repurchases)
    {
        this.repurchases = repurchases;
    }

    @Override
    public void customInitialize(URL location, ResourceBundle resources) throws Exception
    {
        this.minQuantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));

        this.lvl1Quantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));
        this.lvl2Quantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));
        this.lvl3Quantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));
        this.lvl4Quantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));

        this.lvl1RateSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, App.ConstraitConstants.MAX_COMMISSION_RATE));
        this.lvl2RateSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, App.ConstraitConstants.MAX_COMMISSION_RATE));
        this.lvl3RateSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, App.ConstraitConstants.MAX_COMMISSION_RATE));
        this.lvl4RateSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, App.ConstraitConstants.MAX_COMMISSION_RATE));

        this.lvl1RateSlider.setMax(App.ConstraitConstants.MAX_COMMISSION_RATE);
        this.lvl1RateSlider.setBlockIncrement(1);
        this.lvl1RateSlider.valueProperty().addListener
        (
            (ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> 
            {
                this.lvl1RateSpinner.getValueFactory().setValue(newValue.intValue());
            }
        );

        this.lvl1RateSpinner.valueProperty().addListener
        (
            (ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> 
            {
                this.lvl1RateSlider.setValue(newValue.intValue());
            }
        );

        this.lvl2RateSlider.setMax(App.ConstraitConstants.MAX_COMMISSION_RATE);
        this.lvl2RateSlider.setBlockIncrement(1);
        this.lvl2RateSlider.valueProperty().addListener
        (
            (ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> 
            {
                this.lvl2RateSpinner.getValueFactory().setValue(newValue.intValue());
            }
        );

        this.lvl2RateSpinner.valueProperty().addListener
        (
            (ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> 
            {
                this.lvl2RateSlider.setValue(newValue.intValue());
            }
        );

        this.lvl3RateSlider.setMax(App.ConstraitConstants.MAX_COMMISSION_RATE);
        this.lvl3RateSlider.setBlockIncrement(1);
        this.lvl3RateSlider.valueProperty().addListener
        (
            (ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> 
            {
                this.lvl3RateSpinner.getValueFactory().setValue(newValue.intValue());
            }
        );

        this.lvl3RateSpinner.valueProperty().addListener
        (
            (ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> 
            {
                this.lvl3RateSlider.setValue(newValue.intValue());
            }
        );

        this.lvl4RateSlider.setMax(App.ConstraitConstants.MAX_COMMISSION_RATE);
        this.lvl4RateSlider.setBlockIncrement(1);
        this.lvl4RateSlider.valueProperty().addListener
        (
            (ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> 
            {
                this.lvl4RateSpinner.getValueFactory().setValue(newValue.intValue());
            }
        );

        this.lvl4RateSpinner.valueProperty().addListener
        (
            (ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> 
            {
                this.lvl4RateSlider.setValue(newValue.intValue());
            }
        );
    }

    // ===================================== services subscriber methods =====================================
    @Override
    public void refresh() 
    {
        //Refill management in order to get the new/updated commission
        ((ClientManagementViewCntlr)this.getPrevViewCntlr()).fillManagementView();
    }

    @Override
    public void suggestCommisionCreation()
    {
        // TODO Auto-generated method stub
    }

    @Override
    public void showCommission(Commission commission)
    {
        this.commission = commission;

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

        this.lvl1RateSpinner.getValueFactory().setValue(commission.getLvl1Factor());
        this.lvl2RateSpinner.getValueFactory().setValue(commission.getLvl2Factor());
        this.lvl3RateSpinner.getValueFactory().setValue(commission.getLvl3Factor());
        this.lvl4RateSpinner.getValueFactory().setValue(commission.getLvl4Factor());
    }
}