package adminroute.tars.adminroute.service;

import adminroute.entity.RouteInfo;
import adminroute.tars.adminroute.RouteInfoTars;
import adminroute.tars.rpc.route.ResponseRoute;
import adminroute.tars.rpc.route.ResponseRoutes;
import adminroute.tars.rpc.route.ResponseString;

/**
 * @Auther: yaya
 * @Date: 2019/7/17 15:43
 * @Description:
 */
public interface AdminRouteTarsService {
    ResponseRoutes getAllRoutes();

    ResponseRoute createAndModifyRoute(RouteInfoTars request);

    ResponseString deleteRoute(String routeId);
}
