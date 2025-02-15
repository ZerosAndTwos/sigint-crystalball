package ae.pegasus.framework.services;

import ae.pegasus.framework.app_context.AppContext;
import ae.pegasus.framework.app_context.properties.G4Properties;
import ae.pegasus.framework.http.G4HttpClient;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.model.SearchFilter;
import ae.pegasus.framework.model.AbstractEntity;

import java.util.List;

import static ae.pegasus.framework.http.G4HttpClient.Protocol.HTTP;


public interface EntityService<T extends AbstractEntity> {

    G4HttpClient g4HttpClient = new G4HttpClient(G4Properties.getRunProperties().getApplicationURL(), HTTP);
    AppContext appContext = AppContext.get();

    /**
     * ADD new entity
     *
     * @param entity entity
     * @return {@link OperationResult}
     */
    OperationResult<?> add(T entity);

    /**
     * DELETE entity
     *
     * @param entity entity
     * @return {@link OperationResult}
     */
    OperationResult remove(T entity);

    /**
     * Filter Entities Search
     *
     * @param filter search filter for payload
     * @return {@link OperationResult}
     */
    OperationResult<List<T>> search(SearchFilter filter);

    /**
     * Get list of entities
     *
     * @return {@link OperationResult}
     */
    OperationResult<List<T>> list();

    /**
     * UPDATE entity
     *
     * @param entity entity
     * @return {@link OperationResult}
     */
    OperationResult<T> update(T entity);

    /**
     * GET entity
     *
     * @param id id of entity
     * @return {@link OperationResult}
     */
    OperationResult<T> view(String id);
}
