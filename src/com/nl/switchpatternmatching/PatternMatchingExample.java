package com.nl.switchpatternmatching;
class Shape {}
class Circle extends Shape{
    double radius;

    Circle(double radius){
        this.radius = radius;
    }
}
class Rectangle extends Shape{
    double width, height;

    Rectangle(double width, double height){
        this.width = width;
        this.height = height;
    }
}
public class PatternMatchingExample {
    public static void main(String[] args) {
        Shape shape = new Circle(10.0);
        String description = switch (shape){
            case Circle c -> "Circle with radios: " + c.radius;
            case Rectangle r -> "Rectangle with width: " + r.width + " with height: " + r.height;
            case null -> "Shape is null";
            default -> "Unknown shape";
        };

        System.out.println(description);
    }
}
