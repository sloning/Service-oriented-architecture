package com.example.service.impl;

import com.example.ServiceDiscovery;
import com.example.exception.BadRequestException;
import com.example.exception.EntityNotFoundException;
import com.example.model.Length;
import com.example.model.Route;
import com.example.service.RouteService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.Comparator;
import java.util.List;

@Stateless(name = "RouteService")
@Remote(RouteService.class)
public class RouteServiceImpl implements RouteService {

    @EJB
    private ServiceDiscovery serviceDiscovery;

    private final OkHttpClient client = new OkHttpClient().newBuilder()
            .hostnameVerifier((hostname, session) -> hostname.equals("127.0.0.1") || hostname.equals("localhost"))
            .build();

    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    private String get(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            var json = new JSONObject(response.body().string());
            return json.getJSONArray("content").toString();
        } catch (Exception e) {
            throw new BadRequestException("Invalid request");
        }
    }

    public List<Route> findFromLocationToLocation(Integer idFrom, Integer idTo, Integer page, Integer size, String orderBy) {
        String url = buildUrl(idFrom, idTo, page, size, orderBy);

        var response = get(url);
        try {
            return mapper.readValue(response, new TypeReference<>() {});
        } catch (Exception e) {
            throw new BadRequestException("Invalid request");
        }
    }

    private String buildUrl(Integer idFrom, Integer idTo, Integer page, Integer size, String orderBy) {
        String url = "https://" + serviceDiscovery.getMainServiceAddress() + "/routes";
        url += "?locationFromFilter=" + idFrom;
        url += "&locationToFilter=" + idTo;
        url += "&page=";
        url += page == null ? 0 : page;
        url += "&size=";
        url += size == null ? Integer.MAX_VALUE : size;
        if (orderBy != null) {
            url += "&sort=" + orderBy + ",desc";
        }
        return url;
    }

    public List<Route> findFromLocationToLocation(Integer idFrom, Integer idTo) {
        return findFromLocationToLocation(idFrom, idTo, 0, Integer.MAX_VALUE, null);
    }

    public Route getRouteByLength(Integer idFrom, Integer idTo, Length length) {
        if (length == null) {
            throw new BadRequestException("Invalid length");
        }
        List<Route> routes = findFromLocationToLocation(idFrom, idTo);
        if (routes.isEmpty()) {
            throw new EntityNotFoundException("There are no routes");
        }

        if (length == Length.SHORTEST) {
            return routes.stream().min(Comparator.comparing(route -> route.getDistance())).get();
        }
        return routes.stream().max(Comparator.comparing(route -> route.getDistance())).get();
    }

    public List<Route> getOrderedRoutes(Integer idFrom, Integer idTo, String orderBy) {
        return findFromLocationToLocation(idFrom, idTo, 0, Integer.MAX_VALUE, orderBy);
    }
}
