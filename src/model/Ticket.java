package model;

// Абстрактный базовый класс билета
public abstract class Ticket implements Priced {
    private final int id;
    private final String owner;
    private final double basePrice;

    public Ticket(int id, String owner, double basePrice) {
        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException("Имя покупателя не может быть пустым");
        }
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Цена должна быть больше нуля");
        }
        this.id = id;
        this.owner = owner;
        this.basePrice = basePrice;
    }

    public int getId() { return id; }
    public String getOwner() { return owner; }
    protected double getBasePrice() { return basePrice; }

    public abstract String getType();

    @Override
    public String toString() {
        return "Билет №" + id + " [" + getType() + "] " + owner + ", цена: " + getPrice();
    }
}
