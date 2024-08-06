/**
 * The TransactionManager class manages transactions for parking charges
 * withing the parking system.
 * 
 *
 * @author (Maddie Hirschfeld)
 * @version (October 22, 2023)
 */

package src.main.java.parking.transactionManager;

import src.main.java.shared.JsonSerializable;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import src.main.java.parking.management.ParkingEvent;
import src.main.java.parking.parkingCharges.*;
import src.main.java.parking.parkingLotData.ParkingLotType;

public class TransactionManager implements JsonSerializable {
    private ArrayList<ParkingTransaction> transactions = null;
    private static HashMap<String, ArrayList<ParkingTransaction>> carTransaction = null;
    private static Map<DayOfWeek, BaseParkingCharge> chargeStrategies = new HashMap<>();

    public TransactionManager(ParkingChargeStrategyFactory chargeStrategyFactory) {
        this.transactions = new ArrayList<ParkingTransaction>();
        TransactionManager.carTransaction = new HashMap<String, ArrayList<ParkingTransaction>>();
    }

    // Constructors for TransactionManager class. The constructor will set the
    // paramaters
    // while creating the instance
    //
    // @param transactions
    //
    public TransactionManager(ArrayList<ParkingTransaction> transactions,
            ParkingChargeStrategyFactory chargeStrategyFactory) {
        this.transactions = transactions;
        TransactionManager.carTransaction = new HashMap<String, ArrayList<ParkingTransaction>>();
    }

    public TransactionManager(HashMap<String, ArrayList<ParkingTransaction>> permitTransaction,
            ParkingChargeStrategyFactory chargeStrategyFactory) {
        this.transactions = new ArrayList<ParkingTransaction>();
        TransactionManager.carTransaction = permitTransaction;
    }

    // Getters and setteres
    public ArrayList<ParkingTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<ParkingTransaction> transactions) {
        this.transactions = transactions;
    }

    // Method that handles parking charge and adds it to the transaction list
    public ParkingTransaction park(ParkingEvent event) {
        ParkingLotType lotType = event.getLotType();
        ParkingPermit permit = event.getPermit();

        // defined charge strategies based on the day of the week
        chargeStrategies.put(DayOfWeek.MONDAY, ParkingChargeStrategyFactory.createDailyChargeDecorator(lotType));
        chargeStrategies.put(DayOfWeek.TUESDAY, ParkingChargeStrategyFactory.createDailyChargeDecorator(lotType));
        chargeStrategies.put(DayOfWeek.WEDNESDAY, ParkingChargeStrategyFactory.createDailyChargeDecorator(lotType));
        chargeStrategies.put(DayOfWeek.THURSDAY, ParkingChargeStrategyFactory.createDailyChargeDecorator(lotType));
        chargeStrategies.put(DayOfWeek.FRIDAY, ParkingChargeStrategyFactory.createDailyChargeDecorator(lotType));
        chargeStrategies.put(DayOfWeek.SATURDAY, ParkingChargeStrategyFactory.createWeekendChargeDecorator(lotType));
        chargeStrategies.put(DayOfWeek.SUNDAY, ParkingChargeStrategyFactory.createWeekendChargeDecorator(lotType));

        // registers parking charge
        ParkingTransaction transaction = new ParkingTransaction();
        transaction.setPermit(permit);
        transactions.add(transaction);

        ArrayList<ParkingTransaction> licenseTransaction = carTransaction.get(permit.getCar().getLicense());
        if (licenseTransaction == null) {
            licenseTransaction = new ArrayList<ParkingTransaction>();
        }
        licenseTransaction.add(transaction);
        carTransaction.put(permit.getCar().getLicense(), licenseTransaction);

        return transaction;
    }

    //method to retrieve strategy based on day of the week
    public BaseParkingCharge getChargeStrategyForDay(DayOfWeek dayOfWeek) {
        return chargeStrategies.get(dayOfWeek);
    }

    // calculates parking charges for transactions based on day of the week
    public void calculateParkingCharge(DayOfWeek dayOfWeek) {
        // initialize to accumulate total charges
        Money totalCharges = new Money(0.0, 0);

        Iterator<ParkingTransaction> transactionIterator = transactions.iterator();
        while (transactionIterator.hasNext()) {
            ParkingTransaction transaction = transactionIterator.next();
            ParkingPermit permit = transaction.getPermit();
            BaseParkingCharge strategy = chargeStrategies.get(dayOfWeek);

            if (strategy != null) {
                Money charge = getParkingCharges(permit, strategy);
                totalCharges = totalCharges.add(charge);

                System.out.println(dayOfWeek + "'s Parking Charge': $" + charge.getAmount());
            } else {
                System.out.println("Associated fee not found for " + dayOfWeek);
            }
        }
    }

    // calculates total charges for specific permit
    public Money getParkingCharges(ParkingPermit permit, BaseParkingCharge baseParkingCharge) {
        Money charge = null;
        long initChargeInCents = 0;

        String license = permit.getCar().getLicense();
        ArrayList<ParkingTransaction> licenseTransaction = carTransaction.get(license);
        for (ParkingTransaction trans : licenseTransaction) {
            initChargeInCents += trans.getChargedAmount(baseParkingCharge).getCents();
        }
        charge = new Money(0.0, initChargeInCents);

        return charge;
    }

    public Money getParkingCharges(String license, BaseParkingCharge baseParkingCharge) {
        Money charge = null;
        long tempChargeInCents = 0;

        ArrayList<ParkingTransaction> licenseTransaction = carTransaction.get(license);

        for (ParkingTransaction trans : licenseTransaction) {
            tempChargeInCents += trans.getChargedAmount(baseParkingCharge).getCents();
        }
        charge = new Money(0.0, tempChargeInCents);

        return charge;
    }
}