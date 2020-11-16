package vitniksys.backend.model.services;

//import javafx.concurrent.Task;
import vitniksys.backend.util.ExpressionChecker;
import vitniksys.frontend.views_subscriber.ServiceSubscriber;

public abstract class Service
{
    private ExpressionChecker expressionChecker;
    private ServiceSubscriber serviceSubscriber;

    // ================================= Getters && setters =================================
    public ExpressionChecker getExpressionChecker()
    {
        return this.expressionChecker;
    }

    public void setExpressionChecker(ExpressionChecker expressionChecker)
    {
        this.expressionChecker = ExpressionChecker.getExpressionChecker();
    }

    public ServiceSubscriber getServiceSubscriber()
    {
        return this.serviceSubscriber;
    }

    public void setServiceSubscriber(ServiceSubscriber serviceSubscriber)
    {
        this.serviceSubscriber = serviceSubscriber;
    }

    // =================================== private methods ==================================


    // ================================= protected methods ==================================
    
    
    // =================================== public methods ===================================

    /*
    public void executeTask(Task<Void> task)
    {

    }
    */
}