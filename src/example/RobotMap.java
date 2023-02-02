package example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RobotMap {

    private final int n;
    private final int m;
    private final int maxRobotCount;

    private final List<Robot> robots;

    /**
     * Вызов списка
     * @return - возврат списка действующих роботов
     */
    public List<Robot> getRobots() {
        return robots;
    }

    /**
     * Конструктор создания карты по умолчанию
     * @param n - длина
     * @param m - ширина
     */
    public RobotMap(int n, int m) throws RobotMapException{
        this(n, m, 10);

    }

    /**
     * Конструктор создание карты
     * @param n - длина
     * @param m - ширина
     */
    public RobotMap(int n, int m, int maxRobotCount) throws RobotMapException{
        // TODO: 13.01.2023 Реализовать проверку входных данных
        if(n < 10 || m < 10 || n > 100 || m > 100)
            throw new RobotMapException("Некоректные координаты для поля");
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
        if (robots.size() > maxRobotCount) {
            throw new RobotCreationException("Достигнуто максимальное количество роботов");
        }

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

    /**
     * Создание изменяемого списка с именами роботов
     * @return - возврат списка имен
     */
    public ArrayList <String> listNameOfRobot() {
        ArrayList<String> listNameOfRobot = new ArrayList<>();
        listNameOfRobot.add("T-1000 ");
        listNameOfRobot.add("T-800 ");
        listNameOfRobot.add("ВАЛЛИ-И ");
        listNameOfRobot.add("Бамблби ");
        listNameOfRobot.add("Робокоп ");
        listNameOfRobot.add("R2D2 ");
        listNameOfRobot.add("C3PO ");
        listNameOfRobot.add("Чаппи ");
        listNameOfRobot.add("Бэндер ");
        listNameOfRobot.add("Конор из компании Киберлайф ");

        return listNameOfRobot;
    }

    /**
     * Имя для робота
     * @param listName - Список имен
     * @return - имя
     */
    public String nameOfRobots(ArrayList<String> listName){
        Random random = new Random();
        int num = random.nextInt(0, listName.size() - 1);
        return listName.get(num);
    }


    public class Robot {
        public static final Direction DEFAULT_DIRECTION = Direction.TOP;

        private final String nameOfRobot;

        private MapPoint point;
        private Direction direction;

        /**
         * Параметры робота
         * @param point - координаты
         */
        public Robot(MapPoint point) {
            this.point = point;
            this.direction = DEFAULT_DIRECTION;
            this.nameOfRobot = nameOfRobots(listNameOfRobot());
        }

        /**
         * движение робота на заданное количество клеток
         * @throws RobotMoveException - Ошибка движения робота
         */
        public void move(int motion) throws RobotMoveException{
            final MapPoint newPoint;
            try {
                newPoint = switch (direction) {
                    case TOP -> new MapPoint(point.getX() + motion, point.getY());
                    case BOTTOM -> new MapPoint(point.getX() - motion, point.getY());
                    case RIGHT -> new MapPoint(point.getX(), point.getY() + motion);
                    case LEFT -> new MapPoint(point.getX(), point.getY() - motion);
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
            return "demo.Robot-" + nameOfRobot + point;

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
                throw new PointValidationException("Недопустимое значение координат: " + this);
            }
        }


    }
}
