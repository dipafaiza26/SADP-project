import java.io.Serializable;

// =============================================
// STRATEGY PATTERN
// =============================================

interface PricingStrategy {
    double calculate(double basePrice);
    String name();
}

class RegularPricing implements PricingStrategy, Serializable {
    public double calculate(double basePrice) { return basePrice; }
    public String name()                      { return "Regular"; }
}

class DiscountPricing implements PricingStrategy, Serializable {
    public double calculate(double basePrice) { return basePrice * 0.9; } // 10% off
    public String name()                      { return "Discount (10% off)"; }
}

class PeakPricing implements PricingStrategy, Serializable {
    public double calculate(double basePrice) { return basePrice * 1.2; } // 20% surcharge
    public String name()                      { return "Peak (+20%)"; }
}

// =============================================
// Room - uses a PricingStrategy
// =============================================
public class Room implements Serializable {

    private int roomNo;
    private RoomType roomType;
    private double basePrice;
    private PricingStrategy pricingStrategy = new RegularPricing(); // default

    public Room(int roomNo, RoomType roomType, double basePrice) {
        this.roomNo      = roomNo;
        this.roomType    = roomType;
        this.basePrice   = basePrice;
    }

    // Swap strategy at any time
    public void setPricingStrategy(PricingStrategy strategy) {
        this.pricingStrategy = strategy;
    }

    public String getStrategyName() {
        return pricingStrategy != null ? pricingStrategy.name() : "Regular";
    }

    // Price after applying the current strategy
    public double getFinalPrice() 
    {
        return pricingStrategy != null ? pricingStrategy.calculate(basePrice) : basePrice;
    }

    public int getRoomNo()                  
    { 
        return roomNo; 

    }
    public void setRoomNo(int roomNo)        
    {
         this.roomNo = roomNo; 
    }

    public RoomType getRoomType()                { return roomType; }
    public void     setRoomType(RoomType rt)     { this.roomType = rt; }

    public double   getBasePrice()               { return basePrice; }
    public void     setBasePrice(double p)       { this.basePrice = p; }

    // Keep getPrice() so RoomForm still works without changes
    public double   getPrice()                   { return getFinalPrice(); }
}
