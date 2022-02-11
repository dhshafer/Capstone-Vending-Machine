package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditLog {
    private String AUDIT_LOG_FILE_PATH = "src/main/resources/Log.txt";

    public AuditLog() {
    }

    public void setAUDIT_LOG_FILE_PATH(String AUDIT_LOG_FILE_PATH) {
        this.AUDIT_LOG_FILE_PATH = AUDIT_LOG_FILE_PATH;
    }

    public void writeToFile(String message){
        File logPath = new File(AUDIT_LOG_FILE_PATH);
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
        String output = formatter.format(currentDate) + " " + message;
        try(PrintWriter outputLog = new PrintWriter(new FileOutputStream(logPath, true))){
            outputLog.println(output);
        } catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    public void resetLog(){
        File logPath = new File(AUDIT_LOG_FILE_PATH);
        try(PrintWriter outputLog =  new PrintWriter(logPath)){
            outputLog.print("");
        } catch (FileNotFoundException e){
            System.out.println("Error");
        }
    }
}
