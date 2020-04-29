package app;

import java.util.Arrays;
import java.util.Scanner;

public class App {
    /**
     * Matrix2D
     * @String
     */
    static String[][] matrix2D;

    /**
     * Mảng chứa tạo độ của bom
     * {
     *  {x, y},
     * }
     */
    static int[][] matrix_bom;
    static int WIDTH;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập vào độ dài ma trận (6 = 6×6, 9 = 9×9, ...): ");
        int size = scanner.nextInt();

        System.out.print("Nhập số lượng bom trên bản đồ: ");
        int count_boms = scanner.nextInt();
        int bomsLength = count_boms;

        WIDTH = size-1;
        // Tạo ma trận vuông
        matrix2D   = new String[size][];    // Bản đồ
        matrix_bom = new int[count_boms][]; // ma trận lưu vị trí bom trên bản đồ
        for(int i = 0; i < size; i++){
            matrix2D[i] = new String[size];
        }
        for (int i = 0; i < count_boms; i++) {
            matrix_bom[i] = new int[2];
        }

        // Đặt bom vào bản đồ
        while (bomsLength > 0) {
            int randomX = (int) Math.round(Math.random()*WIDTH);
            int randomY = (int) Math.round(Math.random()*WIDTH);
            if (matrix2D[randomX][randomY] == null) {
                // đặt bom
                matrix2D[randomX][randomY] = "*";
                // Lưu lại vị trí bom

                int[] temp = matrix_bom[count_boms - bomsLength];
                temp[0] = randomX;
                temp[1] = randomY;

                bomsLength--;
            }
        }

        // Đánh dấu . vào các ô không phải bom;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                String string = matrix2D[i][j];
                if(string == null){
                    matrix2D[i][j] = ".";
                }
                //
                System.out.print(matrix2D[i][j]+" ");
            }
            //
            System.out.print("\n");
        }

        // Đánh số các ô gần quả bom;
        for (int i = 0; i < matrix_bom.length; i++) {
            int[] data_bom = matrix_bom[i];

            if(data_bom[0] == 0 && data_bom[1] == 0){
                plusBottomRight(data_bom);
            } else if (data_bom[0] == 0 && data_bom[1] == WIDTH) {
                plusBottomLeft(data_bom);
            } else if (data_bom[0] == WIDTH && data_bom[1] == 0) {
                plusTopRight(data_bom);
            } else if (data_bom[0] == WIDTH && data_bom[1] == WIDTH){
                plusTopLeft(data_bom);
            } else if (data_bom[0] == 0) {
                plusRight(data_bom);
            } else if (data_bom[0] == WIDTH) {
                plusLeft(data_bom);
            } else if (data_bom[1] == 0){
                plusBottom(data_bom);
            } else if (data_bom[1] == WIDTH) {
                plusTop(data_bom);
            } else {
                plusAll(data_bom);
            }
        }

        // print
        System.out.print("\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix2D[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    static void plusTop(int[] bom) {
        // Top
        plus(bom[0], bom[1] - 1);
        // Top left
        plus(bom[0] - 1, bom[1] - 1);
        // Top Right
        plus(bom[0] + 1, bom[1] - 1);
        // left
        plus(bom[0] - 1, bom[1]);
        // Right
        plus(bom[0] + 1, bom[1]);
    }
    static void plusLeft(int[] bom) {
        // Top
        plus(bom[0], bom[1] - 1);
        // Top left
        plus(bom[0] - 1, bom[1] - 1);
        // left
        plus(bom[0] - 1, bom[1]);
        // Bottom
        plus(bom[0], bom[1] + 1);
        // Bottom left
        plus(bom[0] - 1, bom[1] + 1);
    }
    static void plusBottom(int[] bom) {
        // left
        plus(bom[0] - 1, bom[1]);
        // Right
        plus(bom[0] + 1, bom[1]);
        // Bottom
        plus(bom[0], bom[1] + 1);
        // Bottom left
        plus(bom[0] - 1, bom[1] + 1);
        // Bottom Right
        plus(bom[0] + 1, bom[1] + 1);
    }
    static void plusRight(int[] bom) {
        // Top
        plus(bom[0], bom[1] - 1);
        // Top Right
        plus(bom[0] + 1, bom[1] - 1);
        // Right
        plus(bom[0] + 1, bom[1]);
        // Bottom
        plus(bom[0], bom[1] + 1);
        // Bottom Right
        plus(bom[0] + 1, bom[1] + 1);
    }
    // plus all
    static void plusAll(int[] bom) {
        // Top
        plus(bom[0], bom[1]-1);
        // Top left
        plus(bom[0]-1, bom[1]-1);
        // Top Right
        plus(bom[0]+1, bom[1]-1);

        // left
        plus(bom[0]-1, bom[1]);
        // Right
        plus(bom[0]+1, bom[1]);

        // Bottom
        plus(bom[0], bom[1]+1);
        // Bottom left
        plus(bom[0]-1, bom[1]+1);
        // Bottom Right
        plus(bom[0]+1, bom[1]+1);
    }

    // Top Left
    static void plusTopLeft(int[] bom) {
        // Top
        plus(bom[0], bom[1] - 1);
        // Top left
        plus(bom[0] - 1, bom[1] - 1);
        // left
        plus(bom[0] - 1, bom[1]);
    }

    // Top Right
    static void plusTopRight(int[] bom) {
        // Top
        plus(bom[0], bom[1] - 1);
        // Top Right
        plus(bom[0] + 1, bom[1] - 1);
        // Right
        plus(bom[0] + 1, bom[1]);
    }
    //
    static void plusBottomRight(int[] bom){
        // Right
        plus(bom[0] + 1, bom[1]);
        // Bottom
        plus(bom[0], bom[1] + 1);
        // Bottom Right
        plus(bom[0] + 1, bom[1] + 1);
    }

    //
    static void plusBottomLeft(int[] bom){
        // left
        plus(bom[0] - 1, bom[1]);
        // Bottom
        plus(bom[0], bom[1] + 1);
        // Bottom left
        plus(bom[0] - 1, bom[1] + 1);
    }

    static void plus(int x, int y){
        String point = matrix2D[x][y];
        if(!point.equals("*")){
            if(point.equals(".")){
                matrix2D[x][y] = "1";
            }else{
                int a = Integer.valueOf(point);
                matrix2D[x][y] = point.valueOf(a+1);
            }
        }
    }
}
