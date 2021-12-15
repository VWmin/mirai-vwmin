package com.vwmin.miraivwmin.setu;

import picocli.CommandLine;


/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/7 15:30
 */
@CommandLine.Command
public class SetuCommand {

    @CommandLine.Option(names = {"-r", "--r18"}, description = "指定r18等级；0: 无, 1: 有, 2：混合")
    private int r18;

    @CommandLine.Option(names = {"-w", "--word"}, description = "指定关键词")
    private String word;

    @CommandLine.Option(names = {"-n", "--num"}, description = "一次请求的数量")
    private int num = 1;

    public SetuEntity call(SetuApiV2 api) {
        return api.setu(
                new SetuRequestEntity.Builder()
                        .tag(word).num(num).r18(r18)
                        .build()
        );
    }
}
