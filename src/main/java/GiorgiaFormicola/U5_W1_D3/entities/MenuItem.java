package GiorgiaFormicola.U5_W1_D3.entities;

import lombok.Getter;
import lombok.Setter;

/*@AllArgsConstructor*/
@Getter
@Setter
public abstract class MenuItem {
    private String name;
    private int calories;
    private double price;

    public MenuItem(int calories, double price) {
        if (calories < 0 || price < 0)
            throw new IllegalArgumentException("Calories and price values must be bigger or equal to 0.");
        else {
            this.calories = calories;
            this.price = price;
        }
    }

    public MenuItem(String name, int calories, double price) {
        if (calories < 0 || price < 0)
            throw new IllegalArgumentException("Calories and price values must be bigger or equal to 0.");
        else {
            this.name = name;
            this.calories = calories;
            this.price = price;
        }
    }

    protected String getTitleString() {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    ;

    private String getInfoString() {
        return calories + "     " + String.format("%.2f", price);
    }

    ;

    public void printItem() {
        int titleLength = getTitleString().length();
        int infoLength = getInfoString().length();
        int spacesLeft = 70 - titleLength - infoLength;
        System.out.println(getTitleString() + " ".repeat(spacesLeft) + getInfoString());
    }

    ;

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", calories=" + calories +
                ", price=" + price;
    }
}

