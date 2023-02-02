package example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 0. Добавить проверку входных данных в конструкторе RobotMap
        // 1. Доработать класс карты: запретить одновременное нахождение какого-то количества робов на карте.
        //    Ограничение в 10 роботов.
        // 2. Задать возможность вызова #move на заданное количество единиц.
        //    Метод без аргументов перемещает на 1 единицу.
        // 3*. Реализовать пользовательское взаимодействие с картой через консоль.
        // 3.1 Через консоль спрашивать параметры создания карты
        // 3.2 Через консоль спрашивать создания робота или его передвижение
        //     Например, move 17489123641-84-1o1-321 TOP 10
        // 3.3 Любые другие взаимодействия пользователя с картой...
        // 4*. Попробовать реализовать какую-то свою структуру (Автомобиль)


//        Scanner scanner = new Scanner(System.in);
//        RobotMap map = null;
//
//        System.out.println("введите размер поля: (2 числа черерз пробел)");
//        int n = scanner.nextInt();
//        int m = scanner.nextInt();
//        try {
//            map = new RobotMap(n, m);
//        } catch (IllegalArgumentException e) {
//            System.err.println("Не удалось создать карту: " + e.getMassage());
//            System.out.println("Попробуйте еще раз!");
//        }

        RobotMap map = new RobotMap(100, 100, 15);

        try {
            RobotMap.Robot robot1 = map.createRobot(new Point(-3, 5));
        } catch (RobotCreationException e) {
            System.err.println("Не удалось создать робота!");
        }
        try {
            RobotMap.Robot robot2 = map.createRobot(new Point(4, 5));
            robot2.changeDirection(Direction.BOTTOM);
            robot2.move();
            System.out.println(robot2);
        } catch (RobotCreationException e) {
            System.err.println("Не удалось создать робота!");
        } catch (RobotMoveException e) {
            System.err.println("Не удалось сдвинуть робота" + e.getRobot() + " с места!");
        }
        try {
            RobotMap.Robot robot = map.createRobot(new Point(100, 100));
            robot.changeDirection(Direction.BOTTOM);
            robot.move();
            System.out.println(robot);
        } catch (RobotCreationException e) {
            System.err.println("Не удалось создать робота!");
        } catch (RobotMoveException e) {
            System.err.println("Не удалось сдвинуть робота" + e.getRobot() + " с места!");
        }
    }
}
