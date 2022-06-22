/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL.SalesInvoice;

import VIEW.SalesInvoice.BillFrame;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Sarah
 */
public class SalesHeaderTable extends AbstractTableModel{
    
    
    private String[] tableOFHeader = {"SalesInvoice Number ", "SalesInvoice Date", "Customer Name", "SalesInvoice Total"};
    private ArrayList<SalesInvoiceHeader> invoicesArray ;

    
    
    
    public SalesHeaderTable(ArrayList<SalesInvoiceHeader> invoicesArray) {
        this.invoicesArray = invoicesArray;
    }
    
    

    @Override
    public int getRowCount() {
       // return 5 ;
      return  invoicesArray.size() ;
    }

    @Override
    public int getColumnCount() {
        //return  3 ;
       return tableOFHeader.length ;
       
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      SalesInvoiceHeader headerData = invoicesArray.get(rowIndex);
        switch (columnIndex) {
            case 0  :
                return  headerData.getNumber();                    
            case 1  :
                return  BillFrame.dateFormat.format(headerData.getInvoiceDate());   
            case 2  :
                return  headerData.getCustomer();                         
            case 3  : 
                return  headerData.getInvoiceTotal();                      
        }
        return "";
    }
    
    @Override
    public String getColumnName(int column) {
      return tableOFHeader[column];
    }
    
    
    
    
}
