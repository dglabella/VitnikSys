package vitniksys.backend.util;

import vitniksys.App;
import java.util.List;
import java.util.Iterator;
import java.sql.Timestamp;
import java.util.ArrayList;
import javafx.scene.control.CheckBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import vitniksys.backend.model.entities.Order;
import vitniksys.backend.model.enums.OrderType;

public class OrdersRowTable
{
    private Integer code;
    private Integer prefClientId;
    private Integer deliveryNumber;
    private Integer quantity;
    private Integer returnedQuantity;
    private Float cost;
    private float commissionCost;
    private float commission;
    private String name;
    private OrderType orderType;
    private String articleId; // Table id = letra
    private Float unitPrice;
    private Timestamp withdrawalDate;
    private CheckBox countForCommission;
    private CheckBox commissionable;

    private Order order;

    public OrdersRowTable(Integer code, Integer prefClientId, Integer deliveryNumber, Integer quantity, Integer returnedQuantity, Float cost, String name, OrderType orderType, 
        String articleId, Float unitPrice, Timestamp withdrawalDate, boolean commissionable, boolean countForCommission, Order order)
    {
        this.code = code;
        this.prefClientId = prefClientId;
        this.deliveryNumber = deliveryNumber;
        this.quantity = quantity;
        this.returnedQuantity = returnedQuantity;
        this.cost = cost;
        this.name = name;
        this.orderType = orderType;
        this.articleId = articleId;
        this.unitPrice = unitPrice;
        this.withdrawalDate = withdrawalDate;
        this.commissionable = new CheckBox();
        this.commissionable.setSelected(commissionable);
        this.countForCommission = new CheckBox();
        this.countForCommission.setSelected(countForCommission);
        this.order = order;

        this.commissionable.selectedProperty().addListener
        (
            (ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) -> 
            {
                this.order.setCommissionable(newValue);
            }
        );

        this.countForCommission.selectedProperty().addListener
        (
            (ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) -> 
            {
                this.order.setCountForCommission(newValue);
            }
        );
    }

    public OrdersRowTable(Integer code, Integer prefClientId, Integer deliveryNumber, Integer quantity, Integer returnedQuantity, Float cost, String name, OrderType orderType, 
        String articleId, Float unitPrice, Timestamp withdrawalDate, boolean commissionable, boolean countForCommission, ChangeListener<? super Boolean> changeListener, Order order)
    {
        this.code = code;
        this.prefClientId = prefClientId;
        this.deliveryNumber = deliveryNumber;
        this.quantity = quantity;
        this.returnedQuantity = returnedQuantity;
        this.cost = cost;
        this.name = name;
        this.orderType = orderType;
        this.articleId = articleId;
        this.unitPrice = unitPrice;
        this.withdrawalDate = withdrawalDate;
        this.commissionable = new CheckBox();
        this.commissionable.setSelected(commissionable);
        this.commissionable.selectedProperty().addListener(changeListener);

        this.order = order;
    }

    public static List<OrdersRowTable> generateRows(List<Order> orders, int commisionRatio, int fpCommisionRatio, int otherCommisionRatio)
    {
        List<OrdersRowTable> ret = new ArrayList<>();

        Order order = null;
        OrdersRowTable ordersRowTable = null;

        if(orders != null)
        {
            Iterator<Order> ordersIterator = orders.iterator();
    
            while(ordersIterator.hasNext())
            {
                order = ordersIterator.next();
                ordersRowTable = new OrdersRowTable(order.getCode(), order.getPrefClientId(), order.getDeliveryNumber(), order.getQuantity(), order.getReturnedQuantity(), order.getCost(), order.getArticle().getName(), 
                    order.getType(), order.getArticle().getId(), order.getUnitPrice(), order.getWithdrawalDate(), order.isCommissionable(), order.isCountForCommission(), order);
                
                if(order.isCommissionable())
                {
                    switch(order.getType())
                    {
                        case PEDIDO:
                        case OPORTUNIDAD:
                            ordersRowTable.setCommissionCost(order.getCost()-(order.getCost()*(commisionRatio/App.ConstraitConstants.COMMISSION_RATIO_FACTOR)));
                            ordersRowTable.setCommission(order.getCost()*(commisionRatio/App.ConstraitConstants.COMMISSION_RATIO_FACTOR));
                        break;

                        case FREEPREMIUM:
                        case PROMO:
                            ordersRowTable.setCommissionCost(order.getCost()-(order.getCost()*(fpCommisionRatio/App.ConstraitConstants.COMMISSION_RATIO_FACTOR)));
                            ordersRowTable.setCommission(order.getCost()*(fpCommisionRatio/App.ConstraitConstants.COMMISSION_RATIO_FACTOR));
                        break;

                        default:
                            ordersRowTable.setCommissionCost(order.getCost()-(order.getCost()*(otherCommisionRatio/App.ConstraitConstants.COMMISSION_RATIO_FACTOR)));
                            ordersRowTable.setCommission(order.getCost()*(otherCommisionRatio/App.ConstraitConstants.COMMISSION_RATIO_FACTOR));
                    }

                    
                }
    
                ret.add(ordersRowTable);
            }
        }

        if(ret.size() == 0)
            ret = null;

        return ret;
    }

    public static List<OrdersRowTable> generateRows(List<Order> orders, int commisionRatio, int fpCommisionRatio, int otherCommisionRatio, ChangeListener<? super Boolean> changeListener)
    {
        List<OrdersRowTable> ret = new ArrayList<>();

        Order order = null;
        OrdersRowTable ordersRowTable = null;

        if(orders != null)
        {
            Iterator<Order> ordersIterator = orders.iterator();
    
            while(ordersIterator.hasNext())
            {
                order = ordersIterator.next();
                ordersRowTable = new OrdersRowTable(order.getCode(), order.getPrefClientId(), order.getDeliveryNumber(), order.getQuantity(), order.getReturnedQuantity(), order.getCost(), order.getArticle().getName(), 
                    order.getType(), order.getArticle().getId(), order.getUnitPrice(), order.getWithdrawalDate(), order.isCommissionable(), order.isCountForCommission(), changeListener, order);
                
                if(order.isCommissionable())
                {
                    switch(order.getType())
                    {
                        case PEDIDO:
                        case OPORTUNIDAD:
                            ordersRowTable.setCommissionCost(order.getCost()-(order.getCost()*(commisionRatio/App.ConstraitConstants.COMMISSION_RATIO_FACTOR)));
                            ordersRowTable.setCommission(order.getCost()*(commisionRatio/App.ConstraitConstants.COMMISSION_RATIO_FACTOR));
                        break;

                        case FREEPREMIUM:
                        case PROMO:
                            ordersRowTable.setCommissionCost(order.getCost()-(order.getCost()*(fpCommisionRatio/App.ConstraitConstants.COMMISSION_RATIO_FACTOR)));
                            ordersRowTable.setCommission(order.getCost()*(fpCommisionRatio/App.ConstraitConstants.COMMISSION_RATIO_FACTOR));
                        break;

                        default:
                            ordersRowTable.setCommissionCost(order.getCost()-(order.getCost()*(otherCommisionRatio/App.ConstraitConstants.COMMISSION_RATIO_FACTOR)));
                            ordersRowTable.setCommission(order.getCost()*(otherCommisionRatio/App.ConstraitConstants.COMMISSION_RATIO_FACTOR));
                    }

                    
                }
    
                ret.add(ordersRowTable);
            }
        }

        if(ret.size() == 0)
            ret = null;

        return ret;
    }

    // Getters && Setters
    public Integer getCode()
    {
        return this.code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public Integer getPrefClientId()
    {
        return this.prefClientId;
    }

    public void setPrefClientId(Integer prefClientId)
    {
        this.prefClientId = prefClientId;
    }

    public Integer getDeliveryNumber()
    {
        return this.deliveryNumber;
    }

    public void setDeliveryNumber(Integer deliveryNumber)
    {
        this.deliveryNumber = deliveryNumber;
    }

    public Integer getQuantity()
    {
        return this.quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public Integer getReturnedQuantity()
    {
        return this.returnedQuantity;
    }

    public void setReturnedQuantity(Integer returnedQuantity)
    {
        this.returnedQuantity = returnedQuantity;
    }
    
    public Float getCost()
    {
        return this.cost;
    }

    public void setCost(Float cost)
    {
        this.cost = cost;
    }

    public float getCommissionCost()
    {
        return this.commissionCost;
    }

    public void setCommissionCost(float commissionCost)
    {
        this.commissionCost = commissionCost;
    }

    public float getCommission()
    {
        return this.commission;
    }

    public void setCommission(float commission)
    {
        this.commission = commission;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public OrderType getArticleType()
    {
        return this.orderType;
    }

    public void setArticleType(OrderType orderType)
    {
        this.orderType = orderType;
    }

    public String getArticleId()
    {
        return this.articleId;
    }

    public void setArticleId(String articleId)
    {
        this.articleId = articleId;
    }

    public Float getUnitPrice()
    {
        return this.unitPrice;
    }

    public void setUnitPrice(Float unitPrice)
    {
        this.unitPrice = unitPrice;
    }

    public Timestamp getWithdrawalDate()
    {
        return this.withdrawalDate;
    }

    public void setWithdrawalDate(Timestamp withdrawalDate)
    {
        this.withdrawalDate = withdrawalDate;
    }

    public CheckBox getCountForCommission()
    {
        return this.countForCommission;
    }

    public void setCountForCommission(CheckBox countForCommission)
    {
        this.countForCommission = countForCommission;
    }

    public CheckBox getCommissionable()
    {
        return this.commissionable;
    }

    public void setCommissionable(CheckBox commissionable)
    {
        this.commissionable = commissionable;
    }

    public Order getOrder()
    {
        return this.order;
    }

    public void setOrder(Order order)
    {
        this.order = order;
    }
}