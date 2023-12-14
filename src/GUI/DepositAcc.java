package GUI;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Exceptions.AccNotFound;
import Exceptions.InvalidAmount;

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

public class DepositAcc extends JFrame implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	
	/**
	 * Create the frame.
	 */
	public DepositAcc() {
		setTitle("Deposit To Account");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDepositToAccount = new JLabel("Deposit To Account");
		lblDepositToAccount.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDepositToAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblDepositToAccount.setBounds(10, 11, 414, 36);
		contentPane.add(lblDepositToAccount);
		
		JLabel lblName = new JLabel("Account Number:");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(0, 86, 111, 14);
		contentPane.add(lblName);
		
		textField = new JTextField();
		textField.setBounds(121, 83, 211, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(121, 147, 211, 20);
		contentPane.add(textField_1);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmount.setBounds(0, 150, 111, 14);
		contentPane.add(lblAmount);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accountNum = textField.getText();
				double amount = Double.parseDouble(textField_1.getText());
				
				// Document Verification
				boolean isIdentityVerified = verifyIdentity(accountNum);
				boolean isAddressVerified = verifyAddress(accountNum);
				
				if (!isIdentityVerified || !isAddressVerified) {
					JOptionPane.showMessageDialog(getComponent(0), "Document verification is incomplete. Not eligible for banking services.");
					return;
				}
				
				// Credit Evaluation
				double annualIncome = getAnnualIncome(accountNum);
				int creditScore = getCreditScore(accountNum);
				
				if (annualIncome >= 30000 && creditScore >= 700) {
					JOptionPane.showMessageDialog(getComponent(0), "Congratulations! You are eligible for a credit score with a high limit.");
				} else if (annualIncome >= 20000 && creditScore >= 600) {
					JOptionPane.showMessageDialog(getComponent(0), "You are eligible for a credit score with a moderate limit.");
				}
				
				// Disbursement
				double disbursedAmount = getDisbursedAmount(accountNum);
				double vehicleAssessmentValue = getVehicleAssessmentValue(accountNum);
				
				if (disbursedAmount <= vehicleAssessmentValue) {
					JOptionPane.showMessageDialog(getComponent(0), "Vehicle assessment passed. Disbursed Amount: $" + disbursedAmount);
				} else {
					JOptionPane.showMessageDialog(getComponent(0), "Vehicle assessment failed. Loan amount cannot exceed vehicle value.");
				}
				
				// Payment Approval
				double paymentAmount = getPaymentAmount(accountNum);
				
				if (paymentAmount <= 1000.0) {
					JOptionPane.showMessageDialog(getComponent(0), "Payment approved. Disbursement process successful.");
				} else {
					JOptionPane.showMessageDialog(getComponent(0), "Payment approval required. Please grant payment approval.");
				}
				
				textField.setText(null);
				textField_1.setText(null);
			}
		});
		btnDeposit.setBounds(73, 212, 89, 23);
		contentPane.add(btnDeposit);
		
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
		return true; // Placeholder
	}
	
	private boolean verifyAddress(String accountNum) {
		// Code to verify address using provided input
		return true; // Placeholder
	}
	
	private double getAnnualIncome(String accountNum) {
		// Code to get annual income based on account number
		return 0.0; // Placeholder
	}
	
	private int getCreditScore(String accountNum) {
		// Code to get credit score based on account number
		return 0; // Placeholder
	}
	
	private double getDisbursedAmount(String accountNum) {
		// Code to determine disbursed amount based on account number
		return 0.0; // Placeholder
	}
	
	private double getVehicleAssessmentValue(String accountNum) {
		// Code to get vehicle assessment value based on account number
		return 0.0; // Placeholder
	}
	
	private double getPaymentAmount(String accountNum) {
		// Code to get payment amount based on account number
		return 0.0; // Placeholder
	}
}