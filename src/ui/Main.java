package ui;

import exception.SoldOutException;
import model.Event;
import model.Ticket;
import service.TicketService;
import storage.FileStorage;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Event event = new Event("Концерт", 5, 1000);
        TicketService service = new TicketService(event);
        FileStorage storage = new FileStorage("tickets.txt");
        Scanner sc = new Scanner(System.in);

        try {
            service.restore(storage.load(event.getTicketPrice()));
        } catch (IOException | RuntimeException e) {
            System.out.println("Не удалось прочитать файл: " + e.getMessage());
        }

        boolean run = true;
        while (run) {
            System.out.println("\n=== " + event.getName() + " ===");
            System.out.println("1 - Купить билет");
            System.out.println("2 - Вернуть билет");
            System.out.println("3 - Все билеты");
            System.out.println("4 - Поиск по покупателю");
            System.out.println("5 - Свободные места и сумма продаж");
            System.out.println("0 - Выход");
            System.out.print("Выбор: ");
            String choice = sc.nextLine();

            try {
                switch (choice) {
                    case "1" -> {
                        System.out.print("Имя покупателя: ");
                        String name = sc.nextLine();
                        System.out.print("VIP? (да/нет): ");
                        boolean vip = sc.nextLine().equalsIgnoreCase("да");
                        System.out.println("Куплено: " + service.buy(name, vip));
                    }
                    case "2" -> {
                        System.out.print("Номер билета: ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.println(service.refund(id) ? "Билет возвращён" : "Билет не найден");
                    }
                    case "3" -> printList(service.getAll());
                    case "4" -> {
                        System.out.print("Имя: ");
                        printList(service.findByOwner(sc.nextLine()));
                    }
                    case "5" -> {
                        System.out.println("Свободно мест: " + service.freeSeats());
                        System.out.println("Сумма продаж: " + service.totalSum());
                    }
                    case "0" -> run = false;
                    default -> System.out.println("Нет такого пункта");
                }
            } catch (SoldOutException e) {
                System.out.println("Ошибка: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Нужно ввести число");
            } catch (IllegalArgumentException e) {
                System.out.println("Некорректные данные: " + e.getMessage());
            }
        }

        try {
            storage.save(service.getAll());
            System.out.println("Данные сохранены.");
        } catch (IOException e) {
            System.out.println("Ошибка сохранения: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    private static void printList(List<Ticket> list) {
        if (list.isEmpty()) System.out.println("Пусто");
        for (Ticket t : list) System.out.println(t);
    }
}
