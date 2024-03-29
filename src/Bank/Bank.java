package Bank;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import Exceptions.AccNotFound;
import Exceptions.InvalidAmount;
import Exceptions.MaxBalance;
import Exceptions.MaxWithdraw;

public class Bank implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private BankAccount[] accounts = new BankAccount[100];
    private List<DisbursementLog> disbursementLogs = new ArrayList<>();

    public int addAccount(BankAccount acc) {
        int i = 0;
        for (i = 0; i < 100; i++) {
            if (getAccounts()[i] == null) {
                break;
            }
        }
        getAccounts()[i] = acc;
        return i;
    }

    public int addAccount(String name, double balance, double maxWithLimit) {
        SavingsAccount acc = new SavingsAccount(name, balance, maxWithLimit);
        return this.addAccount(acc);
    }

    public int addAccount(String name, double balance, String tradeLicense) throws Exception {
        CurrentAccount acc = new CurrentAccount(name, balance, tradeLicense);
        return this.addAccount(acc);
    }

    public int addAccount(String name, String institutionName, double balance, double min_balance) {
        StudentAccount acc = new StudentAccount(name, balance, institutionName);
        return this.addAccount(acc);
    }

    public BankAccount findAccount(String aacountNum) {
        int i;
        for (i = 0; i < 100; i++) {
            if (getAccounts()[i] == null) {
                break;
            }
            if (getAccounts()[i].acc_num.equals(aacountNum)) {
                return getAccounts()[i];
            }

        }
        return null;
    }

    public void deposit(String aacountNum, double amt) throws InvalidAmount, AccNotFound

    {
        if (amt < 0) {
            throw new InvalidAmount("Invalid Deposit amount");
        }
        BankAccount temp = findAccount(aacountNum);
        if (temp == null) {
            throw new AccNotFound("Account Not Found");
        }
        if (temp != null) {
            temp.deposit(amt);

        }

    }

    public void withdraw(String aacountNum, double amt) throws MaxBalance, AccNotFound, MaxWithdraw, InvalidAmount {
        BankAccount temp = findAccount(aacountNum);

        if (temp == null) {
            throw new AccNotFound("Account Not Found");
        }

        if (amt <= 0) {
            throw new InvalidAmount("Invalid Amount");
        }

        if (amt > temp.getbalance()) {
            throw new MaxBalance("Insufficient Balance");
        }
        if (temp != null) {
            temp.withdraw(amt);
        }
    }

    public DefaultListModel<String> display() {
        DefaultListModel<String> list = new DefaultListModel<String>();
        int i;

        for (i = 0; i < 100; i++) {
            if (getAccounts()[i] == null) {
                break;
            }

            list.addElement(getAccounts()[i].toString());

        }

        return list;
    }

    public void createMethodForDocumentVerification(String identity, String address) {
        System.out.println("Welcome to the Document Verification App!");

        boolean isIdentityVerified = verifyIdentity(identity);
        boolean isAddressVerified = verifyAddress(address);

        if (isIdentityVerified && isAddressVerified) {
            System.out.println("Document verification is complete. You are eligible for banking services.");
        } else {
            System.out.println("Document verification is incomplete. You are not eligible for banking services.");
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

    public void createMethodForCreditEvaluation(double annualIncome, int creditScore) {
        if (annualIncome >= 30000 && creditScore >= 700) {
            System.out.println("Congratulations! You are eligible for a credit score with a high limit.");
        } else if (annualIncome >= 20000 && creditScore >= 600) {
            System.out.println("You are eligible for a credit score with a moderate limit.");
        } else {
            System.out.println("You are not eligible for a credit score.");
        }
    }

    public void createMethodForDisbursement(double disbursedAmount, double vehicleAssessmentValue) {
        if (disbursedAmount <= vehicleAssessmentValue) {
            System.out.println("Vehicle assessment passed.");
            System.out.println("Disbursed Amount: $" + disbursedAmount);
            logDisbursement(disbursedAmount, vehicleAssessmentValue, "Passed");
        } else {
            System.out.println("Vehicle assessment failed.");
            System.out.println("Loan amount cannot exceed vehicle value.");
            logDisbursement(disbursedAmount, vehicleAssessmentValue, "Failed");
        }
    }

    private void logDisbursement(double disbursedAmount, double vehicleAssessmentValue, String status) {
        LocalDateTime dateTime = LocalDateTime.now();
        String user = "User"; // Placeholder for user
        DisbursementLog log = new DisbursementLog(dateTime, user, disbursedAmount, vehicleAssessmentValue, status);
        disbursementLogs.add(log);
    }

    public void createMethodForPaymentApproval(double paymentAmount) {
        if (paymentAmount <= 1000.0) {
            System.out.println("Payment approved.");
        } else {
            System.out.println("Payment approval required.");
        }
    }

    public void createMethodForVendorInformationVerification(String vendorName) {
        boolean isVendorInformationValid = verifyVendorInformation(vendorName);

        if (isVendorInformationValid) {
            System.out.println("Vendor information verified.");
            confirmFundsAvailability();
            createMethodForPaymentApproval(1000.0); // Placeholder for payment amount
            System.out.println("Successful disbursement process.");
            System.out.println("Vendor: " + vendorName);
            System.out.println("Payment Amount: $1000.0"); // Placeholder for payment amount
        } else {
            System.out.println("Invalid vendor information.");
        }
    }

    private boolean verifyVendorInformation(String vendorName) {
        // Logic to verify vendor information
        return true; // Placeholder
    }

    private void confirmFundsAvailability() {
        // Logic to confirm funds availability
    }

    public BankAccount[] getAccounts() {
        return accounts;
    }

    public void setAccounts(BankAccount[] accounts) {
        this.accounts = accounts;
    }

    public List<DisbursementLog> getDisbursementLogs() {
        return disbursementLogs;
    }

    public void setDisbursementLogs(List<DisbursementLog> disbursementLogs) {
        this.disbursementLogs = disbursementLogs;
    }

    public class DisbursementLog {
        private LocalDateTime dateTime;
        private String user;
        private double disbursedAmount;
        private double vehicleValue;
        private String status;

        public DisbursementLog(LocalDateTime dateTime, String user, double disbursedAmount, double vehicleValue,
                String status) {
            this.dateTime = dateTime;
            this.user = user;
            this.disbursedAmount = disbursedAmount;
            this.vehicleValue = vehicleValue;
            this.status = status;
        }

        public LocalDateTime getDateTime() {
            return dateTime;
        }

        public void setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public double getDisbursedAmount() {
            return disbursedAmount;
        }

        public void setDisbursedAmount(double disbursedAmount) {
            this.disbursedAmount = disbursedAmount;
        }

        public double getVehicleValue() {
            return vehicleValue;
        }

        public void setVehicleValue(double vehicleValue) {
            this.vehicleValue = vehicleValue;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = dateTime.format(formatter);
            return "Date/Time: " + formattedDateTime + ", User: " + user + ", Disbursed Amount: " + disbursedAmount
                    + ", Vehicle Value: " + vehicleValue + ", Status: " + status;
        }
    }
}