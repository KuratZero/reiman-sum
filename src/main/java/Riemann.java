import Riemann.*;
import Functions.*;

public class Riemann {
    public static void main(String[] args) {
        RiemannSum sm = new RiemannSum(new MyFunction());

        int n = 10;
        double l = 1;
        double r = 4;

        double al = 25.5;

        System.out.println(new MyFunction());
        System.out.printf("LeftPoint : %.6f\n",
                sm.calculateSum(n, l, r, Method.LeftPoint, true, true));
        System.out.printf("Error LeftPoint : %.6f\n\n",
                Math.abs(al - sm.calculateSum(n, l, r, Method.LeftPoint, false, false)));

        System.out.printf("MiddlePoint : %.6f\n",
                sm.calculateSum(n, l, r, Method.MiddlePoint, true, false));
        System.out.printf("Error MiddlePoint : %.6f\n\n",
                Math.abs(al - sm.calculateSum(n, l, r, Method.MiddlePoint, false, false)));

        System.out.printf("RightPoint : %.6f\n",
                sm.calculateSum(n, l, r, Method.RightPoint, true, false));
        System.out.printf("Error RightPoint : %.6f\n\n",
                Math.abs(al - sm.calculateSum(n, l, r, Method.RightPoint, false, false)));

        System.out.printf("RandomPoint : %.6f\n",
                sm.calculateSum(n, l, r, Method.RandomPoint, true, false));
        System.out.printf("Error RandomPoint : %.6f\n\n",
                Math.abs(al - sm.calculateSum(n, l, r, Method.RandomPoint, false, false)));

        System.out.printf("Trapezium : %.6f\n",
                sm.calculateSum(n, l, r, Method.Trapezium, true, false));
        System.out.printf("Error Trapezium : %.6f\n\n",
                Math.abs(al - sm.calculateSum(n, l, r, Method.Trapezium, false, false)));

        // дополнительно
        l = -4;
        r = 3.14;
        RiemannSum sin = new RiemannSum(new SinFunc());

        System.out.println(new SinFunc());
        System.out.printf("Trapezium : %.6f\n", sin.calculateSum(n, l, r, Method.Trapezium, false, false));



    }
}
