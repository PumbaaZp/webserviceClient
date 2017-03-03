package com.pro.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.pro.service.Refresh;

public class RefreshClient {

    private static String WS_URL_REFRESH = "http://127.0.0.1:8080/ws/Refresh?wsdl";

    public static String getClientRefresh() {
        URL url = null;
        try {
            url = new URL(WS_URL_REFRESH);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        QName qname = new QName("http://service.ws.com/", "RefreshService");
        Service service = Service.create(url, qname);
        Refresh refreshService = service.getPort(Refresh.class);
        String result = refreshService.refreshConfig();
        return result;
    }

    public static void main(String[] args) throws Exception {

        String resultRefresh = RefreshClient.getClientRefresh();
        System.out.println(resultRefresh);
    }
}
