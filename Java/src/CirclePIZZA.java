public class CirclePIZZA {
    int radius;
    String name;

    public CirclePIZZA() {}

    public double getArea() { // 메서드 이름을 getArea로 수정
        return 3.14 * radius * radius;
    }

    public static void main(String[] args) {
        CirclePIZZA pizza = new CirclePIZZA();
        pizza.radius = 10;
        pizza.name = "자바피자";
        double area = pizza.getArea(); // 여기서 호출한 메서드 이름과 맞춤
        System.out.println(pizza.name + "의 면적은 " + area);

        CirclePIZZA donut = new CirclePIZZA(); // "dount" 오타를 "donut"으로 수정
        donut.radius = 2;
        donut.name = "자바도넛";
        area = donut.getArea(); // 여기서 호출한 메서드 이름과 맞춤
        System.out.println(donut.name + "의 면적은 " + area);
    }
}
