package creatures;
import huglife.*;
import java.awt.Color;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TestClorus {
    @Test
    public void testBasics() {
        Clorus p = new Clorus(2);
        assertEquals(2, p.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), p.color());
        p.move();
        assertEquals(1.97, p.energy(), 0.01);
        p.stay();
        assertEquals(1.96, p.energy(), 0.01);
    }

    @Test
    public void testAttack() {
        Clorus c = new Clorus(2);
        Plip p = new Plip(1);
        c.attack(p);
        assertEquals(3, c.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus p1 = new Clorus(1.6);
        Clorus p2 = p1.replicate();
        assertEquals(0.8, p1.energy(), 0.01);
        assertEquals(0.8, p2.energy(), 0.01);
    }

    public static void main(String[] args) {
        System.exit(jh61b.junit.textui.runClasses(TestPlip.class));
    }
}
