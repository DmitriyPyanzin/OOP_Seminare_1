package example;

import java.util.Objects;

public class Point {

    private final int x;
    private final int y;

    /**
     * Конструктор точки
     * @param x - координата по x
     * @param y - координата по y
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Получение значения поля
     * @return - координата x
     */
    public int getX() {
        return x;

    }

    /**
     * Получение значения поля
     * @return - координата y
     */
    public int getY() {
        return y;

    }

    /**
     * Переопределение метода
     * @return - возврат координат точки в нужном формате
     */
    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";

    }

    /**
     * Метод сравнения объектов
     * @param o - объект
     * @return - подтверждение совпадения точки
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    /**
     * генерация целочисленного кода объекта
     * @return - equels true -> то hashCode -> true
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);

    }
}
