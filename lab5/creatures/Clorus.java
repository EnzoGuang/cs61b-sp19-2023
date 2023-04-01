package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Clorus extends Creature {
    /** red color */
    private static final int r = 34;

    /** green color */
    private static final int g = 0;

    /** blue color */
    private static final int b = 231;

    /** Creates Clorus with energy equals to E.*/
    public Clorus(double e) {
        super("clorus");
        energy = e;
    }

    public Clorus() {
        this(1);
    }

    @Override
    public Color color() {
        return color(r, g, b);
    }

    @Override
    public void attack(Creature c) {
        energy += c.energy();
    }

    @Override
    public void move() {
        energy -= 0.03;
        if (energy < 0) {
            energy = 0;
        }
    }

    @Override
    public void stay() {
        energy -= 0.01;
        if (energy < 0) {
            energy = 0;
        }
    }

    @Override
    public Clorus replicate() {
        energy *= 0.5;
        return new Clorus(energy);
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        ArrayList<Direction> emptyList = new ArrayList<>();
        ArrayList<Direction> plipList = new ArrayList<>();
        int emptySize = 0;
        int plipSize = 0;
        for (Map.Entry<Direction, Occupant> entry: neighbors.entrySet()) {
            boolean isEmpty = entry.getValue().name().equals("empty");
            boolean isPlip = entry.getValue().name().equals("plip");
            if (isEmpty) {
                emptySize++;
                emptyList.add(entry.getKey());
            }
            if (isPlip) {
                plipSize++;
                plipList.add(entry.getKey());
            }
        }
        if (emptySize == 0) {
            return new Action(Action.ActionType.STAY);
        } else if (plipSize > 0) {
            Random random = new Random();
            int randomIndex = random.nextInt(plipList.size());
            return new Action(Action.ActionType.ATTACK, plipList.get(randomIndex));
        } else if (this.energy >= 1.0) {
            Random random = new Random();
            int randomIndex = random.nextInt(emptyList.size());
            return new Action(Action.ActionType.REPLICATE, emptyList.get(randomIndex));
        } else {
            Random random = new Random();
            int randomIndex = random.nextInt(emptyList.size());
            return new Action(Action.ActionType.MOVE, emptyList.get(randomIndex));
        }
        /** rule1 no empty ,clorus will stay.*/

        /** rule2 if any Plips are seen, clorus will attack one of then randomly.*/

        /** rule3 if clorus energy greater than or equal to one ,will replicate. */

        /** rule4 other situation clorus will move to a random empty square.*/
    }


}
