package project20280.stacksqueues;

import project20280.interfaces.Stack;

class BracketChecker {
    private final String input;

    public BracketChecker(String in) {
        input = in;
    }

    public void check() {
        Stack<Character> stack = new ArrayStack<>(input.length());

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    System.out.println("Not correct: Missing left delimiter");
                    return;
                }
                char open = stack.pop();
                boolean match = false;
                if (open == '(' && ch == ')') match = true;
                if (open == '[' && ch == ']') match = true;
                if (open == '{' && ch == '}') match = true;

                if (!match) {
                    System.out.println("Not correct: Mismatched delimiter");
                    return;
                }
            }
        }

        if (!stack.isEmpty()) {
            System.out.println("Not correct: Missing right delimiter");
        } else {
            System.out.println("Correct");
        }
    }

    public static void main(String[] args) {
        String[] inputs = {
                "[]]()()",
                "c[d]",
                "a{b[c]d}e",
                "a{b(c]d}e",
                "a[b{c}d]e}",
                "a{b(c) ",
        };

        for (String input : inputs) {
            BracketChecker checker = new BracketChecker(input);
            System.out.print("Checking: " + input + " -> ");
            checker.check();
        }
    }
}