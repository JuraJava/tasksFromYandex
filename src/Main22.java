/**
 * 22. Из тупика в тупик

 * Условие:

 * Каждый вечер после работы Вася приходит домой и думает о жизни. Вася описал свои перспективы в виде n жизненных
 * ситуаций и соединил их n−1 двунаправленными переходами так, что между двумя любыми жизненными ситуациями существовал
 * ровно один простой путь, возможно через промежуточные ситуации. Жизненным тупиком называется жизненная ситуация, из
 * которой существует ровно один переход.
 * Помогите Васе найти самый короткий путь из одного жизненного тупика в другой.

 * Формат ввода
 * В первой строке вводится целое число n — число жизненных ситуаций (2≤n≤100000).
 * В следующих n−1 строках заданы по два числа ai, bi— номера жизненных ситуаций, между которыми возможен переход (1≤ai,bi≤n).
 * Гарантируется, что между любыми двумя жизненными ситуациями существует ровно один простой путь.

 * Формат вывода
 * Выведите одно число — минимальное количество переходов, которое нужно совершить Васе чтобы попасть из одного жизненного тупика в другой.

 * Ограничение времени
 * 2 с
 * Ограничение памяти
 * 256 МБ

 * Пример 1
 * Ввод
 * 5
 * 1  2
 * 1  3
 * 2  4
 * 2  5
 * Вывод
 * 2
 * Пример 2
 * Ввод
 * 5
 * 1  3
 * 2  1
 * 4  5
 * 5  3
 * Вывод
 * 4
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main22 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        // Список смежности
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        // Находим все листья (степень 1)
        List<Integer> leaves = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (graph[i].size() == 1) {
                leaves.add(i);
            }
        }

        int[] color = new int[n + 1];   // цвет (идентификатор источника)
        int[] dist = new int[n + 1];    // расстояние до источника
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        // Инициализация: все листья в очереди, каждый со своим цветом
        for (int leaf : leaves) {
            color[leaf] = leaf;   // уникальный цвет
            dist[leaf] = 0;
            queue.add(leaf);
        }

        // Многоисточниковый BFS
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : graph[u]) {
                if (color[v] == 0) {
                    // Вершина не посещена – помечаем цветом текущего источника
                    color[v] = color[u];
                    dist[v] = dist[u] + 1;
                    queue.add(v);
                } else if (color[v] != color[u]) {
                    // Встреча двух разных цветов – нашли минимальное расстояние
                    int ans = dist[u] + dist[v] + 1;
                    System.out.println(ans);
                    return;
                }
            }
        }
    }
}
