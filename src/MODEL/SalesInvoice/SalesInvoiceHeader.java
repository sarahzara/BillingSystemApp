/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL.SalesInvoice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author Sarah
 */
public class SalesInvoiceHeader {
    
    private int number ;
    private String customer ;
    private Date invoiceDate ; 
    private ArrayList<SalesInvoiceLine > lines ;
    private DateFormat date = new SimpleDateFormat("dd-MM-yyyy");

    public SalesInvoiceHeader() {
        
    }

    public SalesInvoiceHeader(int number, String customer, Date invoiceDate) {
        this.number = number;
        this.customer = customer;
        this.invoiceDate = invoiceDate;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
    
   

     public ArrayList<SalesInvoiceLine> getLines() {
        if (lines == null) {
            lines = new ArrayList<>();
        }
        return lines;
    }

    public void setLines(ArrayList<SalesInvoiceLine> lines) {
        this.lines = lines;
    }
    
    public double getInvoiceTotal() {
        double total = 0.0;
        
        for (int i = 0; i < getLines().size(); i++) {
            total += getLines().get(i).getTotalOfLines();
        }
        
        return total;
    }
    

    @Override
    public String toString() {
        return "SalesInvoiceHeader{" + "number=" + number + ", customer=" + customer + ", invoiceDate=" + invoiceDate + ", lines=" + lines + '}';
    }
    
    public String toString2() {
        return number + "," + date.format(invoiceDate) + "," + customer ;
    }
    
    
}
  
  
    
