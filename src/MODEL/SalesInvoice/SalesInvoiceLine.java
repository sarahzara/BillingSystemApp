/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL.SalesInvoice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Sarah
 */
public class SalesInvoiceLine {
    
   // int invoiceNumber ;
    private int invoiceCount ;
    private String invoiceItems ;
    private double invoicePrice ;
    
    private SalesInvoiceHeader invoiceHeader ; 
    private DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
 
    public SalesInvoiceLine() {
    }

    public SalesInvoiceLine(int invoiceCount, String invoiceItems, double invoicePrice, SalesInvoiceHeader invoiceHeader) {
        this.invoiceCount = invoiceCount;
        this.invoiceItems = invoiceItems;
        this.invoicePrice = invoicePrice;
        this.invoiceHeader = invoiceHeader;
    }

    public SalesInvoiceHeader getInvoiceHeader() {
        return invoiceHeader;
    }

    public void setInvoiceHeader(SalesInvoiceHeader invoiceHeader) {
        this.invoiceHeader = invoiceHeader;
    }

    public int getInvoiceCount() {
        return invoiceCount;
    }

    public void setInvoiceCount(int invoiceCount) {
        this.invoiceCount = invoiceCount;
    }

    public String getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(String invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public double getInvoicePrice() {
        return invoicePrice;
    }

    public void setInvoicePrice(double invoicePrice) {
        this.invoicePrice = invoicePrice;
    }
    
    
    public double getTotalOfLines(){
           return invoiceCount * invoicePrice ;
    }

    @Override
    public String toString() {
        return "SalesInvoiceLine{" + "invoiceCount=" + invoiceCount + ", invoiceItems=" + invoiceItems + ", invoicePrice=" + invoicePrice + ", invoiceHeader=" + invoiceHeader + '}';
    }
    
    
    public String toString2() {
        return invoiceHeader.getNumber()  + "," + invoiceItems + "," + invoicePrice + "," + invoiceCount;
    }

    
    
    
    
}
