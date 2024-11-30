//Record-клас для зберігання статистичних даних про ціни автомобілів.
public record CarStatistics(int min, int max, double avg, double stdDeviation) {
}