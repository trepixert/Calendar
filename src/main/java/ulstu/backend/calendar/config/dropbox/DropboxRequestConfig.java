package ulstu.backend.calendar.config.dropbox;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.concurrent.Executor;

@Configuration
@EnableEncryptableProperties
public class DropboxRequestConfig {
    @Value("${dropbox.access.token}")
    private String ACCESS_TOKEN;

    private DbxRequestConfig config;

    private DbxClientV2 client;

    @Value("${dropbox.client.identifier}")
    private String clientIdentifier;

    @PostConstruct
    public void init() {
        config = DbxRequestConfig.newBuilder(clientIdentifier).withUserLocale(null).build();
        client = new DbxClientV2(config, ACCESS_TOKEN);
    }

    @Bean
    public DbxClientV2 getClient() {
        return client;
    }

    @Bean
    public WebSocketClient webSocketClient() {
        return new SockJsClient(Collections.singletonList(new WebSocketTransport(new StandardWebSocketClient())));
    }

    @Bean
    public WebSocketStompClient stompClient(TaskScheduler taskScheduler) {
        WebSocketStompClient webSocketStompClient = new WebSocketStompClient(webSocketClient());
        webSocketStompClient.setTaskScheduler(taskScheduler);
        webSocketStompClient.setReceiptTimeLimit(5000);
        webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());
        return webSocketStompClient;
    }

    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }

    @Bean
    public Executor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }
}
