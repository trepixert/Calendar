package ulstu.backend.calendar.config.handler;

import com.vk.api.sdk.callback.CallbackApi;
import com.vk.api.sdk.objects.messages.Message;
import org.springframework.stereotype.Service;

@Service
public class VkCallbackApiHandler extends CallbackApi {
    @Override
    public void messageNew(Integer groupId, Message message) {
        System.out.println(message.getBody());
    }
}
