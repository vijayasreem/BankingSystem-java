package GUI;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Exceptions.AccNotFound;
import Exceptions.InvalidAmount;
import Exceptions.MaxBalance;
import Exceptions.MaxWithdraw;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.awt.event.ActionEvent;
import Data.FileIO;
import java.awt.SystemColor;

public class WithdrawAcc extends JFrame implements Serializable {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;


    public WithdrawAcc() {
        setTitle("Withdraw From Account");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.activeCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblDepositToAccount = new JLabel("Withdraw From Account");
        lblDepositToAccount.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblDepositToAccount.setHorizontalAlignment(SwingConstants.CENTER);
        lblDepositToAccount.setBounds(10, 11, 414, 36);
        contentPane.add(lblDepositToAccount);

        JLabel lblName = new JLabel("Account Number:");
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);
        lblName.setBounds(0, 86, 106, 14);
        contentPane.add(lblName);

        textField = new JTextField();
        textField.setBounds(116, 83, 216, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(116, 147, 216, 20);
        contentPane.add(textField_1);

        JLabel lblAmount = new JLabel("Amount:");
        lblAmount.setHorizontalAlignment(SwingConstants.RIGHT);
        lblAmount.setBounds(10, 150, 96, 14);
        contentPane.add(lblAmount);

        JButton btnWithdraw = new JButton("Withdraw");
        btnWithdraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accountNum = textField.getText();
                double amount = Double.parseDouble(textField_1.getText());

                try {
                    int confirmation = JOptionPane.showConfirmDialog(getComponent(0), "Confirm?");
                    if (confirmation == 0) {
                        boolean isIdentityVerified = verifyIdentity(accountNum);
                        boolean isAddressVerified = verifyAddress(accountNum);

                        if (isIdentityVerified && isAddressVerified) {
                            JOptionPane.showMessageDialog(getComponent(0), "Document verification successful. Eligible for banking services.");
                        } else {
                            JOptionPane.showMessageDialog(getComponent(0), "Document verification incomplete. Not eligible for banking services.");
                        }

                        boolean isCreditEligible = evaluateCreditworthiness(accountNum);
                        if (isCreditEligible) {
                            JOptionPane.showMessageDialog(getComponent(0), "Congratulations! Eligible for a credit score with a high limit.");
                        } else {
                            JOptionPane.showMessageDialog(getComponent(0), "Eligible for a credit score with a moderate limit.");
                        }

                        boolean isDisbursedAmountValid = determineDisbursedAmount(accountNum, amount);
                        if (isDisbursedAmountValid) {
                            JOptionPane.showMessageDialog(getComponent(0), "Vehicle assessment passed. Disbursed Amount: $" + amount);
                        } else {
                            JOptionPane.showMessageDialog(getComponent(0), "Vehicle assessment failed. Loan amount cannot exceed vehicle value.");
                        }

                        boolean isPaymentApproved = approvePayment(amount);
                        if (isPaymentApproved) {
                            String vendorName = getVendorName();
                            JOptionPane.showMessageDialog(getComponent(0), "Successful disbursement process. Vendor: " + vendorName + ", Payment Amount: $" + amount);
                        } else {
                            JOptionPane.showMessageDialog(getComponent(0), "Payment approval required. Please grant approval.");
                        }

                        dispose();
                    } else {
                        textField.setText(null);
                        textField_1.setText(null);
                    }
                } catch (MaxBalance e1) {
                    JOptionPane.showMessageDialog(getComponent(0), "Insufficient Balance");
                    JOptionPane.showMessageDialog(getComponent(0), "Failed");
                    textField.setText(null);
                    textField_1.setText(null);
                } catch (AccNotFound e1) {
                    JOptionPane.showMessageDialog(getComponent(0), "Sorry! Account Not Found");
                    JOptionPane.showMessageDialog(getComponent(0), "Failed");
                    textField.setText(null);
                    textField_1.setText(null);
                } catch (MaxWithdraw e1) {
                    JOptionPane.showMessageDialog(getComponent(0), "Maximum Withdraw Limit Exceeded");
                    JOptionPane.showMessageDialog(getComponent(0), "Failed");
                    textField.setText(null);
                    textField_1.setText(null);
                } catch (InvalidAmount e1) {
                    JOptionPane.showMessageDialog(getComponent(0), "Invalid Amount");
                    JOptionPane.showMessageDialog(getComponent(0), "Failed");
                    textField.setText(null);
                    textField_1.setText(null);
                }

                textField.setText(null);
                textField_1.setText(null);
            }
        });
        btnWithdraw.setBounds(73, 212, 89, 23);
        contentPane.add(btnWithdraw);

        JButton btnReset = new JButton("Reset");
        btnReset.setBounds(243, 212, 89, 23);
        contentPane.add(btnReset);
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText(null);
                textField_1.setText(null);
            }
        });
    }

    private boolean verifyIdentity(String accountNum) {
        // Code to verify identity using provided input
        return true; // Placeholder, replace with actual implementation
    }

    private boolean verifyAddress(String accountNum) {
        // Code to verify address using provided input
        return true; // Placeholder, replace with actual implementation
    }

    private boolean evaluateCreditworthiness(String accountNum) {
        // Code to evaluate creditworthiness based on annual income and credit score
        return true; // Placeholder, replace with actual implementation
    }

    private boolean determineDisbursedAmount(String accountNum, double amount) {
        // Code to determine if disbursed amount is less than or equal to vehicle assessment value
        return true; // Placeholder, replace with actual implementation
    }

    private boolean approvePayment(double amount) {
        // Code to approve payment based on payment amount
        return true; // Placeholder, replace with actual implementation
    }

    private String getVendorName() {
        // Code to get vendor name
        return ""; // Placeholder, replace with actual implementation
    }
}