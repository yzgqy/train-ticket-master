package fdse.microservice.tars.basic.service;

import fdse.microservice.entity.PriceConfig;
import fdse.microservice.entity.Route;
import fdse.microservice.entity.TrainType;
import fdse.microservice.tars.basic.ResponseString;
import fdse.microservice.tars.basic.ResponseTravelResult;
import fdse.microservice.tars.basic.TravelResultTars;
import fdse.microservice.tars.basic.TravelTars;
import fdse.microservice.tars.rpc.priceconfig.PriceControllerPrx;
import fdse.microservice.tars.rpc.priceconfig.ResponsePriceConfig;
import fdse.microservice.tars.rpc.route.ResponseRoute;
import fdse.microservice.tars.rpc.route.RouteControllerPrx;
import fdse.microservice.tars.rpc.station.StationControllerPrx;
import fdse.microservice.tars.rpc.train.ResponseTrainType;
import fdse.microservice.tars.rpc.train.TrainControllerPrx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @Auther: yaya
 * @Date: 2019/7/17 10:37
 * @Description:
 */
@Service
@Slf4j
public class BasicTarsServiceImpl implements BasicTarsService{
    @Autowired
    private RouteControllerPrx routeControllerPrx;

    @Autowired
    private StationControllerPrx stationControllerPrx;

    @Autowired
    private TrainControllerPrx trainControllerPrx;

    @Autowired
    private PriceControllerPrx priceControllerPrx;

    @Override
    public ResponseTravelResult queryForTravel(TravelTars info) {
        ResponseTravelResult response = new ResponseTravelResult();
        TravelResultTars result = new TravelResultTars();
        result.setStatus(true);
        response.setStatus(1);
        response.setMsg("Success");
        boolean startingPlaceExist = checkStationExists(info.getStartingPlace());
        boolean endPlaceExist = checkStationExists(info.getEndPlace());
        if (!startingPlaceExist || !endPlaceExist) {
            result.setStatus(false);
            response.setStatus(0);
            response.setMsg("Start place or end place not exist!");
        }

        TrainType trainType = queryTrainType(info.getTrip().getTrainTypeId());
        if (trainType == null) {
            System.out.println("traintype doesn't exist");
            result.setStatus(false);
            response.setStatus(0);
            response.setMsg("Train type doesn't exist");
        } else {
            result.setTrainType(trainType.toTars());
        }

        String routeId = info.getTrip().getRouteId();
        String trainTypeString = trainType.getId();
        Route route = getRouteByRouteId(routeId);
        PriceConfig priceConfig = queryPriceConfigByRouteIdAndTrainType(routeId, trainTypeString);

        String startingPlaceId = (String) queryForStationId(info.getStartingPlace()).getData();
        String endPlaceId = (String) queryForStationId(info.getEndPlace()).getData();

        log.info("startingPlaceId : " + startingPlaceId + "endPlaceId : " + endPlaceId);

        int indexStart = route.getStations().indexOf(startingPlaceId);
        int indexEnd = route.getStations().indexOf(endPlaceId);

        log.info("indexStart : " + indexStart + " __ " + "indexEnd : " + indexEnd);
        log.info("route.getDistances().size : " + route.getDistances().size());
        HashMap<String, String> prices = new HashMap<String, String>();
        try {
            int distance = route.getDistances().get(indexEnd) - route.getDistances().get(indexStart);

            double priceForEconomyClass = distance * priceConfig.getBasicPriceRate();//需要price Rate和距离（起始站）
            double priceForConfortClass = distance * priceConfig.getFirstClassPriceRate();
            prices.put("economyClass", "" + priceForEconomyClass);
            prices.put("confortClass", "" + priceForConfortClass);
        }catch (Exception e){
            prices.put("economyClass", "95.0");
            prices.put("confortClass", "120.0");
        }
        result.setPrices(prices);
        result.setPercent(1.0);
        response.setData(result);
        return response;
    }

    @Override
    public ResponseString queryForStationId(String stationName) {
        System.out.println("[Basic Information Service][Query For Station Id] Station Id:" + stationName);
        fdse.microservice.tars.rpc.station.ResponseString response = stationControllerPrx.queryForStationId(stationName);
        ResponseString rp = new ResponseString();
        rp.setData(response.getData());
        rp.setMsg(response.getMsg());
        rp.setStatus(response.getStatus());
        return  rp;
    }

    public boolean checkStationExists(String stationName) {
        System.out.println("[Basic Information Service][Check Station Exists] Station Name:" + stationName);
        fdse.microservice.tars.rpc.station.ResponseString exist = stationControllerPrx.queryForStationId(stationName);
        if (exist.getStatus() ==1)
            return true;
        return false;
    }

    public TrainType queryTrainType(String trainTypeId) {
        System.out.println("[Basic Information Service][Query Train Type] Train Type:" + trainTypeId);
        ResponseTrainType response = trainControllerPrx.retrieve(trainTypeId);
        return new TrainType(response.getData());
    }

    private Route getRouteByRouteId(String routeId) {
        System.out.println("[Basic Information Service][Get Route By Id] Route ID：" + routeId);
        ResponseRoute result =routeControllerPrx.queryById(routeId);
        if ( result.getStatus() == 0) {
            System.out.println("[Basic Information Service][Get Route By Id] Fail." + result.getMsg());
            return null;
        } else {
            System.out.println("[Basic Information Service][Get Route By Id] Success.");
            return new Route(result.getData());
        }
    }

    private PriceConfig queryPriceConfigByRouteIdAndTrainType(String routeId, String trainType) {
        System.out.println("[Basic Information Service][Query For Price Config] RouteId:"
                + routeId + "TrainType:" + trainType);

        ResponsePriceConfig result = priceControllerPrx.query(routeId,trainType);
        System.out.println("Response Resutl to String " + result.toString());
        return  new PriceConfig(result.getData());
    }
}
