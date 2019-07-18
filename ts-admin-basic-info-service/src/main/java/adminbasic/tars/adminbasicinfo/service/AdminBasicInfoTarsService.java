package adminbasic.tars.adminbasicinfo.service;

import adminbasic.entity.*;
import adminbasic.tars.rpc.config.ConfigTars;
import adminbasic.tars.rpc.contacts.ContactsTars;
import adminbasic.tars.rpc.priceconfig.PriceConfigTars;
import adminbasic.tars.rpc.station.StationTars;
import adminbasic.tars.rpc.train.TrainTypeTars;
import edu.fudan.common.util.Response;

import java.util.List;

/**
 * @Auther: yaya
 * @Date: 2019/7/18 15:24
 * @Description:
 */

public interface AdminBasicInfoTarsService {

    ////////////contact/////////////////////////////////////////
    Response<List<ContactsTars>> getAllContacts();

    Response<ContactsTars> addContact(ContactsTars c);

    Response<String> deleteContact( String contactsId);

    Response<ContactsTars> modifyContact(ContactsTars mci);

    ////////////////////////////station///////////////////////////////
    Response<List<StationTars>> getAllStations();

    Response<StationTars> addStation(StationTars s);

    Response<StationTars> deleteStation(StationTars s);

    Response<StationTars> modifyStation(StationTars s);

    ////////////////////////////train///////////////////////////////
    Response<List<TrainTypeTars>> getAllTrains();

    Response<TrainTypeTars> addTrain(TrainTypeTars t);

    Response<TrainTypeTars> deleteTrain(String id);

    Response<TrainTypeTars> modifyTrain(TrainTypeTars t);

    ////////////////////////////config///////////////////////////////
    Response<List<ConfigTars>> getAllConfigs();

    Response<ConfigTars> addConfig(ConfigTars c);


    Response<ConfigTars> deleteConfig(String name);

    Response<ConfigTars> modifyConfig(ConfigTars c);

    ////////////////////////////price///////////////////////////////
    Response<List<PriceConfigTars>> getAllPrices();

//    PriceInfo->PriceConfigTars
    Response<PriceConfigTars> addPrice(PriceConfigTars pi);

    Response<PriceConfigTars> deletePrice(PriceConfigTars pi);

    Response<PriceConfigTars> modifyPrice(PriceConfigTars pi);

//    Contacts login(String name, String password);

}
