
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * {@link ArrayList} is an implementation of {@link List} interface. This resizable data structure
 * based on an array and is simplified version of {@link java.util.ArrayList}.
 */
public static void main(String[]args){

        }
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 5;
    private Object[] elements;
    private int size;
    /**
     * This constructor creates an instance of {@link ArrayList} with a specific capacity of an array inside.
     *
     * @param initCapacity - the initial capacity of the list
     * @throws IllegalArgumentException – if the specified initial capacity is negative or 0.
     */
    public ArrayList(int initCapacity) {
        if (initCapacity <= 0) {
            throw new IllegalArgumentException();
        } else {
            elements = new Object[initCapacity];
        }
    }

    /**
     * This constructor creates an instance of {@link ArrayList} with a default capacity of an array inside.
     * A default size of inner array is 5;
     */
    public ArrayList() {
//         this(DEFAULT_CAPACITY);
         elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Creates and returns an instance of {@link ArrayList} with provided elements
     *
     * @param elements to add
     * @return new instance
     */

    public static <T> List<T> of(T... elements) {
        List<T> list = new ArrayList<>();
        Stream.of(elements).forEach(list::add);
        return list;
    }

    /**
     * Adds an element to the array.
     *
     * @param element element to add
     */
////////////////////////////////////////////////////////////////////////
    @Override
    public void add(T element) {
            if (elements.length == size) {
                resizeIfNeeded();
            }
        elements[size] = element;
        size++;
    }
    private void resizeIfNeeded() {
                Object[] nawArray = new Object[elements.length * 2];
                System.arraycopy(elements, 0, nawArray, 0, size);
                elements = nawArray;
        }

    /**
     * Adds an element to the specific position in the array where
     *
     * @param index   index of position
     * @param element element to add
     */
////////////////////////////////////////////////////////////////////
    @Override
    public void add(int index, T element) {
        Objects.checkIndex(index,size+1);
        resizeIfNeeded();
        System.arraycopy(elements, index, elements, index+1, size-index);
        elements[index] = element;
        size++;
    }

    /**
     * Retrieves an element by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index index of element
     * @return en element
     */
///////////////////////////////////////////////////////////////////
    @Override
    @SuppressWarnings("uncheked")
    public T get(int index) {
//        Objects.checkIndex(index, size);
//        return (T) elements[index];
        if (index >= 0 && index < size) {
            if (elements[index] == null) {
                throw new NoSuchElementException();
            }
            return (T) elements[index];
        } else {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
    }
    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
/////////////////////////////////////////////////////////////////
    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return get(0);
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
/////////////////////////////////////////////////////////////////
    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return get(size-1);
    }

    /**
     * Changes the value of array at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   position of value
     * @param element a new value
     */
/////////////////////////////////////////////////////////////////
    @Override
    public void set(int index, T element) {
//        Objects.checkIndex(index, size);
//        elements[index] = element;
        if (index >= 0 && index < size) {
            elements[index] = element;
        } else {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
/////////////////////////////////////////////////////////////////
    @Override
    @SuppressWarnings("uncheked")
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElement;
        
/////////////////////////////////////???
      /*try{
            T[] temp = (T[])elements;
            elements = (T[]) new Object[temp.length - 1];
            System.arraycopy(temp,0,elements,0,index);
            int amountElemAfterIndex = temp.length - index - 1;
            System.arraycopy(temp,index+1,elements,index,amountElemAfterIndex);
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
        T removedElement = (T) elements[index];
        return removedElement;*/
//////////////////////////////////???
    }

    /**
     * Checks for existing of a specific element in the list.
     *
     * @param element is element
     * @return If element exists method returns true, otherwise it returns false
     */
/////////////////////////////////////////////////////////////////
    @Override
    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
/////////////////////////////////////////////////////////////////
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return amount of saved elements
     */
/////////////////////////////////////////////////////////////////
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
       /* size = 0;
        elements = new Object[DEFAULT_CAPACITY];*/
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }
}
///////////////////////////////////////////////////////////////


public interface List<T> {
    void add(T element);

    void add(int index, T element);

    void set(int index, T element);

    T get(int index);

    T getFirst();

    T getLast();

    T remove(int index);

    boolean contains(T element);

    boolean isEmpty();

    int size();

    void clear();
}
