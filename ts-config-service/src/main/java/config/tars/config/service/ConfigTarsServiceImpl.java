package config.tars.config.service;

import config.entity.Config;
import config.repository.ConfigRepository;
import config.tars.config.ConfigTars;
import config.tars.config.ResponseConfig;
import config.tars.config.ResponseConfigs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: yaya
 * @Date: 2019/7/11 10:31
 * @Description:
 */
@Service
public class ConfigTarsServiceImpl implements ConfigTarsService{
    @Autowired
    ConfigRepository repository;

    @Override
    public ResponseConfig create(ConfigTars info) {
        if (repository.findByName(info.getName()) != null) {
            String result = "Config " + info.getName() + " already exists.";
            return new ResponseConfig(0, "Already exists.", null);
        } else {
            Config config = new Config(info.getName(), info.getValue(), info.getDescription());
            repository.save(config);
            return new ResponseConfig(1, "Create success", config.toTars());
        }
    }

    @Override
    public ResponseConfig update(ConfigTars info) {
        if (repository.findByName(info.getName()) == null) {
            String result = "Config " + info.getName() + " doesn't exist.";
            return new ResponseConfig(0, "Doesn't exist.", null);
        } else {
            Config config = new Config(info.getName(), info.getValue(), info.getDescription());
            repository.save(config);
            return new ResponseConfig(1, "Update success", config.toTars());
        }
    }

    @Override
    public ResponseConfig query(String name) {
        Config config = repository.findByName(name);
        if (config == null) {
            return new ResponseConfig(0, "No content", null);
        } else {
            return new ResponseConfig(1, "Success", config.toTars());
        }
    }

    @Override
    public ResponseConfig delete(String name) {
        Config config = repository.findByName(name);
        if (config == null) {
            String result = "Config " + name + " doesn't exist.";
            return new ResponseConfig(0, "Doesn't exist.", null);
        } else {
            repository.deleteByName(name);
            return new ResponseConfig(1, "Delete success", config.toTars());
        }
    }

    @Override
    public ResponseConfigs queryAll() {
        List<Config> configList = repository.findAll();
        List<ConfigTars> configTarsList = new ArrayList<>();
        if (configList != null && configList.size() > 0) {
            for(Config config:configList){
                configTarsList.add(config.toTars());
            }
            return new ResponseConfigs(1, "Find all  config success", configTarsList);
        } else {
            return new ResponseConfigs(0, "No content", null);
        }
    }
}
