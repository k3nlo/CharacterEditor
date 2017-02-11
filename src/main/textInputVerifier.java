package main;

import java.awt.Color;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

/*
 * An instance of this class will be used to verify the name entered by the user.
 */
public class textInputVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText();
        
        //if (Character.isDigit(text.charAt(0))) return false;
        //if (text.length() > 20) return false;
        
        if (text.matches("[a-zA-Z][a-zA-Z0-9]*") && text.length() < 20) return true;
        
        else
        {
            input.setBackground( Color.red );
            return false;
        }
                                     
    }
}