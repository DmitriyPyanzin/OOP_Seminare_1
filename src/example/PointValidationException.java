package example;

public class PointValidationException extends Exception {

    /**
     * Ошибка перемецения робота на занятую точку
     * @param message - недопустимое значение или точка занята
     */
    public PointValidationException(String message) {
        super(message);

    }
}
