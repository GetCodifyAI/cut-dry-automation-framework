package com.cutanddry.qa.data.models.api.responses.get_task;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "task_id",
        "expires_at",
        "running",
        "controller",
        "method",
        "args",
        "created_at"
})

public class GetPendingTaskResponse {

    @JsonProperty("task_id")
    public Integer taskId;
    @JsonProperty("expires_at")
    public Integer expiresAt;
    @JsonProperty("running")
    public Boolean running;
    @JsonProperty("controller")
    public String controller;
    @JsonProperty("method")
    public String method;
    @JsonProperty("args")
    public List<String> args;
    @JsonProperty("created_at")
    public Integer createdAt;


    public GetPendingTaskResponse() {
    }

    public GetPendingTaskResponse(Integer taskId, Integer expiresAt, Boolean running, String controller, String method, List<String> args, Integer createdAt) {
        super();
        this.taskId = taskId;
        this.expiresAt = expiresAt;
        this.running = running;
        this.controller = controller;
        this.method = method;
        this.args = args;
        this.createdAt = createdAt;
    }

}
