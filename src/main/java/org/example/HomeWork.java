package org.example;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.BinaryOperator;

public class HomeWork {

    private static final Map<String, BinaryOperator<Double>> op = Map.of(
            "+", Double::sum,
            "-", (a, b) -> a - b,
            "*", (a, b) -> a * b,
            "/", (a, b) -> a / b
    );
    final Map<String, BinaryOperator<Double>> func = Map.of(
            "sin", (empty, a) -> Math.sin(a),
            "cos", (empty, a) -> Math.cos(a),
            "sqr", (empty, a) -> a * a,
            "pow", Math::pow
    );


    /**
     * <h1>Задание 1.</h1>
     * Требуется реализовать метод, который по входной строке будет вычислять математические выражения.
     * <br/>
     * Операции: +, -, *, / <br/>
     * Функции: sin, cos, sqr, pow <br/>
     * Разделители аргументов в функции: , <br/>
     * Поддержка скобок () для описания аргументов и для группировки операций <br/>
     * Пробел - разделитель токенов, пример валидной строки: "1 + 2 * ( 3 - 4 )" с результатом -1.0 <br/>
     * <br/>
     * sqr(x) = x^2 <br/>
     * pow(x,y) = x^y
     */
    double calculate(String expr) {
        var rpn = toRpn(expr);
        var result = new ArrayDeque<Double>();
        for (String token : rpn) {
            if (token.matches("\\d+(\\.\\d+)?")) {
                result.push(Double.parseDouble(token));
            } else {
                if (op.containsKey(token)) {
                    var b = result.pop();
                    var a = result.pop();
                    result.push(op.get(token).apply(a, b));
                }
                if (func.containsKey(token)) {
                    var b = result.pop();
                    var a = "pow".equals(token) ? result.pop() : null;
                    result.push(func.get(token).apply(a, b));
                }
            }
        }
        return result.pop();
    }

    private ArrayList<String> toRpn(String expr) {
        var tokens = expr.split(" ");
        var stack = new ArrayDeque<String>();
        var rpn = new ArrayList<String>();

        for (String token : tokens) {
            if (token.matches("\\d+(\\.\\d+)?")) {
                rpn.add(token);
            }
            if (func.containsKey(token) || "(".equals(token)) {
                stack.push(token);
            }
            if (")".equals(token)) {
                while (!"(".equals(stack.peek())) {
                    rpn.add(stack.pop());
                }
                stack.pop();
            }
            if (op.containsKey(token)) {
                var el = stack.peek();
                while (null != el && (func.containsKey(el) || (op.containsKey(el) && priority(el) >= priority(token)))) {
                    rpn.add(stack.pop());
                    el = stack.peek();
                }
                stack.push(token);
            }
            if (",".equals(token)) {
                while (!stack.isEmpty() && !"(".equals(stack.peek())) {
                    rpn.add(stack.pop());
                }
            }
        }

        while (!stack.isEmpty()) {
            rpn.add(stack.pop());
        }
        return rpn;
    }

    private int priority(String token) {
        if (func.containsKey(token)) {
            return 3;
        }
        if ("*".equals(token) || "/".equals(token)) {
            return 2;
        }
        return 1;
    }

}
