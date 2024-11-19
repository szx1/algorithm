package leetcode.hot100;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zengxi.song
 * @date 2024/11/19
 */
public class RandomSelectiveHot100 {

    static final Pattern IMPORT_PATTERN = Pattern.compile("^import\\s+(.*?);$");

    public static void main(String[] args) throws IOException {
        // 读取目标类的路径
        String filePath = "src/main/java/leetcode/hot100/Hot100.java";
        List<String> imports = extractImports(filePath);

        if (imports.isEmpty()) {
            System.out.println("No imports found in the class.");
            return;
        }

        // 随机选择一个import类
        String randomImport = getRandomImport(imports);

        // 打印出随机选择的import
        System.out.println("Randomly selected import: " + randomImport);

        // 打开随机选中的类文件
        openClassInIDE(randomImport);
    }

    /**
     * 读取并提取类中的所有import语句
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    private static List<String> extractImports(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        List<String> imports = new ArrayList<>();


        for (String line : lines) {
            Matcher matcher = IMPORT_PATTERN.matcher(line.trim());
            if (matcher.matches()) {
                imports.add(matcher.group(1));
            }
        }
        return imports;
    }

    /**
     * 从imports列表中随机选择一个
     *
     * @param imports
     * @return
     */
    private static String getRandomImport(List<String> imports) {
        Random random = new Random();
        return imports.get(random.nextInt(imports.size()));
    }

    /**
     * 打开随机选中的类文件
     */
    private static void openClassInIDE(String randomImport) {
        try {
            // 假设import路径格式为 com.example.MyClass
            String filePath = convertImportToFilePath(randomImport);
            Path path = Paths.get(filePath);

            // 检查文件是否存在
            if (Files.exists(path)) {
                // 使用桌面应用打开文件，操作系统会选择IDEA来打开
                Desktop desktop = Desktop.getDesktop();
                desktop.open(path.toFile());  // 使用默认应用打开文件
            } else {
                System.out.println("File does not exist: " + filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将import的路径转换为文件路径（根据实际的文件结构进行调整）
     *
     * @param importPath
     * @return
     */
    private static String convertImportToFilePath(String importPath) {
        // 假设import是类似 com.example.MyClass 这样的格式
        // 将其转换为路径 com/example/MyClass.java
        return "src/main/java/" + importPath.replace('.', '/') + ".java";
    }
}
