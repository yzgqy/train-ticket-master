// **********************************************************************
// This file was generated by a TARS parser!
// TARS version 1.0.1.
// **********************************************************************

package adminroute.tars.rpc.route;

import com.qq.tars.protocol.annotation.*;
import com.qq.tars.protocol.tars.annotation.*;
import com.qq.tars.common.support.Holder;

@Servant
public interface RouteControllerPrx {

	public String home();

	public String home(@TarsContext java.util.Map<String, String> ctx);

	public void async_home(@TarsCallback RouteControllerPrxCallback callback);

	public void async_home(@TarsCallback RouteControllerPrxCallback callback, @TarsContext java.util.Map<String, String> ctx);

	public ResponseRoute createAndModifyRoute(RouteInfoTars createAndModifyRouteInfo);

	public ResponseRoute createAndModifyRoute(RouteInfoTars createAndModifyRouteInfo, @TarsContext java.util.Map<String, String> ctx);

	public void async_createAndModifyRoute(@TarsCallback RouteControllerPrxCallback callback, RouteInfoTars createAndModifyRouteInfo);

	public void async_createAndModifyRoute(@TarsCallback RouteControllerPrxCallback callback, RouteInfoTars createAndModifyRouteInfo, @TarsContext java.util.Map<String, String> ctx);

	public ResponseString deleteRoute(String routeId);

	public ResponseString deleteRoute(String routeId, @TarsContext java.util.Map<String, String> ctx);

	public void async_deleteRoute(@TarsCallback RouteControllerPrxCallback callback, String routeId);

	public void async_deleteRoute(@TarsCallback RouteControllerPrxCallback callback, String routeId, @TarsContext java.util.Map<String, String> ctx);

	public ResponseRoute queryById(String routeId);

	public ResponseRoute queryById(String routeId, @TarsContext java.util.Map<String, String> ctx);

	public void async_queryById(@TarsCallback RouteControllerPrxCallback callback, String routeId);

	public void async_queryById(@TarsCallback RouteControllerPrxCallback callback, String routeId, @TarsContext java.util.Map<String, String> ctx);

	public ResponseRoutes queryAll();

	public ResponseRoutes queryAll(@TarsContext java.util.Map<String, String> ctx);

	public void async_queryAll(@TarsCallback RouteControllerPrxCallback callback);

	public void async_queryAll(@TarsCallback RouteControllerPrxCallback callback, @TarsContext java.util.Map<String, String> ctx);

	public ResponseRoutes queryByStartAndTerminal(String startId, String terminalId);

	public ResponseRoutes queryByStartAndTerminal(String startId, String terminalId, @TarsContext java.util.Map<String, String> ctx);

	public void async_queryByStartAndTerminal(@TarsCallback RouteControllerPrxCallback callback, String startId, String terminalId);

	public void async_queryByStartAndTerminal(@TarsCallback RouteControllerPrxCallback callback, String startId, String terminalId, @TarsContext java.util.Map<String, String> ctx);
}
