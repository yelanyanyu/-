package com.yelanyanyu;

import org.junit.jupiter.api.Test;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
public class _1678GoalParserInterpretation {
    public String interpret(String command) {
        return command.replace("()","o").replace("(al)","al");
    }

    @Test
    public void Test01() {
        String str = "G()(al)";
        System.out.println(interpret(str));
    }
}
