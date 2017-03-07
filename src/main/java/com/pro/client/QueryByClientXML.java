package com.pro.client;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.pro.service.Query;

public class QueryByClientXML {

    public static ApplicationContext context = new ClassPathXmlApplicationContext(
            new String[] { "webservice-client.xml" });

    public static void main(String[] args) {

        Query query = (Query) context.getBean("QueryClient");

        Map<String, Object> req_ctx = ((BindingProvider) query).getRequestContext();
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Username", Collections.singletonList("TEST"));
        headers.put("Password", Collections.singletonList("TEST"));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("serviceName", "RESOURCE_DIRECTORY_PERSON_BY_ID_CARD");
        jsonObject.put("idCard", "330726196710020512");
        jsonObject.put("idType", 1);
        jsonObject.put("provinceCode", "330000");

        String result = query.query(jsonObject.toJSONString());
        System.out.println(result);

    }
}

