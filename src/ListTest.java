

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ListTest {
    IList<Integer> listen;
    IList<Integer> enIListen;
    @BeforeEach
    void setup(){
        listen = new LinkedList<>();
        enIListen = new LinkedList<>(2);
    }

    @Test
    void testEmptyFirst(){
        assertThrows(NoSuchElementException.class, ()-> listen.first());
        assertTrue(listen.isEmpty());
    }

    @Test
    void testEmptyRest(){
        assertEquals(0, listen.rest().size());
        assertTrue(listen.isEmpty());

        IList<Integer> restList = listen.rest();

        assertTrue(restList instanceof LinkedList);
        assertNotEquals(listen, restList);
        assertTrue(restList.isEmpty());
        assertEquals(0, restList.size());
        assertThrows(NoSuchElementException.class, restList::first);
    }

    @Test
    void testEmptyAdd(){
        listen.add(5);
        assertEquals(new Integer(5), listen.first());
        assertEquals(1, listen.size());
        assertTrue(listen.contains(5));
        assertFalse(listen.isEmpty());
    }

    @Test
    void testEmptyPut(){
        listen.put(9);
        assertEquals(new Integer(9), listen.first());
        assertFalse(listen.isEmpty());
        assertEquals(1, listen.size());
        assertTrue(listen.contains(9));

    }

    @Test
    void testEmptyRemove(){
        assertThrows(NoSuchElementException.class, ()-> listen.remove());
    }

    @Test
    void testOneFirst(){
        assertFalse(enIListen.isEmpty());
        assertEquals(new Integer(2), enIListen.first());
        assertEquals(1, enIListen.size());
    }

    @Test
    void testOneRest(){
        assertEquals(1, enIListen.rest().size());
        assertFalse(enIListen.isEmpty());

        IList<Integer> restList = enIListen.rest();

        assertTrue(restList instanceof LinkedList);
        assertNotEquals(enIListen, restList);
        assertTrue(restList.isEmpty());
        assertEquals(0, restList.size());
        assertThrows(NoSuchElementException.class,  restList::first);
    }

    @Test
    void testOneAdd(){
        assertEquals(1, enIListen.size());
        assertTrue(enIListen.add(69));
        assertFalse(enIListen.isEmpty());
        assertEquals(2, enIListen.size());
        assertEquals(new Integer(2), enIListen.first());
    }

    @Test
    void testOnePut(){

    }

    @Test
    void testOneRemove(){

    }

    @Test
    void testManyFirst(){

    }

    @Test
    void testManyRest(){

    }

    @Test
    void testManyAdd(){

    }

    @Test
    void testManyPut(){

    }

    @Test
    void testManyRemove(){

    }
    @Test
    void oppg8_sortIntegers() {
        // Se oppgave 8
        IList<Integer> list = new LinkedList<>();
        List<Integer> values = Arrays.asList(3, 8, 4, 7, 10, 6, 1, 2, 9, 5);

        for (Integer value : values) {
            list.add(value);
        }
        list.sort(Comparator.comparingInt(x -> x));


        int n = list.remove();
        while (list.size() > 0) {
            int m = list.remove();
            if (n > m) {
                fail("Integer list is not sorted.");
            }
            n = m;
        }

    }
    @Test
    void oppg8_sortStrings() {
        // Se oppgave 8
        IList<String> list = new LinkedList<>();
        List<String> values = Arrays.asList(
                "g", "f", "a", "c", "b", "d", "e", "i", "j", "h"
        );
        for (String value : values) {
            list.add(value);
        }

        list.sort(Comparator.naturalOrder());

        String n = list.remove();
        while (list.size() > 0) {
            String m = list.remove();
            if (n.compareTo(m) > 0) {
                fail("String list is not sorted.");
            }

        }
    }

    @Test
    void oppg9_filter() {
        // Se oppgave 9
        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        IList<Integer> list = new LinkedList<>();
        for (Integer value : values) {
            list.add(value);
        }

        list.filter(n -> n % 2 == 1);

        int n = list.remove();
        while(list.size() > 0) {
            if (n % 2 == 1) {
                fail("List contains filtered out elements.");
            }
            n = list.remove();

        }

    }

    @Test
    void oppg10_map() {
        // Se oppgave 10
        List<String> values = Arrays.asList("1", "2", "3", "4", "5");

        IList<String> list = new LinkedList<>();
        for (String value : values) {
            list.add(value);
        }

        IList<Integer> result = list.map(Integer::parseInt);

        List<Integer> target = Arrays.asList(1, 2, 3, 4, 5);


        for (int t : target) {
            if (result.remove() != t) {
                fail("Result of map gives the wrong value.");
            }
        }
    }

    @Test
    void oppg11_reduceInts() {
        // Se oppgave 11
        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5);

        IList<Integer> list = new LinkedList<>();
        for (Integer value : values) {
            list.add(value);
        }

        int result = list.reduce(0, Integer::sum);

        assertEquals(result, 5*((1 + 5)/2));
    }

    @Test
    void oppg11_reduceStrings() {
        List<String> values = Arrays.asList("e", "s", "t");
        IList<String> list = new LinkedList<>();
        for (String s : values) {
            list.add(s);
        }

        String result = list.reduce("t", (acc, s) -> acc + s);

        assertEquals(result, "test");
    }

    @Test
    void ex1_FastSort() {
        // Se ekstraoppgave 1
        Random r = new Random();
        IList<Integer> list = new LinkedList<>();
        for (int n = 0; n < 1000000; n++) {
            list.add(r.nextInt());
        }

        assertTimeout(Duration.ofSeconds(2), () -> list.sort(Integer::compare));

        int n = list.remove();
        for(int m = list.remove(); !list.isEmpty(); n = m) {
            if (n > m) {
                fail("List is not sorted");
            }
        }


    }
}
