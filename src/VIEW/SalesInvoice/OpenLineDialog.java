/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW.SalesInvoice;

import java.awt.Frame;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Sarah
 */
public class OpenLineDialog extends JDialog{

    public OpenLineDialog() {
    }

    public OpenLineDialog(Frame owner) {
        super(owner);
    }
    
     public OpenLineDialog(BillFrame frame) {
        
         invoiceNameField = new JTextField(35);
         invoiceNameLabl = new JLabel("Name OF Invoice");
         
         invoiceCountField = new JTextField(35);
         invoiceCountLabl = new JLabel("Count OF Invoice ");
         
         invoicePriceField = new JTextField(35);
         invoicePriceLabl = new JLabel("Price OF Invoice");
         
         okButton = new JButton("OK");
         cancelButton = new JButton ("Cancel");
         
           setLayout(new GridLayout(6, 3));
         
        //ActionCommand
        okButton.setActionCommand("createNewInvoiceLineOK" );
        cancelButton.setActionCommand("createNewInvoiceLineCancel");
        
        //AddACTIONlISTENER
        
        okButton.addActionListener(frame.getActionListener());
        cancelButton.addActionListener(frame.getActionListener());
        
        
        //ADD
        
        add(invoiceNameLabl);
        add(invoiceNameField );
        add(invoiceCountLabl);
        add(invoiceCountField);
        add(invoicePriceLabl);
        add(invoicePriceField);
        add(okButton);
        add(cancelButton);
        
        
        
          pack();
         
    }

     
     
     // VariablesDeclaration
        private JTextField invoiceNameField ;        
        private JLabel  invoiceNameLabl ;
        private JTextField invoiceCountField ;        
        private JLabel  invoiceCountLabl ;
        private JTextField invoicePriceField  ;        
        private JLabel  invoicePriceLabl ;
        private JButton okButton ;
        private JButton cancelButton  ;

    public JTextField getInvoiceNameField() {
        return invoiceNameField;
    }

    public JTextField getInvoiceCountField() {
        return invoiceCountField;
    }

    public JTextField getInvoicePriceField() {
        return invoicePriceField;
    }
        
       
    
    
    
}
