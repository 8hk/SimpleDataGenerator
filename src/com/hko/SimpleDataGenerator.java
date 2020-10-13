package com.hko;
import com.hko.cli.ProgressBar;
import com.hko.generators.LineGenerator;
import org.apache.commons.cli.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.concurrent.*;

public class SimpleDataGenerator {
    public static void main(String[] args) {
        Options options = new Options();

        Option columns_option = new Option("c", "columns_option", true, "how many columns for each line");
        columns_option.setRequired(true);
        options.addOption(columns_option);

        Option string_length_option = new Option("ch", "string_length_option", true, "character size of each columns");
        string_length_option.setRequired(true);
        options.addOption(string_length_option);


        Option lines_option = new Option("l", "lines_option", true, "how many lines will be generated");
        lines_option.setRequired(true);
        options.addOption(lines_option);


        Option seperator_option = new Option("sep", "seperator_option", true, "which seperator will be used between columns");
        seperator_option.setRequired(true);
        options.addOption(seperator_option);

        Option filename_option = new Option("f", "filename_option", true, "filename");
        filename_option.setRequired(true);
        options.addOption(filename_option);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (org.apache.commons.cli.ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }
        int columns = Integer.parseInt(cmd.getOptionValue("columns_option"));
        int lines = Integer.parseInt(cmd.getOptionValue("lines_option"));
        int string_length = Integer.parseInt(cmd.getOptionValue("string_length_option"));
        String seperator = cmd.getOptionValue("seperator_option");
        String filename = cmd.getOptionValue("filename_option");
        String buff = "";
        try {
            ExecutorService executor = Executors.newFixedThreadPool(100);
//            ProgressBar progressBar = new ProgressBar();
            int buff_len=100000;
            ProgressBar.updateProgress(0);
            for (int i = 0; i < lines; i++) {
                Future<String> task = executor.submit(new LineGenerator(columns, string_length, seperator));
                String result = task.get(); // this blocks until result is ready
                buff = buff + result;
                if (i % buff_len == 0 && i > 0) {
                    writeResult(filename, buff);
                    buff = "";
                }
                if(lines<buff_len && i==lines-1){
                    writeResult(filename, buff);
                    buff="";
                }
                ProgressBar.updateProgress((double) i / lines);
            }
            ProgressBar.updateProgress(1);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.exit(0);
    }


    public static void writeResult(String writeFileName, String text) {
        try {
            FileWriter fw = new FileWriter(writeFileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(text);
            bw.close();
        } catch (Exception ex) {
            System.out.println("Error writing to file '" + writeFileName + "' " + ex.toString());
        }
    }
}
