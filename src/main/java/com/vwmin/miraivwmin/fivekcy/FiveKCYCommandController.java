package com.vwmin.miraivwmin.fivekcy;

import com.vwmin.miraivwmin.core.BotHandler;
import com.vwmin.miraivwmin.core.CommandController;
import com.vwmin.miraivwmin.core.Reply;
import com.vwmin.miraivwmin.core.message.MessageBuilder;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

@Slf4j
@CommandController(bind = "5千日元", alias = {"5kcy", "五千日元"})
public class FiveKCYCommandController implements Reply<FiveKCYCommand> {

    @Override
    public Message reply(FiveKCYCommand command, Contact subject, User sender) {
        MessageBuilder builder = new MessageBuilder();
        if (command.word.size() != 2) {
            builder.plaintext("请使用命令例如：5千日元 我他妈好想跟 大和赤骥做爱");
        } else {
            String filename = genImage(command.word.get(0), command.word.get(1));
            builder.image(subject, "./5kcy/out/" + filename);
        }
        return builder.build();
    }

    String genImage(String wordA, String wordB) {
        String filename = UUID.randomUUID() + ".png";
        Process proc;
        try {
            proc = Runtime.getRuntime().exec(String.format("python3 ./5kcy/generator.py %s %s %s", wordA, wordB, filename));
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                log.info("while 5kcy >>> {}", line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return filename;
    }
}
