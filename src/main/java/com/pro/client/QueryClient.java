package com.pro.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.MessageContext;

import com.alibaba.fastjson.JSONObject;
import com.pro.service.Query;

public class QueryClient {
    private static String WS_URL = "http://127.0.0.1:8080/ws/Query?wsdl";
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
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("serviceName", "RESOURCE_DIRECTORY_VEHICLE_BY_PLATE");
        jsonObject1.put("vehicleNo", "");
        jsonObject1.put("plateColorCode", "");
        jsonObject1.put("provinceCode", "");

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("serviceName", "T_LIMITS_JK");
        jsonObject2.put("FLAG", "1");

        String result = QueryClient.getClient(jsonObject1.toJSONString());
        System.out.println(result);
    }

}
