
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ListTest {
    private IList<Integer> myList;
    private IList<Integer> enList;
    private IList<Integer> flerList;

    @BeforeEach
    void setup(){
        myList = new LinkedList<>();
        enList = new LinkedList<>(69);
        flerList = new LinkedList<>(420);
        flerList.add(666);
        flerList.put(8008);
    }
    // Tester for deloppg 1.1 - 0 elements
    @Test
    void test_first_zero(){
        assertThrows(NoSuchElementException.class, ()-> myList.first());
    }

    @Test
    void test_rest_zero(){
        test_rest_zero_or_one(myList);
        assertTrue(myList.isEmpty());
    }

    @Test
    void test_add_zero(){
        // sjekk at den er tom først, add et par integer og sjekk at size går opp
        // Sjekk at 5 og 2 er i listen, random sjekk at 0 ikke er der og at den ikke er "tom"
        assertTrue(myList.isEmpty());
        myList.add(5);
        assertEquals(1, myList.size());
        assertEquals(5, (int) myList.first());
        myList.add(2);
        assertEquals(2, myList.size());
        //Make sure first er still.. first..
        assertEquals(5, (int) myList.first());
        assertTrue(myList.contains(5));
        assertTrue(myList.contains(2));
        assertFalse(myList.contains(0));
        assertFalse(myList.isEmpty());

    }

    @Test
    void test_put_zero(){
        assertTrue(myList.isEmpty());
        myList.put(3);
        assertEquals(1, myList.size());
        assertEquals(3, (int) myList.first());
        myList.put(7);
        assertEquals(2, myList.size());
        assertEquals(7, (int) myList.first());
        assertTrue(myList.contains(3));
        assertTrue(myList.contains(7));
        assertFalse(myList.contains(0));
        assertFalse(myList.isEmpty());
    }

    @Test
    void test_remove_zero(){
        // Forventer at den straight ut kaster NoSuchElementException
        assertThrows(NoSuchElementException.class, ()->myList.remove());
        assertTrue(myList.isEmpty());
    }

    // Test for deloppg 1.2 - 1 element
    @Test
    void test_first_one(){
        assertFalse(enList.isEmpty());
        assertEquals(69, (int) enList.first());
        assertTrue(enList.contains(69));
    }

    @Test
    void test_rest_one(){
        test_rest_zero_or_one(enList);
        assertFalse(enList.isEmpty());
    }

    @Test
    void test_add_one(){

        assertEquals(69, (int) enList.first());
        assertTrue(enList.add(123));
        assertEquals(69, (int) enList.first());
        assertEquals(2, enList.size());
        assertTrue(enList.add(777));
        assertEquals(69, (int) enList.first());
        assertEquals(3, enList.size());
        assertTrue(enList.contains(123));
        assertTrue(enList.contains(777));
    }

    @Test
    void test_put_one(){
        assertEquals(69, (int) enList.first());
        assertTrue(enList.put(321));
        assertEquals(321, (int) enList.first());
        assertEquals(2, enList.size());
        assertTrue(enList.put(11111));
        assertEquals(11111, (int) enList.first());
        assertEquals(3, enList.size());
        assertTrue(enList.contains(321));
        assertTrue(enList.contains(11111));
    }

    @Test
    void test_remove_one(){
        assertFalse(enList.isEmpty());
        int removed = enList.remove();
        assertEquals(69, removed);
        assertTrue(enList.isEmpty());
        assertThrows(NoSuchElementException.class, ()-> enList.remove());
    }
    // Siden rest fungere likt for tom og liste med 1 har jeg en felles sjekk for dem her
    void test_rest_zero_or_one(IList<Integer> listo){
        IList<Integer> restList = listo.rest();
        assertEquals(0, restList.size());
        assertTrue(restList instanceof LinkedList);

        assertTrue(restList.isEmpty());
        assertThrows(NoSuchElementException.class, restList::first);
    }
    // Test for deloppg 1.3 - 2 or more elements
    @Test
    void test_first_more(){
        assertFalse(flerList.isEmpty());
        assertEquals(3, flerList.size());
        assertEquals(8008, (int) flerList.first());
    }

    @Test
    void test_rest_more(){
        IList<Integer> restList = flerList.rest();
        assertFalse(restList.isEmpty());
        assertEquals(2, restList.size());
        assertEquals(8008, (int) flerList.first());
    }

    @Test
    void test_add_more(){
        assertEquals(8008, (int) flerList.first());
        assertEquals(3, flerList.size());
        assertTrue(flerList.add(4444));
        assertEquals(4, flerList.size());
        assertEquals(8008, (int) flerList.first());
        assertTrue(flerList.add(42));
        assertEquals(5, flerList.size());
        assertEquals(8008, (int) flerList.first());
        assertTrue(flerList.contains(42));
        assertTrue(flerList.contains(4444));
        assertTrue(flerList.contains(420));
        assertTrue(flerList.contains(666));
        assertTrue(flerList.contains(8008));
    }

    @Test
    void test_put_more(){
        assertEquals(8008, (int) flerList.first());
        assertEquals(3, flerList.size());
        assertTrue(flerList.put(4444));
        assertEquals(4, flerList.size());
        assertEquals(4444, (int) flerList.first());
        assertTrue(flerList.put(42));
        assertEquals(5, flerList.size());
        assertEquals(42, (int) flerList.first());
        assertTrue(flerList.contains(42));
        assertTrue(flerList.contains(4444));
        assertTrue(flerList.contains(420));
        assertTrue(flerList.contains(666));
        assertTrue(flerList.contains(8008));
    }

    @Test
    void test_remove_more(){
        int removed;

        assertTrue(flerList.remove(420));
        assertEquals(2, flerList.size());
        assertEquals(666, (int) flerList.first());
        assertFalse(flerList.isEmpty());

        assertFalse(flerList.isEmpty());
        removed = flerList.remove();
        assertEquals(8008, removed);
        assertEquals(1, flerList.size());


        removed = flerList.remove();
        assertEquals(666, removed);
        assertEquals(0, flerList.size());
        assertTrue(flerList.isEmpty());

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

        int n = result.remove();
        for (Integer t : target) {
            if (n != t) {
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
