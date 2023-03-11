package Riemann;

import Functions.*;
import org.knowm.xchart.*;
import org.knowm.xchart.style.markers.BaseSeriesMarkers;

import java.awt.*;
import java.util.*;
import java.util.List;

// Подсчёт суммы с визуализацией
public class RiemannSum {

    final Function f;
    final Random random;
    // Параметр для сравнения двух double
    private double EPS = 1e-9;
    // Параметр для построения графика
    private double STEP = 0.01;

    public RiemannSum(Function func) {
        Objects.requireNonNull(func);
        this.f = func;
        this.random = new Random();
    }

    public void setEPS(double newEPS) {
        this.EPS = newEPS;
    }

    public void setSTEP(double newSTEP) {
        this.STEP = newSTEP;
    }

    private record GetNode(double result, double lhs, double rhs) {
    }

    // Выбор точки
    private GetNode get(double l, double r, Method md) {
        double y;
        switch (md) {
            case LeftPoint -> {
                y = f.evaluate(l);
                return new GetNode(y, y, y);
            }
            case RightPoint -> {
                y = f.evaluate(r);
                return new GetNode(y, y, y);
            }
            case MiddlePoint -> {
                y = f.evaluate((l + r) / 2);
                return new GetNode(y, y, y);
            }
            case RandomPoint -> {
                double x = random.nextDouble(l, r + EPS);
                y = f.evaluate(x);
                return new GetNode(y, y, y);
            }
            case Trapezium -> {
                double y1 = f.evaluate(l);
                double y2 = f.evaluate(r);
                return new GetNode((y1 + y2) / 2, y1, y2);
            }
            default -> throw new RuntimeException("Unknown Method: " + md.name() + ", l/r : " + l + " / " + r);

        }
    }

    // Новая трапеция
    private void addNewInterval(XYChart chart, double tl, double tr, double lhs, double rhs) {
        XYSeries ser = chart.addSeries("NONE" + tl + tr,
                new double[]{tl, tl, tr, tr},
                new double[]{0, lhs, rhs, 0});
        ser.setLineWidth((float) 1.3);
        if(lhs > EPS && rhs > EPS) {
            ser.setLineColor(Color.GREEN);
        } else {
            ser.setLineColor(Color.RED);
        }

        ser.setMarker(BaseSeriesMarkers.NONE);
        ser.setShowInLegend(false);
    }

    // Подсчёт суммы и визуализация
    public double calculateSum(int n, double l, double r, Method md, boolean createChart, boolean cerr) {
        if (r - l < EPS) {
            throw new RuntimeException("Invalid left and right: l = " + l + " r = " + r + "\n");
        }

        XYChart chart = null;
        if (createChart) {
            chart = createXYChart(l, r, md);
        }


        double sum = 0;
        double tl = l;
        double tr = l + (r - l) / n;
        while (Math.abs(r - tl) > EPS) {
            GetNode get = get(tl, tr, md);

            if (createChart) {
                addNewInterval(chart, tl, tr, get.lhs, get.rhs);
            }

            double sumDar = get.result * ((r - l) / n);

            if (cerr) {
                System.err.printf("f(x) = %s for l = %.6f r = %.6f with %s : %.6f\n",
                        f, tl, tr, md.name(), sumDar);
            }

            sum += sumDar;
            tl = tr;
            tr += (r - l) / n;
        }

        if (createChart) {
            new SwingWrapper(chart).displayChart();
        }

        return sum;
    }

    // Создание графика
    private XYChart createXYChart(double l, double r, Method md) {
        List<Double> x = new ArrayList<>();
        List<Double> y = new ArrayList<>();

        for (double i = l; (r - i) > EPS; i += STEP) {
            x.add(i);
            y.add(f.evaluate(i));
        }

        XYChart chart = new XYChartBuilder().width(600).height(400)
                .title("Riemann " + f + " " + md.name()).xAxisTitle("X").yAxisTitle("Y").build();

        XYSeries ser = chart.addSeries("zero", new double[]{l, r}, new double[]{0, 0});
        ser.setMarker(BaseSeriesMarkers.NONE);
        ser.setLineColor(Color.darkGray);
        ser.setShowInLegend(false);

        ser = chart.addSeries("f(x)", x, y);
        ser.setMarker(BaseSeriesMarkers.NONE);
        ser.setLineColor(Color.BLUE);

        return chart;
    }

}
