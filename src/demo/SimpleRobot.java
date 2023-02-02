package demo;

public class SimpleRobot {

    // У каждого робота есть его позиция на двумерном поле (целочисленные координаты x и y)
    // По усолчанию робот наъодится в какой-то определенной позиции (0, 0)
    // Мы должны усеть перемещать робота на другую позицию с другими координатами
    // Робот должен уметь отдавать свое текущее положение

    public static final int DEFAULT_X_POSITION = 0;
    public static final int DEFAULT_Y_POSITION = 0;

    public static final Point DEFAULT_POSITION = new Point(DEFAULT_X_POSITION, DEFAULT_Y_POSITION);

    private Point point;

    public SimpleRobot() {
        this.point = DEFAULT_POSITION;

    }

    public void move(Point point){
        validatePoint(point);
        this.point = point;
    }

    private void validatePoint(Point point) {
        if(point.getX() < 0 || point.getY() < 0) {
            throw new IllegalArgumentException();
        }
    }

    public Point getPoint() {
        return point;

    }

    @Override
    public String toString() {
        return "demo.Robot-" + point;

    }
}
