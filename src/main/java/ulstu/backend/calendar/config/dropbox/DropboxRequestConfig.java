package ulstu.backend.calendar.config.dropbox;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DropboxRequestConfig {
    @Value("${dropbox.access.token}")
    private String ACCESS_TOKEN;

    private DbxRequestConfig config;

    private DbxClientV2 client;

    @Value("${dropbox.client.identifier}")
    private String clientIdentifier;

    @PostConstruct
    public void init(){
        config = DbxRequestConfig.newBuilder(clientIdentifier).withUserLocale(null).build();
        client = new DbxClientV2(config, ACCESS_TOKEN);
    }

    @Bean
    public DbxClientV2 getClient(){
        return client;
    }

}
