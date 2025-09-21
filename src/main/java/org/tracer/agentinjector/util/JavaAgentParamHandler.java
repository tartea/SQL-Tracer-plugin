package org.tracer.agentinjector.util;

/**
 * 参数构建
 */
public class JavaAgentParamHandler {


    public static int buildParam(boolean outputToConsole, boolean fileOverlay) {
        String binaryString = "000000" + convert(fileOverlay) + convert(outputToConsole) ;
        return Integer.parseInt(binaryString, 2);
    }

    private static String convert(boolean var) {
        return var ? "1" : "0";
    }

}
