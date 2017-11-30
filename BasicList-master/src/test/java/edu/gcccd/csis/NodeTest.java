package edu.gcccd.csis;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NodeTest {
    NodeList<Student> studentList;
    private Student s;
    private Student t;
    private Student u;

    @Before
    public void setup() {
        s = new Student(123, "Jane Smith", "js@gcccd.edu");
        t = new Student(124, "John Doe", "jd@gcccd.edu");
        u = new Student(125, "Jim Cook", "jc@gcccd.edu");
    }

    @Test
    public void testAppend() {
        studentList = new NodeList<>();

        assertTrue(0 == studentList.getLength());
        assertFalse(studentList.contains(s));

        studentList.append(s);

        assertTrue(1 == studentList.getLength());

        studentList.append(t);

        assertTrue(2 == studentList.getLength());
        assertTrue(studentList.contains(s));
        assertTrue(studentList.contains(t));
    }


    @Test
    public void testRemove() {
        studentList = new NodeList<>();
        studentList.append(s);
        studentList.append(t);
        studentList.append(u);
        studentList.remove(s); // remove 1st item

        assertTrue(2 == studentList.getLength());
        assertFalse(studentList.contains(s));
        assertTrue(studentList.contains(t));
        assertTrue(studentList.contains(u));

        studentList.append(s);
        studentList.remove(u); // remove middle item

        assertTrue(2 == studentList.getLength());
        assertFalse(studentList.contains(u));

        studentList.remove(t); // remove last item

        assertTrue(1 == studentList.getLength());
        assertFalse(studentList.contains(t));

        studentList.remove(s);

        assertTrue(0 == studentList.getLength());
    }

    @Test
    public void printAll() {
        studentList = new NodeList<>();
        studentList.append(s);
        studentList.append(t);
        studentList.append(u);
        Iterator<Student> iter = studentList.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}