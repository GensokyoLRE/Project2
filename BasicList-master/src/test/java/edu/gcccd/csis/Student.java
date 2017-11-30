package edu.gcccd.csis;

public class Student  {
    final int id;
    final String name;
    String email;

    public Student(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj!=null && obj instanceof Student && this.id==((Student)obj).id);
    }

    @Override
    public String toString() {
        return String.format("%d : %s : %s",id,name,email);
    }
}
