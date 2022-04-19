package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.util.stream.Stream;

public class Controller extends Main{
    public javafx.scene.text.Text numberText;
    public ScrollPane scroll;
    File filename;
    Stage primaryStage;
    StringBuilder sb = new StringBuilder();
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();
    static int count = 0;
    static String line;

    int a =1;
    int b =0;


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
// for line numbers
                    if (numberText.getText().equals("1")  ) {

                        sb1.append("1  ");
//             System.out.println("hello");
//             System.out.println(sb.toString());
                    }
                    a = a+1;
                    int i = 0;

                    int len1 = 3 - String.valueOf(a).length();
                    sb1.append(String.valueOf(a));
                    while( i < len1){
                        sb1.append(" ");
//                    System.out.println(sb.toString());
                        i++;
                    }
                    numberText.setText(sb1.toString());
//---
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


    public void enter(KeyEvent keyEvent) {
//         System.out.println(numberText.getText());
         if (numberText.getText().equals("1")  ) {

             sb1.append("1  ");
//             System.out.println("hello");
//             System.out.println(sb.toString());
         }

        switch (keyEvent.getCode()) {
            case ENTER:
                a = a+1;
                int i = 0;

                int len1 = 3 - String.valueOf(a).length();
                sb1.append(String.valueOf(a));
                while( i < len1){
                    sb1.append(" ");
//                    System.out.println(sb.toString());
                    i++;
                }
                numberText.setText(sb1.toString());
                scroll.setVvalue(1.0);
                scroll.setPannable(true);

                scroll.viewportBoundsProperty();
//                System.out.println(sb.toString());
        }
    }
//      Format
    public void format(ActionEvent actionEvent) {
        System.out.println("Invoked");
        StringBuilder sb = new StringBuilder();
        line = " ";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (line.contains("package") | (line.contains("import"))) {
                if (line.contains(";") )
                    sb.append(line.replace(";", ";\n").stripLeading());
                else{
                    sb.append(line.stripLeading());
                }

            }





            if (line.contains(";") && !line.contains("for")) {
                if (!(line.contains("package") | (line.contains("import")) )) {
                    if (line.stripLeading().length() <= 1) {
                        count = count-4;
                        sb.append(line.replace(";", ";\n").stripLeading());
                    } else {
                        if (!line.contains("}")) {
                            int i = 0;
                            while (i < count) {
                                sb.append(" ");
                                i++;

                            }


                            sb.append(line.replace(";", ";\n").stripLeading());
                        }
                    }
                }

            }


            if (line.contains("{")) {
                if (line.stripLeading().length() <= 1){

                    sb.append(line.replace("{", "{\n").stripLeading());
                }
                else {
                    int i = 0;
                    while (i < count) {
                        sb.append(" ");
                        i++;

                    }
                    sb.append(line.replace("{", "{\n").stripLeading());
                    count = count + 4;
                }



            }
            if (line.contains("}")  ){

                int i = 4;
                while (i < count) {
                    sb.append(" ");
                    i++;
                }
                sb.append(line.replace("}", "}\n").stripLeading());
                count = count - 4;


            }
            if (!(line.contains("{") | line.contains(";") |  line.contains("}") | line.length()== 0)) {

                int i = 0;
                while (i < count) {
                    sb.append(" ");
                    i++;
                }
                sb.append(line.stripLeading());

                count = count + 4;
            }
            if (!(line.contains("{")) && line.contains("for") ) {

                count = count +4 ;
                int i = 0;
                while (i < count) {
                    sb.append(" ");
                    i++;
                }
                sb.append(line.stripLeading());
                sb.append("\n");
                //count = count + 1;
            }


        }
        textedit.setText(sb.toString());
        System.out.println(sb.toString());


    }
}

