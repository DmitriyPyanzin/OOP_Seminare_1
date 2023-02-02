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


        // Вводные данные
        Scanner sc = new Scanner(System.in);
        RobotMap robotMap = null;
        MyMethods textMethods = new MyMethods();
        boolean is_program = true;


        // Сама программа
        textMethods.Greetin();
        try {
            int n = sc.nextInt(), m = sc.nextInt();
            robotMap = new RobotMap(n, m);
            textMethods.finishMap();
            while(is_program) {
                boolean flag = true;
                textMethods.mainMenu();
                int answer = sc.nextInt();
                switch (answer) {
                    case 1 -> {
                        while (flag) {
                            try {
                                textMethods.textCreateRobot();
                                int x = sc.nextInt(), y = sc.nextInt();
                                RobotMap.Robot robot = robotMap.createRobot(new Point(x, y));
                                System.out.println(robot + " создан!");
                                flag = false;
                            } catch (RobotCreationException e) {
                                System.err.println(e.getMessage());
                                System.out.println("Выбери другой пункт меню!");
                                System.out.println();
                                flag = false;
                            }
                        }
                    }
                    case 2 -> {
                        if (robotMap.getRobots().size() == 0) {
                            System.out.println("Не создано не одного робота!");
                            System.out.println();
                        } else {
                            System.out.println(robotMap.getRobots());
                            System.out.println();
                        }
                    }
                    case 3 -> {
                        while (flag) {
                            textMethods.choiseMenu();
                            int num = sc.nextInt();
                            if (num == 2) {
                                flag = false;
                            } else if (num ==1) {
                                textMethods.choiseRobot();
                                System.out.println(robotMap.getRobots());
                                try {
                                    int index = sc.nextInt() - 1;
                                    System.out.println("Вы выбрали " + robotMap.getRobots().get(index));
                                    textMethods.textChoiseMove();
                                    Direction direction = Direction.valueOf(sc.next());
                                    RobotMap.Robot robot = robotMap.getRobots().get(index);
                                    robot.changeDirection(direction);
                                    textMethods.lengthStep();
                                    int lenStep = sc.nextInt();
                                    robot.move(lenStep);
                                    System.out.println("Теперь другие координаты у " + robot );
                                } catch (RobotMoveException e) {
                                    System.err.println(e.getMessage());
                                }
                            } else
                                System.out.println("Вам нужно ввести 1 или 2");
                        }
                    }
                    case 4 -> {
                        is_program = false;
                        textMethods.GoodBay();
                    }
                }
            }
        } catch (RobotMapException | RuntimeException e) {
            System.err.println(e.getMessage());

        }
    }
}
