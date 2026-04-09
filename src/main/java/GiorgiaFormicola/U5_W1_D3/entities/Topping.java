package GiorgiaFormicola.U5_W1_D3.entities;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Topping extends MenuItem {
    public Topping(String name, int calories, double price) {
        super(name, calories, price);
    }

    @Override
    public String toString() {
        return "Topping { " +
                super.toString() +
                " }";
    }
}
