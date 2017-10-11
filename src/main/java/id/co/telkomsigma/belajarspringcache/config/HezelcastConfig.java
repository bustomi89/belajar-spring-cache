package id.co.telkomsigma.belajarspringcache.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;


@Configuration
public class HezelcastConfig {
	
    @Bean
    public Config hezelCastConf(){
        return new Config()
                .setInstanceName("hezelcast-instance")
                .addMapConfig(
                        new MapConfig()
                            .setName("products")
                            .setMaxSizeConfig(
                                    new MaxSizeConfig(
                                            200,
                                            MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE
                                    )                            
                            )
                            .setEvictionPolicy(EvictionPolicy.LRU)
                            .setTimeToLiveSeconds(20)
                );  
    }
}
