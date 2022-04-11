package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.util.stream.Stream;

public class Controller extends Main{
    File filename;
    Stage primaryStage;
    @FXML
    private Button run;
    @FXML
    public TextArea output;
    @FXML
    public TextArea textedit;

    @FXML

//    public void Run(ActionEvent actionEvent) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Information Dialog");
//        alert.setHeaderText("Look, an Information Dialog");
//        alert.setContentText("I have a great message for you!");
//
//        alert.showAndWait();
//    }
    public void openAction(ActionEvent actionEvent) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Information Dialog");
//        alert.setHeaderText("Look, an Information Dialog");
//        alert.setContentText("I have a great message for you!");
//
//        alert.showAndWait();

        FileChooser file = new FileChooser();
        file.setTitle("Open File");
        File fileToLoad = file.showOpenDialog(primaryStage);
        filelocation(fileToLoad);
        System.out.println(file.getTitle());
        System.out.println(fileToLoad);
        if (fileToLoad != null) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(fileToLoad));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //Use Files.lines() to calculate total lines - used for progress
            long lineCount;
            try (Stream<String> stream = Files.lines(fileToLoad.toPath())) {
                lineCount = stream.count();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Load in all lines one by one into a StringBuilder separated by "\n" - compatible with TextArea
            String line;
            StringBuilder totalFile = new StringBuilder();
            long linesLoaded = 0;
            while (true) {
                try {
                    if (!((line = reader.readLine()) != null)) break;
                    textedit.appendText(line);
                    textedit.appendText("\n");
                    //totalFile.append(line);
                    //totalFile.append("\n");

                } catch (IOException e) {
                    e.printStackTrace();
                }

                //return totalFile.toString();
            }
            filename = fileToLoad;
            System.out.println(totalFile);
            //textedit.setText("hello");
            //textedit.setText(totalFile.toString());
        }
    }
        // save As
        //
        public void saveAs(ActionEvent actionEvent) {

            FileChooser file = new FileChooser();
            file.setTitle("Save Image");
            //System.out.println(pic.getId());
            File file1 = file.showSaveDialog(primaryStage);
            filelocation(file1);
            try {
                PrintWriter writer;
                writer = new PrintWriter(file1);
                writer.println(textedit.getText());
                writer.close();
//                new BufferedWriter(new FileWriter(file1));
//                area.getText().
//            	FileWriter fileWriter = new FileWriter(file1);
//            	area..write(fileWriter);
//            	fileWriter.close();
            }
            catch(IOException ie) {
                System.out.print(ie);
            }
            System.out.println(file1);
            filename= file1;
        }
    // save
    //
    public void save(ActionEvent actionEvent) {

//        FileChooser file = new FileChooser();
//        file.setTitle("Save Image");
//        //System.out.println(pic.getId());
//        File file1 = file.showSaveDialog(primaryStage);
//        filelocation(file1);
        try {
            PrintWriter writer;
            writer = new PrintWriter(filename);
            writer.println(textedit.getText());
            writer.close();
//                new BufferedWriter(new FileWriter(file1));
//                area.getText().
//            	FileWriter fileWriter = new FileWriter(file1);
//            	area..write(fileWriter);
//            	fileWriter.close();
        }
        catch(IOException ie) {
            System.out.print(ie);
        }
        System.out.println(filename);

    }
    // autosave
    //
    public void save2(KeyEvent keyEvent) {

        try {
            System.out.println("save the changes");
            PrintWriter writer;
            writer = new PrintWriter(filename);
            writer.println(textedit.getText());
            writer.close();
//                new BufferedWriter(new FileWriter(file1));
//                area.getText().
//            	FileWriter fileWriter = new FileWriter(file1);
//            	area..write(fileWriter);
//            	fileWriter.close();
        }
        catch(IOException ie) {
            System.out.print(ie);
        }
        System.out.println(filename);


    }




//        FileChooser file = new FileChooser();
//        file.setTitle("Save Image");
//        //System.out.println(pic.getId());
//        File file1 = file.showSaveDialog(primaryStage);
//        filelocation(file1);


    //Run
    public void run(ActionEvent actionEvent) {


        System.out.println(filename);
        String command = "java  " + filename;
        //String command = "java  C:\\Users\\sunitha\\Desktop\\Java\\MavenProject\\src\\main\\java\\InvokingJavaExample.java";
        System.out.println(command);
        Process pro = null;

        try {

            pro = Runtime.getRuntime().exec(command);

//                StringBuilder f = new StringBuilder();
//                f.append(" stdout:")
//                 .append(pro.getInputStream().toString())
//                 .append("\n")
//                 .append(" stderr:")
//                 .append(pro.getErrorStream().toString());


            printLines(command + " stdout:", pro.getInputStream());
            //totalFile1.append("\n");
            printLines(command + " stderr:", pro.getErrorStream());
            output.appendText("\n");
            pro.waitFor();
            String line2 = command + " exitValue() " + pro.exitValue();
            //
            output.appendText(line2);
            System.out.println(command + " exitValue() " + pro.exitValue());
            //ouput.setText(totalFile1.toString());
        } catch (IOException | InterruptedException ioException) {
            ioException.printStackTrace();
        }
    }


    //compile
    public void compile(ActionEvent actionEvent) {


        System.out.println(filename);
        String command = "javac  " + filename;
        //String command = "java  C:\\Users\\sunitha\\Desktop\\Java\\MavenProject\\src\\main\\java\\InvokingJavaExample.java";
        System.out.println(command);
        Process pro = null;

        try {

            pro = Runtime.getRuntime().exec(command);

//                StringBuilder f = new StringBuilder();
//                f.append(" stdout:")
//                 .append(pro.getInputStream().toString())
//                 .append("\n")
//                 .append(" stderr:")
//                 .append(pro.getErrorStream().toString());


            printLines(command + " stdout:", pro.getInputStream());
            //totalFile1.append("\n");
            printLines(command + " stderr:", pro.getErrorStream());
            output.appendText("\n");
            pro.waitFor();
            String line2 = command + " exitValue() " + pro.exitValue();
            //
            output.appendText(line2);
            System.out.println(command + " exitValue() " + pro.exitValue());
            //ouput.setText(totalFile1.toString());
        } catch (IOException | InterruptedException ioException) {
            ioException.printStackTrace();
        }
    }
//    public  void printLines(String cmd, InputStream ins) {
//        String line ;
//        System.out.println("hello");
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(ins));
//        while (true) {
//
//            try {
//                line = in.readLine();
//                System.out.println(line);
//                if (line == null)
//
//                    break;
//                output.appendText(cmd + " " + line);
//                output.appendText("\n");
//
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//            System.out.println("hello1");
//            //System.out.println(cmd + " " + line);
//
//
//
//
//        }

    public  void printLines(String cmd, InputStream ins) {
        String line ;
        System.out.println("hello");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        while (true) {

            try {
                line = in.readLine();
                System.out.println(line);
                if (line == null)

                    break;
                output.appendText(cmd + " " + line);
                output.appendText("\n");

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            System.out.println("hello1");
            //System.out.println(cmd + " " + line);




        }

}



}

