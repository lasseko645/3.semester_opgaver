package testdrevetdevelopment.linieregner;

public class Linie {

    private Punkt punkt_a;
    private Punkt punkt_b;


    public Linie(double a_x,double a_y, double b_x, double b_y){
        punkt_a = new Punkt(a_x,a_y);
        punkt_b = new Punkt(b_x,b_y);
    }

    public Punkt getPunkt_a() {
        return punkt_a;
    }

    public Punkt getPunkt_b() {
        return punkt_b;
    }

    public double length(){
        double result;

        result = Math.sqrt(
                Math.pow((punkt_a.getX() - punkt_b.getX()),2)
                +
                Math.pow((punkt_a.getY() - punkt_b.getY()),2));
        return result;
    }


}
