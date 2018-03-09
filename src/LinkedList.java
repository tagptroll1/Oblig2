import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class LinkedList<E> implements IList<E>{
    private Node<E> node;
    private Node<E> first;
    private Node<E> last;
    private Integer size;

    public LinkedList(){
        node = new Node<>();
        this.first = node;
        this.last = node;
        this.size = 0;
    }

    public LinkedList(E first){
        node = new Node<>(first);
        this.first = node;
        this.last = node;
        this.size = 1;
    }

    public LinkedList(E first, IList<E> list){
        this.first = new Node(first);

    }

    /**
     * ,* Gir det første elementet i listen.
     * ,*
     * ,* @return Det første elementet i listen.
     * ,* @throws NoSuchElementException Hvis listen er tom.
     * ,
     */
    @Override
    public E first(){
        if (size <= 0){
            throw new NoSuchElementException("List is empty");
        }
        return this.first.getData();
    }

    /**
     * ,* Returnerer alle elementene i listen bortsett fra det
     * ,* første.
     * ,*
     * ,* @return Resten av listen.
     * ,
     */
    @Override
    public IList<E> rest() {
        IList<E> tempList;

        if (size <= 1){
          return tempList = new LinkedList<>();
        }
        Node<E> firstNew = first.getNext();
        tempList = new LinkedList<>();
        while (true){
            if (firstNew.hasNext()){
                node = new Node<>(firstNew.getData(), firstNew.getNext());
            } else {
                node = new Node<>(firstNew.getData(), null);
            }
            tempList.add(node.getData());
            if (node.hasNext()){
                firstNew = firstNew.getNext();
            } else {
                break;
            }
        }


        return tempList;

    }

    /**
     * ,* Legger til et element på slutten av listen.
     * ,
     *
     * @param elem
     */
    @Override
    public boolean add(E elem) {
        node = new Node<>(elem);
        last.changeNext(node);
        if (isEmpty()){
            first = node;
        }
        last = node;
        size++;
        return true;
    }

    /**
     * ,* Legger til et element på begynnelsen av listen.
     * ,
     *
     * @param elem
     */
    @Override
    public boolean put(E elem) {
        node = new Node<>(elem, first);
        if (isEmpty()){
            last = node;
        }
        first = node;
        size++;
        return true;
    }

    /**
     * ,* Fjerner det første elementet i listen.
     * ,*
     * ,* @return Det første elementet i listen.
     * ,* @throws NoSuchElementException Hvis listen er tom.
     * ,
     */
    @Override
    public E remove() {
        if (isEmpty()){
            throw new NoSuchElementException("List is empty");
        }

        Node<E> tempStart = first;
        if (first.hasNext()){
            first = first.getNext();
        } else {
            first = null;
        }
        size--;

        return tempStart.getData();
    }

    /**
     * ,* Fjerner det angitte objektet fra listen.
     * ,*
     * ,* @param o Objektet som skal fjernes.
     * ,* @return true hvis et element ble fjernet, false
     * ,* ellers.
     * ,
     *
     * @param o
     */
    @Override
    public boolean remove(Object o) {
        Node<E> startNode = first;
        if (o.equals(startNode.getData())){
            first = startNode.getNext();
            size--;
            return true;
        }
        if (o.equals(last)){
            while(startNode.getNext() != last){
                startNode = startNode.getNext();
            }
            last = startNode;
            size--;
        }
        while (true){
            if (o.equals(startNode.getNext().getData())){
                startNode.changeNext(startNode.getNext().getNext());
                size--;

                return true;
            }
            if (startNode.hasNext()){
                startNode = startNode.getNext();
            } else {
                break;
            }
        }
        return false;
    }

    /**
     * ,* Sjekker om et element er i listen.
     * ,*
     * ,* @param o objektet vi sjekker om er i listen.
     * ,* @return true hvis objektet er i listen, false ellers.
     * ,
     *
     * @param o
     */
    @Override
    public boolean contains(Object o) {
        Node<E> startNode = first;
        if (o.equals(first.getData())){
            return true;
        }
        while (true){
            if (o.equals(startNode.getData())){
                return true;
            }
            if (startNode.hasNext()){
                startNode = startNode.getNext();
            } else {
                break;
            }
        }
        return false;
    }

    /**
     * ,* Sjekker om listen er tom.
     * ,*
     * ,* @return true hvis listen er tom, false ellers.
     * ,
     */
    @Override
    public boolean isEmpty() {
        if (size <= 0){
            return true;
        } else {
            return false;
        }
    }

    /**
     * ,* Legger til alle elementene i den angitte listen på
     * ,* slutten av listen.
     * ,*
     * ,* @param listen som blir lagt til.
     * ,
     *
     * @param list
     */
    @Override
    public void append(IList<? super E> list) {

    }

    /**
     * ,* Legger til alle elementene i den angitte listen på
     * ,* begynnelsen av listen.
     * ,*
     * ,* @param list listen som blir lagt til
     * ,
     *
     * @param list
     */
    @Override
    public void prepend(IList<? super E> list) {

    }

    /**
     * ,* Slår sammen flere lister
     * ,*
     * ,* @param lists listene som skal slås sammen
     * ,* @return Ny liste med alle elementene fra listene som
     * ,* skal slås sammen.
     * ,
     *
     * @param lists
     */
    @Override
    public IList<E> concat(IList<? super E>... lists) {
        return null;
    }

    /**
     * ,* Sorterer listen ved hjelp av en
     * ,* sammenligningsfunksjon
     * ,* @param c sammenligningsfunksjon som angir rekkefølgen
     * ,* til elementene i listen
     * ,
     *
     * @param c
     */
    @Override
    public void sort(Comparator<? super E> c) {

    }

    /**
     * ,* Fjerner elementer fra listen som svarer til et
     * ,* predikat.
     * ,* @param filter predikat som beskriver hvilken
     * ,* elementer som skal fjernes.
     * ,
     *
     * @param filter
     */
    @Override
    public void filter(Predicate<? super E> filter) {

    }

    /**
     * ,* Kjører en funksjon over hvert element i listen
     * ,*
     * ,* @param f en funksjon fra typen til elementene i
     * ,* listen til en annen type
     * ,* @return En liste over elementene som funksjonen
     * ,* returnerer
     * ,
     *
     * @param f
     */
    @Override
    public <U> IList<U> map(Function<? super E, ? extends U> f) {
        return null;
    }

    /**
     * ,* Slår sammen alle elementene i listen ved hjelp av en
     * ,* kombinasjonsfunksjon.
     * ,*
     * ,* @param t Det første elementet i sammenslåingen
     * ,* @param accum Funksjonen som holder styr på de
     * ,* sammenslåtte elementene
     * ,* @param combiner funksjonen som slår sammen to
     * ,* elementer
     * ,* @return Den akkumulerte verdien av sammenslåingene
     * ,
     *
     * @param t
     * @param f
     */
    @Override
    public <T> T reduce(T t, BiFunction<T, ? super E, T> f) {
        return null;
    }

    /**
     * ,* Gir størrelsen på listen
     * ,*
     * ,* @return Størrelsen på listen
     * ,
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * ,* Fjerner alle elementene i listen.
     * ,
     */
    @Override
    public void clear() {

    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {

                return false;
            }

            @Override
            public E next() {
                return null;
            }
        };
    }
}
