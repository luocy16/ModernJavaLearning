package LambdaLearning;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FilterInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.logging.Filter;

interface FS {
    void show(BufferedReader br);
}

public class FileShower {
    public static void showfile(FS fileShower) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\HP\\IdeaProjects\\learning_modern_java\\data"));
            fileShower.show(br);
        } catch (Exception r) {
            System.out.println("找不到文件");
        }
    }

    public static void main(String[] args) {
        //直接传入
        showfile((BufferedReader br) ->
        {
            try {
                System.out.println(br.readLine()+br.readLine());
            } catch (Exception r) {
                System.out.println("找不到文件");
            };
            ;
        });

        FS show_one_line = (BufferedReader br) -> {try {
            System.out.println(br.readLine());
        } catch (Exception r) {
            System.out.println("找不到文件");
        };
            ;};

        showfile(show_one_line);
    }
}
