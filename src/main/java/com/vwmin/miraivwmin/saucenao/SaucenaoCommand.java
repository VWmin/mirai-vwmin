package com.vwmin.miraivwmin.saucenao;


import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/11 16:18
 */
@Slf4j
@CommandLine.Command(name = "search", description = "搜图")
public class SaucenaoCommand {

    @CommandLine.Parameters(index = "0")
    private String url;

    public SaucenaoEntity call(SaucenaoApi api) {

        return api.search(url, 999, 2, 3);
    }
}
