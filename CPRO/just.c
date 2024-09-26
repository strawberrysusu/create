#include <stdio.h>

int main() {
    int i, j, n;
    
    // 사용자로부터 줄 수 입력 받기
    printf("put in the star: ");
    scanf("%d", &n);
    
    // 별 찍기
    for (i = 1; i <= n; i++) {
        // 공백 출력
        for (j = 1; j <= n - i; j++) {
            printf(" ");
        }
        // 별 출력
        for (j = 1; j <= 2 * i - 1; j++) {
            printf("*");
        }
        printf("\n");
    }
    printf("press any key to close it.....\n");
    getchar(); // 이전의 입력에서 남아있는 엔터키 처리
    getchar(); // 사용자로부터 아무 키나 입력받기
    return 0;
}
