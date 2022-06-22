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
public class OpenHeaderDialog extends JDialog {
    
  

    public OpenHeaderDialog() {
    }

//    public OpenHeaderDialog(Frame owner) {
//        super(owner);
//    }

    public OpenHeaderDialog(BillFrame frame) {

        invoiceCustomerNameLBl = new JLabel("Customer Name");
        invoicecustomerField = new JTextField(40);
        
        invoiceDateLBl = new JLabel("Date Of Invoice");
        invoiceDateField = new JTextField(40);
        
        
        okButton = new JButton("OK");
        cancelButton = new JButton ("Cancel");
        
         

          
        //ActionCommand
        okButton.setActionCommand("createNewInvoiceHeaderOK");
        cancelButton.setActionCommand("createNewInvoiceHeaderCancel");
        
        //AddACTIONlISTENER
        
        okButton.addActionListener(frame.getActionListener());
        cancelButton.addActionListener(frame.getActionListener());
        
     
        setLayout(new GridLayout(6, 3));
        
        //ADD
       add(invoiceCustomerNameLBl);
       add(invoicecustomerField);
       add(invoiceDateLBl);
       add(invoiceDateField);
       add(okButton);
       add(cancelButton);
  
       pack();
      
    }
    
       // VariablesDeclaration
        private  JLabel invoiceCustomerNameLBl ;        
        private JTextField invoicecustomerField ;
        private JLabel invoiceDateLBl ;
        private JTextField invoiceDateField ;
        private JButton okButton ;
        private JButton cancelButton  ;

        
        //METHOD 
        
    public JTextField getInvoicecustomerField() {
        return invoicecustomerField;
    }

    public JTextField getInvoiceDateField() {
        return invoiceDateField;
    }
        
     


    
    
    
    
    
    
    
    
}
