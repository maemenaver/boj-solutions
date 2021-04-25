import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[][] xy = new int[100001][2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                xy[i][j] = scanner.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int tmpX;
                int tmpY;
                if (xy[j + 1][0] > xy[j][0]) {
                    tmpX = xy[j + 1][0];
                    tmpY = xy[j + 1][1];

                    xy[j + 1][0] = xy[j][0];
                    xy[j + 1][1] = xy[j][1];

                    xy[j][0] = tmpX;
                    xy[j][1] = tmpY;
                } else if ((xy[j + 1][0] == xy[j][0]) && (xy[j + 1][1] > xy[j][1])) {
                    tmpX = xy[j + 1][0];
                    tmpY = xy[j + 1][1];

                    xy[j + 1][0] = xy[j][0];
                    xy[j + 1][1] = xy[j][1];

                    xy[j][0] = tmpX;
                    xy[j][1] = tmpY;
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            System.out.println(xy[i][0] + " " + xy[i][1]);
        }

        scanner.close();
    }
}
