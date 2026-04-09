package GiorgiaFormicola.U5_W1_D3.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Pizza extends MenuItem {
    private List<Topping> toppingsList;
    private List<Topping> extraToppingsList;
    private List<Topping> removedToppingsList;

    public Pizza(int calories, double price, List<Topping> toppingsList) {
        this.toppingsList = toppingsList;
        this.extraToppingsList = new ArrayList<>();
        this.removedToppingsList = new ArrayList<>();
        super(calories, price);
        String pizzaFlavour = this.getPizzaFlavour();
        this.setName(pizzaFlavour);
    }

    public void addTopping(Topping topping) {
        extraToppingsList.add(topping);
        this.setPrice(this.getPrice() + topping.getPrice());
        this.setCalories(this.getCalories() + topping.getCalories());
    }

    public void removeTopping(Topping topping) {
        removedToppingsList.add(topping);
        this.setPrice(this.getPrice() - topping.getPrice());
        this.setCalories(this.getCalories() - topping.getCalories());
    }

    public String getPizzaFlavour() {
        if (toppingsList.stream().anyMatch(topping -> topping.getName().equals("salami")))
            return "Salami Pizza";
        if (toppingsList.stream().anyMatch(topping -> topping.getName().equals("ham")) && toppingsList.stream().anyMatch(topping -> topping.getName().equals("pineapple")))
            return "Hawaiian Pizza";
        else
            return "Pizza Margherita";
    }

    protected String getPizzaToppingsString() {
        return toppingsList.stream().map(MenuItem::getName).collect(Collectors.joining(", "));
    }

    ;

    @Override
    protected String getTitleString() {
        return super.getTitleString() + " (" + getPizzaToppingsString() + ")";
    }

    @Override
    public String toString() {
        return "Pizza { " +
                super.toString() +
                ", toppingsList=" + toppingsList +
                '}';
    }
}
