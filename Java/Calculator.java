import java.util.Scanner;

public class Calculator {
    
    public double add(double a, double b) {
        return a + b;
    }

   
    public double subtract(double a, double b) {
        return a - b;
    }

    
    public double multiply(double a, double b) {
        return a * b;
    }

   
    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("0으로 나눌수 없습니다.");
        }
        return a / b;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("계산기 입니다!");
        System.out.print("첫 번째 수를 입력하세요: ");
        double num1 = scanner.nextDouble();
        
        System.out.print("두번째 수를 입력하세요: ");
        double num2 = scanner.nextDouble();
        
        System.out.print("연산자를 선택하세요 (+, -, *, /): ");
        String operation = scanner.next();
        
        double result = 0;
        boolean validOperation = true;
        
        switch (operation.toLowerCase()) {
            case "+":
                result = calculator.add(num1, num2);
                break;
            case "-":
                result = calculator.subtract(num1, num2);
                break;
            case "*":
                result = calculator.multiply(num1, num2);
                break;
            case "/10":
                try {
                    result = calculator.divide(num1, num2);
                } catch (ArithmeticException e) {
                    System.out.println(e.getMessage());
                    validOperation = false;
                }
                break;
            default:
                System.out.println("잘못된 연산자입니다.");
                validOperation = false;
        }
        
        if (validOperation) {
            System.out.println("값은: " + result);
        }
        
        scanner.close();
    }
}
