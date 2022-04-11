package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {
    //StringBuilder totalFile1 = new StringBuilder();
    static File filename;
    //static StringBuilder totalFile1 ;
    public static void filelocation(File file){
        filename = file;
    }
    public static void filelocation1(){
        File filename1 = filename;
    }
    StringBuilder totalFile1 = new StringBuilder();
//    public  void printLines(String cmd, InputStream ins) {
//        String line = null;
//
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(ins));
//        while (true) {
//            try {
//                if (!((line = in.readLine()) != null)) break;
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//            System.out.println(cmd + " " + line);
//
//            totalFile1.append(cmd + " " + line);
//            totalFile1.append("\n");
//
//
//        }

//    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
       primaryStage.setScene(new Scene(root, 300, 275));
       //primaryStage.setMaximized(true);
        primaryStage.show();
      /*
//        TextField tf1=new TextField();
//        Button b = new Button("Run");
//        GridPane root = new GridPane();
//        root.addRow(0,tf1);
//        root.addRow(1,b);
//        Scene scene=new Scene(root,800,200);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Text Field Example");
//        primaryStage.show();
        File fileRun;
        //TextField tf1=new TextField();
        Button b = new Button("Run");
        Button b1 = new Button("Open");
        Button btn = new Button("Save");
//        ScrollBar s = new ScrollBar();
//        s.setMin(0);
//        s.setMax(200);
//        s.setValue(110);
//        s.setOrientation(Orientation.VERTICAL);
//        s.setUnitIncrement(12);
//        s.setBlockIncrement(10);
        TextArea area = new TextArea();
        TextArea area1 = new TextArea();
        //StackPane root = new StackPane();
        //root.getChildren().add(scene);
        //area.setText("Enter your address here");
        area.setPrefColumnCount(15);
        area.setPrefHeight(120);
        area.setPrefWidth(300);

        //Creating a hbox to hold the pagination
//        FileChooser file = new FileChooser();
//        file.setTitle("Open File");
//        file.showOpenDialog(primaryStage);
        HBox hbox = new HBox();
        //VBox vbox = new VBox();
        hbox.setSpacing(20);
        hbox.setPadding(new Insets(20, 50, 50, 60));
        hbox.getChildren().addAll(area, b, b1 , btn,area1);
        //Setting the stage
        Group root = new Group(hbox);

        Scene scene = new Scene(root, 300, 200);

        primaryStage.setScene(scene);
        primaryStage.setTitle("ScrollBar Example");
        primaryStage.show();
        //save
        btn.setOnAction(e->
        {
            FileChooser file = new FileChooser();
            file.setTitle("Save Image");
            //System.out.println(pic.getId());
            File file1 = file.showSaveDialog(primaryStage);
            filelocation(file1);
            try {
                PrintWriter writer;
                writer = new PrintWriter(file1);
                writer.println(area.getText());
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
        });



        //open

        b1.setOnAction(event -> {

            FileChooser file = new FileChooser();
            file.setTitle("Open File");
            File fileToLoad = file.showOpenDialog(primaryStage);
            filelocation(fileToLoad);
            System.out.println(file.getTitle());
            if(fileToLoad != null){
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
                while(true) {
                    try {
                        if (!((line = reader.readLine()) != null)) break;
                        totalFile.append(line);
                        totalFile.append("\n");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                     System.out.println(totalFile);
                    //return totalFile.toString();
                }
                area.setText(totalFile.toString());
            }
            // save

        });
        //Run
        b.setOnAction(e->
        {
           System.out.println(filename);
           String command = "java  " + filename;
            //String command = "java  C:\\Users\\sunitha\\Desktop\\Java\\MavenProject\\src\\main\\java\\InvokingJavaExample.java";
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
                totalFile1.append("\n");
                pro.waitFor();
                String line2 = command + " exitValue() " + pro.exitValue();
                //
                totalFile1.append(line2);
                System.out.println(command + " exitValue() " + pro.exitValue());
                area1.setText(totalFile1.toString());
            } catch (IOException | InterruptedException ioException) {
                ioException.printStackTrace();
            }






        });
    */

    }
            //System.out.println(file.getInitialDirectory());


//        FileChooser file = new FileChooser();
//        file.setTitle("Open File");
//        file.showOpenDialog(primaryStage);
//        area.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(5));
//);
//        area.getOnMouseClicked((e) -> {
//            area.setText("");;
//        });
//
//            if (area.getOnMouseClicked()) {
//            }
//                (new EventHandler<KeyEvent>() {
//
//
//                    @Override
//                    public void handle(KeyEvent key) {
//                        // TODO Auto-generated method stub
//
//                        area.setText("");
//                    }
//
//                });
//            }
//
//
//
//}


    public static void main(String[] args) {
        launch(args);
    }

 }