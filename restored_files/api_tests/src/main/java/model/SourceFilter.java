package model;

import abs.SearchFilter;

public class SourceFilter extends SearchFilter<Source> {

    @Override
    public boolean isAppliedToEntity(Source entity) {
        return false;
    }
}
