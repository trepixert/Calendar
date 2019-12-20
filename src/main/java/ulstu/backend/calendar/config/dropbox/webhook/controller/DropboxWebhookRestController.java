package ulstu.backend.calendar.config.dropbox.webhook.controller;

import com.dropbox.core.v2.DbxClientV2;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import ulstu.backend.calendar.config.dropbox.webhook.service.DeltaUsersParserService;

import java.net.URI;

@RestController
@RequestMapping("/dropbox/webhook")
@Log4j2
public class DropboxWebhookRestController {
    private final DeltaUsersParserService deltaUsersParserService;

    private final DbxClientV2 clientV2;

    private final WebSocketStompClient stompClient;

    @Value("${websocket.url}")
    private String url;

    public DropboxWebhookRestController(DeltaUsersParserService deltaUsersParserService, DbxClientV2 clientV2, WebSocketStompClient stompClient) {
        this.deltaUsersParserService = deltaUsersParserService;
        this.clientV2 = clientV2;
        this.stompClient = stompClient;
    }

    /**
     * Once you enter your webhook URI, an initial "verification request" will be made to that URI. This verification is an HTTP GET request with a query
     * parameter called challenge.
     */
    @GetMapping
    public String getWebhookVerification(@RequestParam("challenge") final String challenge) {
        log.info("Respond to the webhook verification (GET request) by echoing back the challenge parameter.");
        return challenge;
    }

    /**
     * Once your webhook URI is added, your app will start receiving "notification requests" every time a user's files change. A notification request is an HTTP
     * POST request with a JSON body.
     */
    @PostMapping
    public void getFileData(@RequestBody final String notificationBody) throws Exception {
        log.info("Receive a list of changed user IDs from Dropbox and process each: '{}'", notificationBody);
        log.info("URL to WebSocket is: "+ this.url);
        StompSessionHandler sessionHandler = new CustmStompSessionHandler();
        StompSession stompSession = stompClient.connect(String.valueOf(URI.create(url)),
                sessionHandler).get();
        stompSession.send("/app/file-changed",  notificationBody);
    }
}
