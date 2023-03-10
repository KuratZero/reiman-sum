import Riemann.*;
import Functions.*;

public class Riemann {
    public static void main(String[] args) {
        RiemannSum sm = new RiemannSum(new MyFunction());
        int n = 10;
        double l = 1;
        double r = 4;
        System.out.println(new MyFunction());
        System.out.printf("LeftPoint : %.6f\n", sm.calculateSum(n, l, r, Method.LeftPoint, true, false));
        System.out.printf("MiddlePoint : %.6f\n", sm.calculateSum(n, l, r, Method.MiddlePoint, true, false));
        System.out.printf("RightPoint : %.6f\n", sm.calculateSum(n, l, r, Method.RightPoint, true, false));
        System.out.printf("RandomPoint : %.6f\n", sm.calculateSum(n, l, r, Method.RandomPoint, true, false));
        System.out.printf("Trapezium : %.6f\n", sm.calculateSum(n, l, r, Method.Trapezium, true, false));
        System.out.println();

        // дополнительно
        l = -4;
        r = 3.14;
        System.out.println(new SinFunc());
        RiemannSum sin = new RiemannSum(new SinFunc());
        System.out.printf("Trapezium : %.6f\n", sin.calculateSum(n, l, r, Method.Trapezium, true, false));



    }
}
