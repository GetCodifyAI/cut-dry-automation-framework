package com.cutanddry.qa.aio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FolderDetails {
    private String name;
    private String id;
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
}
