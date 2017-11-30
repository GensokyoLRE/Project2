package edu.gcccd.csis;

import java.io.*;
import java.util.Iterator;
import java.util.Random;

/**
 * Use the starter code, including the NodeList class, our implementation of a BasicList.
 * <p>
 * We are going to use a very simple lists to store positive long numbers, one list element per digit.
 * The most significant digit is stored in the head element, the least significant digit is stored in the tail.
 * <p>
 * The starter code's main method creates very long numbers.
 * It is your task, to complete the class so that it can calculate the sum of positive very long numbers and
 * store the result in a file.
 * <p>
 * Of course, all methods need to have unit-tests to verify corner cases and happy-paths.
 * For that you may find the java.math.BigInteger class help-full when writing the unit-tests.
 * In the test code you are free to use java classes from all packages.
 * In the implementation of the Project2 class however, you are limited to
 * <p>
 * import java.io.*;
 * import java.util.Iterator;
 * import java.util.Random;
 * Moreover, you need to provide a detailed estimate for how often on average ANY iterator's next() method gets called
 * (depending on the value of L) when addition(Iterator&lt;NodeList&lt;Integer&gt;&gt; iterator) gets called.
 */
public class Project2 {

    static NodeList<Integer> generateNumber(final int maxLength) {
        final NodeList<Integer> nodeList = new NodeList<>();
        final int len = 1 + new Random().nextInt(maxLength);
        for (int i = 0; i < len; i++) {
            nodeList.append(new Random().nextInt(10));
        }
        System.out.print("Generated Number: ");
        print(nodeList);
        return nodeList;
    }

    /**
     * Prints a very long number to System.out
     *
     * @param nodeList NodeList<Integer>
     */
    static void print(final NodeList<Integer> nodeList) {
        for (final Integer i : nodeList) {
            System.out.print(i);
        }
        System.out.println();
    }

    public static void main(final String[] args) {
        final int L = 30; // original: 30

        final NodeList<Integer> n1 = generateNumber(L); // (head 1st) e.g. 3457
        final NodeList<Integer> n2 = generateNumber(L); // (head 1st) e.g. 682

        final Project2 project = new Project2();

        print(project.addition(n1, n2)); //  n1+n2, e.g. 4139

        final NodeList<NodeList<Integer>> listOfLists = new NodeList<>();
        for (int i = 0; i < L; i++) {
            listOfLists.append(generateNumber(L));
        }
        project.save(project.addition(listOfLists.iterator()), "result.bin");
        print(project.load("result.bin"));
    }

    /**
     * Add two very long numbers
     *
     * @param nodeList1 NodeList&lt;Integer&gt;
     * @param nodeList2 NodeList&lt;Integer&gt;
     * @return nodeList representing the sum (add) of nodeList1 and nodeList2, without leading '0'
     */
    public NodeList<Integer> addition(NodeList<Integer> nodeList1, NodeList<Integer> nodeList2) {
        NodeList<Integer> pass;
        NodeList<Integer> temp1 = new NodeList<>();
        NodeList<Integer> temp2 = new NodeList<>();
        Iterator itr1 = nodeList1.iterator();
        Iterator itr2 = nodeList2.iterator();

        for(int a = nodeList1.getLength()-1; a >= 0; a--){
            if (itr1.hasNext()){
                temp1.append((Integer)itr1.next());
                nodeList1.remove(a);
            }
        }
        //puts nodeList1 backwards into temp1

        for(int b = nodeList2.getLength()-1; b >= 0; b--){
            if (itr2.hasNext()){
                temp2.append((Integer)itr2.next());
                nodeList2.remove(b);
            }
        }
        //puts nodeList2 backwards into temp2


        pass = sum(temp1,temp2);
        //if nodeList1 length > nodeList2 length, do add with nodeList1 on top, do with nodeList2 on top otherwise



        return pass;
    }

    public NodeList<Integer> sum(NodeList<Integer> nList1, NodeList<Integer> nList2){
        NodeList <Integer> rList1 = new NodeList<>();
        NodeList <Integer> rList2 = new NodeList<>();
        NodeList<Integer> tempList1 = new NodeList<>();
        NodeList<Integer> tempList2 = new NodeList<>();
        boolean foundHead = false;
        Iterator itrNL1 = nList1.iterator();
        Iterator itrNL2 = nList2.iterator();
        while (itrNL1.hasNext()){
            if ((Integer)itrNL1.next() != 0 && !foundHead){
                foundHead = true;
                for (Integer k : nList1){
                    tempList1.append(k);
                }
            }
        }
        nList1 = reverse(nList1, rList1, nList1.getLength());
        System.out.println("\nnList1 reverse came out as: ");
        for (Integer i: nList1){
            System.out.print(i);
        }
        while (itrNL2.hasNext()){
            if ((Integer)itrNL2.next() != 0 && !foundHead){
                foundHead = true;
                for (Integer l : nList2){
                    tempList2.append(l);
                }
            }
        }
        nList2 = reverse(nList2, rList2, nList2.getLength());
        System.out.println("\nnList2 reverse came out as: ");
        for (Integer j : nList2){
            System.out.print(j);
        }
        System.out.println("\nSum of 2 numbers is: ");
        Iterator sumThingTop = nList2.iterator();
        Iterator sumThingBot = nList1.iterator();
        NodeList<Integer> sum = new NodeList<>();
        int carry = 0;
        int counter = 0;

        if (nList1.getLength() > nList2.getLength()){
            sumThingTop = nList1.iterator();
            sumThingBot = nList2.iterator();
            for (final Integer i : nList1){
                counter += 1;
                if (sumThingBot.hasNext()){
                    int adding = (Integer)sumThingTop.next() + (Integer)sumThingBot.next();
                    if (counter > 1){
                        if (adding >= 10){
                            carry += 1;
                            adding -= 10;
                        }
                    }
                    sum.append(adding + carry);
                }
                else if (sumThingTop.hasNext()){
                    sum.append(i + carry);
                }
                carry = 0;
            }
        } else if (nList2.getLength() > nList1.getLength() || nList1.getLength() == nList2.getLength()) {
            for (final Integer j : nList2) {
                counter += 1;
                if (sumThingBot.hasNext()) {
                    int adding = (Integer) sumThingTop.next() + (Integer) sumThingBot.next();
                    if (counter > 1) {
                        if (adding >= 10) {
                            carry += 1;
                            adding -= 10;
                        }
                    }
                    sum.append(adding + carry);
                } else if (sumThingTop.hasNext()) {
                    sum.append(j + carry);
                }
            }
        }
        NodeList<Integer> sumRev = new NodeList<>();
        sum = reverse(sum,sumRev,sum.getLength());
        print(sum);

        return sum;
    }
    //NodeList<NodeList<Integer>> listOfLists = new NodeList<>



    private NodeList<Integer> reverse(NodeList<Integer> rList, NodeList<Integer> reverseNL, final long maxLength) {
        Iterator iterRL = rList.iterator();
        if (iterRL.hasNext() && reverseNL.getLength() <= maxLength){
            if (maxLength == 1){
                return rList;
            } else {
                final int i = (Integer)iterRL.next();
                rList.remove(i);
                reverseNL.prepend(i);
                reverse(rList, reverseNL, maxLength);
            }
        }
        return reverseNL;
    }
    //PUT THAT THANG DOWN FLIP IT N REVERSE IT

    /**
     * Add very long numbers
     *
     * @param iterator NodeList&lt;Integer&gt;
     * @return nodeList representing the sum (add) of all very long numbers, without leading '0'
     */
    public NodeList<Integer> addition(Iterator<NodeList<Integer>> iterator) {
        NodeList<Integer> add = iterator.hasNext() ? iterator.next() : new NodeList<>();
        while (iterator.hasNext()){
            add = addition(add,iterator.next());
        }
        return add;
    }

    /**
     * Saves a very large number as a file
     *
     * @param nodeList NodeList&lt;Integer&gt;
     * @param fileName String
     */
    public void save(NodeList<Integer> nodeList, String fileName) {
//        try {
//            final DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File (fileName))));
//            for (Integer i : nodeList){
//                dos.writeInt(i);
//            }
//            dos.close();
//        } catch (IOException ioe){
//            System.err.println(ioe.getMessage());
//        }
    }

    /**
     * Loads a very large number from a file
     *
     * @param fileName String
     * @return NodeList&lt;Integer&gt;
     */
    public NodeList<Integer> load(final String fileName) {
        return new NodeList<>();
    }
}