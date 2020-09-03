/**
* Implementation of Random Bag which implements BAG APIs like isEmpty, size and add
* It implements random function that would iterate randomly on the items in the bag
* @author  Aditya Raghunath Sawant
* @version 1.0
* @since   08-31-2020 
*/

import java.util.*;

public class RandomBag<Item> implements Iterable<Item>{

    //Declaration of variables
    private Item[] array;
    private int size;

    /**
     * Constructor to set default values
     * Supress general type cast warnings of Object array to generic array
     */
    @SuppressWarnings("unchecked")
    public RandomBag() {
        array = (Item[]) new Object[1];
        size = 0;
    }

    /**
     * Function to check if random bag is empty
     * @return boolean of size equals 0
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Function to get size of random bag
     * @return the size of random bag
     */
    public int size() {
        return size;
    }

    /**
     * Function to add item in random bag
     * Works same as add API of BAG
     * @param item is the new item to be added in the bag
     */
    public void add(Item item) {
        //Check if size is same as length of array
        if (size() == array.length) 
        {
            //resize the length of array if it is equal to its size
            resize(array.length * 2);
        }

        //Add item at the location in the array or random bag
        array[size] = item;
        //Increase the size
        size++;
    }

    /**
     * This function increases the capacity of RandomBag to double
     * It performs deep copy of all items in the bag to the new bag
     * Supress general type cast warnings of Object array to generic array
     * @param capacity that would be the size of new array
     */
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {

        //Create new array with new capacity
        Item[] temp = (Item[]) new Object[capacity];

        //Perform deep copy of every element in the old random bag
        for(int i = 0; i < size(); i++) {
            temp[i] = array[i];
        }

        //Point the new array to old array variable
        array = temp;
    }

    /**
     * @return object of Iterator
     */
    @Override
    public Iterator<Item> iterator() {
        return new RandomBagIterator();
    }

    /**
     * Inner class that implements all the methods of Iterator interface
     * Supress general type cast warnings of Object array to generic array
     */
    @SuppressWarnings("unchecked")
    private class RandomBagIterator implements Iterator<Item> {

        ////Declare variables
        int index;
        Item[] arrayCopy;

        /**
         * Constructor of Iterator that would randomize the items in the array
         */
        public RandomBagIterator() {
            index = 0;
            arrayCopy = (Item[]) new Object[size];

            //Deep copy the elements in copy of array
            for(int i = 0; i < size; i++) {
                arrayCopy[i] = array[i];
            }

            //Call sort function to randomize array
            sortArrayCopy();
        }

        /**
         * function to check if next element exists in the random bag
         * @return boolean of index less than size
         */
        @Override
        public boolean hasNext() {
            return index < size();
        }

        /**
         * Override next function that will return the next item in RandomBag
         * @return next item in the steque
         */
        @Override
        public Item next() {
            //Get item at index from copy of array
            Item item = arrayCopy[index];
            index++;
            return item;
        }

        private void sortArrayCopy() {
            //Randomly sort array
            for(int i = 0; i < size; i++) {
                //Get random index
                int randomIndex = (int) (Math.random() * size);
                System.out.println(randomIndex);
                //int randomIndex = StdRandom.uniform(0, size - 1);
                //Perform swap with element at i and random index generated
                Item temp = arrayCopy[i];
                arrayCopy[i] = arrayCopy[randomIndex];
                arrayCopy[randomIndex] = temp;
            }
        }
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        System.out.println("1. Checking random order of items inserted in ascending order :");
        System.out.println("----------------------");
        RandomBag<Integer> randomBag1 = new RandomBag<>();
        randomBag1.add(1);
        randomBag1.add(2);
        randomBag1.add(3);
        randomBag1.add(4);
        randomBag1.add(5);


        System.out.print("Random bag items generated : ");

        Iterator itr1 = randomBag1.iterator();
        while(itr1.hasNext())
        {
            System.out.print(itr1.next().toString()+" ");
        }

        System.out.println();

        System.out.println("2. Checking random order of items inserted in descending order :");
        System.out.println("----------------------");
        RandomBag<Integer> randomBag2 = new RandomBag<>();
        randomBag2.add(5);
        randomBag2.add(4);
        randomBag2.add(3);
        randomBag2.add(2);
        randomBag2.add(1);


        System.out.print("Random bag items generated : ");

        Iterator itr2 = randomBag2.iterator();
        while(itr2.hasNext())
        {
            System.out.print(itr2.next().toString()+" ");
        }
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println();
        System.out.println("Time Elapsed is:"+(elapsedTime/1000000)+"ms");

    }

}