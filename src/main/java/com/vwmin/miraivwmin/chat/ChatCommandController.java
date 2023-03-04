package com.vwmin.miraivwmin.chat;

import com.vwmin.miraivwmin.core.CommandController;
import com.vwmin.miraivwmin.core.Reply;
import com.vwmin.miraivwmin.core.message.MessageBuilder;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Message;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vwmin
 * @date 2023/3/2 15:10
 */

@Slf4j
@CommandController(bind = "chat")
public class ChatCommandController implements Reply<ChatCommand> {
   private final GPTApi gptApi;

   public ChatCommandController(GPTApi gptApi) {
      this.gptApi = gptApi;
   }


   @Override
   public Message reply(ChatCommand command, Contact subject, User sender) {
      try{
         String resp = command.call(gptApi);
         return new MessageBuilder().at(sender.getId()).plaintext(resp).build();
      } catch (HttpClientErrorException.TooManyRequests  e) {
         return new MessageBuilder().plaintext("少女祈祷中...").build();
      }
   }
}
