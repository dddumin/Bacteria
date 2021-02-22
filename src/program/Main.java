package program;

import model.Field;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Задайте размер поля:");
        int N = scanner.nextInt();
        System.out.println("Введите минимальное количество бактерий, которые могу родиться:");
        int K1 = scanner.nextInt();
        System.out.println("Введите максимальное количество бактерий, которые могу родиться:");
        int K2 = scanner.nextInt();
        System.out.println("Введите вероятность, с которой бактерия будет размножаться:");
        double P1 = scanner.nextDouble();
        System.out.println("Введите вероятность, с которой бактерия будет умирать:");
        double P2 = scanner.nextDouble();

        System.out.println(Arrays.toString(Field.average(1000, N, K1, K2, P1, P2)));

    }
}
