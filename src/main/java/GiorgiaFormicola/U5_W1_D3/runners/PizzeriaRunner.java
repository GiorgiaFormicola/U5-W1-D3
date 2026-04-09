package GiorgiaFormicola.U5_W1_D3.runners;

import GiorgiaFormicola.U5_W1_D3.entities.*;
import GiorgiaFormicola.U5_W1_D3.enums.TableState;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class PizzeriaRunner implements CommandLineRunner {
    private final Menu menu;
    private final List<Table> tablesList;
    private final ConfigurableApplicationContext context;
    private final double coverCharge;

    public PizzeriaRunner(Menu menu, List<Table> tablesList, ConfigurableApplicationContext context, double coverCharge) {
        this.menu = menu;
        this.tablesList = tablesList;
        this.context = context;
        this.coverCharge = coverCharge;
    }

    @Override
    public void run(String... args) throws Exception {
        menu.print();
        List<Table> availableTables = tablesList.stream().filter(table -> table.getMaxCovers() >= 5 && table.getState().equals(TableState.EMPTY)).sorted(Comparator.comparing(Table::getMaxCovers)).toList();
        if (availableTables.isEmpty()) System.out.println("No tables available for 5 people");
        else {
            Table assignedTable = availableTables.getFirst();
            Pizza customPizza = context.getBean("getMargheritaPizza", Pizza.class);
            System.out.println(customPizza);
            customPizza.addTopping(context.getBean("getOnions", Topping.class));
            customPizza.addTopping(context.getBean("getSalami", Topping.class));
            customPizza.removeTopping(context.getBean("getTomato", Topping.class));
            System.out.println(customPizza);
            System.out.println(customPizza.getExtraToppingsList());
            List<MenuItem> orderList = new ArrayList<>(List.<MenuItem>of(context.getBean("getMargheritaPizza", Pizza.class), context.getBean("getSalamiPizza", Pizza.class), context.getBean("getWater", Drink.class), customPizza));
            Order order = new Order(5, assignedTable, orderList);
            order.print(coverCharge);
        }
    }

}
