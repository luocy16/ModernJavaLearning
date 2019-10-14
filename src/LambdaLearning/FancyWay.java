package LambdaLearning;


/*
 * Predicate<T>: 内置方法为test(), 返回boolean:某种筛选的意思 --filter in python
 * Consumer<T>: 内置方法为accept(), 返回void: 做某种处理
 * Function<T,R>: 内置方法为R apply (T ), 返回R, 也是做某种处理
 *
 * 下面演示对一个整数数组的处理
 * *2/+1/..
 * */


import java.util.*;
import java.util.function.Function;

interface Print{
    void print(String a);
}



class Test {
    static Integer Double (Integer x) {
        return x * x;
    }
}


class Point {
    int x;
    int y;
    int z;

    Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    int CMP (Point B) {
        return (
                this.x > B.x ?
                    1 :  (
                            this.x < B.x ?
                                   -1 : (
                                            this.y > B.y ?
                                                    1 : (
                                                            this.y < B.y ?
                                                                -1 :
                                                                    this.z < B.z ? -1 : 1
                                                    )
                                    )
                        )
                );
    }

    static int CMP_2 (Point A, Point B) {
        return (
                A.x > B.x ?
                        1 :  (
                        A.x < B.x ?
                                -1 : (
                                A.y > B.y ?
                                        1 : (
                                        A.y < B.y ?
                                                -1 :
                                                A.z < B.z ? -1 : 1
                                )
                        )
                )
        );
    }

    public String toString () {
        return String.format("x:%d, y:%d, z:%d \n", x, y, z);
    };
}

public class FancyWay <T> {

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

    public static void initial (ArrayList<Point> a) {
        Random random = new Random(10);
        for (int i = 0; i < 10; i++) {
           a.add(new Point(random.nextInt(4), random.nextInt(4), random.nextInt(4)));
        }
    }


    public static void main (String [] args) {
        int ratio = 3;
        Function<Integer, Integer> Double = (a) -> { return a * ratio;};
        Function<String, String> DoubleString = (a) -> { return a + a;};
        // 可以去掉传入参数，支持自动类型推断
        // 可以像C++一样，实现获取外部变量, 但是只支持终结局部变量，并且不能修改
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> ans = new InternalFunc<Integer>().general_change(Double, integerList);
        System.out.println(ans.toString());

        // 方法引用： 一种语法糖，如果Lambda表达式里面调用某个类的方法，可以直接写
        Function<Integer, Integer> Double1 = Test::Double;
        List<Integer> integerList1 = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> ans1 = new InternalFunc<Integer>().general_change(Double1, integerList);
        System.out.println(ans1.toString());

        // 方法引用例2, 这里，我们直接把类的成员方法拿出来了，为什么可以呢，注意类的成员方法最后会变成 cmp (Point this, Point b)
        ArrayList<Point> Points = new ArrayList<>();
        initial(Points);
        Print output = a -> System.out.println(a);
        output.print(Points.toString());
        Comparator<Point> cmp = Point::CMP_2; // 实际上是语法糖，等价于Comparator<Point> cmp1 = (a, b) -> {return a.CMP(b);};
        Points.sort(cmp);
        output.print(Points.toString());
    }
}
