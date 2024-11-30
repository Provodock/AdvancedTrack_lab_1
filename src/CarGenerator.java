import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.stream.Stream;
import java.util.Map;

/**Генератор випадкових об'єктів Car з реалістичними цінами в гривнях.
 * Використовує Stream API для генерації нескінченного потоку автомобілів.
 */
public class CarGenerator {
    // Константи для генерації даних
    private static final String[] BRANDS = {"Toyota", "BMW", "Mercedes", "Audi", "Volkswagen"};
    private static final String[] CLASSES = {"Economy", "Business", "Premium", "Luxury", "Sport"};

    //Базові ціни в гривнях для кожного класу автомобілів
    private static final Map<String, Integer> BASE_PRICES = Map.of(
            "Economy", 300000,
            "Business", 800000,
            "Premium", 1200000,
            "Luxury", 2000000,
            "Sport", 1500000
    );

    /**Генерує нескінченний потік випадкових автомобілів з реалістичними цінами.
     * Ціна розраховується на основі:
     * - базової ціни для класу автомобіля
     * - випадкової варіації ±20%
     * - знижки за вік автомобіля (до 50% для найстаріших)
     */
    public static Stream<Car> generateCars() {
        Random random = new Random();

        return Stream.generate(() -> {
            // Вибір випадкового класу та базової ціни
            String carClass = CLASSES[random.nextInt(CLASSES.length)];
            int basePrice = BASE_PRICES.get(carClass);

            // Додавання випадкової варіації до базової ціни (±20%)
            double priceMultiplier = 0.8 + (random.nextDouble() * 0.4);
            int finalPrice = (int)(basePrice * priceMultiplier);

            // Розрахунок знижки за вік
            LocalDate manufacturingDate = LocalDate.now().minusMonths(random.nextInt(240));
            long monthsOld = ChronoUnit.MONTHS.between(manufacturingDate, LocalDate.now());
            double ageDiscount = Math.max(0.5, 1.0 - (monthsOld * 0.005));

            finalPrice = (int)(finalPrice * ageDiscount);

            return new Car(
                    BRANDS[random.nextInt(BRANDS.length)],
                    carClass,
                    manufacturingDate,
                    finalPrice
            );
        });
    }
}