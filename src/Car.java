import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.text.NumberFormat;
import java.util.Locale;

//Клас, що представляє собою автомобіль з основними характеристиками
public class Car {
    //Марка автомобіля
    private String brand;

    //Клас автомобіля
    private String carClass;

    //Дата виробництва автомобіля
    private LocalDate manufacturingDate;

    //Ціна автомобіля в гривнях
    private int price;

    /**Конструктор для створення нового автомобіля
     * @param brand Марка автомобіля
     * @param carClass Клас автомобіля
     * @param manufacturingDate Дата виробництва
     * @param price Ціна в гривнях
     */
    public Car(String brand, String carClass, LocalDate manufacturingDate, int price) {
        this.brand = brand;
        this.carClass = carClass;
        this.manufacturingDate = manufacturingDate;
        this.price = price;
    }

    //Отримати марку автомобіля
    public String getBrand() {
        return brand;
    }

    //Отримати клас автомобіля
    public String getCarClass() {
        return carClass;
    }

    //Розраховує кількість місяців від дати виробництва до поточної дати
    public long getMonthsSinceManufacturing() {
        return ChronoUnit.MONTHS.between(manufacturingDate, LocalDate.now());
    }

    //Отримати ціну автомобіля
    public int getPrice() {
        return price;
    }

    /**Метод для форматованого виведення інформації про автомобіль.
     * Використовує українські ціни.
     */
    @Override
    public String toString() {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("uk", "UA"));
        return "Car{" +
                "brand='" + brand + '\'' +
                ", carClass='" + carClass + '\'' +
                ", monthsSinceManufacturing=" + getMonthsSinceManufacturing() +
                ", price=" + formatter.format(price) +
                '}';
    }
}