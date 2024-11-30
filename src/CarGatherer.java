import java.util.stream.Stream;

/**Утилітний клас для фільтрації автомобілів за брендом.
 * Дозволяє пропускати певну кількість автомобілів конкретного бренду.
 */
public class CarGatherer {
    private final String brandToSkip;
    private final int skipCount;

    public CarGatherer(int skipCount, String brandToSkip) {
        this.skipCount = skipCount;
        this.brandToSkip = brandToSkip;
    }

    /**Фільтрує потік автомобілів, виключаючи вказаний бренд
     * та обмежуючи кількість результатів
     */
    public Stream<Car> filterCars(Stream<Car> cars) {
        return cars.filter(car -> !car.getBrand().equals(brandToSkip))
                .limit(skipCount);
    }
}