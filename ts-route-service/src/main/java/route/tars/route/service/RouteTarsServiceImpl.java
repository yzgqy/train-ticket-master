package route.tars.route.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import route.entity.Route;
import route.repository.RouteRepository;
import route.tars.route.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Auther: yaya
 * @Date: 2019/7/12 14:49
 * @Description:
 */
@Service
public class RouteTarsServiceImpl implements RouteTarsService{
    @Autowired
    private RouteRepository routeRepository;

    @Override
    public ResponseRoutes getRouteByStartAndTerminal(String startId, String terminalId) {
        ArrayList<Route> routes = routeRepository.findAll();
        List<RouteTars> routeTars = new ArrayList<>();
        System.out.println("[Route Service] Find All:" + routes.size());
        List<Route> resultList = new ArrayList<>();
        for (Route route : routes) {
            if (route.getStations().contains(startId) &&
                    route.getStations().contains(terminalId) &&
                    route.getStations().indexOf(startId) < route.getStations().indexOf(terminalId)) {
                resultList.add(route);
                routeTars.add(route.toTars());
            }
        }
        if (resultList.size() > 0) {
            return new ResponseRoutes(1, "Success", routeTars);
        } else {
            return new ResponseRoutes(0, "No routes with the startId and terminalId"+startId + " -- " + terminalId, null);
        }
    }

    @Override
    public ResponseRoutes getAllRoutes() {
        ArrayList<Route> routes = routeRepository.findAll();
        List<RouteTars> routeTars = new ArrayList<>();

        if (routes != null && routes.size() > 0) {
            for (Route route : routes) {
                routeTars.add(route.toTars());
            }
            return new ResponseRoutes(1, "Success", routeTars);
        } else {
            return new ResponseRoutes(0, "No Content", routeTars);
        }
    }

    @Override
    public ResponseRoute getRouteById(String routeId) {
        Route route = routeRepository.findById(routeId);
        if (route == null) {
            return new ResponseRoute(0, "No content with the routeId", null);
        } else {
            return new ResponseRoute(1, "Success", route.toTars());
        }
    }

    @Override
    public ResponseString deleteRoute(String routeId) {
        routeRepository.removeRouteById(routeId);
        Route route = routeRepository.findById(routeId);
        if (route == null) {
            return new ResponseString(1, "Delete Success", routeId);
        } else {
            return new ResponseString(0, "Delete failed, Reason unKnown with this routeId", routeId);
        }
    }

    @Override
    public ResponseRoute createAndModify(RouteInfoTars info) {
        System.out.println("[Route Service] Create And Modify Start:" + info.getStartStation() + " End:" + info.getEndStation());

        String[] stations = info.getStationList().split(",");
        String[] distances = info.getDistanceList().split(",");
        List<String> stationList = new ArrayList<>();
        List<Integer> distanceList = new ArrayList<>();
        if (stations.length != distances.length) {
            System.out.println("Station Number Not Equal To Distance Number");

            return new ResponseRoute(0, "Station Number Not Equal To Distance Number", null);
        }
        for (int i = 0; i < stations.length; i++) {
            stationList.add(stations[i]);
            distanceList.add(Integer.parseInt(distances[i]));
        }
        if (info.getId() == null || info.getId().length() < 10) {
            Route route = new Route();
            route.setId(UUID.randomUUID().toString());
            route.setStartStationId(info.getStartStation());
            route.setTerminalStationId(info.getEndStation());
            route.setStations(stationList);
            route.setDistances(distanceList);
            routeRepository.save(route);
            System.out.println("Save success");

            return new ResponseRoute(1, "Save Success", route.toTars());
        } else {
            Route route = routeRepository.findById(info.getId());
            if (route == null) {
                route = new Route();
                route.setId(info.getId());
            }

            route.setStartStationId(info.getStartStation());
            route.setTerminalStationId(info.getEndStation());
            route.setStations(stationList);
            route.setDistances(distanceList);
            routeRepository.save(route);
            System.out.println("Modify success");
            return new ResponseRoute(1, "Modify success", route.toTars());
        }
    }
}
