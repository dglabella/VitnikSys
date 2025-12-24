package vitniksys.frontend.view_subscribers;

import java.util.List;
import vitniksys.backend.model.entities.Catalogue;

public interface CatalogueBLServiceSubscriber extends BLServiceSubscriber
{
    void showQueriedCatalogue(Catalogue catalogue) throws Exception;

    void showQueriedCatalogues(List<Catalogue> catalogues) throws Exception;
}