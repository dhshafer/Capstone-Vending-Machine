package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditLog {
    private final String AUDIT_LOG_FILE_PATH = "src/main/resources/Log.txt";

    public AuditLog() {
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
}
