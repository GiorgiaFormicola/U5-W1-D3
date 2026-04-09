package GiorgiaFormicola.U5_W1_D3.config;

import GiorgiaFormicola.U5_W1_D3.entities.Drink;
import GiorgiaFormicola.U5_W1_D3.entities.Pizza;
import GiorgiaFormicola.U5_W1_D3.entities.Table;
import GiorgiaFormicola.U5_W1_D3.entities.Topping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;


@Configuration
@PropertySource("application.properties")
public class ConfigClass {
    @Bean
    public Drink getLemonade() {
        return new Drink("lemonade", 128, 1.29, 0.33, 0);
    }

    @Bean
    public Drink getWater() {
        return new Drink("water", 0, 1.29, 0.5, 0);
    }

    @Bean
    public Drink getWine() {
        return new Drink("wine", 607, 7.49, 0.75, 13);
    }

    @Bean
    public Topping getTomato() {
        return new Topping("tomato", 20, 0.00);
    }

    @Bean
    public Topping getCheese() {
        return new Topping("cheese", 92, 0.69);
    }

    @Bean
    public Topping getHam() {
        return new Topping("ham", 35, 0.99);
    }

    @Bean
    public Topping getOnions() {
        return new Topping("onions", 22, 0.69);
    }

    @Bean
    public Topping getPineapple() {
        return new Topping("pineapple", 24, 0.79);
    }

    @Bean
    public Topping getSalami() {
        return new Topping("salami", 86, 0.99);
    }

    @Bean
    public List<Topping> getMargheritaToppings() {
        return new ArrayList<>(List.of(getTomato(), getCheese()));
    }

    @Bean
    public List<Topping> getHawaiianToppings() {
        List<Topping> toppingsList = new ArrayList<>(getMargheritaToppings());
        toppingsList.add(getHam());
        toppingsList.add(getPineapple());
        return toppingsList;
    }

    @Bean
    public List<Topping> getSalamiToppings() {
        List<Topping> toppingsList = new ArrayList<>(getMargheritaToppings());
        toppingsList.add(getSalami());
        return toppingsList;
    }

    @Bean
    @Scope("prototype")
    public Pizza getMargheritaPizza() {
        return new Pizza(1104, 4.99, getMargheritaToppings());
    }

    @Bean
    @Scope("prototype")
    public Pizza getHawaiianPizza() {
        return new Pizza(1024, 6.49, getHawaiianToppings());
    }

    @Bean
    @Scope("prototype")
    public Pizza getSalamiPizza() {
        return new Pizza(1160, 5.99, getSalamiToppings());
    }

    @Bean
    public Table getTable1() {
        return new Table(1, 4);
    }

    @Bean
    public Table getTable2() {
        return new Table(2, 8);
    }

    @Bean
    public Table getTable3() {
        return new Table(3, 10);
    }

    @Bean
    public Table getTable4() {
        return new Table(5, 6);
    }

    @Bean
    public Table getTable5() {
        return new Table(6, 2);
    }

    @Bean
    public List<Table> getTablesList(List<Table> tablesList) {
        return tablesList;
    }

    @Bean
    public double getCoverCharge(@Value("${pizzeria.coverCharge}") double coverCharge) {
        return coverCharge;
    }

}
