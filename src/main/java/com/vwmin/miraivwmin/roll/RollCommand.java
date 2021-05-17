package com.vwmin.miraivwmin.roll;

import picocli.CommandLine;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/5/17 21:28
 */
@CommandLine.Command
public class RollCommand {
    @CommandLine.Option(names = {"-d"}, description = "多少面？")
    int d = 6;

}
