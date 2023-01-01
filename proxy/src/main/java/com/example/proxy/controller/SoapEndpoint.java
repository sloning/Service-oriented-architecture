package com.example.proxy.controller;

import javax.xml.namespace.QName;

public interface SoapEndpoint {
    String NAMESPACE_URI = "https://github.com/sloning/Service-oriented-architecture";

    interface QNames {
        QName getRouteByLength = new QName(NAMESPACE_URI, "getRouteByLength");
        QName getOrderedRoutes = new QName(NAMESPACE_URI, "getOrderedRoutes");
    }
}
