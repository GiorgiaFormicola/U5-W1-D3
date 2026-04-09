package GiorgiaFormicola.U5_W1_D3.entities;

import GiorgiaFormicola.U5_W1_D3.enums.TableState;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Table {
    private int number;
    private int maxCovers;
    @Setter(AccessLevel.NONE)
    private TableState state;

    public Table(int number, int maxCovers) {
        this.number = number;
        this.maxCovers = maxCovers;
        this.state = TableState.EMPTY;
    }

    public void changeState() {
        if (state.equals(TableState.EMPTY)) this.state = TableState.FILLED;
        else this.state = TableState.EMPTY;
    }
}
