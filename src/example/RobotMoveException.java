package example;

public class RobotMoveException extends Exception{

    private final RobotMap.Robot robot;

    /**
     * Ошибка движения робота
     * @param message - Не удалось сдвинуть робота
     * @param robot - вызов робота
     */
    public RobotMoveException(String message, RobotMap.Robot robot) {
        super(message);
        this.robot = robot;
    }

    /**
     * Вызов робота
     * @return - id робота и его положение
     */
    public RobotMap.Robot getRobot() {
        return robot;

    }
}
