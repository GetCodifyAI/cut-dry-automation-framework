package com.cutanddry.qa.data.models.api.requests.create_task;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "controller",
        "method",
        "args",
        "run_immediately"
})

public class CreateTaskRequest {

    @JsonProperty("controller")
    public String controller;
    @JsonProperty("method")
    public String method;
    @JsonProperty("args")
    public List<String> args;
    @JsonProperty("run_immediately")
    public Boolean runImmediately;


    public CreateTaskRequest() {
    }

    public CreateTaskRequest(String controller, String method, List<String> args, Boolean runImmediately) {
        super();
        this.controller = controller;
        this.method = method;
        this.args = args;
        this.runImmediately = runImmediately;
    }

}
