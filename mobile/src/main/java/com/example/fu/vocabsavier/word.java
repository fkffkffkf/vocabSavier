package com.example.fu.vocabsavier;

/**
 * Created by FU on 6/6/2016.
 */
public class word {
    private int id;
    private String w;
    private String explain;
    public word(){}
    public word(String w,String explain)
    {
        super();
        this.w=w;
        this.explain=explain;
    }
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        id=id;
    }
    public String getWord()
    {
        return w;
    }
    public String getExplain()
    {
        return explain;
    }
}
