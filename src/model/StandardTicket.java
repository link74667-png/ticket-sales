package model;

public class StandardTicket extends Ticket {
    public StandardTicket(int id, String owner, double basePrice) {
        super(id, owner, basePrice);
    }

    @Override
    public String getType() { return "STANDARD"; }

    @Override
    public double getPrice() { return getBasePrice(); }
}
