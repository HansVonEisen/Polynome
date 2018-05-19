import java.util.ArrayList;

public class Polynom {

    private ArrayList<Double> coefficients = new ArrayList<>(); // index = exponent

    public int getDegree() {
        // 0 ist auch drin, deswegen ist der Grad des Polynoms eins kleiner als die ArrayList
        return coefficients.size() - 1;
    }

    public double getCoefficient(int exp) {
        // wenn valider Exponent (index in ArrayList vorhanden), gib ihn zurück, sonst ist er 0
        if (exp >= 0 && exp <=getDegree()) {
            return coefficients.get(exp);
        }
        return (double) 0;
    }

    public void setCoefficient(int exp, double coefficient) {
        // falls negativer Exponent, fail.
        // Falls ArrayList noch nicht groß genug, erweitere es, bis groß genug.
        // Setze Koeffizient.
        if (exp < 0) {
            throw new IllegalArgumentException("Negative exponents are not allowed!");
        }
        while (exp >= coefficients.size()) {
            coefficients.add((double)0);
        }
        coefficients.set(exp, coefficient);
    }

    @Override
    public String toString() { // wird aufgerufen in System.out.println(polynom)
        // Anfang: leerer String, füge dann immer den richtigen String vorne an.
        StringBuilder output = new StringBuilder();
        for (int exp = 0; exp < coefficients.size(); exp++) {
            double c = coefficients.get(exp);
            // ?: Ist c >= 0, dann füge + ein, sonst füge - ein
            output.insert(0, (c >= 0 ? " + " : " - ") + Math.abs(c) + "x^" + exp);
        }
        return output.toString();
    }

    public double getY(double x) {
        // rechne das Ergebnis (coefficient * x^exp) für jede Stelle aus (Schleife) und addiere alles.
        double result = 0;
        for (int exp = 0; exp < coefficients.size(); exp++) {
            result += coefficients.get(exp) * Math.pow(x, exp);
        }
        return result;
    }

    public Polynom sum(Polynom other) {
        // Finde raus, wie viele Koeffizienten du brauchst (Maximum aus beiden Inputs). Addiere immer beide Coeffizienten und schmeiß das Ergebnis in das Ergebnispolynom.
        int degree = Math.max(this.getDegree(), other.getDegree());
        Polynom result = new Polynom();
        for (int exp = 0; exp <= degree; exp++) {
            result.setCoefficient(exp, this.getCoefficient(exp) + other.getCoefficient(exp));
        }
        return result;
    }

    public Polynom diff(Polynom other) { // analog zu sum, nur halt mit -
        int degree = Math.max(this.getDegree(), other.getDegree());
        Polynom result = new Polynom();
        for (int exp = 0; exp <= degree; exp++) {
            result.setCoefficient(exp, this.getCoefficient(exp) - other.getCoefficient(exp));
        }
        return result;
    }

    public Polynom derivative() {
        // mach ein neues Polynom was 1 kürzer ist, berechne die Koeffizenten
        // (Koeffizient von einer Stelle höher * Exponent von einer Stelle höher) und schmeiß es in das Ergebnispolynom
        Polynom result = new Polynom();
        for (int exp = 0; exp <= this.getDegree() - 1; exp++) {
            result.setCoefficient(exp, this.getCoefficient(exp+1)*(exp+1));
        }
        return result;
    }

    public static void main(String[] args) {
        Polynom p = new Polynom();
        int c = 1;
        for (int exp = 0; exp <= p.getDegree(); exp++) {
            // p.setCoefficient(exp, Math.random()*20-10);
            p.setCoefficient(exp, c);
            c++;
        }

        System.out.println(p.getY(3.5));
    }
}
