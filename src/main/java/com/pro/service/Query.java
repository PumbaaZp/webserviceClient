package com.pro.service;

import javax.jws.WebService;

@WebService(targetNamespace = "http://webservice.ws.com/")
public interface Query {
    public String query(String parameters);
}
