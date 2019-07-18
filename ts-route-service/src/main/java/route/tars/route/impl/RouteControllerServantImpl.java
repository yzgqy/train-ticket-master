package route.tars.route.impl;

import com.qq.tars.spring.annotation.TarsServant;
import org.springframework.beans.factory.annotation.Autowired;
import route.tars.route.*;
import route.tars.route.service.RouteTarsService;

/**
 * @Auther: yaya
 * @Date: 2019/7/12 14:49
 * @Description:
 */
@TarsServant("RouteObj")
public class RouteControllerServantImpl implements RouteControllerServant {
    @Autowired
    private RouteTarsService routeTarsService;

    @Override
    public String home() {
        return "Welcome to [ Route Service ] !";
    }

    @Override
    public ResponseRoute createAndModifyRoute(RouteInfoTars createAndModifyRouteInfo) {
        System.out.println("Create Route id: " + createAndModifyRouteInfo.getId());
        return routeTarsService.createAndModify(createAndModifyRouteInfo);
    }

    @Override
    public ResponseString deleteRoute(String routeId) {
        System.out.println("Route id: " + routeId);
        return routeTarsService.deleteRoute(routeId);
    }

    @Override
    public ResponseRoute queryById(String routeId) {
        System.out.println("Route id: " + routeId);
        return routeTarsService.getRouteById(routeId);
    }

    @Override
    public ResponseRoutes queryAll() {
        return routeTarsService.getAllRoutes();
    }

    @Override
    public ResponseRoutes queryByStartAndTerminal(String startId, String terminalId) {
        System.out.println("startId : " + startId + ",  terminalId： " +terminalId );
        return routeTarsService.getRouteByStartAndTerminal(startId, terminalId);
    }
}
