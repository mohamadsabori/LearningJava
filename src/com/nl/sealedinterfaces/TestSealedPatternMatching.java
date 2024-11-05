package com.nl.sealedinterfaces;

sealed interface Shapes permits Rectangle, Circle, Square {
}

final class Circle implements Shapes {
    double radius;
}

final class Rectangle implements Shapes {
    double width, height;
}

sealed class Square implements Shapes permits ColoredSquare {
}

final class ColoredSquare extends Square {
    ColoredSquare(String color) {
        this.color = color;
    }

    String color;
}

public class TestSealedPatternMatching {
    public static void main(String[] args) {
        Shapes shape = new ColoredSquare("Red");

        String description = switch (shape) {
            case ColoredSquare cs -> "this is square" + cs.color;
            case Rectangle r -> "This is Rectangle " + r.height;
            case Circle c -> "This is Circle " + c.radius;
            default -> "Unknown shape";
        };
        System.out.println(description);
    }
}