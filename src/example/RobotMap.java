package example;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RobotMap {

    private final int n;
    private final int m;
    private final int maxRobotCount;

    private final List<Robot> robots;

    public RobotMap(int n, int m) {
        this(n, m, 10);
    }

    /**
     * Конструктор создание карты
     * @param n - длина
     * @param m - ширина
     */
    public RobotMap(int n, int m, int maxRobotCount) {
        // TODO: 13.01.2023 Реализовать проверку входных данных
        this.n = n;
        this.m = m;
        this.maxRobotCount = maxRobotCount;
        this.robots = new ArrayList<>();
    }

    /**
     * Создание робота
     * @param point - местоположение
     * @return - робот создан
     * @throws RobotCreationException - место занято
     */
    public Robot createRobot(Point point) throws RobotCreationException{

        final MapPoint robotPosition;
        try {
            validatePoint(point);
            robotPosition = new MapPoint(point.getX(), point.getY());
        } catch (PointValidationException e) {
            throw new RobotCreationException(e.getMessage());
        }

        Robot robot = new Robot(robotPosition);
        robots.add(robot);
        return robot;
    }

    /**
     * Проверка свободной точки
     * @param point - точка назначения
     * @throws PointValidationException - Ошибка перемецения робота на занятую точку
     */
    private void validatePointIsFree(Point point) throws PointValidationException {
        for(Robot robot : robots) {
            if (point.equals(robot.getPoint())) {
                throw new PointValidationException("Позиция " + point + " занята другим роботом: " + robot);
            }
        }
    }

    /**
     * Валидация точки (корректность и занятость)
     * @param point - координаты
     * @throws PointValidationException - недопустимое значение или точка занята
     */
    private void validatePoint(Point point) throws PointValidationException {
        validatePointIsFree(point);
    }

    public class Robot {
        public static final Direction DEFAULT_DIRECTION = Direction.TOP;

        private final UUID id;
        private MapPoint point;
        private Direction direction;

        /**
         * Параметры робота
         * @param point - координаты
         */
        public Robot(MapPoint point) {
            this.id = UUID.randomUUID();
            this.point = point;
            this.direction = DEFAULT_DIRECTION;
        }

        /**
         * движение робота на одну клетку
         * @throws RobotMoveException - Ошибка движения робота
         */
        public void move() throws RobotMoveException{
            final MapPoint newPoint;
            try {
            newPoint = switch (direction) {
                case TOP -> new MapPoint(point.getX() - 1, point.getY());
                case BOTTOM -> new MapPoint(point.getX() + 1, point.getY());
                case RIGHT -> new MapPoint(point.getX(), point.getY() + 1);
                case LEFT -> new MapPoint(point.getX(), point.getY() - 1);
            };


                validatePoint(newPoint);
            } catch (PointValidationException e) {
                throw new RobotMoveException(e.getMessage(), this);
            }

            this.point = newPoint;
        }

        /**
         * движение робота на заданное количество клеток
         * @throws RobotMoveException - Ошибка движения робота
         */
        public void move(int x) throws RobotMoveException{
            final MapPoint newPoint;
            try {
                newPoint = switch (direction) {
                    case TOP -> new MapPoint(point.getX() - 1, point.getY());
                    case BOTTOM -> new MapPoint(point.getX() + 1, point.getY());
                    case RIGHT -> new MapPoint(point.getX(), point.getY() + 1);
                    case LEFT -> new MapPoint(point.getX(), point.getY() - 1);
                };


                validatePoint(newPoint);
            } catch (PointValidationException e) {
                throw new RobotMoveException(e.getMessage(), this);
            }

            this.point = newPoint;
        }

        /**
         * Выбор направления
         * @param direction - параметр
         */
        public void changeDirection(Direction direction) {
            this.direction = direction;

        }

        /**
         * метод получения значения поля
         * @return - возврат точки
         */
        public MapPoint getPoint() {
            return point;

        }

        /**
         * переопределение toString
         * @return - возврат робота в нужном формате (id и координаты)
         */
        @Override
        public String toString() {
            return "demo.Robot-" + id + point;

        }
    }

    public class MapPoint extends Point {

        /**
         * Конструктор определение точки Point как часть карты
         * @param x - координата X
         * @param y - координата Y
         */
        public MapPoint(int x, int y) throws PointValidationException{
            super(x, y);

            if (x < 0 || x > n || y < 0 || y > m) {
                throw new PointValidationException("Недопустимое значение Point: " + this);
            }
        }


    }
}
