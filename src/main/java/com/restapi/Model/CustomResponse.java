package com.restapi.Model;

import java.io.Serializable;

public class CustomResponse implements Serializable {
    private Object response;
    public CustomResponse(){

    }
    public CustomResponse(Object response){this.response=response;}

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}


