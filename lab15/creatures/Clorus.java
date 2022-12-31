package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Action.ActionType;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.Map;
import java.util.List;

public class Clorus extends Creature {
    private int r;
    private int g;
    private int b;

    public Clorus (double e) {
        super("Clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    public Clorus() {
        this(1);
    }

    public Color color() {
        return new Color(r, g, b);
    }

    public void attack(Creature c) {
        energy += c.energy();
    }

    public void move() {
        energy -= 0.03;
    }

    public void stay() {
        energy -= 0.01;
    }

    public Clorus replicate() {
        Clorus child = new Clorus(energy / 2);
        energy /= 2;
        return child;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        List<Direction> plip = getNeighborsOfType(neighbors, "plip");
        if (empties.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }
        else {
            if (plip.size() > 0) {
                Direction d = HugLifeUtils.randomEntry(plip);
                return new Action(Action.ActionType.ATTACK, d);
            }
            else {
                if (energy >= 1) {
                    Direction d = HugLifeUtils.randomEntry(empties);
                    return new Action(Action.ActionType.REPLICATE, d);
                }
                else {
                    Direction d = HugLifeUtils.randomEntry(empties);
                    return new Action(Action.ActionType.MOVE, d);
                }
            }
        }
    }

}
