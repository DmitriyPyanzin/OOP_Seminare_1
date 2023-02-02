package example;

public class RobotCreationException extends Exception{

    /**
     * Ошибка создания робота
     * @param message - место занято
     */
    public RobotCreationException(String message) {
        super(message);

    }
}
