package model;

public class Event {
    private final String name;
    private final int capacity;
    private final double ticketPrice;

    public Event(String name, int capacity, double ticketPrice) {
        if (capacity <= 0) throw new IllegalArgumentException("Вместимость должна быть > 0");
        this.name = name;
        this.capacity = capacity;
        this.ticketPrice = ticketPrice;
    }

    public String getName() { return name; }
    public int getCapacity() { return capacity; }
    public double getTicketPrice() { return ticketPrice; }
}
