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
import com.pro.service.QueryDelegate;

public class QueryClient {
    private static String WS_URL = "http://127.0.0.1:8080/ws/QueryPort?wsdl";
    public static String getClient(String param) {
        URL url = null;
        try {
            url = new URL(WS_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        QName qname = new QName("http://service.ws.com/", "QueryDelegateService");
        Service service = Service.create(url, qname);
        QueryDelegate queryDelegate = service.getPort(QueryDelegate.class);
        Map<String, Object> req_ctx = ((BindingProvider) queryDelegate).getRequestContext();
        req_ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, WS_URL);

        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Username", Collections.singletonList("TEST"));
        headers.put("Password", Collections.singletonList("TEST"));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
        String result = queryDelegate.query(param);
        return result;
    }

    public static void main(String[] args) throws Exception {
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("serviceName", "RESOURCE_DIRECTORY_PERSON_BY_ID_CARD");
        jsonObject1.put("idCard", "330726196710020512");
        jsonObject1.put("idType", 1);
        jsonObject1.put("provinceCode", "330000");

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("serviceName", "T_LIMITS_JK");
        jsonObject2.put("FLAG", "1");

        String result = QueryClient.getClient(jsonObject1.toJSONString());
        System.out.println(result);
    }

}
