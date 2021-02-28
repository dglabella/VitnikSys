package vitniksys.frontend.views_subscriber;

import java.util.List;
import vitniksys.backend.model.entities.ReturnedArticle;

public interface StockAvailableServiceSubscriber extends ServiceSubscriber
{
    void showStockAvailable(List<ReturnedArticle> returnedArticles) throws Exception;
}