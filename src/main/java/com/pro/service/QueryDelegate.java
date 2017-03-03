package com.pro.service;

import javax.jws.WebService;

@WebService(targetNamespace = "http://service.ws.com/")
public interface QueryDelegate {
    public String query(String parameters);
}
