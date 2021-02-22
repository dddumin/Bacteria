package model;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

import static java.lang.Math.random;

public class Field {
    private Bacteria[][] field;

    public Field(int N) {
        this.field = new Bacteria[N][N];
    }

    public int[] run(int K1, int K2, double P1, double P2) {
        int N = this.field.length;
        this.field = new Bacteria[N][N];
        int countDay = 1;
        int countDeaths = 0;
        int a = 0;
        while (a <= N * N - 1) {
            if (countDay <= 7) {
                int k = K1 + (int) (random() * (K2 + 1));
                if (a + k >= N * N - 1)
                    k = N * N - 1 - a;
                for (int i = 0; i < k; ) {
                    int position = (int) (Math.random() * (N * N));
                    int line = position / N;
                    int column = position % N;
                    if (this.field[line][column] == null) {
                        this.field[line][column] = new Bacteria(countDay);
                        a++;
                        i++;
                    }
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (this.field[i][j] != null) {
                        if (this.field[i][j].isLife() && this.field[i][j].needReproduce(countDay)) {
                            ArrayList<Map.Entry<Integer, Integer>> neighbors = Field.neighbors(N, i, j);
                            for (Map.Entry<Integer, Integer> entry : neighbors) {
                                if (this.field[entry.getKey()][entry.getValue()] == null){
                                    if (Math.random() <= P1){
                                        this.field[entry.getKey()][entry.getValue()] = new Bacteria(countDay);
                                        a++;
                                    }
                                }
                            }
                        }
                        if (this.field[i][j].isLife() && this.field[i][j].needDeath(countDay)){
                            if (Math.random() <= P2){
                                this.field[i][j].setLife(false);
                                countDeaths++;
                            }
                        }
                    }
                }
            }
            countDay++;
            if (countDay > 7 && countDeaths >= a)
                return null;
        }
        return new int[]{countDay, countDeaths};
    }


    private static ArrayList<Map.Entry<Integer, Integer>> neighbors(int N, int line, int column) {
        int[] massI = new int[]{line - 1, line, line + 1};
        int[] massJ = new int[]{column - 1, column, column + 1};
        ArrayList<Map.Entry<Integer, Integer>> neighbors = new ArrayList<>();
        for (int i = 0; i < massI.length; i++) {
            if (massI[i] < 0 || massI[i] >= N)
                continue;
            for (int j = 0; j < massJ.length; j++) {
                if (massI[i] == line && massJ[j] == column)
                    continue;
                if (massJ[j] < 0 || massJ[j] >= N)
                    continue;
                neighbors.add(new AbstractMap.SimpleEntry<>(massI[i], massJ[j]));
            }
        }
        return neighbors;
    }

    public static int[] average(int count, int N, int K1, int K2, double P1, double P2) {
        int a = 0;
        int b = 0;
        for (int i = 0; i < count; i++) {
            Field field = new Field(N);
            int[] result = field.run(K1, K2, P1, P2);
            if (result != null){
                a += result[0];
                b += result[1];
            }
        }
        return new int[]{a / count, b / count};
    }


}
