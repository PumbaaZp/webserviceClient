package com.pro.service;

import javax.jws.WebService;

@WebService(targetNamespace = "http://service.ws.com/")
public interface Refresh {
    public String refreshConfig();
}
