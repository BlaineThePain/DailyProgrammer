import edge_detection.Tools;
import org.junit.Test;
import static org.junit.Assert.*;


public class MiscTests {
    
    @Test
    public void TestAngles() {
        assertEquals(Math.PI / 4, Tools.closestOctant(3 * Math.PI / 16), 0.1);
        assertEquals(0, Tools.closestOctant(1 * Math.PI / 16), 0.1);
        assertEquals(Math.PI / 4, Tools.closestOctant(5 * Math.PI / 16), 0.1);
        assertEquals(Math.PI / 2, Tools.closestOctant(7 * Math.PI / 16), 0.1);
        
        assertEquals(Math.PI / 2, Tools.closestOctant(9 * Math.PI / 16), 0.1);
        assertEquals(3 * Math.PI / 4, Tools.closestOctant(11 * Math.PI / 16), 0.1);
        assertEquals(3 * Math.PI / 4, Tools.closestOctant(13 * Math.PI / 16), 0.1);
        assertEquals(Math.PI, Tools.closestOctant(15 * Math.PI / 16), 0.1);
        
        assertEquals(Math.PI, Tools.closestOctant(- Math.PI / 16), 0.1);
        assertEquals(3 * Math.PI / 4, Tools.closestOctant(-3 * Math.PI / 16), 0.1);
        assertEquals(3 * Math.PI / 4, Tools.closestOctant(-5 * Math.PI / 16), 0.1);
        assertEquals(Math.PI / 2, Tools.closestOctant(-7 * Math.PI / 16), 0.1);
    }
    
    @Test
    public void test() {
        assertTrue(Tools.areClose(Math.PI / 4, Tools.closestOctant(3 * Math.PI / 16)));
    }
    
}
