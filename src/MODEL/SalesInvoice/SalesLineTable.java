/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL.SalesInvoice;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Sarah
 */
public class SalesLineTable  extends AbstractTableModel {

    
    private String[] tableOFLine =  {"SalesItem Name", "SalesItem Price", "SalesCount", "SalesItem Total"};
    private ArrayList<SalesInvoiceLine> LinesArray ;

    public SalesLineTable(ArrayList<SalesInvoiceLine> LinesArray) {
        this.LinesArray = LinesArray;
    }
    
    
    
    @Override
    public int getRowCount() {
        return LinesArray == null ? 0 : LinesArray.size();
    }

    @Override
    public int getColumnCount() {
        return tableOFLine.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

      if (LinesArray == null) {
            return "";
        } else {
        SalesInvoiceLine lineData = LinesArray.get(rowIndex);
        switch(columnIndex){
            case 0  :
                return  lineData.getInvoiceItems();                 
            case 1  :
                return  lineData.getInvoicePrice();    
            case 2  :
                return  lineData.getInvoiceCount();
            case 3  : 
                return  lineData.getTotalOfLines();
                
            default : 
                return "" ;
          }
        }
    }
 
    
    @Override
    public String getColumnName(int column) {
      return tableOFLine[column];
    }
    
}
