package vitniksys.backend.model.services;

import vitniksys.backend.util.ExpressionChecker;
import vitniksys.frontend.views_subscriber.ServiceSubscriber;

public abstract class Service
{
    protected ExpressionChecker expressionChecker;

    protected ServiceSubscriber serviceSubscriber;

    public Service(ServiceSubscriber serviceSubscriber)
    {
        this.serviceSubscriber = serviceSubscriber;
        this.expressionChecker = ExpressionChecker.getExpressionChecker();
    }
}