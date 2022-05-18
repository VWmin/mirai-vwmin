package com.vwmin.miraivwmin.fivekcy;

import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;

@CommandLine.Command
public class FiveKCYCommand {
    @CommandLine.Parameters(description = "指定文字")
    List<String> word = new ArrayList<>();
}
