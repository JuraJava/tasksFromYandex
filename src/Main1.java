/**
 * Условие:
 * Вася и Маша ходили в лес и собрали n грибов, для каждого гриба известен его вес a_i. Они выложили их в один ряд и решили делить следующим образом: первый гриб берёт Вася, второй −− Маша, третий −− Вася, четвёртый −− Маша и т.д.
 * Вася очень любит грибы и не очень любит Машу. Количество радости Васи равно разности суммарного веса грибов, доставшихся Васе, и суммарного веса грибов, доставшихся Маше.
 * Маша отвлеклась на минутку, и за это время Вася может выбрать любые два гриба и поменять их местами (а может и не менять). Определите максимальную радость Васи, которую можно достичь не более чем одним обменом.

 * Формат ввода
 * В первой строке содержится одно натуральное число n −− количество грибов (2≤n≤10^5).
 * Во второй строке содержится n чисел ai −− вес грибов (1≤ai≤1000).

 * Формат вывода
 * Выведите максимальную радость Васи.

 * Ограничение времени
 * 1 с
 * Ограничение памяти
 * 256 МБ

 * Пример 1
 * Ввод
 * 2
 * 1 2
 * Вывод
 * 1

 * Пример 2
 * Ввод
 * 3
 * 2 2 2
 * Вывод
 * 2

 * Пример 3
 * Ввод
 * 11
 * 4 10 7 5 4 5 3 8 3 2 5
 * Вывод
 * 10
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        long sum = 0;          // знакопеременная сумма
        int minOdd = Integer.MAX_VALUE;   // минимум на нечётных позициях (1,3,5,...)
        int maxEven = Integer.MIN_VALUE;  // максимум на чётных позициях (2,4,6,...)

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {         // нечётная позиция (индекс 0 → 1)
                sum += a[i];
                if (a[i] < minOdd) minOdd = a[i];
            } else {                  // чётная позиция
                sum -= a[i];
                if (a[i] > maxEven) maxEven = a[i];
            }
        }

        long diff = (long) maxEven - minOdd;
        if (diff > 0) {
            sum += 2 * diff;          // прибавляем выигрыш от лучшего обмена
        }

        System.out.println(sum);
    }
}
