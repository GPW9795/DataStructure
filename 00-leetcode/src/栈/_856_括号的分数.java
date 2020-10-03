package 栈;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/score-of-parentheses/
 */
public class _856_括号的分数 {
    public int scoreOfParentheses(String S) {
        Stack<Character> stack = new Stack<>();
        int score = 0;
        int len = S.length();
        for (int i = 0; i < len; i++) {
            char c = S.charAt(i);
            if (c == '(') {
                stack.push(c);
            }
            else {
                char left = stack.peek();
                if (left == '(') {
                    stack.pop();
                    score++;
                }
            }
        }
        return score;
    }
}
