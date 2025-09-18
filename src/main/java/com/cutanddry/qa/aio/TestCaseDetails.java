package com.cutanddry.qa.aio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TestCaseDetails {
    private String key;
    private String title;
    private String description;
    private String precondition;
    private List<TestStep> steps;
    private FolderDetails folder;
    private Priority priority;
    private Status status;
    
    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getPrecondition() { return precondition; }
    public void setPrecondition(String precondition) { this.precondition = precondition; }
    
    public List<TestStep> getSteps() { return steps; }
    public void setSteps(List<TestStep> steps) { this.steps = steps; }
    
    public FolderDetails getFolder() { return folder; }
    public void setFolder(FolderDetails folder) { this.folder = folder; }
    
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
    
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
