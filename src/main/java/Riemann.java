import Riemann.*;
import Functions.*;

public class Riemann {
    public static void main(String[] args) {
        RiemannSum sm = new RiemannSum(new MyFunction());
        int n = 100;
        double l = 1;
        double r = 50;
        System.out.printf("%.6f\n", sm.calculateSum(n, l, r, Method.MiddlePoint, false));
        System.out.printf("%.6f\n", sm.calculateSum(n, l, r, Method.Trapezium, false));


    }
}
