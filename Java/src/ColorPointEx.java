class Point {
    private int x, y;

    // 포함 관계에서 사용될 다른 클래스 인스턴스 선언
    private Label label;

    public Point() {
        this.label = new Label();  // 포함 관계로 Label 객체 생성
    }

    // 오버로딩된 set 메서드 (두 개의 매개변수를 받는 경우)
    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // 오버로딩된 set 메서드 (세 개의 매개변수를 받는 경우)
    public void set(int x, int y, String labelText) {
        this.x = x;
        this.y = y;
        this.label.setText(labelText);  // 포함된 Label 객체에 텍스트 설정
    }

    public void showPoint() {
        System.out.println(label.getText() + "(" + x + ", " + y + ")");
    }
}

// Label 클래스는 Point 클래스 안에 포함될 클래스입니다.
class Label {
    private String text = "";

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text.isEmpty() ? "" : text + ": "; // 레이블이 비어 있지 않으면 콜론을 추가하여 표시
    }
}

class ColorPoint extends Point {
    private String color;

    public void setColor(String color) {
        this.color = color;
    }

    // 오버라이딩된 showPoint 메서드 (부모 클래스 메서드 변경)
    @Override
    public void showPoint() {
        System.out.print(color + " ");
        super.showPoint();  // 부모 클래스의 showPoint 호출
    }
}

public class ColorPointEx {
    public static void main(String[] args) {
        ColorPoint cp = new ColorPoint();
        cp.set(3, 4);
        cp.setColor("red");
        cp.showPoint();  // 오버라이딩된 메서드 호출

        System.out.println();

        Point p = new Point();
        p.set(1, 2, "Starting Point"); // 오버로딩된 set 메서드 호출
        p.showPoint();  // 기본 Point 클래스의 showPoint 호출
    }
}
