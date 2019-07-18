package adminbasic.tars.adminbasicinfo.service;

import adminbasic.tars.rpc.config.ConfigControllerPrx;
import adminbasic.tars.rpc.config.ConfigTars;
import adminbasic.tars.rpc.config.ResponseConfig;
import adminbasic.tars.rpc.config.ResponseConfigs;
import adminbasic.tars.rpc.contacts.*;
import adminbasic.tars.rpc.priceconfig.PriceConfigTars;
import adminbasic.tars.rpc.priceconfig.PriceControllerPrx;
import adminbasic.tars.rpc.priceconfig.ResponsePriceConfig;
import adminbasic.tars.rpc.priceconfig.ResponsePriceConfigs;
import adminbasic.tars.rpc.station.ResponseStation;
import adminbasic.tars.rpc.station.ResponseStations;
import adminbasic.tars.rpc.station.StationControllerPrx;
import adminbasic.tars.rpc.station.StationTars;
import adminbasic.tars.rpc.train.ResponseTrainType;
import adminbasic.tars.rpc.train.ResponseTrainTypes;
import adminbasic.tars.rpc.train.TrainControllerPrx;
import adminbasic.tars.rpc.train.TrainTypeTars;
import edu.fudan.common.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: yaya
 * @Date: 2019/7/18 15:25
 * @Description:
 */
@Service
@Slf4j
public class AdminBasicInfoTarsServiceImpl implements AdminBasicInfoTarsService {
    @Autowired
    private ConfigControllerPrx configControllerPrx;

    @Autowired
    private ContactsControllerPrx contactsControllerPrx;

    @Autowired
    private PriceControllerPrx priceControllerPrx;

    @Autowired
    private StationControllerPrx stationControllerPrx;

    @Autowired
    private TrainControllerPrx trainControllerPrx;


    @Override
    public Response<List<ContactsTars>> getAllContacts() {
        ResponseContactsList responseContactsList = contactsControllerPrx.getAllContacts();
        return new Response<>(responseContactsList.getStatus(),responseContactsList.getMsg(),responseContactsList.getData());
    }

    @Override
    public Response<ContactsTars> addContact(ContactsTars c) {
        ResponseContacts responseContacts = contactsControllerPrx.createNewContactsAdmin(c);
        return new Response<>(responseContacts.getStatus(),responseContacts.getMsg(),responseContacts.getData());

    }

    @Override
    public Response<String> deleteContact(String contactsId) {
        ResponseString responseString =contactsControllerPrx.deleteContacts(contactsId);
        return new Response<>(responseString.getStatus(),responseString.getMsg(),responseString.getData());

    }

    @Override
    public Response<ContactsTars> modifyContact(ContactsTars mci) {
        ResponseContacts responseContacts = contactsControllerPrx.modifyContacts(mci);
        return new Response<>(responseContacts.getStatus(),responseContacts.getMsg(),responseContacts.getData());
    }

    @Override
    public Response<List<StationTars>> getAllStations() {
        ResponseStations responseStations = stationControllerPrx.query();
        return new Response<>(responseStations.getStatus(),responseStations.getMsg(),responseStations.getData());
    }

    @Override
    public Response<StationTars> addStation(StationTars s) {
        ResponseStation responseStation = stationControllerPrx.create(s);
        return new Response<>(responseStation.getStatus(),responseStation.getMsg(),responseStation.getData());
    }

    @Override
    public Response<StationTars> deleteStation(StationTars s) {
        ResponseStation responseStation = stationControllerPrx.delete(s);
        return new Response<>(responseStation.getStatus(),responseStation.getMsg(),responseStation.getData());

    }

    @Override
    public Response<StationTars> modifyStation(StationTars s) {
        ResponseStation responseStation = stationControllerPrx.update(s);
        return new Response<>(responseStation.getStatus(),responseStation.getMsg(),responseStation.getData());

    }

    @Override
    public Response<List<TrainTypeTars>> getAllTrains() {
        ResponseTrainTypes responseTrainTypes = trainControllerPrx.query();
        return new Response<>(responseTrainTypes.getStatus(),responseTrainTypes.getMsg(),responseTrainTypes.getData());

    }

    @Override
    public Response<TrainTypeTars> addTrain(TrainTypeTars t) {
        ResponseTrainType responseTrainType = trainControllerPrx.create(t);
        return new Response<>(responseTrainType.getStatus(),responseTrainType.getMsg(),responseTrainType.getData());

    }

    @Override
    public Response<TrainTypeTars> deleteTrain(String id) {
        ResponseTrainType responseTrainType = trainControllerPrx.delete(id);
        return new Response<>(responseTrainType.getStatus(),responseTrainType.getMsg(),responseTrainType.getData());

    }

    @Override
    public Response<TrainTypeTars> modifyTrain(TrainTypeTars t) {
        ResponseTrainType responseTrainType = trainControllerPrx.update(t);
        return new Response<>(responseTrainType.getStatus(),responseTrainType.getMsg(),responseTrainType.getData());

    }

    @Override
    public Response<List<ConfigTars>> getAllConfigs() {
        ResponseConfigs responseConfigs = configControllerPrx.queryAll();
        return new Response<>(responseConfigs.getStatus(),responseConfigs.getMsg(),responseConfigs.getData());

    }

    @Override
    public Response<ConfigTars> addConfig(ConfigTars c) {
        ResponseConfig responseConfig = configControllerPrx.createConfig(c);
        return new Response<>(responseConfig.getStatus(),responseConfig.getMsg(),responseConfig.getData());

    }

    @Override
    public Response<ConfigTars> deleteConfig(String name) {
        ResponseConfig responseConfig = configControllerPrx.deleteConfig(name);
        return new Response<>(responseConfig.getStatus(),responseConfig.getMsg(),responseConfig.getData());

    }

    @Override
    public Response<ConfigTars> modifyConfig(ConfigTars c) {
        ResponseConfig responseConfig = configControllerPrx.updateConfig(c);
        return new Response<>(responseConfig.getStatus(),responseConfig.getMsg(),responseConfig.getData());

    }

    @Override
    public Response<List<PriceConfigTars>> getAllPrices() {
        ResponsePriceConfigs responsePriceConfigs = priceControllerPrx.queryAll();
        return new Response<>(responsePriceConfigs.getStatus(),responsePriceConfigs.getMsg(),responsePriceConfigs.getData());

    }

    @Override
    public Response<PriceConfigTars> addPrice(PriceConfigTars pi) {
        ResponsePriceConfig responsePriceConfig = priceControllerPrx.create(pi);
        return new Response<>(responsePriceConfig.getStatus(),responsePriceConfig.getMsg(),responsePriceConfig.getData());

    }

    @Override
    public Response<PriceConfigTars> deletePrice(PriceConfigTars pi) {
        ResponsePriceConfig responsePriceConfig =priceControllerPrx.delete(pi);
        return new Response<>(responsePriceConfig.getStatus(),responsePriceConfig.getMsg(),responsePriceConfig.getData());

    }

    @Override
    public Response<PriceConfigTars> modifyPrice(PriceConfigTars pi) {
        ResponsePriceConfig responsePriceConfig = priceControllerPrx.update(pi);
        return new Response<>(responsePriceConfig.getStatus(),responsePriceConfig.getMsg(),responsePriceConfig.getData());

    }
}
