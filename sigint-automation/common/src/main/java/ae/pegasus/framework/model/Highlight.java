package ae.pegasus.framework.model;


public class Highlight
{
    private String preTags = "[mark]";

    private Integer fragmentSize = 0;

    private String postTags = "[/mark]";

    public String getPreTags ()
    {
        return preTags;
    }

    public void setPreTags (String preTags)
    {
        this.preTags = preTags;
    }

    public Integer getFragmentSize ()
    {
        return fragmentSize;
    }

    public void setFragmentSize (Integer fragmentSize)
    {
        this.fragmentSize = fragmentSize;
    }

    public String getPostTags ()
    {
        return postTags;
    }

    public void setPostTags (String postTags)
    {
        this.postTags = postTags;
    }

}


