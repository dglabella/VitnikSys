package vitniksys.frontend.views_subscriber;

import java.util.List;
import vitniksys.backend.model.entities.ReturnedArticle;

public interface StockAvailableBLServiceSubscriber extends BLServiceSubscriber
{
    void showStockAvailable(List<ReturnedArticle> returnedArticles) throws Exception;
}