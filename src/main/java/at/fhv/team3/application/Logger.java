package at.fhv.team3.application;

import java.io.*;
import java.text.SimpleDateFormat;
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
    private File _file;


    public static Logger getInstance(){
        if (_logger == null){
            _logger = new Logger();
        }
        return _logger;
    }

    private Logger(){
        File directory = new File("logfiles");
        directory.mkdir();
        init();
    }

    public void init(){
        try {
            setFile();
            _reader = new BufferedReader(new FileReader(_file));
            _writer = new BufferedWriter(new FileWriter(_file));
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
            try {
              _writer.write(log);
              _writer.newLine();
                _writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void setFile(){
        Date d = new Date();
        SimpleDateFormat ft =  new SimpleDateFormat ("yyyy-MM-dd");

        String date = ft.format(d);

        _file = new File("logfiles/Log" + date + ".txt");
        if (!_file.exists()) {
            try {
                _file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void log(String log){
        getInstance().write(log);
    }

}
