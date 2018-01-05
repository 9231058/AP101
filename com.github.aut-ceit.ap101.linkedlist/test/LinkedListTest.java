import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkedListTest {
    @Test
     void testAppend() {
        LinkedList ll = new LinkedList();

        ll.append(1);
        ll.append(2);

        assertEquals(ll.getLength(), 2);

        assertEquals((int) ll.get(0), 1);
        assertEquals((int) ll.get(1), 2);
    }
}
