package example;

public class MaxRobotException extends Exception{

    /**
     * Ошибка превышения лимита роботов
     * @param message - много роботов
     */
    public MaxRobotException(String message) {
        super(message);
    }
}
