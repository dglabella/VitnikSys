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
import vitniksys.backend.model.bussines_logic.CommissionBLService;
import vitniksys.backend.model.entities.Campaign;
import vitniksys.backend.model.entities.Commission;
import vitniksys.backend.model.entities.Repurchase;
import vitniksys.backend.model.entities.PreferentialClient;
import vitniksys.frontend.views_subscriber.CommissionBLServiceSubscriber;

public class CommissionRegisterViewCntlr extends ViewCntlr implements CommissionBLServiceSubscriber
{
    private PreferentialClient prefClient;
    private Campaign camp;
    private List<Order> orders;
    private List<Repurchase> repurchases;
    private Commission commission;

    @FXML private Label idCpNameLastName;
    @FXML private Label actualQuantity;
    @FXML private Label actualRate;
    @FXML private Label fpActualRate;
    @FXML private Label otherActualRate;

    @FXML private Slider lvl1RateSlider;
    @FXML private Slider lvl2RateSlider;
    @FXML private Slider lvl3RateSlider;
    @FXML private Slider lvl4RateSlider;
    @FXML private Slider fpRateSlider;
    @FXML private Slider otherRateSlider;

    @FXML private Spinner<Integer> minQuantity;
    @FXML private Spinner<Integer> lvl1Quantity;
    @FXML private Spinner<Integer> lvl2Quantity;
    @FXML private Spinner<Integer> lvl3Quantity;
    
    @FXML private Spinner<Integer> lvl1RateSpinner;
    @FXML private Spinner<Integer> lvl2RateSpinner;
    @FXML private Spinner<Integer> lvl3RateSpinner;
    @FXML private Spinner<Integer> lvl4RateSpinner;
    @FXML private Spinner<Integer> fpRateSpinner;
    @FXML private Spinner<Integer> otherRateSpinner;
    
    @FXML private JFXButton update;

    @FXML
    private void updateButtonPressed()
    {
        try
        {
            ((CommissionBLService)this.getBLService(0)).modifyCommission(this.prefClient.getId(), this.camp.getNumber(), this.commission.getActualQuantity(), this.commission.getActualRate(), this.minQuantity.getValue(), this.lvl1Quantity.getValue(), 
                this.lvl2Quantity.getValue(), this.lvl3Quantity.getValue(), this.lvl1RateSpinner.getValue(), this.lvl2RateSpinner.getValue(), this.lvl3RateSpinner.getValue(), this.lvl4RateSpinner.getValue(), 
                this.fpRateSpinner.getValue(), this.otherRateSpinner.getValue(), this.orders, this.repurchases);
            
            //Refill management
            this.getPrevViewCntlr().refresh();
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
                ((CommissionBLService)this.getBLService(0)).createDefaultCommission(this.prefClient, this.orders, this.repurchases);
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

        this.lvl1RateSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, App.ConstraitConstants.MAX_COMMISSION_RATE));
        this.lvl2RateSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, App.ConstraitConstants.MAX_COMMISSION_RATE));
        this.lvl3RateSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, App.ConstraitConstants.MAX_COMMISSION_RATE));
        this.lvl4RateSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, App.ConstraitConstants.MAX_COMMISSION_RATE));
        this.fpRateSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, App.ConstraitConstants.MAX_COMMISSION_RATE));
        this.otherRateSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, App.ConstraitConstants.MAX_COMMISSION_RATE));

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

        this.fpRateSlider.setMax(App.ConstraitConstants.MAX_COMMISSION_RATE);
        this.fpRateSlider.setBlockIncrement(1);
        this.fpRateSlider.valueProperty().addListener
        (
            (ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> 
            {
                this.fpRateSpinner.getValueFactory().setValue(newValue.intValue());
            }
        );

        this.fpRateSpinner.valueProperty().addListener
        (
            (ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> 
            {
                this.fpRateSlider.setValue(newValue.intValue());
            }
        );

        this.otherRateSlider.setMax(App.ConstraitConstants.MAX_COMMISSION_RATE);
        this.otherRateSlider.setBlockIncrement(1);
        this.otherRateSlider.valueProperty().addListener
        (
            (ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> 
            {
                this.otherRateSpinner.getValueFactory().setValue(newValue.intValue());
            }
        );

        this.otherRateSpinner.valueProperty().addListener
        (
            (ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> 
            {
                this.otherRateSlider.setValue(newValue.intValue());
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
        this.fpActualRate.setText(""+commission.getFpFactor());
        this.otherActualRate.setText(""+commission.getOtherFactor());

        this.minQuantity.getValueFactory().setValue(commission.getMinQuantity());

        this.lvl1Quantity.getValueFactory().setValue(commission.getLvl1Quantity());
        this.lvl2Quantity.getValueFactory().setValue(commission.getLvl2Quantity());
        this.lvl3Quantity.getValueFactory().setValue(commission.getLvl3Quantity());

        this.lvl1RateSlider.setValue(commission.getLvl1Factor());
        this.lvl2RateSlider.setValue(commission.getLvl2Factor());
        this.lvl3RateSlider.setValue(commission.getLvl3Factor());
        this.lvl4RateSlider.setValue(commission.getLvl4Factor());
        this.fpRateSlider.setValue(commission.getFpFactor());
        this.otherRateSlider.setValue(commission.getOtherFactor());

        this.lvl1RateSpinner.getValueFactory().setValue(commission.getLvl1Factor());
        this.lvl2RateSpinner.getValueFactory().setValue(commission.getLvl2Factor());
        this.lvl3RateSpinner.getValueFactory().setValue(commission.getLvl3Factor());
        this.lvl4RateSpinner.getValueFactory().setValue(commission.getLvl4Factor());
        this.fpRateSpinner.getValueFactory().setValue(commission.getFpFactor());
        this.otherRateSpinner.getValueFactory().setValue(commission.getOtherFactor());
    }

    @Override
    public void suggestCompensation()
    {
        // TODO Auto-generated method stub
    }
}