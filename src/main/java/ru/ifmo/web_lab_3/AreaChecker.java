package ru.ifmo.web_lab_3;

public class AreaChecker {
    public static boolean isHit(Dot dot) {
        double x = dot.getX();
        double y = dot.getY();
        double r = dot.getR();

        return (isCircleZone(x, y, r) || isTriangleZone(x, y, r) || isRectangleZone(x, y, r));
    }
    private static boolean isRectangleZone(double x, double y, double r) {
        return (x >= 0) && (x <= r) && (y >= 0) && (y <= r / 2);
    }

    private static boolean isCircleZone(double x, double y, double r) {
        return (x * x + y * y <= r / 2 * r / 2) && (x <= 0) && (y >= 0);
    }

    private static boolean isTriangleZone(double x, double y, double r) {
        return ((x <= 0) && (y <= 0) && (-x -y <= r));
    }
}
