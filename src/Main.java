import java.util.Scanner;// импорт сканера

public class Main {
    public static void main(String[] args) throws Exception {
        // Создаем экземпляр сканера для считывания ввода с консоли
        Scanner scanner = new Scanner(System.in);
        // бесконечный цикл, разорвется по слову exit
        while (true) {
            // 1) вводим математическое выражение
            System.out.println("Введите математическое выражение:");
            String line = scanner.nextLine();
            // если ввели слово exit то разрываем цикл
            if (line.equals("exit")) {
                break;
            }
            // 2) вызвать метод calc и передали в него математическое выражение
            String result = calc(line);
            // 3) вывод результатов
            System.out.println(result);
        }
    }

    public static String calc(String line) throws Exception {

        int a = 0;
        int b = 0;
        if (!line.trim().contains(" ")) {
            throw new Exception("Математичсекое выражение должно содержать пробелы, отделяющие операнды " +
                    "и знак математической операции");
        }
        // прверяем наличие одного из четырех знаков математической операции
        // через регулярное выражение
        if (!line.trim().matches(".*[+\\-*/].*")) {
            throw new Exception("Не обнаружен знак математической операции.");
        }
        // делим входную строку на части по пробелу
        String[] parts = line.split(" ");
        // если у нас не три части, мы не можем выполнить арифметическое выражение
        // выбрасываем исключение с сообщения
        if (parts.length != 3) {
            throw new Exception("Недостаточно данных для выполнения математической операции!");
        }
        // Пытаемся преобразовать значение строки в целое число
        // И если не получается, выбрасываем ошибку
        try {
            // первая часть (нумерация с 0) - это первый операнд
            a = Integer.parseInt(parts[0].trim());
        } catch (NumberFormatException e) {
            System.out.println("Эта строка не является целым числом.");
        }
        // проверяем, что число а находится в диапозоне от 1 до 10, иначе выбрасываем исключение
        if (a > 10 || a < 1) {
            throw new Exception("Первый операнд выходит за диапозон 1-10");
        }
        // ----------------------------------
        // вторая часть - это знак операции
        String znak = parts[1].trim();
        // ----------------------------------
        try {
            // третья часть - это второй операнд
            b = Integer.parseInt(parts[2].trim());
        } catch (NumberFormatException e) {
            System.out.println("Эта строка не является целым числом.");
        }
        if (b > 10 || b < 1) {
            throw new Exception("Второй операнд выходит за диапозон 1-10");
        }
        // в зависимости от знака операции выполняем действие
        int result = switch (znak.charAt(0)) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> a / b;
            default -> throw new Exception("Недопустимая математическая операция");
        };
        return String.valueOf(result);
    }
}