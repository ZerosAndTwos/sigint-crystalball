package model;

public class ProfileEntitySearchFilter extends SearchFilter<ProfileEntity> {

    private String keywords;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean isAppliedToEntity(ProfileEntity entity) {
        return false;
    }
}
