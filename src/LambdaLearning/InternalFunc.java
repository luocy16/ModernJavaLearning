package LambdaLearning;


/*
* Predicate<T>: 内置方法为test(), 返回boolean:某种筛选的意思 --filter in python
* Consumer<T>: 内置方法为accept(), 返回void: 做某种处理
* Function<T,R>: 内置方法为R apply (T ), 返回R, 也是做某种处理
*
* 下面演示对一个整数数组的处理
* *2/+1/..
* */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


public class InternalFunc <T> {

    // 对于整数型数组的处理方法
    public static List<Integer> change(Function<Integer, Integer> dosth, List<Integer> a) {
            List<Integer> ans = new ArrayList<>();
            for (Integer elem : a) {
                ans.add(dosth.apply(elem));
            }
            return ans;
    }

    // 可以进一步扩充到对于更加一般的数组, 实际上利用接口+泛型可以有java中的泛型，只是说我们只需要指定一个Lambda表达式即可
    public List<T> general_change (Function<T, T> dosth, List<T> a) {
        List<T> ans = new ArrayList<>();
        for (T elem : a) {
            ans.add(dosth.apply(elem));
        }
        return ans;
    }

    public static void main (String [] args) {
        Function<Integer, Integer> Double = (Integer a) -> { return a * 2;};
        Function<String, String> DoubleString = (String a) -> { return a + a;};
        List<Integer> ans = new InternalFunc<Integer>().general_change(Double, new ArrayList<Integer>(Arrays.asList(1, 32,321,32,2)));
        System.out.println(ans.toString());
        List<String> ans_string = new InternalFunc<String>().general_change(DoubleString,
                new ArrayList<String>(Arrays.asList("ada","few","fewe","fewfe\"wfe")));
        System.out.println(ans_string.toString());
    }
}
