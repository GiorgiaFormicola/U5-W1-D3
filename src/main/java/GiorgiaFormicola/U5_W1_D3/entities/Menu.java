package GiorgiaFormicola.U5_W1_D3.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Component
public class Menu {
    private List<Pizza> pizzasList;
    private List<Topping> toppingsList;
    private List<Drink> drinksList;

    public void print() {
        System.out.println("\n" + "-".repeat(32) + " MENU " + "-".repeat(32));
        System.out.println("Pizzas" + " ".repeat(70 - "Pizzas".length() - ("Calories    Price").length()) + "Calories    Price");
        pizzasList.forEach(MenuItem::printItem);
        System.out.println("\n");
        System.out.println("Toppings" + " ".repeat(70 - "Toppings".length() - ("Calories    Price").length()) + "Calories    Price");
        toppingsList.stream().filter(topping -> !topping.getName().equals("tomato")).forEach(MenuItem::printItem);
        System.out.println("\n");
        System.out.println("Drinks" + " ".repeat(70 - "Drinks".length() - ("Calories    Price").length()) + "Calories    Price");
        drinksList.forEach(MenuItem::printItem);
        System.out.println("\n");
    }
}
