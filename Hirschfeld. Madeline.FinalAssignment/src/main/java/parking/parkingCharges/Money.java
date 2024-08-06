/**
 * The money class provides methods to convert cents into the amount in
 * dollars and then converts it into a string
 * 
 * @author (Maddie Hirschfeld)
 * @version (November 6, 2023)
 */

package src.main.java.parking.parkingCharges;

import src.main.java.shared.JsonSerializable;
import java.util.Objects;

public class Money implements JsonSerializable {
    // declare variable
    private long cents;
    private double amount;

    //Default constructor that doesn't take any parameters and creates
    //object based on default values
    public Money(double amount, long cents) {
        this.cents = cents;
        this.amount = amount;
    }

    // constructor to initialize money object
    public Money(Double amount, long cents) {
        this.cents = (long) (amount * 100) + cents;
    }
    //additional constructor that accepts int as first parameter
    public Money(int amount, long cents) {
        this.cents = (long) (amount * 100) + cents;
    }
    //additional constructor that accepts single integer
    public Money(double amount) {
        this.cents = (long) (amount * 100);
    }

    //Getters and setters
    public long getCents () {
        return cents;
    }
    public void setCents(long cents) {
        this.cents = cents;
    }

    // a method to convert cents into dollars
    public double getDollars() {
        return cents / 100.0;
    }

    public Money multiply(long factor) {
        return new Money(0, this.cents * factor);
    }

    public Money subtract(Money other) {
        return new Money(0, this.cents - other.cents);
    }
    
    public Money multiply(double factor) {
        return new Money(0, (long) (this.cents * factor));
    }

    //method performs addition between two money objects
    public Money add(Money other) {
        double newAmount = this.amount + other.amount;
        long newCents = this.cents + other.cents;

        if (newCents >= 100) {
            newAmount += newCents / 100;
            newCents %= 100;
        }

        return new Money(newAmount, newCents);
    }

    // format money as a string
    // Handles negative values and adds currency symbol
    @Override
    public String toString() {
        if (cents < 0) {
            long absCents = Math.abs(cents);
            long dollars = absCents / 100;
            long remainingCents = absCents % 100;
            String formattedCents = String.format("%02d", remainingCents);
            return "-$" + dollars + "." + formattedCents;
        } else {
            long dollars = cents/100;
            long remainingCents = cents % 100;
            String formattedCents = String.format("%02d", remainingCents);
            return "$" + dollars + "." + formattedCents;
        }
    }

    // compares money objects based on cents value
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Money money = (Money) obj;
        return cents == money.cents;
    }

    // generates hash code based on cents value
    @Override
    public int hashCode() {
        return Objects.hash(cents);
    }

    public String getAmount() {
        return null;
    }
}