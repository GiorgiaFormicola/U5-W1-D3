package GiorgiaFormicola.U5_W1_D3.runners;

import GiorgiaFormicola.U5_W1_D3.entities.*;
import GiorgiaFormicola.U5_W1_D3.enums.OrderState;
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

    public Order acceptNewOrder(int covers, List<MenuItem> orderList) {
        List<Table> availableTables = tablesList.stream().filter(table -> table.getMaxCovers() >= covers && table.getState().equals(TableState.EMPTY)).sorted(Comparator.comparing(Table::getMaxCovers)).toList();
        if (availableTables.isEmpty())
            throw new RuntimeException("\nImpossible to accept the order! No tables available for " + covers + " people!");
        else {
            Table assignedTable = availableTables.getFirst();
            Order order = new Order(covers, assignedTable, orderList);
            System.out.println("\nNew order for " + covers + " people accepted! Table " + assignedTable.getNumber() + " filled!");
            order.print(coverCharge);
            assignedTable.changeState();
            return order;
        }
    }

    public void processOrder(Order order) {
        order.setOrderState(OrderState.SERVED);
        order.getTable().changeState();
        System.out.println("\nOrder number " + order.getNumber() + " processed! Table " + order.getTable().getNumber() + " " + order.getTable().getState().toString().toLowerCase() + "!");
    }

    @Override
    public void run(String... args) throws Exception {
        menu.print();
        Pizza customPizza = context.getBean("getMargheritaPizza", Pizza.class);
        customPizza.addTopping(context.getBean("getOnions", Topping.class));
        customPizza.addTopping(context.getBean("getSalami", Topping.class));
        customPizza.removeTopping(context.getBean("getTomato", Topping.class));
        List<MenuItem> orderList = new ArrayList<>(List.<MenuItem>of(context.getBean("getMargheritaPizza", Pizza.class), context.getBean("getSalamiPizza", Pizza.class), context.getBean("getWater", Drink.class), customPizza));

        Order newOrder = null;
        try {
            newOrder = acceptNewOrder(10, orderList);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        try {
            newOrder = acceptNewOrder(10, orderList);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        try {
            processOrder(newOrder);
        } catch (NullPointerException e) {
            System.out.println("Impossible to process the order! Order not found.");
        }

        try {
            newOrder = acceptNewOrder(10, orderList);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }

    }
}
