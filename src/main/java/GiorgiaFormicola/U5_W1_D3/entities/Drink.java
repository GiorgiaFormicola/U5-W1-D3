package GiorgiaFormicola.U5_W1_D3.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Drink extends MenuItem {
    private double volume;
    private double alcoholicDegree;

    public Drink(String name, int calories, double price, double volume, double alcoholicDegree) {
        super(name, calories, price);
        this.volume = volume;
        this.alcoholicDegree = alcoholicDegree;
    }

    protected String getDrinkInfoString() {
        if (alcoholicDegree == 0) return " (" + volume + "l)";
        if (alcoholicDegree % 1 == 0) return " (" + volume + "l, " + (int) alcoholicDegree + "%)";
        else return " (" + volume + "l, " + alcoholicDegree + "%)";
    }

    ;

    @Override
    protected String getTitleString() {
        return super.getTitleString() + getDrinkInfoString();
    }

    ;

    @Override
    public String toString() {
        return "Drink { " +
                super.toString() +
                ", volume=" + volume +
                ", alcoholicDegree=" + alcoholicDegree +
                '}';
    }
}
