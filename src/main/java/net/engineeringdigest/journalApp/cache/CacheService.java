package net.engineeringdigest.journalApp.cache;

import net.engineeringdigest.journalApp.entity.ConfigEntity;
import net.engineeringdigest.journalApp.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CacheService {

    public Map<String, String> appCache;
    @Autowired
    private ConfigRepository configRepository;

    @PostConstruct
    public void init() {
        appCache = new HashMap<>();
        final List<ConfigEntity> all = configRepository.findAll();
        for(ConfigEntity configEntity : all) {
            appCache.put(configEntity.getKey(), configEntity.getValue());
        }
    }
}
