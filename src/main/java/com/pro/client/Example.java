package com.pro.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.MessageContext;

import com.alibaba.fastjson.JSONObject;

public class Example {
    private static String WS_URL = "http://10.100.32.41:8893/ws/Query?wsdl";

    public static String getClient(String param) {
        URL url = null;
        try {
            url = new URL(WS_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        QName qname = new QName("http://webservice.ws.com/", "QueryService");
        Service service = Service.create(url, qname);
        Query query = service.getPort(Query.class);
        Map<String, Object> req_ctx = ((BindingProvider) query).getRequestContext();
        req_ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, WS_URL);

        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Username", Collections.singletonList("TEST"));
        headers.put("Password", Collections.singletonList("TEST"));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
        String result = query.query(param);
        return result;
    }

    public static void main(String[] args) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("serviceName", "RESOURCE_DIRECTORY_PERSON_BY_ID_CARD");
        jsonObject.put("idCard", "330726196710020512");
        jsonObject.put("idType", "1");
        jsonObject.put("provinceCode", "330000");
        jsonObject.put("page", "1");        
        String result = Example.getClient(jsonObject.toJSONString()); 
        System.out.println(result);
    }

    @WebService(targetNamespace = "http://webservice.ws.com/")
    public static interface Query{
        public String query(String parameters);
    }
}
