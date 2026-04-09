package GiorgiaFormicola.U5_W1_D3;

import GiorgiaFormicola.U5_W1_D3.entities.Drink;
import GiorgiaFormicola.U5_W1_D3.entities.Pizza;
import GiorgiaFormicola.U5_W1_D3.entities.Topping;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MenuItemTests {

    @ParameterizedTest
    @DisplayName("Test Topping constructor with values lower than zero")
    @CsvSource({"-1, 1", "1, -1"})
    public void toppingConstructorTest1(int calories, double price) {
        assertThrows(IllegalArgumentException.class, () -> new Topping("topping", calories, price));
    }

    @ParameterizedTest
    @DisplayName("Test Topping constructor with values bigger than zero")
    @CsvSource({"0, 1", "1, 0"})
    public void toppingConstructorTest2(int calories, double price) {
        Topping topping = new Topping("topping", calories, price);
        assertNotNull(topping);
        assertEquals("topping", topping.getName());
        assertEquals(calories, topping.getCalories());
        assertEquals(price, topping.getPrice());
        assertDoesNotThrow(() -> new Topping("name", calories, price));
    }

    @ParameterizedTest
    @DisplayName("Test Drink constructor with values lower than zero")
    @CsvSource({"-1, 1, 1, 1", "1, -1, 1, 1", "1, 1, -1, 1", "1, 1, 1, -1"})
    public void drinkConstructorTest1(int calories, double price, double volume, double alcoholicDegree) {
        assertThrows(IllegalArgumentException.class, () -> new Drink("drink", calories, price, volume, alcoholicDegree));
    }

    @ParameterizedTest
    @DisplayName("Test Drink constructor with values bigger than zero")
    @CsvSource({"1, 0, 0, 0", "0, 1, 0, 0", "0, 0, 1, 0", "0, 0, 0, 1"})
    public void drinkConstructorTest2(int calories, double price, double volume, double alcoholicDegree) {
        Drink drink = new Drink("drink", calories, price, volume, alcoholicDegree);
        assertNotNull(drink);
        assertEquals(calories, drink.getCalories());
        assertEquals(price, drink.getPrice());
        assertEquals(volume, drink.getVolume());
        assertEquals(alcoholicDegree, drink.getAlcoholicDegree());
        assertEquals("drink", drink.getName());
        assertDoesNotThrow(() -> new Drink("drink", calories, price, volume, alcoholicDegree));
    }

    /*@ParameterizedTest
    @DisplayName("Test Pizza constructor with values lower than zero")
    @CsvSource({"-1, 1, 1, 1", "1, -1, 1, 1", "1, 1, -1, 1", "1, 1, 1, -1"})
    public void pizzaConstructorTest1(int calories, double price, double volume, double alcoholicDegree) {
        assertThrows(IllegalArgumentException.class, () -> new Pizza("pizza", calories, price, volume, alcoholicDegree));
    }*/

    @ParameterizedTest
    @DisplayName("Test Pizza constructor with values bigger than zero")
    @CsvSource({"1, 0, 0, 0", "0, 1, 0, 0", "0, 0, 1, 0", "0, 0, 0, 1"})
    public void pizzaConstructorTest2(int calories, double price) {
        List<Topping> toppingList = new ArrayList<>(List.of( new Topping("onions", 12, 5), new Topping("pepperoni", 10, 2));
        Pizza pizza = new Pizza(calories, price);
        assertNotNull(pizza);
        assertEquals(calories, pizza.getCalories());
        assertEquals(price, pizza.getPrice());
        assertEquals(volume, pizza.getVolume());
        assertEquals(alcoholicDegree, pizza.getAlcoholicDegree());
        assertEquals("pizza", pizza.getName());
        assertDoesNotThrow(() -> new Pizza("pizza", calories, price, volume, alcoholicDegree));
    }




}
