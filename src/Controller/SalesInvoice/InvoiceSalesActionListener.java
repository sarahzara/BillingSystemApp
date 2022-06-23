/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.SalesInvoice;

import MODEL.SalesInvoice.SalesHeaderTable;
import MODEL.SalesInvoice.SalesInvoiceHeader;
import MODEL.SalesInvoice.SalesInvoiceLine;
import MODEL.SalesInvoice.SalesLineTable;
import VIEW.SalesInvoice.BillFrame;
import VIEW.SalesInvoice.OpenHeaderDialog;
import VIEW.SalesInvoice.OpenLineDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Sarah
 */
public class InvoiceSalesActionListener implements ActionListener{
    
     // VariablesDeclaration
    private BillFrame frame ;
    private ArrayList<SalesInvoiceHeader> SalesInvoiceHeaders;
    private OpenHeaderDialog  headerDialog ;
    private OpenLineDialog    lineDialog ;


    public InvoiceSalesActionListener() {
    }
  
    public InvoiceSalesActionListener(BillFrame frame){
    this.frame = frame ;
    }
    
    public InvoiceSalesActionListener(OpenHeaderDialog headerDialog, OpenLineDialog lineDialog) {
        this.headerDialog = headerDialog;
        this.lineDialog = lineDialog;
    }

   

    @Override
    public void actionPerformed(ActionEvent e ) {
        switch (e.getActionCommand()){
                case "LOAD Files"  :
                LOADFiles();
                break;
                
                case "SAVED Files"  :
                SAVEDFiles();
                break;
                
                case "Create NeW Invoice"  :
                createNeWInvoice();
                break;
                
                case "deleteInvoice":
                deleteInvoice();
                break;
                        
                
                case "SAVE"  :
                SAVE();
                break;
                
                case "CANCEL"  :
                CANCEL();
                break;
                
                
                //OpenHeaderDialog( OK , SAVE )
                case "createNewInvoiceHeaderOK":
                   NewInvoiceHeaderOK();
                    break;
                
                case "createNewInvoiceHeaderCancel":
                    NewInvoiceHeaderCancel();
                     break;
                
                // OpenLineDialog ( ok , save)
                case "createNewInvoiceLineOK":
                    NewInvoiceLineOK();
                     break;
                
                case "createNewInvoiceLineCancel":
                    NewInvoiceLineCancel();
                     break;
                
        }
        
        
        
    }

  
      
     private void LOADFiles() {
            //***start Reading File 
             System.out.println("********************** Start reading OF Header and Lines Files ///****************************** ");
             
             
        JFileChooser fileChooser = new JFileChooser();       
        try {
            int resultFile = fileChooser.showOpenDialog(frame);
            if (resultFile == JFileChooser.APPROVE_OPTION) {
                File loadHeaderFile = fileChooser.getSelectedFile();
                Path loadHeaderPath = Paths.get(loadHeaderFile.getAbsolutePath());
                List<String> loadHeaderLines = Files.readAllLines(loadHeaderPath );
                ArrayList<SalesInvoiceHeader> SalesInvoiceHeaders  = new ArrayList<>();
                for (String loadHeaderLine : loadHeaderLines ) {
                    String[] arrayOfLoadFile = loadHeaderLine.split(",");
                      String string1 = arrayOfLoadFile[0];
                      String string2 = arrayOfLoadFile[1];
                      String string3 = arrayOfLoadFile[2];
             
                    int codeFirstStrings = Integer.parseInt(string1);
                    //InvoiceFrame.dateFormat.parse(str2);
                    Date invoiceDate = BillFrame.dateFormat.parse(string2);  
                    SalesInvoiceHeader header = new SalesInvoiceHeader(codeFirstStrings, string3 , invoiceDate);
                    SalesInvoiceHeaders.add(header);
                    
                     
                    //*** read of HEADER FILE 
                  System.out.println("please notes FILE OF HEADER");
                  System.out.println(header);
                }
                frame.setHeadersArray(SalesInvoiceHeaders);
                resultFile = fileChooser.showOpenDialog(frame);
                if (resultFile == JFileChooser.APPROVE_OPTION) {
                    File loadlineFile = fileChooser.getSelectedFile();
                    Path loadlinePath = Paths.get(loadlineFile.getAbsolutePath());
                    List<String> linesOfLines = Files.readAllLines(loadlinePath);
                    ArrayList<SalesInvoiceLine> SalesInvoiceLines = new ArrayList<>();
                    for (String lineOfLine : linesOfLines) {
                        String[] arrayOfLineFile = lineOfLine.split(",");
                         String string1 = arrayOfLineFile[0];
                         String string2 = arrayOfLineFile[1];
                         String string3 = arrayOfLineFile[2];
                         String string4 = arrayOfLineFile[3];
 
                        int codeFirstStrings = Integer.parseInt(string1);
                        double priceThridStrings = Double.parseDouble(string3);
                        int count = Integer.parseInt(string4);
                        SalesInvoiceHeader invHeader  = frame.getInviceObject(codeFirstStrings);
                        SalesInvoiceLine line = new SalesInvoiceLine(count, string2, priceThridStrings , invHeader);
                        invHeader.getLines().add(line);
                        // Start Reading LINE Files 
                         System.out.println("please notes FILE OF LINES");
                         System.out.println(lineOfLine);

                    }
            }
                SalesHeaderTable headerTablel = new SalesHeaderTable(SalesInvoiceHeaders);
                frame.setSalesHeaderTable(headerTablel);
                frame.getInvoiceTable().setModel(headerTablel);
                
                
               //*****End Reading 
               System.out.println("********************/// End OF reading OF Header and Lines Files ///*************************** ");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error of Exception", JOptionPane.ERROR_MESSAGE);
        } catch (ArrayIndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error of Array", JOptionPane.ERROR_MESSAGE);
        } catch(NullPointerException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error in NULL ", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            Logger.getLogger(InvoiceSalesActionListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  


    private void createNeWInvoice() {
        
        headerDialog = new OpenHeaderDialog(frame);
        headerDialog.setVisible(true);
     
        System.out.println("please notes YOU CREATE NEW INVOICE");

    }

    
    private void  deleteInvoice() {
        int selectedLineIndex = frame.getInvoiceTable().getSelectedRow();
        if(selectedLineIndex != -1 ){
           
            frame.getInvoicesArray().remove(selectedLineIndex);
            frame.getSalesHeaderTable().fireTableDataChanged();
            
            frame.getInvoiceItems().setModel(new SalesLineTable(null));
            
            frame.setLinesArray(null);
            
            frame.getCustomerNameLBL().setText("");
            frame.getInvNumLBL().setText("");
            frame.getInvTotalLBL().setText("");
            frame.getInvDateLBL().setText("");
            
            System.out.println("please notes YOU DELETE INVOICE");
        }

    }


    
    //Open Header Dialog( OK ) Method
    private void NewInvoiceHeaderOK() {
        try{
        headerDialog.setVisible(false);
        String customerName = headerDialog.getInvoicecustomerField().getText();
        String dateHeader = headerDialog.getInvoiceDateField().getText();
        Date date = new Date();
        try{
        date = BillFrame.dateFormat.parse(dateHeader);
        }catch(ParseException e) {
            JOptionPane.showMessageDialog(frame, "wrong in date , resetting to today.", "Invalid date format", JOptionPane.ERROR_MESSAGE);
        }
        int invoiceNumber = 0 ;
        for (SalesInvoiceHeader header :frame.getInvoicesArray()){
            if (header.getNumber() > invoiceNumber){
                invoiceNumber = header.getNumber();
            }
        
        }
        invoiceNumber++ ;
        SalesInvoiceHeader invoiceheader = new SalesInvoiceHeader(invoiceNumber, customerName , date);
        frame.getInvoicesArray().add(invoiceheader);
        frame.getSalesHeaderTable().fireTableDataChanged();
        headerDialog.dispose();
        headerDialog =null; 
        
         System.out.println("you click **YES** ADD new invoice ");
         
          }catch (NullPointerException ex){
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error in OK message", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    //Open Header Dialog(cancel ) Method
    private void NewInvoiceHeaderCancel() {
        headerDialog.setVisible(false);
        headerDialog.dispose();
        headerDialog =null;
        System.out.println("you click **cancel** Delete new invoice ");
         
    }
  
     // Open Line Dialog ( cancel) Method
    private void NewInvoiceLineCancel() {
         lineDialog.setVisible(false);
         lineDialog.dispose();
         lineDialog = null ;
         System.out.println("you click **cancel** Delete new line invoice ");
         

    }
    
     // Open Line Dialog ( ok ) Method
    private void NewInvoiceLineOK() {
        try{
        lineDialog.setVisible(false);
        
        String lineName = lineDialog.getInvoiceNameField().getText();
        String lineCount = lineDialog.getInvoiceCountField().getText();
        String linePrice = lineDialog.getInvoicePriceField().getText();
        
        int countDialog = 1 ;
        double priceDialog = 1 ;
        
        try{
            countDialog=Integer.parseInt(lineCount);
        }catch(NumberFormatException eframe){
              JOptionPane.showMessageDialog(frame, "Error in number ", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }
        
        try{
            priceDialog =Double.parseDouble(linePrice);
        }catch(NumberFormatException eframe){
            JOptionPane.showMessageDialog(frame, "Error in number", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }
        int invoiceHeader = frame.getInvoiceTable().getSelectedRow();
        if (invoiceHeader != -1 ){
            SalesInvoiceHeader header = frame.getInvoicesArray().get(invoiceHeader);
            SalesInvoiceLine line = new SalesInvoiceLine(countDialog ,  lineName ,priceDialog  , header  );
            frame.getLinesArray().add(line);
            SalesLineTable lineTable = (SalesLineTable) frame.getInvoiceItems().getModel();
            lineTable.fireTableDataChanged();
            frame.getSalesHeaderTable().fireTableDataChanged();
             System.out.println("you click **YES** ADD new line invoice ");
          
            
        }
        
         frame.getInvoiceTable().setRowSelectionInterval(invoiceHeader, invoiceHeader);
         lineDialog.dispose();
         lineDialog = null ;
         
        }catch (IllegalArgumentException ex){
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }
    
    
    
    
    private void SAVE() {
        lineDialog = new OpenLineDialog(frame);
        lineDialog.setVisible(true);
        System.out.println("you click **YES** ADD new line invoice ");
    }

    private void CANCEL() {
        int selectedLineIndex = frame.getInvoiceItems().getSelectedRow();
        int selectedInvoice = frame.getInvoiceTable().getSelectedRow();    
        if(selectedLineIndex != -1 ){
            frame.getLinesArray().remove(selectedLineIndex);
            SalesLineTable lineTable = (SalesLineTable) frame.getInvoiceItems().getModel();
            lineTable.fireTableDataChanged();
            frame.getInvTotalLBL().setText("" + frame.getInvoicesArray().get(selectedInvoice).getInvoiceTotal());
            frame.getSalesHeaderTable().fireTableDataChanged();
            frame.getInvoiceTable().setRowSelectionInterval(selectedInvoice, selectedInvoice);
     
        }
        System.out.println("you click **cancel** DElete new line invoice ");
        
    }
    
    
    private void SAVEDFiles() {
        ArrayList<SalesInvoiceHeader> invoicesArray = frame.getInvoicesArray();
        JFileChooser fileUploaded = new JFileChooser();
        try{
            int resultFile = fileUploaded.showSaveDialog(frame);
            if(resultFile == JFileChooser.APPROVE_OPTION){
                File fileHeader = fileUploaded.getSelectedFile();
                FileWriter ownerOfHeader = new FileWriter(fileHeader);
                String tableOFHeader = "";
                String tableOFLine = "";
                for(SalesInvoiceHeader invoiceHeader : invoicesArray){
                    tableOFHeader += invoiceHeader.toString2();
                    tableOFHeader += "\n";
                    for(SalesInvoiceLine  invoiceLine : invoiceHeader.getLines()){
                         tableOFLine += invoiceLine.toString2();
                         tableOFLine += "\n";                  
                    }
                }
                tableOFHeader = tableOFHeader.substring(0, tableOFHeader.length()-1);
                tableOFLine = tableOFLine.substring(0, tableOFLine.length()-1);
                resultFile = fileUploaded.showSaveDialog(frame);
                File fileLine = fileUploaded.getSelectedFile();
                FileWriter ownerOfLine = new FileWriter(fileLine);
                
                   System.out.println("********************** Start upload OF Header and Lines Files ///****************************** ");
                   
                  
                ownerOfHeader.write(tableOFHeader);
                 System.out.println("HERE IS ownerOfHeader");
                 System.out.println(ownerOfHeader);
                 
                 
                ownerOfLine.write(tableOFLine);
                 System.out.println("HERE IS ownerOfLine");
                 System.out.println(ownerOfLine);
                             
                ownerOfHeader.close();
                ownerOfLine.close();
                

                System.out.println("********************/// End upload OF Header and Lines Files ///*************************** ");
          
            }
        }catch(IOException eframe){
             JOptionPane.showMessageDialog(frame, eframe.getMessage(), "Error in Saved File ", JOptionPane.ERROR_MESSAGE);
        }

    }
        
        
}




