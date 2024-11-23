// package com.mycompany.study;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class Cryptography extends JFrame{
  
    JLabel ME = new JLabel("Message to Encrypt");
    JLabel MD = new JLabel("Message to Decrypt");
    JLabel EK = new JLabel("Encryption Key");
    JLabel DK = new JLabel("Decryption Key");
    JLabel CA = new JLabel("Choose Algorithm");
    
    JButton Encrypt;
    JButton Decrypt;
    JButton copyEncryption;
    JButton copyDecryption;
    
    JTextArea MessageToEncrypt;
    JTextArea MessageToDecrypt;
    JTextArea Encrypted;
    JTextArea Decrypted;
    JTextField EncryptionKey;
    JTextField DecryptionKey;
    JComboBox Algorithm;
    
    public Cryptography(){
        
        setSize(650,400);
        setTitle("Cryptography");
        Container c = getContentPane();
        
        
       
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new TitledBorder("Cryptography"));
        mainPanel.setLayout(new GridLayout(2,2));
        mainPanel.setBackground(new Color(56, 76, 133));
        
        JPanel  panel1 = new JPanel();
        panel1.setBorder(new TitledBorder("Encryption"));
        panel1.setLayout(new BorderLayout());
        panel1.add(ME, BorderLayout.NORTH);
        MessageToEncrypt = new JTextArea("Enter the Message to Encrypt");
        panel1.add(MessageToEncrypt, BorderLayout.CENTER);
        
        JPanel panel1_b = new JPanel();
        panel1_b.setLayout(new GridLayout(1,2));
        panel1_b.add(EK);
        EncryptionKey = new JTextField();
        panel1_b.add(EncryptionKey);
        panel1.add(panel1_b, BorderLayout.SOUTH);
        mainPanel.add(panel1);
        
        JPanel  panel2 = new JPanel();
        panel2.setBorder(new TitledBorder("Decryption"));
        panel2.setLayout(new BorderLayout());
        panel2.add(MD, BorderLayout.NORTH);
        MessageToDecrypt = new JTextArea("Enter the Message to Decrypt");
        panel2.add(MessageToDecrypt, BorderLayout.CENTER);
        
        JPanel panel2_b = new JPanel();
        panel2_b.setLayout(new GridLayout(1,2));
        panel2_b.add(DK);
        DecryptionKey = new JTextField();
        panel2_b.add(DecryptionKey);
        panel2.add(panel2_b, BorderLayout.SOUTH);
        mainPanel.add(panel2);
        
        JPanel panel3 = new JPanel();
        panel3.setBorder(new TitledBorder("Decrypted"));
        panel3.setLayout(new BorderLayout());
        
        JPanel panel3_t = new JPanel();
        panel3_t.setLayout(new GridLayout(1,2));
        Decrypt = new JButton("Decrypt");
        copyDecryption = new JButton("copyDecryption");
        panel3_t.add(Decrypt);
        panel3_t.add(copyDecryption);
        panel3.add(panel3_t,BorderLayout.NORTH);
        Decrypted = new JTextArea("Decrypted");
        panel3.add(Decrypted, BorderLayout.CENTER);
        
        
        JPanel panel4 = new JPanel();
        panel4.setBorder(new TitledBorder("Encrypted"));
        panel4.setLayout(new BorderLayout());
        
        JPanel panel4_t = new JPanel();
        panel4_t.setLayout(new GridLayout(1,2));
        Encrypt = new JButton("Encrypt");
        copyEncryption = new JButton("copyEncryption");
        panel4_t.add(Encrypt);
        panel4_t.add(copyEncryption);
        panel4.add(panel4_t,BorderLayout.NORTH);
        Encrypted = new JTextArea("Encrypted");
        panel4.add(Encrypted, BorderLayout.CENTER);
        JPanel panel4_b = new JPanel();
        panel4_b.setLayout(new GridLayout(1,2));
        panel4_b.add(CA);
        String items[] = {"One-time pad","3DES","AES"};
        Algorithm = new JComboBox(items);
        
        panel4_b.add(Algorithm);
        panel4.add(panel4_b,BorderLayout.SOUTH);
        
        mainPanel.add(panel4);
        mainPanel.add(panel3);
        
        c.add(mainPanel);
        
        
        //Now lets write the implementation of Each buttons...using Lambda expression
        
        //String Algorithms = (String)Algorithm.getSelectedItem();
        if(Algorithm.getSelectedItem() == "One-time pad"){
            Encrypt.addActionListener((ActionEvent e)->{
            StringBuilder ciphertext = new StringBuilder();
            String plaintext = MessageToEncrypt.getText();
            String key = EncryptionKey.getText();
            
            if (key.length() < plaintext.length()) {
                throw new IllegalArgumentException("Key length must be at least as long as the plaintext");
            }
        
            for (int i = 0; i < plaintext.length(); i++) {
                char plainChar = plaintext.charAt(i);
                char keyChar = key.charAt(i);
            
            // XOR the plaintext character with the corresponding key character
                char encryptedChar = (char) (plainChar ^ keyChar);
            
                ciphertext.append(encryptedChar);
            }
        
            ciphertext.toString();
            Encrypted.setText(""+ciphertext);
            });
        
        Decrypt.addActionListener((ActionEvent e)->{
            StringBuilder plaintext = new StringBuilder();
            String ciphertext = MessageToDecrypt.getText();
            String key = DecryptionKey.getText();
            
        
        // Ensure the key length is at least as long as the plaintext
        if (key.length() < ciphertext.length()) {
            throw new IllegalArgumentException("Key length must be at least as long as the plaintext");
        }
        
        for (int i = 0; i < ciphertext.length(); i++) {
            char cipherChar = ciphertext.charAt(i);
            char keyChar = key.charAt(i);
            
            // XOR the plaintext character with the corresponding key character
            char encryptedChar = (char) (cipherChar ^ keyChar);
            
            plaintext.append(encryptedChar);
        }
        
        plaintext.toString();
        Decrypted.setText(""+plaintext);
        });
    }
        else if(Algorithm.getSelectedItem() == "3DES"){
        
        Encrypt.addActionListener((ActionEvent e)->{
            try {
                String ciphertext;
                String plaintext = MessageToEncrypt.getText();
                String key = EncryptionKey.getText();
                
                byte[] plaintextBytes = plaintext.getBytes(StandardCharsets.UTF_8);
                byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
                
                KeySpec keySpec = new DESedeKeySpec(keyBytes);
                SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
                SecretKey secretKey = keyFactory.generateSecret(keySpec);
                
                Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                
                byte[] encryptedBytes = null;
                try {
                    encryptedBytes = cipher.doFinal(plaintextBytes);
                } catch (IllegalBlockSizeException | BadPaddingException ex) {
                    Logger.getLogger(Cryptography.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                ciphertext = Base64.getEncoder().encodeToString(encryptedBytes);
                
                Encrypted.setText(""+ciphertext);
            } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException ex) {
                Logger.getLogger(Cryptography.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Decrypt.addActionListener((ActionEvent e)->{
            try {
                String encryptedMessage = MessageToDecrypt.getText();
                String key = DecryptionKey.getText();
                byte[] secretKeyBytes = Base64.getDecoder().decode(key);
                
                // Create DESede key specification
                DESedeKeySpec keySpec = new DESedeKeySpec(secretKeyBytes);
                SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
                SecretKey secretKey = keyFactory.generateSecret(keySpec);
                
                // Create the cipher and initialize it for decryption
                Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
                
                // Decode the encrypted message
                byte[] encryptedMessageBytes = Base64.getDecoder().decode(encryptedMessage.getBytes(StandardCharsets.UTF_8));
                
                // Decrypt the message
                byte[] decryptedMessageBytes = cipher.doFinal(encryptedMessageBytes);
                
                // Convert the decrypted message to a string
                String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
            } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException ex) {
                Logger.getLogger(Cryptography.class.getName()).log(Level.SEVERE, null, ex);
            }
            });
        }
            else if(Algorithm.getSelectedItem() == "AES")
                {
                
                }

    }
  
    public static void main(String[] args) {
        Cryptography a = new Cryptography();
        a.setVisible(true);
    }    
}
