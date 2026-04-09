package GiorgiaFormicola.U5_W1_D3.entities;

import GiorgiaFormicola.U5_W1_D3.enums.OrderState;
import GiorgiaFormicola.U5_W1_D3.enums.TableState;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.util.List;


@Getter
@Setter
@ToString
public class Order {
    private static int ordersCounter = 0;
    private int number;
    private int covers;
    private Table table;
    private LocalTime captureTime;
    private List<MenuItem> orderList;
    private OrderState orderState;

    public Order(int covers, Table table, List<MenuItem> orderList) {
        if (table.getState().equals(TableState.FILLED))
            throw new RuntimeException("Impossible to assign the order to the desired table. Table already filled.");
        if (covers > table.getMaxCovers())
            throw new RuntimeException("Impossible to assign the order to the desired table. Table maximum number of covers lower than " + covers + ".");
        else {
            ordersCounter++;
            this.number = ordersCounter;
            this.covers = covers;
            this.table = table;
            this.captureTime = LocalTime.now();
            this.orderList = orderList;
            this.orderState = OrderState.PENDING;
        }
    }

    public double getPricesSum() {
        return orderList.stream().mapToDouble(MenuItem::getPrice).sum();
    }

    public double getTotalAmount(double coverCharge) {
        double total = getPricesSum() + coverCharge * covers;
        BigDecimal bd = new BigDecimal(total);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void print(double coverCharge) {
        System.out.println("ORDER NUMBER " + number);
        System.out.println("Capture Time = " + captureTime);
        System.out.println("Covers = " + covers);
        System.out.println("Table = " + table.getNumber());
        System.out.println("-".repeat(32) + " ORDER " + "-".repeat(31));
        System.out.println("ITEM" + " ".repeat(70 - "Item".length() - ("Calories    Price").length()) + "CALORIES    PRICE");
        getOrderList().forEach(menuItem -> {
            menuItem.printItem();
            if (menuItem instanceof Pizza) {
                Pizza pizza = (Pizza) menuItem;
                if (!pizza.getExtraToppingsList().isEmpty()) {
                    pizza.getExtraToppingsList().forEach(topping -> {
                        System.out.println("   + " + topping.getName() + " ".repeat(55) + topping.getPrice());
                    });
                }
                if (!pizza.getRemovedToppingsList().isEmpty()) {
                    pizza.getRemovedToppingsList().forEach(topping -> {
                        System.out.println("   - " + topping.getName() + " ".repeat(55) + topping.getPrice());
                    });
                }
            }
        });
        System.out.println("-".repeat(32) + " BILL " + "-".repeat(32));
        System.out.println(" ".repeat(55) + "TOTAL = " + getPricesSum() + " $");
        System.out.println(" ".repeat(49) + "COVER FEE = " + covers + " x " + coverCharge + " $");
        System.out.println(" ".repeat(43) + "TOTAL + COVER FEE = " + getTotalAmount(coverCharge) + " $");
    }

}
