package com.vwmin.miraivwmin.chat;

import picocli.CommandLine;

import java.util.List;

/**
 * @author vwmin
 * @date 2023/3/2 15:23
 */
@CommandLine.Command
public class ChatCommand {

    @CommandLine.Parameters(description = "内容")
    private List<String> word;

    @CommandLine.Option(names = {"-s", "--system"})
    private boolean system = false;

    public ResponseBody call(GPTApi api, List<ChatMessage> conversation) {
        StringBuilder sb = new StringBuilder();
        word.forEach(w -> sb.append(w).append(" "));

        return api.chat(
                new RequestBody.RequestBuilder()
                        .conversation(conversation)
                        .chat(system?"system":"user", sb.toString())
                        .build()
        );
    }
}
