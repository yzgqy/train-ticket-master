package config.tars.config.impl;

import com.qq.tars.spring.annotation.TarsServant;
import config.tars.config.ConfigControllerServant;
import config.tars.config.ConfigTars;
import config.tars.config.ResponseConfig;
import config.tars.config.ResponseConfigs;
import config.tars.config.service.ConfigTarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @Auther: yaya
 * @Date: 2019/7/11 10:20
 * @Description:
 */
@TarsServant("ConfigObj")
public class ConfigControllerServantImpl implements ConfigControllerServant {
    @Autowired
    private ConfigTarsService configTarsService;

    @Override
    public String home() {
        return "Welcome to [ Config Service ] !";
    }

    @Override
    public ResponseConfigs queryAll() {

        return configTarsService.queryAll();
    }

    @Override
    public ResponseConfig createConfig(ConfigTars info) {

        return configTarsService.create(info);
    }

    @Override
    public ResponseConfig updateConfig(ConfigTars info) {

        return configTarsService.update(info);
    }

    @Override
    public ResponseConfig deleteConfig(String configName) {

        return configTarsService.delete(configName);
    }

    @Override
    public ResponseConfig retrieve(String configName) {

        return configTarsService.query(configName);
    }
}
