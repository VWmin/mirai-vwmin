package com.vwmin.miraivwmin.pixiv.subscribe;

import com.vwmin.miraivwmin.pixiv.PixivApi;
import picocli.CommandLine;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/31 16:47
 */
@CommandLine.Command
public class SubscribeCommand {
    @CommandLine.Parameters(index = "0")
    public String username;

    @CommandLine.Parameters(index = "1")
    public String password;

    public String call(PixivApi api){
        return api.login(username, password);
    }

}
