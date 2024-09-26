public class NestedLoop {
    public static void main(String[] args) {
        for(int i = 1; i < 10; i++){
            for(int j = 1; j < 10; j++){
                System.out.print(i + "*" + j + "=" + (i * j));
                System.out.print('\t'); // 탭으로 구분
            }
            System.out.println(); // i의 모든 곱셈 결과를 출력한 후 줄바꿈
        }
    }
}
