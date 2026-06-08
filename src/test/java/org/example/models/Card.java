package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Card {

    private String id;
    private String name;
    private String desc;
    private String idList;
    private String idBoard;
    private boolean closed;
    private String url;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }

    public String getIdList() { return idList; }
    public void setIdList(String idList) { this.idList = idList; }

    public String getIdBoard() { return idBoard; }
    public void setIdBoard(String idBoard) { this.idBoard = idBoard; }

    public boolean isClosed() { return closed; }
    public void setClosed(boolean closed) { this.closed = closed; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}
