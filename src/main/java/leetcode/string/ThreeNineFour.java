package leetcode.string;

/**
 * 字符串解码
 *
 * @author zengxi.song
 * @date 2024/8/14
 */
public class ThreeNineFour {

    public String decodeString(String s) {
        // 递归
        return recursion(s).toString();
    }

    private StringBuilder recursion(String s) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            } else if ('[' == c) {
                int valid = 1;
                for (int j = i + 1; j < s.length(); j++) {
                    char c1 = s.charAt(j);
                    if (c1 == ']') {
                        valid--;
                    } else if (c1 == '[') {
                        valid++;
                    }
                    if (valid == 0) {
                        StringBuilder recursion = recursion(s.substring(i + 1, j));
                        for (int k = 0; k < num; k++) {
                            sb.append(recursion);
                        }
                        num = 0;
                        i = j;
                        break;
                    }
                }
            } else {
                sb.append(c);
            }
        }
        return sb;
    }

    public static void main(String[] args) {
        System.out.println(new ThreeNineFour().decodeString("3[a]2[bc]"));
        System.out.println(new ThreeNineFour().decodeString("3[a2[c]]"));
        System.out.println(new ThreeNineFour().decodeString("2[abc]3[cd]ef"));
    }
}
