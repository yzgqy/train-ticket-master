package adminroute.tars.adminroute.service;

import adminroute.entity.RouteInfo;
import adminroute.tars.adminroute.RouteInfoTars;
import adminroute.tars.rpc.route.ResponseRoute;
import adminroute.tars.rpc.route.ResponseRoutes;
import adminroute.tars.rpc.route.ResponseString;
import adminroute.tars.rpc.route.RouteControllerPrx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: yaya
 * @Date: 2019/7/17 15:43
 * @Description:
 */
@Service
@Slf4j
public class AdminRouteTarsServiceImpl implements AdminRouteTarsService{
    @Autowired
    private RouteControllerPrx routeControllerPrx;

    @Override
    public ResponseRoutes getAllRoutes() {
        return routeControllerPrx.queryAll();
    }

    @Override
    public ResponseRoute createAndModifyRoute(RouteInfoTars request) {
        adminroute.tars.rpc.route.RouteInfoTars routeInfoTars = new adminroute.tars.rpc.route.RouteInfoTars();
        routeInfoTars.setId(request.getId());
        routeInfoTars.setDistanceList(request.getDistanceList());
        routeInfoTars.setStationList(request.getStationList());
        routeInfoTars.setEndStation(request.getEndStation());
        routeInfoTars.setStartStation(request.getStartStation());
        return routeControllerPrx.createAndModifyRoute(routeInfoTars);
    }

    @Override
    public ResponseString deleteRoute(String routeId) {

        return routeControllerPrx.deleteRoute(routeId);
    }
}
