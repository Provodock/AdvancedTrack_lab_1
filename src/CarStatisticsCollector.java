import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collector;

/**Колектор для обчислення базової статистики цін на автомобілі.
 * Реалізує інтерфейс Collector для використання зі Stream API.
 * Обчислює: мінімальну, максимальну, середню ціну та стандартне відхилення.
 */
public class CarStatisticsCollector implements Collector<Car, List<Integer>, CarStatistics> {

    //Створює початковий накопичувач
    public Supplier<List<Integer>> supplier() {
        return ArrayList::new;
    }

    //Додає ціну автомобіля до накопичувача
    public BiConsumer<List<Integer>, Car> accumulator() {
        return (accumulator, item) -> accumulator.add(item.getPrice());
    }

    //Об'єднує два накопичувача при паралельній обробці
    public BinaryOperator<List<Integer>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    //Обчислює фінальну статистику з накопичених цін
    public Function<List<Integer>, CarStatistics> finisher() {
        return prices -> {
            int min = prices.stream().min(Integer::compare).orElse(0);
            int max = prices.stream().max(Integer::compare).orElse(0);
            double avg = prices.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
            // Обчислення стандартного відхилення
            double dev = Math.sqrt(prices.stream()
                    .mapToDouble(price -> Math.pow(price - avg, 2))
                    .average().orElse(0.0));
            return new CarStatistics(min, max, avg, dev);
        };
    }

    public Set<Characteristics> characteristics() {
        Set<Characteristics> characteristics = new HashSet<>();
        characteristics.add(Characteristics.CONCURRENT);
        return characteristics;
    }
}
