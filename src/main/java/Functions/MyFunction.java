package Functions;


// Функция из условия лабораторной
public class MyFunction implements Function {
    @Override
    public double evaluate(double x) {
        return x * x + x - 1;
    }

    @Override
    public String toString() {
        return "x^2 + x - 1";
    }
}
