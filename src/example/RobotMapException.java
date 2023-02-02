package example;

public class RobotMapException extends Exception{

    /**
     * Ошибка создания карты
     * @param message - неверные параметры
     */
    public RobotMapException(String message) {
        super(message);

    }
}
