package model;

public class VipTicket extends Ticket {
    public VipTicket(int id, String owner, double basePrice) {
        super(id, owner, basePrice);
    }

    @Override
    public String getType() { return "VIP"; }

    @Override
    public double getPrice() { return getBasePrice() * 2.5; } // VIP дороже в 2.5 раза
}
