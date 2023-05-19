package middletest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class middletest {
    public static void main(String[] args) throws IOException {
        
        System.out.println("数式を記入してください:");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String form = reader.readLine();

        System.out.println("=" + rp(form));
        System.out.println("=" + String.valueOf(rpcal(rp(form))));
    }

    static String rp(String form) {
        form = "(" + form + ")";
        Pattern minusPattern = Pattern.compile("\\((-\\d+\\.?\\d*)\\)");
        Matcher matcher = minusPattern.matcher(form);
        while (matcher.find()) {
            form = form.substring(0, matcher.start()) + "[" + matcher.group(1) + "]" + form.substring(matcher.end());
            matcher = minusPattern.matcher(form);
        }

        Pattern tokenPattern = Pattern.compile("[^\\[0-9](\\d+\\.?\\d*)[^\\]0-9]");
        matcher = tokenPattern.matcher(form);
        while (matcher.find()) {
            form = form.substring(0, matcher.start(1)) + "[" + matcher.group(1) + "]" + form.substring(matcher.end(1));
            matcher = tokenPattern.matcher(form);
        }

        form = form.substring(1, form.length() - 1);
        boolean stop = false;
        int bracketCount = 0;
        char[] formc = form.toCharArray();
        StringBuilder ret = new StringBuilder(formc.length);
        StringBuilder kakst = new StringBuilder(formc.length);
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : formc) {
            if (!stop) {
                switch (c) {
                    case '+':
                    case '-':
                        if (ret.toString().charAt(ret.length() - 1) == '[') {
                            ret.append(c);
                            break;
                        }

                        if (!stack.isEmpty()) {
                            if (stack.getFirst() == '+' || stack.getFirst() == '-') {
                                ret.append(stack.removeFirst());
                            }
                        }

                        while (!stack.isEmpty()) {
                            if (stack.getFirst() == '*' || stack.getFirst() == '/') {
                                ret.append(stack.removeFirst());
                            } else {
                                break;
                            }
                        }

                        stack.addFirst(c);
                        break;

                    case '*':
                    case '/':
                        if (!stack.isEmpty()) {
                            if (stack.getFirst() == '*' || stack.getFirst() == '/') {
                                ret.append(stack.removeFirst());
                            }
                        }

                        stack.addFirst(c);
                        break;

                    case '(':
                        stop = true;
                        bracketCount = 1;
                        break;

                    default:
                        ret.append(c);
                        break;
                }
            } else {
                switch (c) {
                    case '(':
                        bracketCount++;
                        kakst.append(c);
                        break;

                    case ')':
                        bracketCount--;

                        if (bracketCount == 0) {
                            String kakret = rp(kakst.toString());
                            ret.append(kakret);
                            stop = false;
                        } else {
                            kakst.append(c);
                        }
                        break;

                    default:
                        kakst.append(c);
                        break;
                }
            }
        }

        while (!stack.isEmpty()) {
            ret.append(stack.removeFirst());
        }

        form = ret.toString();
        return form;
    }

    static Double rpcal(String form) {
        char[] formc = form.toCharArray();
        double ret, val, valf, vall;
        String keep = "";
        Deque<String> stack = new ArrayDeque<>();

        for (char c : formc) {
            switch (c) {
                case '[':
                    keep = "[";
                    break;

                case ']':
                    stack.addFirst(keep.substring(1));
                    keep = "";
                    break;

                case '+':
                    val = Double.valueOf(stack.removeFirst()) + Double.valueOf(stack.removeFirst());
                    stack.addFirst(String.valueOf(val));
                    break;

                case '-':
                    if (keep == "[") {
                        keep += String.valueOf(c);
                        break;
                    }

                    valf = Double.valueOf(stack.removeFirst());
                    vall = Double.valueOf(stack.removeFirst());
                    stack.addFirst(String.valueOf(vall - valf));
                    break;

                case '*':
                    val = Double.valueOf(stack.removeFirst()) * Double.valueOf(stack.removeFirst());
                    stack.addFirst(String.valueOf(val));
                    break;

                case '/':
                    valf = Double.valueOf(stack.removeFirst());
                    vall = Double.valueOf(stack.removeFirst());
                    stack.addFirst(String.valueOf(vall / valf));
                    break;

                default:
                    keep += c;
                    break;
            }
        }

        ret = Double.valueOf(stack.removeFirst());
        return ret;
    }
}
