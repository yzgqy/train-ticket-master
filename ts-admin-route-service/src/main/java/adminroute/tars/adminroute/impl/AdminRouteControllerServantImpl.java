package adminroute.tars.adminroute.impl;

import adminroute.tars.adminroute.AdminRouteControllerServant;
import adminroute.tars.adminroute.RouteInfoTars;
import adminroute.tars.adminroute.service.AdminRouteTarsService;
import adminroute.tars.rpc.route.ResponseRoute;
import adminroute.tars.rpc.route.ResponseRoutes;
import adminroute.tars.rpc.route.ResponseString;
import com.qq.tars.spring.annotation.TarsServant;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: yaya
 * @Date: 2019/7/17 15:43
 * @Description:
 */
@TarsServant("AdminRouteObj")
public class AdminRouteControllerServantImpl implements AdminRouteControllerServant {
    @Autowired
    private AdminRouteTarsService adminRouteTarsService;

    @Override
    public String home() {
        return "Welcome to [ AdminRoute Service ] !";
    }

    @Override
    public ResponseRoutes getAllRoutes() {

        return adminRouteTarsService.getAllRoutes();
    }

    @Override
    public ResponseRoute addRoute(RouteInfoTars request) {
        return adminRouteTarsService.createAndModifyRoute(request);

    }

    @Override
    public ResponseString deleteRoute(String routeId) {
        return adminRouteTarsService.deleteRoute(routeId);

    }
}
