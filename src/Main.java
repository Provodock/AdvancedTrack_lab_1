import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**Головний клас програми, який демонструє:
 * - фільтрацію автомобілів
 * - групування за класами
 * - статистичний аналіз
 * - аналіз цін та виявлення викидів
 */
public class Main {
    public static void main(String[] args) {
        int N = 10;
        String brandToSkip = "Toyota";
        long maxMonthsOld = 60;

        // Генерація та фільтрація списку автомобілів:
        // - виключає вказаний бренд (Toyota)
        // - обмежує вибірку до 500 автомобілів
        List<Car> cars = CarGenerator.generateCars()
                .filter(car -> !car.getBrand().equals(brandToSkip))
                .limit(500)
                .collect(Collectors.toList());

        // Виведення всіх автомобілів
        for (Car car : cars) {
            System.out.println(car);
        }

        // Групування автомобілів за класом з додатковою фільтрацією за віком
        Map<String, List<Car>> groupedCars = cars.stream()
                .filter(car -> car.getMonthsSinceManufacturing() <= maxMonthsOld)
                .collect(Collectors.groupingBy(Car::getCarClass));

        // Виведення кількості автомобілів у кожному класі
        for (Map.Entry<String, List<Car>> entry : groupedCars.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().size());
        }

        // Розрахунок статистики по цінах автомобілів
        CarStatistics statistics = cars.stream()
                .collect(new CarStatisticsCollector());
        System.out.println(statistics);

        Map<String, Long> priceAnalysis = PriceAnalysis.analyzePrices(cars);
        System.out.println(priceAnalysis);
    }
}