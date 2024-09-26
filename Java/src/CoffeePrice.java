import java.util.Scanner;

public class CoffeePrice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("무슨 커피 드릴까요? ");
        String order = scanner.next().trim().toLowerCase(); // 입력값을 소문자로 변환하고, 공백 제거
        int price = 0;

        switch (order) {
            case "에스프레소":
            case "카푸치노":
            case "cafelatte":  // 여기는 "카페라때"로 되어 있어 오타일 가능성이 있습니다.
                price = 3500;
                break;
            case "아메리카노":
                price = 2000;
                break;
            default:
                System.out.println("메뉴에 없습니다.");
        }

        if(price != 0) {
            System.out.print(order + "는 " + price + "원입니다.");
        }

        scanner.close();
    }
}
