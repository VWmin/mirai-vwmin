package com.vwmin.miraivwmin.chat;

import picocli.CommandLine;

import java.util.List;
import java.util.Optional;

/**
 * @author vwmin
 * @date 2023/3/2 15:23
 */
@CommandLine.Command
public class ChatCommand {

    @CommandLine.Parameters(description = "内容")
    private List<String> word;

    @CommandLine.Option(names = {"-s", "--system"})
    private boolean system;

    @CommandLine.Option(names = {"-r"})
    private boolean r;

    public String call(GPTApi api) {
        StringBuilder sb = new StringBuilder();
        Optional.of(word).ifPresent(e -> e.forEach(w->sb.append(w).append(" ")));
        String content = sb.toString();

        Conversation conversation;
        if (r) {
            conversation = Conversation.instance("r");
        } else {
            conversation = Conversation.instance("nya");
        }

        ResponseBody chat = api.chat(
                new RequestBody.RequestBuilder()
                        .conversation(conversation.conversation())
                        .user(content)
                        .build()
        );

        String resp = "error TODO";
        if (chat != null) {
            resp = chat.getChoices().get(0).message.content;
            conversation.add("user", content);
            conversation.add("assistant", resp);
        }


        return resp;
    }
}
