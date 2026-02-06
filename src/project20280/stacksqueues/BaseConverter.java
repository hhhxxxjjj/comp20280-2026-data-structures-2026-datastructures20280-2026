package project20280.stacksqueues;

import project20280.interfaces.Stack;

public class BaseConverter {

    public static String convertToBinary(long dec_num) {
        Stack<Integer> stack = new ArrayStack<>();

        if (dec_num == 0) {
            return "0";
        }

        while (dec_num > 0) {
            long remainder = dec_num % 2;
            stack.push((int) remainder);
            dec_num = dec_num / 2;
        }

        String result = "";
        while (!stack.isEmpty()) {
            result = result + stack.pop();
        }

        return result;
    }

    public static String convertToBase(long dec_num, int base) {
        Stack<Character> stack = new ArrayStack<>();
        String digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        if (dec_num == 0) {
            return "0";
        }

        while (dec_num > 0) {
            int remainder = (int) (dec_num % base);
            char digit = digits.charAt(remainder);
            stack.push(digit);
            dec_num = dec_num / base;
        }

        String result = "";
        while (!stack.isEmpty()) {
            result = result + stack.pop();
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("Binary of 23 is: " + convertToBinary(23));
        System.out.println("Binary of 1027 is: " + convertToBinary(1027));
        System.out.println("Hexadecimal of 255 is: " + convertToBase(255, 16));
    }
}