package com.vwmin.miraivwmin.bot;

import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Message;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/7 19:45
 */
public interface Reply<T> {
    /**
     *
     * @param command 包含一些字段，对应命令的参数
     * @param subject 联系对象
     * @return 命令执行完成的回复内容
     */
    Message reply(T command, Contact subject, User sender);
}
