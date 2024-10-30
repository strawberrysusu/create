class Person {
    private int weight;
    int age;
    protected int height;
    public String name;

    public void setWeight(int weight) {
        this.weight = weight;
    }
    public int getWeight() {
        return weight;
    }
}

class Student extends Person {
    public void set(){
        age = 30;
        name = "홍길동";
        height = 175;
        //weight = 99;  private 멤버이므로 접근이 불가능함
        setWeight(99);   // setWeight() 으로 접근 가능하게 만들어 주었음
    }
}

public class InheritanceEx {
    public static void main(String[] args) {
        Student s = new Student();
        s.set();
    }
}
