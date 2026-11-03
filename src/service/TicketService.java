package service;

import exception.SoldOutException;
import model.*;
import util.Repository;

import java.util.List;

// Бизнес-логика продажи билетов
public class TicketService {
    private final Event event;
    private final Repository<Ticket> tickets = new Repository<>();
    private int nextId = 1;

    public TicketService(Event event) { this.event = event; }

    // Покупка одного билета
    public Ticket buy(String owner, boolean vip) throws SoldOutException {
        if (tickets.size() >= event.getCapacity()) {
            throw new SoldOutException("Свободных мест нет");
        }
        Ticket t = vip
                ? new VipTicket(nextId, owner, event.getTicketPrice())
                : new StandardTicket(nextId, owner, event.getTicketPrice());
        nextId++;
        tickets.add(t);
        return t;
    }

    // Перегрузка: покупка нескольких билетов
    public void buy(String owner, boolean vip, int count) throws SoldOutException {
        for (int i = 0; i < count; i++) buy(owner, vip);
    }

    public boolean refund(int id) { return tickets.remove(t -> t.getId() == id); }

    public List<Ticket> findByOwner(String owner) {
        return tickets.find(t -> t.getOwner().equalsIgnoreCase(owner));
    }

    public List<Ticket> getAll() { return tickets.getAll(); }

    public int freeSeats() { return event.getCapacity() - tickets.size(); }

    public double totalSum() {
        double sum = 0;
        for (Ticket t : tickets.getAll()) sum += t.getPrice(); // полиморфизм
        return sum;
    }

    // Восстановление билетов из файла
    public void restore(List<Ticket> loaded) {
        for (Ticket t : loaded) {
            tickets.add(t);
            if (t.getId() >= nextId) nextId = t.getId() + 1;
        }
    }

    public Event getEvent() { return event; }
}
