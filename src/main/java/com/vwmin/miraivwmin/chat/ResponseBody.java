package com.vwmin.miraivwmin.chat;

import lombok.Data;

import java.util.List;

/**
 * @author vwmin
 * @date 2023/3/2 15:15
 */

@Data
public class ResponseBody {

    public String id;
    public String object;
    public int created;
    public String model;
    public Usage usage;
    public List<Choice> choices;

    public static class Choice{
        public ChatMessage message;
        public String finishReason;
        public int index;
    }


    public static class Usage{
        public int promptTokens;
        public int completionTokens;
        public int totalTokens;
    }


}
