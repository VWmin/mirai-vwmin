package com.vwmin.miraivwmin.chat;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * see https://platform.openai.com/docs/api-reference/chat/create
 *
 * @author vwmin
 * @date 2023/3/2 15:00
 */

@Data
class RequestBody {
    public String model = "gpt-3.5-turbo";
    public List<ChatMessage> messages = new ArrayList<>();
    // 流式传递，就是网页上显示的效果
//   public boolean stream = true;

    // 限制最大回复量
    public Integer max_tokens = 512;


    public static class RequestBuilder {
        private final RequestBody body = new RequestBody();

        public RequestBuilder user(String content) {
            body.messages.add(new ChatMessage("user", content));
            return this;
        }

        public RequestBody build() {
            return body;
        }

        public RequestBuilder conversation(List<ChatMessage> conversation) {
            body.messages.addAll(conversation);
            return this;
        }

        public RequestBuilder system(String content) {
            body.messages.add(new ChatMessage("system", content));
            return this;
        }

        public RequestBuilder chat(String role, String content) {
            body.messages.add(new ChatMessage(role, content));
            return this;
        }





    }
}
