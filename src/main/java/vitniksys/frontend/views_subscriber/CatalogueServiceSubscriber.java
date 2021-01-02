package vitniksys.frontend.views_subscriber;

import java.util.List;
import vitniksys.backend.model.entities.Catalogue;

public interface CatalogueServiceSubscriber extends ServiceSubscriber
{
    void showQueriedCamp(Catalogue catalogue) throws Exception;

    void showQueriedCamps(List<Catalogue> catalogues) throws Exception;
}