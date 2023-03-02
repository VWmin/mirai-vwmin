package com.vwmin.miraivwmin.chat;

import lombok.Data;

/**
 * @author vwmin
 * @date 2023/3/2 16:38
 */

@Data
class ChatMessage {
      /**
       * system: 给暗示
       * user: 用户提问
       * assistant: 期望的回答案例，基于上一条用户提问; 如果在回复中，则是正常由模型回答的内容
       */
      public String role;
      public String content;

      public ChatMessage(String role, String content){
         this.role = role;
         this.content = content;
      }

      public ChatMessage() {

      }
}
