package vitniksys.frontend.view_controllers;

import java.net.URL;
import vitniksys.App;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import vitniksys.backend.model.enums.Bank;
import vitniksys.backend.model.enums.PayItem;
import vitniksys.backend.model.enums.PayType;
import vitniksys.backend.model.enums.PayStatus;
import vitniksys.backend.util.ExpressionChecker;

public class PaymentDialogContentViewCntlr extends DialogContentViewCntlr implements Initializable
{
    // ================================= FXML variables =================================
    @FXML private Label descriptorLabel;
    @FXML private Label amountLabel;

    @FXML private TextField descriptor;
    @FXML private TextField amount;

    @FXML private ComboBox<PayItem> items;
    @FXML private ComboBox<PayType> paymentMethods;
    @FXML private ComboBox<Bank> banks;
    @FXML private ComboBox<PayStatus> paymentStatus;

    // ================================= FXML methods ===================================
    @FXML
    private boolean descriptorCheck()
    {
        boolean ret;
    
        if (this.descriptor.getText().length() <= App.ConstraitConstants.MAX_LENGTH_PAYMENT_DESCRIPTOR)
        {
            this.descriptorLabel.setText("ID / Descriptor");
            this.descriptorLabel.setTextFill(Color.web("#000000"));
            ret = true;
        }
        else
        {
            this.descriptorLabel.setText("Dato invalido");
            this.descriptorLabel.setTextFill(Color.web("#ff0000"));
            ret = false;
        }
        return ret;
    }

    @FXML
    private boolean amountCheck()
    {
        boolean ret;
    
        if (ExpressionChecker.getExpressionChecker().moneyValueWithNegative(this.amount.getText(), 10, 2, false))
        {
            this.amountLabel.setText("ID / Descriptor");
            this.amountLabel.setTextFill(Color.web("#000000"));
            ret = true;
        }
        else
        {
            this.amountLabel.setText("Dato invalido");
            this.amountLabel.setTextFill(Color.web("#ff0000"));
            ret = false;
        }
        return ret;
    }

    // ================================= private methods ===================================

    // ================================= protected methods ===================================

    // ================================= public methods ===================================
    public String getDescriptor()
    {
        return this.descriptor.getText();
    }

    public Float getAmount()
    {
        return Float.parseFloat(this.amount.getText());
    }

    public PayItem getItem()
    {
        return this.items.getValue();
    }

    public PayType getPaymentMethod()
    {
        return this.paymentMethods.getValue();
    }

    public Bank getBank()
    {
        return this.banks.getValue();
    }

    public PayStatus getPaymentStatus()
    {
        return this.paymentStatus.getValue();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.items.getItems().add(PayItem.NA);
        this.items.getItems().add(PayItem.PEDIDO);
        this.items.getItems().add(PayItem.CATALAGO);
        this.items.getItems().add(PayItem.RECOMPRA);
        this.items.getItems().add(PayItem.PAGO_AJUSTE);
        
        this.paymentMethods.getItems().add(PayType.NA);
        this.paymentMethods.getItems().add(PayType.EFECTIVO);
        this.paymentMethods.getItems().add(PayType.DEPOSITO);
        this.paymentMethods.getItems().add(PayType.TRANSFERENCIA);
        this.paymentMethods.getItems().add(PayType.TARJETA);
        this.paymentMethods.getItems().add(PayType.RAPIPAGO);
        this.paymentMethods.getItems().add(PayType.CONVENIO);
        this.paymentMethods.getItems().add(PayType.DEBITO);

        this.banks.getItems().add(Bank.NA);
        this.banks.getItems().add(Bank.SANTANDER);
        this.banks.getItems().add(Bank.NACION);
        this.banks.getItems().add(Bank.ICBC);
        this.banks.getItems().add(Bank.SUPERVILLE);
        this.banks.getItems().add(Bank.HSBC);
        this.banks.getItems().add(Bank.GALICIA);

        this.paymentStatus.getItems().add(PayStatus.NA);
        this.paymentStatus.getItems().add(PayStatus.RECIBIDO);
        this.paymentStatus.getItems().add(PayStatus.ENVIADO);
        this.paymentStatus.getItems().add(PayStatus.ENVIO_PENDIENTE);
        this.paymentStatus.getItems().add(PayStatus.COBRADO);
        this.paymentStatus.getItems().add(PayStatus.COBRO_PENDIENTE);

        this.items.setValue(PayItem.NA);
        this.paymentMethods.setValue(PayType.NA);
        this.banks.setValue(Bank.NA);
        this.paymentStatus.setValue(PayStatus.NA);
    }

    // ================================= service subscriber methods ===================================
}