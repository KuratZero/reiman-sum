package Riemann;

import Functions.*;

import java.util.Objects;
import java.util.Random;


public class RiemannSum {

    final Function f;
    final Random random;
    private double EPS = 1e-9;

    public RiemannSum(Function func) {
        Objects.requireNonNull(func);
        this.f = func;
        this.random = new Random();
    }

    public void setEPS(double newEPS) {
        this.EPS = newEPS;
    }

    private double get(double l, double r, Method md) {
        switch (md) {
            case LeftPoint -> {
                return f.evaluate(l);
            }
            case RightPoint -> {
                return f.evaluate(r);
            }
            case MiddlePoint -> {
                return f.evaluate((l + r) / 2);
            }
            case RandomPoint -> {
                return f.evaluate(random.nextDouble(l, r + EPS));
            }
            case Trapezium ->  {
                return (f.evaluate(l) + f.evaluate(r)) / 2;
            }
            default -> throw new RuntimeException("Unknown Method: " + md.name() + ", l/r : " + l + " / " + r);

        }
    }

    public double calculateSum(int n, double l, double r, Method md, boolean cerr) {
        if(r - l < 0) {
            throw new RuntimeException("Invalid left and right: l = " + l + " r = " + r + "\n");
        }
        double sum = 0;
        double tl = l;
        double tr = l + (r - l) / n;
        while (Math.abs(r - tl) > EPS) {
            if (cerr) {
                System.err.printf("f(x) = %s for l = %.6f r = %.6f with %s : %.6f\n",
                        f, tl, tr, md.name(), get(tl, tr, md) * ((r - l) / n));
            }
            sum += get(tl, tr, md) * ((r - l) / n);
            tl = tr;
            tr += (r - l) / n;
        }

        return sum;
    }

}
