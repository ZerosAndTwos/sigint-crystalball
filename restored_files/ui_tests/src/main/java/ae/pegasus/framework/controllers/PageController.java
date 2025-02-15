package ae.pegasus.framework.controllers;

import ae.pegasus.framework.pages.SigintPage;

public abstract class PageController<T extends SigintPage> {

    public T getPage() {
        return page;
    }

    public void setPage(T page) {
        this.page = page;
    }

    private T page;

    public PageController(SigintPage page, Class<T> userClass) {
        this.page = userClass.cast(page);
    }

    public PageController(T page) {
        this.page = page;
    }
}
