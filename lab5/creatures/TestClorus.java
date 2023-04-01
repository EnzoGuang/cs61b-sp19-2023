package creatures;
import org.junit.Test;
import static org.junit.Assert.*;

import java.nio.file.DirectoryNotEmptyException;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

/** Tests the Clorus class
 * @authr enzo
 */
public class TestClorus {
    @Test
    public void testBasics() {
        double delta = 0.01;
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), delta);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(1.97, c.energy(), delta);
        c.move();
        assertEquals(1.94, c.energy(), delta);
        c.stay();
        assertEquals(1.93, c.energy(), delta);
        c.stay();
        assertEquals(1.92, c.energy(), delta);
    }

    @Test
    public void testReplicate() {
        Clorus actual = new Clorus(2);
        Clorus replicate = actual.replicate();
        assertEquals(1, replicate.energy(), 0.01);

        Clorus actual2 = new Clorus(0.99);
        Clorus replicate2 = actual2.replicate();
        assertEquals(0.5, replicate2.energy(), 0.01);
    }

    @Test
    public void testAttack() {
        Plip victim = new Plip(0.99);
        Clorus attack = new Clorus(1);
        attack.attack(victim);
        assertEquals(1.99, attack.energy(), 0.01);
    }

    @Test
    public void testChoose() {

        /** No empty adjacent spaces; stay. */
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        /** if any Plips are seen, clorus will attack one of them randomly */
        c = new Clorus(0.99);
        HashMap<Direction, Occupant> seenPlip = new HashMap<>();
        seenPlip.put(Direction.TOP, new Plip());
        seenPlip.put(Direction.BOTTOM, new Impassible());
        seenPlip.put(Direction.LEFT, new Empty());
        seenPlip.put(Direction.RIGHT, new Empty());

        actual = c.chooseAction(seenPlip);
        //Action unexpected11 = new Action(Action.ActionType.STAY);
        //Action unexpected12 = new Action(Action.ActionType.REPLICATE, );
        //Action unexpected13 = new Action(Action.ActionType.MOVE);

        Action expected1 = new Action(Action.ActionType.ATTACK, Direction.TOP);
        assertEquals(expected1, actual);

        //assertNotEquals(unexpected11, actual);
        //assertNotEquals(unexpected12, actual);
        //assertNotEquals(unexpected13, actual);


        /** if the clorus has energy greater than or equal to one,it will
         *  replicate to a random empty square.
         */
        c = new Clorus(1.5);
        HashMap<Direction, Occupant> greatThan = new HashMap<>();
        greatThan.put(Direction.TOP, new Impassible());
        greatThan.put(Direction.BOTTOM, new Empty());
        greatThan.put(Direction.LEFT, new Impassible());
        greatThan.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(greatThan);

        Action expected2 = new Action(Action.ActionType.REPLICATE, Direction.BOTTOM);

        //Action unexpected21 = new Action(Action.ActionType.MOVE);
        //Action unexpected22 = new Action(Action.ActionType.STAY);
        //Action unexpected23 = new Action(Action.ActionType.ATTACK);

        assertEquals(expected2, actual);

        //assertNotEquals(unexpected21, actual);
        //assertNotEquals(unexpected22, actual);
        //assertNotEquals(unexpected23, actual);

        /** others, the clorus will move to a random empty square. */
        c = new Clorus(0.4);
        HashMap<Direction, Occupant> others = new HashMap<>();
        others.put(Direction.TOP, new Impassible());
        others.put(Direction.BOTTOM, new Impassible());
        others.put(Direction.LEFT, new Empty());
        others.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(others);

        //Action unexpected31 = new Action(Action.ActionType.REPLICATE);
        //Action unexpected32 = new Action(Action.ActionType.STAY);
        //Action unexpected33 = new Action(Action.ActionType.ATTACK);

        Action expected3 = new Action(Action.ActionType.MOVE, Direction.LEFT);

        assertEquals(expected3, actual);

        //assertNotEquals(unexpected31, actual);
        //assertNotEquals(unexpected32, actual);
        //assertNotEquals(unexpected33, actual);
    }
}
