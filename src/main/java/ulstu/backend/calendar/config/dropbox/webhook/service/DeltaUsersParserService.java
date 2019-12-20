package ulstu.backend.calendar.config.dropbox.webhook.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;

@Service
@Log4j2
public class DeltaUsersParserService {
    private static final Gson GSON = new Gson();

    public List<String> getUsers(final String notification) {
        List<String> users = null;
        try {
            final JsonArray jsonUsers = getJsonUsersFromNotificationBody(notification);
            users = GSON.fromJson(jsonUsers, new TypeToken<List<String>>() {
            }.getType());
            log.debug("Parse users successfully from notification: '{}'", notification);
        } catch (final Exception e) {
            log.error("An error occured while try to parse users: ", e);
            users = emptyList();
        }
        return users;
    }

    private JsonArray getJsonUsersFromNotificationBody(final String notification) {
        final JsonObject rootObject = new JsonParser().parse(notification).getAsJsonObject();
        return rootObject.getAsJsonObject("delta").getAsJsonArray("users");
    }
}
