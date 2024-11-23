import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import DES;
import AES;
import OneTimePadding;

public class DES_Interface {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      JFrame frame = new JFrame("Encryption and Decryption");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(600, 400);

      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(4, 2));

      JTextField encryptInput = new JTextField();
      panel.add(new JLabel("Input to Encrypt:"));
      panel.add(encryptInput);

      JTextField decryptInput = new JTextField();
      panel.add(new JLabel("Input to Decrypt:"));
      panel.add(decryptInput);

      JButton encryptButton = new JButton("Encrypt");
      JTextField encryptionKeyInput = new JTextField();
      panel.add(encryptButton);
      panel.add(encryptionKeyInput);

      JButton decryptButton = new JButton("Decrypt");
      JTextField decryptionKeyInput = new JTextField();
      panel.add(decryptButton);
      panel.add(decryptionKeyInput);

      JTextArea encryptedOutput = new JTextArea();
      encryptedOutput.setEditable(false);
      panel.add(new JLabel("Encrypted Output:"));
      panel.add(encryptedOutput);

      JTextArea decryptedOutput = new JTextArea();
      decryptedOutput.setEditable(false);
      panel.add(new JLabel("Decrypted Output:"));
      panel.add(decryptedOutput);

      String[] encryptionOptions = {"One-time pad (OTP)", "3DES", "AES"};
      JComboBox<String> encryptionDropdown = new JComboBox<>(encryptionOptions);
      panel.add(new JLabel("Select Encryption:"));
      panel.add(encryptionDropdown);

      // Encrypt button action listener
      encryptButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String input = encryptInput.getText();
          String encryptionKey = encryptionKeyInput.getText();
          if (encryptionDropdown.getSelectedItem().equals("One-time pad (OTP)")) {
            // Use OneTimePadding class for encryption
            String encryptedResult = OneTimePadding.encrypt(input, encryptionKey);
            encryptedOutput.setText(encryptedResult);
          } else if (encryptionDropdown.getSelectedItem().equals("3DES")) {
            // // Use DES class for encryption
            String encryptedResult = DES.encrypt(input, encryptionKey);
            encryptedOutput.setText(encryptedResult);
          } else if (encryptionDropdown.getSelectedItem().equals("AES")) {
            // // Use AES class for encryption
            String encryptedResult = AES.encrypt(input, encryptionKey);
            encryptedOutput.setText(encryptedResult);
          }
        }
      });

      // Decrypt button action listener
      decryptButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String input = decryptInput.getText();
          String decryptionKey = decryptionKeyInput.getText();
          if (encryptionDropdown.getSelectedItem().equals("One-time pad (OTP)")) {
            // Use OneTimePadding class for decryption
            String decryptedResult = OneTimePadding.decrypt(input, decryptionKey);
            decryptedOutput.setText(decryptedResult);
          } else if (encryptionDropdown.getSelectedItem().equals("3DES")) {
            // Use DES class for decryption
            String decryptedResult = DES.decrypt(input, decryptionKey);
            decryptedOutput.setText(decryptedResult);
          } else if (encryptionDropdown.getSelectedItem().equals("AES")) {
            // Use AES class for decryption
            String decryptedResult = AES.decrypt(input, decryptionKey);
            decryptedOutput.setText(decryptedResult);
          }
        }
      });
      frame.add(panel);
      frame.setVisible(true);
    });
  }
}