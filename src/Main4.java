/**
 * Вася составляет отборочный контест на стажировку. Сотрудники компании подготовили  n  задач, для каждой из задач
 * определена тема, к которой она относится. Отборочный контест состоит ровно из k задач. Помогите Васе выбрать k задач
 * из n так, чтобы они покрывали как можно больше различных тем.

 * Формат ввода
 * В первой строке вводится два целых числа n и k (1≤k≤n≤100000) — количество задач, придуманных сотрудниками, и
 * количество задач в контесте. В следующей строке записано n чисел a_i (1 ≤ a_i ≤ 1000000000) — тема, к которой
 * относится задача с номером  i.

 * Формат вывода
 * Выведите ровно k целых чисел через пробел — темы задач, которые нужно взять в контест. Если правильных ответов
 * несколько — выведите любой из них.

 * Ограничение времени
 * 2 с
 * Ограничение памяти
 * 256 МБ

 * Пример 1
 * Ввод
 * 5  3
 * 1  1  1  2  2
 * Вывод
 * 1  2  1

 * Пример 2
 * Ввод
 * 10  4
 * 8  8  8  8  8  8  8  8  2  1
 * Вывод
 * 1  2  8  8
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main4 {
    public static void main(String[] args) throws IOException {
        // Создаём BufferedReader для быстрого ввода данных
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Читаем первую строку, содержащую n и k
        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]); // количество всех задач
        int k = Integer.parseInt(firstLine[1]); // количество задач в контесте

        // Читаем вторую строку с темами задач
        String[] topicsStr = reader.readLine().split(" ");
        int[] topics = new int[n];
        for (int i = 0; i < n; i++) {
            topics[i] = Integer.parseInt(topicsStr[i]); // заполняем массив тем
        }

        // HashMap для подсчёта частоты каждой темы
        Map<Integer, Integer> freq = new HashMap<>();
        for (int t : topics) {
            freq.put(t, freq.getOrDefault(t, 0) + 1); // увеличиваем счётчик для темы t
        }

        // Список для хранения выбранных тем (результат)
        List<Integer> result = new ArrayList<>();

        // Внешний счётчик i – номер текущего "слоя"
        int i = 0;
        // Пока не набрали k задач
        while (result.size() < k) {
            // Проходим по всем уникальным темам, хранящимся в ключах HashMap
            for (int t : freq.keySet()) {
                // Если уже набрали k, прерываем внутренний цикл
                if (result.size() >= k) break;
                // Если частота темы t больше текущего номера слоя i,
                // значит у этой темы ещё остались задачи, которые можно взять на этом слое
                if (freq.get(t) > i) {
                    result.add(t); // добавляем тему t в ответ
                }
            }
            i++; // переходим к следующему слою
        }

        // Формируем строку вывода: преобразуем список в массив строк и соединяем пробелами
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < result.size(); j++) {
            if (j > 0) sb.append(" ");
            sb.append(result.get(j));
        }
        System.out.println(sb.toString());
    }
}
