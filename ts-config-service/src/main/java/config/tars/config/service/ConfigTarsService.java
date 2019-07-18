package config.tars.config.service;

import config.entity.Config;
import config.tars.config.ConfigTars;
import config.tars.config.ResponseConfig;
import config.tars.config.ResponseConfigs;

/**
 * @Auther: yaya
 * @Date: 2019/7/11 10:31
 * @Description:
 */
public interface ConfigTarsService {
    ResponseConfig create(ConfigTars info);

    ResponseConfig update(ConfigTars info);
    //    Config retrieve(String name, HttpHeaders headers);
    ResponseConfig query(String name);

    ResponseConfig delete(String name);

    ResponseConfigs queryAll();
}
