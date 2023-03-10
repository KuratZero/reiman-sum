package Functions;

// Дополнительная функция для проверки
public class SinFunc implements Function {
    @Override
    public double evaluate(double x) {
        return Math.sin(x);
    }

    @Override
    public String toString() {
        return "sin x";
    }
}
