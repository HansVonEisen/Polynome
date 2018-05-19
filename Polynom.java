import java.util.ArrayList;
import static Prog1Tools.IOTools.*;

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

    public double getFvonX(double x) {
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
            // hier belege ich result mit den neuen Koeffizienten - ich hole mir den Koeffizienten
            // an der Stelle Indize + 1 (this.getCoefficient(exp+1)) und multipliziere es mit Indize + 1 (exp+1)
            // da mein neuer result-Polynom bis Degree - 1 läuft habe ich automatisch die Exponenten an der richtigen
            // Stelle (= Indizes)
            result.setCoefficient(exp, this.getCoefficient(exp+1) * (exp+1));
        }
        return result;
    }

    public void eingabe() {
        System.out.print("Bitte gib den Grad vom Polynom ein: ");
        int degree;
        degree = readInteger();
        System.out.println("Bitte gib den Koeffizient k für jeden Term k*x^n ein."
            + "Hat der Term keinen Koeffizienten,\ndann gib bitte 1 (für 1*k^n) ein."
            + "Gibt es den Term x^n nicht, dann gib bitte als Term 0 (0*x^n) ein");

        for (int exp = degree + 1; exp >1; exp--) {
            System.out.println("Gib bitte den Koeffizienten für x^"+(exp-1) );
            double c=readDouble();
            this.setCoefficient(exp-1,c);
        }
        System.out.println("Bitte gib den Absolutglied (für x^0) ein: ");
        double c = readDouble();
        this.setCoefficient(0,c);
    }

    public void change() {
/*
        System.out.println("Welches Polynom möchtest du veränder ?");
        // Vom Speicher aussuchen - KOMMT NOCH !!!!

        System.out.println("Welchen Grad des Polynoms willst du verändern"
            +"und welcher Koeffizient soll eingetragen werden");
            */

        int exp = readInteger();
        double coefficient = readDouble();
        setCoefficient(exp,coefficient);



    }

    public static void main(String[] args) {
        Polynom p = new Polynom();

        p.coefficients.add(1.);
        p.coefficients.add(2.);
        p.coefficients.add(3.);
        p.coefficients.add(4.);
        p.coefficients.add(5.);
        p.coefficients.add(6.);

        //p.eingabe();

        System.out.println(p.toString());

        p.coefficients.add(7.);

        System.out.println(p.toString());

        int auswehl = readInteger();


        System.out.println("Welchen Wert hitnerlegen");
        double c = readDouble();
        System.out.println("An wlecher STelle");
        int i = readInteger();
        p.setCoefficient(i,c);

        System.out.println(p.toString());



    }

}
