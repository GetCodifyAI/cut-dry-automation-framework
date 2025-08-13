package com.cutanddry.qa.data.models.api.responses.create_task;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "task_id",
        "message"
})

public class CreateTaskResponse {

    @JsonProperty("task_id")
    public Integer taskId;
    @JsonProperty("message")
    public String message;


    public CreateTaskResponse() {
    }

    public CreateTaskResponse(Integer taskId, String message) {
        super();
        this.taskId = taskId;
        this.message = message;
    }

}