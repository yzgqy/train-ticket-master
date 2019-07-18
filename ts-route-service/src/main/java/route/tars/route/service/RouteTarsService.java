package route.tars.route.service;

import route.entity.RouteInfo;
import route.tars.route.ResponseRoute;
import route.tars.route.ResponseRoutes;
import route.tars.route.ResponseString;
import route.tars.route.RouteInfoTars;

/**
 * @Auther: yaya
 * @Date: 2019/7/12 14:49
 * @Description:
 */
public interface RouteTarsService {
    ResponseRoutes getRouteByStartAndTerminal(String startId, String terminalId);

    ResponseRoutes getAllRoutes();

    ResponseRoute getRouteById(String routeId);

    ResponseString deleteRoute(String routeId);

    ResponseRoute createAndModify(RouteInfoTars info);
}
