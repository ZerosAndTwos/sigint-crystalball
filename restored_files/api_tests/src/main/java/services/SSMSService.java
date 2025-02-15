package services;

import abs.EntityList;
import abs.SearchFilter;
import json.RsClient;
import model.AppContext;
import model.SSMS;
import org.apache.log4j.Logger;

public class SSMSService implements EntityService<SSMS> {

    private static RsClient rsClient = new RsClient();
    private static AppContext context = AppContext.getContext();
    private Logger log = Logger.getLogger(SSMSService.class);
    private final String sigintHost = context.environment().getSigintHost();

    @Override
    public int add(SSMS entity) {
        return 0;
    }

    @Override
    public int remove(SSMS entity) {
        return 0;
    }

    @Override
    public EntityList<SSMS> list(SearchFilter filter) {
        return null;
    }

    @Override
    public int update(SSMS entity) {
        return 0;
    }

    @Override
    public SSMS view(String id) {
        return null;
    }

}
