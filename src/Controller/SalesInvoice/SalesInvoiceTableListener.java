/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.SalesInvoice;

import MODEL.SalesInvoice.SalesInvoiceHeader;
import MODEL.SalesInvoice.SalesInvoiceLine;
import MODEL.SalesInvoice.SalesLineTable;
import VIEW.SalesInvoice.BillFrame;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Sarah
 */
public class SalesInvoiceTableListener implements ListSelectionListener {
    
    
    private BillFrame frame ;

    public SalesInvoiceTableListener(BillFrame frame) {
        this.frame = frame;
    }
    
    

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("click in row ");
        int selectedRow = frame.getInvoiceTable().getSelectedRow();
          //System.out.println(selectedRow);
          System.out.println("YOU SELECTED ROW");
          if(selectedRow != -1 ){
          SalesInvoiceHeader  selectedHeader  = frame.getInvoicesArray().get(selectedRow);
          ArrayList<SalesInvoiceLine> SalesInvoiceLines =  selectedHeader.getLines();
          SalesLineTable lineTable = new SalesLineTable(SalesInvoiceLines);
          frame.setLinesArray(SalesInvoiceLines);  
          frame.getInvoiceItems().setModel(lineTable);     
          frame.getCustomerNameLBL().setText(selectedHeader.getCustomer());
          frame.getInvNumLBL().setText("" + selectedHeader.getNumber());
          frame.getInvDateLBL().setText(BillFrame.dateFormat.format(selectedHeader.getInvoiceDate()));  
          frame.getInvTotalLBL().setText("" +selectedHeader.getInvoiceTotal());
          }
        
    }
    
    
    
    
}
