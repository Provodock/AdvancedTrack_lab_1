import java.util.*;
import java.util.stream.Collectors;

/**Утилітний клас для аналізу цін на автомобілі та виявлення цінових аномалій.
 * Використовує метод міжквартильного розмаху (IQR) для виявлення викидів.
 */
public class PriceAnalysis {

    /**Аналізує ціни автомобілів для виявлення нормальних даних та викидів.
     * Викиди визначаються за формулою:
     * - нижня межа = Q1 - 1.5 * IQR
     * - верхня межа = Q3 + 1.5 * IQR
     * де Q1 - перший квартиль, Q3 - третій квартиль, IQR - міжквартильний розмах
     */
    public static Map<String, Long> analyzePrices(List<Car> cars) {
        // Отримуємо відсортований список цін
        List<Integer> prices = cars.stream()
                .map(Car::getPrice)
                .sorted()
                .toList();

        // Розрахунок розмаху
        double Q1 = prices.get((int) Math.ceil(0.25 * prices.size()) - 1);
        double Q3 = prices.get((int) Math.ceil(0.75 * prices.size()) - 1);
        double IQR = Q3 - Q1;

        // Розділяємо дані на нормальні значення та викиди
        Map<Boolean, Long> result = cars.stream()
                .collect(Collectors.partitioningBy(
                        car -> car.getPrice() >= Q1 - 1.5 * IQR && car.getPrice() <= Q3 + 1.5 * IQR,
                        Collectors.counting()
                ));

        // Форматуємо результат
        Map<String, Long> finalResult = new HashMap<>();
        finalResult.put("data", result.get(true));
        finalResult.put("outliers", result.get(false));

        return finalResult;
    }
}