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
import java.util.List;
import java.util.function.Function;


public class InternalFunc {

    // 对于整数型数组的处理方法
    public static List<Integer> change(Function<Integer, Integer> dosth, List<Integer> a) {
            List<Integer> ans = new ArrayList<>();
            for (Integer elem : a) {
                ans.add(dosth.apply(elem));
            }
            return ans;
    }

    // 可以进一步扩充

    public static void main (String [] args) {
        Function<Integer, Integer> Double = (Integer a) -> { return a * 2;};

    }
}
