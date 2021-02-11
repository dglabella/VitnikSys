package vitniksys.backend.util;

import java.util.List;
import java.util.Iterator;
import java.sql.Timestamp;
import java.util.ArrayList;
import javafx.scene.control.CheckBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import vitniksys.backend.model.entities.Order;
import vitniksys.backend.model.enums.ArticleType;
import vitniksys.backend.model.services.CommissionService;

public class OrdersRowTable
{
    private Integer code;
    private Integer deliveryNumber;
    private Integer quantity;
    private Float cost;
    private float commissionCost;
    private float commission;
    private String name;
    private ArticleType type;
    private String articleId; // Table id = letra
    private Float unitPrice;
    private Timestamp withdrawalDate;
    private CheckBox commissionable;

    private Order order;

    public OrdersRowTable(Integer code, Integer deliveryNumber, Integer quantity, Float cost, String name, ArticleType type, 
        String articleId, Float unitPrice, Timestamp withdrawalDate, boolean commissionable, Order order)
    {
        this.code = code;
        this.deliveryNumber = deliveryNumber;
        this.quantity = quantity;
        this.cost = cost;
        this.name = name;
        this.type = type;
        this.articleId = articleId;
        this.unitPrice = unitPrice;
        this.withdrawalDate = withdrawalDate;
        this.commissionable = new CheckBox();
        this.commissionable.setSelected(commissionable);        
        this.order = order;

        this.commissionable.selectedProperty().addListener
        (
            (ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) -> 
            {
                this.order.setCommissionable(newValue);
            }
        );
    }

    public OrdersRowTable(Integer code, Integer deliveryNumber, Integer quantity, Float cost, String name, ArticleType type, 
        String articleId, Float unitPrice, Timestamp withdrawalDate, boolean commissionable, ChangeListener<? super Boolean> changeListener, Order order)
    {
        this.code = code;
        this.deliveryNumber = deliveryNumber;
        this.quantity = quantity;
        this.cost = cost;
        this.name = name;
        this.type = type;
        this.articleId = articleId;
        this.unitPrice = unitPrice;
        this.withdrawalDate = withdrawalDate;
        this.commissionable = new CheckBox();
        this.commissionable.setSelected(commissionable);
        this.commissionable.selectedProperty().addListener(changeListener);

        this.order = order;
    }

    public static List<OrdersRowTable> generateRows(List<Order> orders, int commisionRatio)
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
                ordersRowTable = new OrdersRowTable(order.getCode(), order.getDeliveryNumber(), order.getQuantity(), order.getCost(), order.getArticle().getName(), 
                    order.getArticle().getType(), order.getArticle().getId(), order.getArticle().getUnitPrice(), order.getWithdrawalDate(), order.isCommissionable(), order);
                
                if(order.isCommissionable())
                {
                    ordersRowTable.setCommissionCost(order.getCost()-(order.getCost()*(commisionRatio/CommissionService.COMMISSION_RATIO_FACTOR)));
                    ordersRowTable.setCommission(order.getCost()*(commisionRatio/CommissionService.COMMISSION_RATIO_FACTOR));
                }
    
                ret.add(ordersRowTable);
            }
        }

        if(ret.size() == 0)
            ret = null;

        return ret;
    }

    public static List<OrdersRowTable> generateRows(List<Order> orders, Float commisionRatio, ChangeListener<? super Boolean> changeListener)
    {
        List<OrdersRowTable> ret = new ArrayList<>();

        Order order = null;
        OrdersRowTable ordersRowTable = null;
        Iterator<Order> ordersIterator = orders.iterator();
    
        while(ordersIterator.hasNext())
        {
            order = ordersIterator.next();
            ordersRowTable = new OrdersRowTable(order.getCode(), order.getDeliveryNumber(), order.getQuantity(), order.getCost(), order.getArticle().getName(), 
                order.getArticle().getType(), order.getArticle().getId(), order.getArticle().getUnitPrice(), order.getWithdrawalDate(), 
                order.isCommissionable(), changeListener, order);

            if(order.isCommissionable())
            {
                ordersRowTable.setCommissionCost(order.getCost()-(order.getCost()*(commisionRatio/CommissionService.COMMISSION_RATIO_FACTOR)));
                ordersRowTable.setCommission(order.getCost()*(commisionRatio/CommissionService.COMMISSION_RATIO_FACTOR));
            }

            ret.add(ordersRowTable);
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

    public ArticleType getType()
    {
        return this.type;
    }

    public void setType(ArticleType type)
    {
        this.type = type;
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