package com.cutanddry.qa.data.models.api.responses.get_task;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "task_id",
        "message"
})

public class GetTaskResponse {

    @JsonProperty("task_id")
    public String taskId;
    @JsonProperty("message")
    public String message;

    public GetTaskResponse() {
    }

    public GetTaskResponse(String taskId, String message) {
        super();
        this.taskId = taskId;
        this.message = message;
    }

}