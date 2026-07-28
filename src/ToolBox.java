// MARY FAITH U ZAMBAS

import java.util.Scanner;

public class ToolBox {
    private static String greet(String name) {
        return "Hello, " + name + "! Welcome to my Java Toolbox.";
    }

    private static double area(double side) {
        return side * side;
    }

    private static double area(double length, double width) {
        return length * width;
    }

    private static int sum(int... numbers) {
        int total = 0;
        for(int num : numbers) total += num;
        return total;
    }

    private static void swap(int a, int b) {
        int temp = a; a = b; b = temp;
        System.out.println("   (inside swap)    a = " + a + ", b = " + b);
    }

    private static void addToBox(Box box, int amount) {
        box.value = box.value + amount;
    }

    private static class Box {
        int value;
        Box(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("===== JAVA TOOLBOX =====");
            System.out.println("1 - Greet me");
            System.out.println("2 - Area (square or rectangle)");
            System.out.println("3 - Sum of numbers");
            System.out.println("4 - Swap demo (pass-by-value)");
            System.out.println("5 - Box demo (object mutation)");
            System.out.println("0 - Exit");
            System.out.print("Choose an option: ");
            choice = Integer.parseInt(scan.nextLine().trim());

            switch (choice) {
                case 1: {
                    System.out.print("Enter your name: ");
                    String name = scan.nextLine();
                    System.out.println(greet(name));
                    break;
                }
                case 2: {
                    System.out.print("Sides (1 = square, 2 = rectangle): ");
                    int type = Integer.parseInt(scan.nextLine().trim());
                    if(type == 1) {
                        System.out.print("Enter side length: ");
                        double side = Double.parseDouble(scan.nextLine().trim());
                        System.out.println("Area of square = " + area(side));
                    } else {
                        System.out.print("Enter length: ");
                        double length = Double.parseDouble(scan.nextLine().trim());
                        System.out.print("Enter width: ");
                        double width = Double.parseDouble(scan.nextLine().trim());
                        System.out.println("Area of rectangle = " + area(length, width));
                    }
                    break;
                }
                case 3: {
                    System.out.println("Sum of 4, 8, 15 = " + sum(4, 8, 15));
                    System.out.println("Sum of 2, 4, 6, 8, 10 = " + sum(2, 4, 6, 8, 10));
                    break;
                }
                case 4: {
                    int x = 5, y = 9;
                    System.out.println("Before swap: x = " + x + ", y = " + y);
                    swap(x, y);
                    System.out.println("After swap: x = " + x + ", y = " + y + " (unchanged, because a & b are merely temporary boxes, they return to their own original method as swap returns)");
                    break;
                }
                case 5: {
                    Box box = new Box(10);
                    System.out.println("Before: box.value = " + box.value);
                    addToBox(box, 25);
                    System.out.println("After: box.value = " + box.value + " (changed, the object is shared but with reference only, not the object itself)");
                    break;
                }
                case 0: {
                    System.out.println("Goodbye!");
                    break;
                }
                default: {
                    System.out.println("Invalid option, try again.");
                }
            }
            System.out.println();
        } while (choice != 0);
        scan.close();
    }
}