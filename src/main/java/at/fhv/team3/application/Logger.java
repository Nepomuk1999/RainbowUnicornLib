package at.fhv.team3.application;

import java.io.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by David on 11/30/2017.
 */
public class Logger {

    private static Logger _logger;
    private BufferedWriter _writer;
    private BufferedReader _reader;
    private String _filename;


    public static Logger getInstance(){
        if (_logger == null){
            _logger = new Logger();
        }
        return _logger;
    }

    private Logger(){
        init();
    }

    public void init(){
        try {
            setFile();
            _reader = new BufferedReader(new FileReader(_filename));
            _writer = new BufferedWriter(new FileWriter(_filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> read(){
        List<String> allLogs = new LinkedList<String>();
        try {
            String line = _reader.readLine();
            while(line != null){
                allLogs.add(line);
                line = _reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allLogs;
    }

    public void write(String log){
        List<String> allLogs = read();
        allLogs.add(log);

            try {
                for(String line : allLogs) {
                    _writer.write(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void setFile(){
        _filename = "Log" + new Date().toString() + ".txt";
    }

    public static void log(String log){
        getInstance().write(log);
    }

}
