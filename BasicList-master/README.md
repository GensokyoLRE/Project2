# BasicList
Use the starter code, including the NodeList class, our implementation of a BasicList.
We are going to use a very simple lists to store positive long numbers, one list element per digit. The most significant digit is stored in the head element, the least significant digit is stored in the tail.

The starter code's main method creates very long numbers. It is your task, to complete the class so that it can calculate the sum of positive very long numbers and store the result in a file.

Of course, all methods need to have unit-tests to verify corner cases and happy-paths. For that you may find the java.math.BigInteger class help-full when writing the unit-tests. In the test code you are free to use java classes from all packages. In the implementation of the Project2 class however, you are limited to

* import java.io.*; 
* import java.util.Iterator; 
* import java.util.Random; 

Moreover, you need to provide a detailed estimate for how often on average ANY iterator's next() method gets called (depending on the value of L) when addition(Iterator<NodeList<Integer>> iterator) gets called.

