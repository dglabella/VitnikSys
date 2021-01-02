package vitniksys.frontend.views_subscriber;

import java.util.List;
import vitniksys.backend.model.entities.Catalogue;

public interface CatalogueServiceSubscriber extends ServiceSubscriber
{
    void showQueriedCatalogue(Catalogue catalogue) throws Exception;

    void showQueriedCatalogues(List<Catalogue> catalogues) throws Exception;
}