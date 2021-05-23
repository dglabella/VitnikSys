package vitniksys.frontend.view_subscribers;

import java.util.List;
import vitniksys.backend.model.entities.ReturnedArticle;

public interface StockAvailableBLServiceSubscriber extends BLServiceSubscriber
{
    void showStockAvailable(List<ReturnedArticle> returnedArticles) throws Exception;
}