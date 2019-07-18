package adminbasic.tars.adminbasicinfo.impl;

import adminbasic.tars.adminbasicinfo.AdminBasicInfoControllerServant;
import adminbasic.tars.adminbasicinfo.Response;
import adminbasic.tars.adminbasicinfo.service.AdminBasicInfoTarsService;
import adminbasic.tars.rpc.config.ConfigTars;
import adminbasic.tars.rpc.contacts.ContactsTars;
import adminbasic.tars.rpc.priceconfig.PriceConfigTars;
import adminbasic.tars.rpc.station.StationTars;
import adminbasic.tars.rpc.train.TrainTypeTars;
import com.qq.tars.common.support.Holder;
import com.qq.tars.spring.annotation.TarsServant;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @Auther: yaya
 * @Date: 2019/7/18 15:24
 * @Description:
 */
@TarsServant("AdminBasicInfoObj")
public class AdminBasicInfoControllerServantImpl implements AdminBasicInfoControllerServant {

    @Autowired
    private AdminBasicInfoTarsService adminBasicInfoTarsService;

    @Override
    public String home() {
        return "Welcome to [ AdminBasicInfo Service ] !";
    }

    @Override
    public Response getAllContacts(Holder<List<ContactsTars>> outData) {
        System.out.println("[Admin Basic Info Service][Find All Contacts by admin ");
        edu.fudan.common.util.Response<List<ContactsTars>> rp =  adminBasicInfoTarsService.getAllContacts();
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response deleteContacts(String contactsId, Holder<String> outData) {
        System.out.println("[Admin Basic Info Service][Delete Contacts by admin ");
        edu.fudan.common.util.Response<String> rp = adminBasicInfoTarsService.deleteContact(contactsId);
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response modifyContacts(ContactsTars mci, Holder<ContactsTars> outData) {
        System.out.println("[Admin Basic Info Service][Modify Contacts by admin: ");
        edu.fudan.common.util.Response<ContactsTars> rp = adminBasicInfoTarsService.modifyContact(mci);
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response addContacts(ContactsTars c, Holder<ContactsTars> outData) {
        System.out.println("[Admin Basic Info Service][Modify Contacts by admin  ");
        edu.fudan.common.util.Response<ContactsTars> rp = adminBasicInfoTarsService.addContact(c);
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response getAllStations(Holder<List<StationTars>> outData) {
        System.out.println("[Admin Basic Info Service][Find All Station by admin  ");
        edu.fudan.common.util.Response<List<StationTars>> rp = adminBasicInfoTarsService.getAllStations();
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response deleteStation(StationTars s, Holder<StationTars> outData) {
        System.out.println("[Admin Basic Info Service][Delete Station by admin ");
        edu.fudan.common.util.Response<StationTars> rp = adminBasicInfoTarsService.deleteStation(s);
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response modifyStation(StationTars s, Holder<StationTars> outData) {
        System.out.println("[Admin Basic Info Service][Modify Station by admin ");
        edu.fudan.common.util.Response<StationTars> rp = adminBasicInfoTarsService.modifyStation(s);
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response addStation(StationTars s, Holder<StationTars> outData) {
        System.out.println("[Admin Basic Info Service][Modify Station by admin");
        edu.fudan.common.util.Response<StationTars> rp = adminBasicInfoTarsService.addStation(s);
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response getAllTrains(Holder<List<TrainTypeTars>> outData) {
        System.out.println("[Admin Basic Info Service][Find All Train by admin: ");
        edu.fudan.common.util.Response<List<TrainTypeTars>> rp = adminBasicInfoTarsService.getAllTrains();
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response deleteTrain(String id, Holder<TrainTypeTars> outData) {
        System.out.println("[Admin Basic Info Service][Delete Train by admin");
        edu.fudan.common.util.Response<TrainTypeTars> rp = adminBasicInfoTarsService.deleteTrain(id);
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response modifyTrain(TrainTypeTars t, Holder<TrainTypeTars> outData) {
        System.out.println("[Admin Basic Info Service][Modify Train by admin  ");
        edu.fudan.common.util.Response<TrainTypeTars> rp  = adminBasicInfoTarsService.modifyTrain(t);
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response addTrain(TrainTypeTars t, Holder<TrainTypeTars> outData) {
        System.out.println("[Admin Basic Info Service][Modify Train by admin ");
        edu.fudan.common.util.Response<TrainTypeTars> rp = adminBasicInfoTarsService.addTrain(t);
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response getAllConfigs(Holder<List<ConfigTars>> outData) {
        System.out.println("[Admin Basic Info Service][Find All Config by admin  ");
        edu.fudan.common.util.Response<List<ConfigTars>> rp = adminBasicInfoTarsService.getAllConfigs();
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response deleteConfig(String name, Holder<ConfigTars> outData) {
        System.out.println("[Admin Basic Info Service][Delete Config by admin ");
        edu.fudan.common.util.Response<ConfigTars> rp = adminBasicInfoTarsService.deleteConfig(name);
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response modifyConfig(ConfigTars c, Holder<ConfigTars> outData) {
        System.out.println("[Admin Basic Info Service][Modify Config by admin ");
        edu.fudan.common.util.Response<ConfigTars> rp = adminBasicInfoTarsService.modifyConfig(c);
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response addConfig(ConfigTars c, Holder<ConfigTars> outData) {
        System.out.println("[Admin Basic Info Service][Modify Config by admin  ");
        edu.fudan.common.util.Response<ConfigTars> rp = adminBasicInfoTarsService.addConfig(c);
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response getAllPrices(Holder<List<PriceConfigTars>> outData) {
        System.out.println("[Admin Basic Info Service][Find All Price by admin: ");
        edu.fudan.common.util.Response<List<PriceConfigTars>> rp = adminBasicInfoTarsService.getAllPrices();
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response deletePrice(PriceConfigTars pi, Holder<PriceConfigTars> outData) {
        System.out.println("[Admin Basic Info Service][Delete Price by admin  ");
        edu.fudan.common.util.Response<PriceConfigTars> rp =  adminBasicInfoTarsService.deletePrice(pi);
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response modifyPrice(PriceConfigTars pi, Holder<PriceConfigTars> outData) {
        System.out.println("[Admin Basic Info Service][Modify Price by admin  ");
        edu.fudan.common.util.Response<PriceConfigTars> rp = adminBasicInfoTarsService.modifyPrice(pi);
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response addPrice(PriceConfigTars pi, Holder<PriceConfigTars> outData) {
        System.out.println("[Admin Basic Info Service][Add Price by admin");
        edu.fudan.common.util.Response<PriceConfigTars> rp = adminBasicInfoTarsService.addPrice(pi);
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }
}
