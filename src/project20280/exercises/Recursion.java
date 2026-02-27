package project20280.exercises;

import java.util.Arrays;
import java.util.HashMap;

public class Recursion {

    // Q1: ReverseArray
    public static void reverseArray(int[] A, int i, int j) {
        if (i < j) {
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
            reverseArray(A, i + 1, j - 1);
        }
    }

    // Q2: Fibonacci (binary recursive)
    static long fibCallCount = 0;

    public static long fibonacci(int n) {
        fibCallCount++;
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // Q2: Fibonacci with memoisation
    public static long fibonacciMemo(int n, HashMap<Integer, Long> memo) {
        if (n <= 1) return n;
        if (memo.containsKey(n)) return memo.get(n);
        long result = fibonacciMemo(n - 1, memo) + fibonacciMemo(n - 2, memo);
        memo.put(n, result);
        return result;
    }

    // Q3: Tribonacci
    public static long tribonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 0;
        if (n == 2) return 1;
        return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
    }

    // Q4: McCarthy-91
    public static int mcCarthy91(int n) {
        if (n > 100) {
            return n - 10;
        } else {
            return mcCarthy91(mcCarthy91(n + 11));
        }
    }

    // Q5: Foo
    public static int foo(int x) {
        if (x / 2 == 0) {
            System.out.print(x);
            return 0;
        }
        foo(x / 2);
        System.out.print(x % 2);
        return 0;
    }

    // Q8: Mystery
    public static int mystery(int n, int a, int d) {
        if (n == 1)
            return a;
        else
            return d + mystery(n - 1, a, d);
    }

    public static void main(String[] args) {

        // === Q1 ===
        System.out.println("=== Q1: ReverseArray ===");
        int[] A = {12, 5, 19, 6, 11, 3, 9, 34, 2, 1, 15};
        System.out.println("Original: " + Arrays.toString(A));
        reverseArray(A, 0, A.length - 1);
        System.out.println("Reversed: " + Arrays.toString(A));

        // === Q2 ===
        System.out.println("\n=== Q2: Fibonacci ===");
        System.out.println("Fibonacci(5) = " + fibonacci(5));

        System.out.println("\nFinding largest Fibonacci in under 1 minute (no memo)...");
        for (int n = 10; n <= 50; n += 5) {
            fibCallCount = 0;
            long start = System.currentTimeMillis();
            long result = fibonacci(n);
            long elapsed = System.currentTimeMillis() - start;
            System.out.println("Fibonacci(" + n + ") = " + result + ", calls = " + fibCallCount + ", time = " + elapsed + "ms");
            if (elapsed > 60000) break;
        }

        System.out.println("\nFinding largest Fibonacci in under 1 minute (with memo)...");
        HashMap<Integer, Long> memo = new HashMap<>();
        int lastSuccess = 0;
        try {
            for (int n = 10; n <= 100000; n += 500) {
                long start = System.currentTimeMillis();
                long result = fibonacciMemo(n, memo);
                long elapsed = System.currentTimeMillis() - start;
                System.out.println("FibonacciMemo(" + n + ") = " + result + ", time = " + elapsed + "ms");
                lastSuccess = n;
                if (elapsed > 60000) break;
            }
        } catch (StackOverflowError e) {
            System.out.println("StackOverflow at some point after n=" + lastSuccess + " (Java stack limit reached)");
        }

        // === Q3 ===
        System.out.println("\n=== Q3: Tribonacci ===");
        System.out.println("First 10 Tribonacci numbers:");
        for (int i = 0; i < 10; i++) {
            System.out.println("Tribonacci(" + i + ") = " + tribonacci(i));
        }
        System.out.println("The 9th term (index 8) = " + tribonacci(8));

        // === Q4 ===
        System.out.println("\n=== Q4: McCarthy-91 ===");
        System.out.println("M(87) = " + mcCarthy91(87));
        System.out.println("M(100) = " + mcCarthy91(100));
        System.out.println("M(101) = " + mcCarthy91(101));
        System.out.println("M(111) = " + mcCarthy91(111));

        // === Q5 ===
        System.out.println("\n=== Q5: Foo ===");
        System.out.print("Foo(2468) = ");
        foo(2468);
        System.out.println();

        // === Q8 ===
        System.out.println("\n=== Q8: Mystery ===");
        System.out.println("mystery(2, 4, 4) = " + mystery(2, 4, 4));
    }
}