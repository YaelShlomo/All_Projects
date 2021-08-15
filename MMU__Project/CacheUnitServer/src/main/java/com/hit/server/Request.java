package com.hit.server;

import java.io.Serializable;
import java.util.Map;

public class Request<T> extends Object implements Serializable {

    T body;
    Map<String, String> headers;

    /**
     *
     * @param headers
     * @param body
     */
    public Request(java.util.Map<java.lang.String,java.lang.String> headers, T body) {
        this.headers = headers;
        this.body = body;
    }
    public T getBody() {
        return this.body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public java.util.Map<java.lang.String,java.lang.String> getHeaders() {
        return this.headers;
    }

    public void setHeaders(java.util.Map<java.lang.String,java.lang.String> headers) {
        this.headers = headers;
    }

    @Override
    public String toString() {
        return "{" +
                "headers:" + this.headers + ", "
                + "body" + this.body+"}";
    }


}
