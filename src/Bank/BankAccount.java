package Bank;

import java.io.Serializable;

import Exceptions.MaxBalance;
import Exceptions.MaxWithdraw;

public class BankAccount implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    String name;
    private double balance;
    double min_balance;
    String acc_num;
    //String type;
    
    
    public BankAccount(String name, double balance, double min_balance) {
        this.name = name;
        this.balance = balance;
        this.min_balance = min_balance;
        acc_num = 10000 + (int)(Math.random()*89999) + "";
    }

    public void deposit(double amount)
    {
        balance+=amount;
    }
    
    public void withdraw(double amount) throws MaxWithdraw, MaxBalance
    {
        if((balance-amount)>=min_balance && amount<balance)
        {
            balance-=amount;
            
        }
        
        else
        {
            throw new MaxBalance("Insufficient Balance");
        }
    }
    
    public double getbalance()
    {
        return balance;
    }
    
    public void documentVerification(String identity, String address) {
        if (verifyIdentity(identity) && verifyAddress(address)) {
            System.out.println("Document verification successful. Eligible for banking services.");
        } else {
            System.out.println("Document verification incomplete. Not eligible for banking services.");
        }
    }
    
    private boolean verifyIdentity(String identity) {
        // Logic to verify identity
        return true; // Placeholder
    }
    
    private boolean verifyAddress(String address) {
        // Logic to verify address
        return true; // Placeholder
    }
    
    public void evaluateCreditworthiness(double annualIncome, int creditScore) {
        if (annualIncome >= 30000 && creditScore >= 700) {
            System.out.println("Congratulations! Eligible for a credit score with a high limit.");
        } else if (annualIncome >= 20000 && creditScore >= 600) {
            System.out.println("Eligible for a credit score with a moderate limit.");
        } else {
            System.out.println("Not eligible for a credit score.");
        }
    }
    
    public void disbursement(double disbursedAmount, double vehicleAssessmentValue) {
        if (disbursedAmount <= vehicleAssessmentValue) {
            System.out.println("Vehicle assessment passed.");
            System.out.println("Disbursed Amount: $" + disbursedAmount);
        } else {
            System.out.println("Vehicle assessment failed. Loan amount cannot exceed vehicle value.");
        }
    }
    
    public void processPayment(double paymentAmount, boolean paymentApproval, String vendorName) {
        if (paymentApproval) {
            System.out.println("Payment approved.");
            System.out.println("Vendor: " + vendorName);
            System.out.println("Payment Amount: $" + paymentAmount);
        } else {
            System.out.println("Payment not approved. Please provide payment approval.");
        }
    }
    
    @Override
    public String toString() {
        return "Name: " + name + ", Id: " + acc_num + ", Balance: " + balance +"Type:"+this.getClass();
    }
}