import com.jcraft.jsch.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.SshSessionFactory;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Stream;

public class Sample extends IdeFX {
    public javafx.scene.text.Text numberText;
    public ScrollPane scroll;
    File filename;
    File filename1;
    Stage primaryStage;
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb3 = new StringBuilder();
    static int count = 0;
    static String line;
    static String line1;
    static String uri;
    static String file;

    int a = 1;
    int b = 0;
    int linecount = 0;
    int startCount = 0;


    @FXML
    public TextArea output;
    @FXML
    public TextArea textedit;
    @FXML
    public ContextMenu contextmenu;


    public void openAction(ActionEvent actionEvent) {

        FileChooser file = new FileChooser();
        file.setTitle("Open File");
        File fileToLoad = file.showOpenDialog(primaryStage);
        filelocation(fileToLoad);
        if (fileToLoad != null) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(fileToLoad));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            String line;
            StringBuilder totalFile = new StringBuilder();

            while (true) {
                try {
                    if (!((line = reader.readLine()) != null)) break;
//  Logic for line number starts here

                    if (startCount == 0) {

                        sb1.append("1  ");

                    }
                    startCount = 1;
                    a = a + 1;
                    int i = 0;

                    int len1 = 3 - String.valueOf(a).length();
                    sb1.append(String.valueOf(a));
                    while (i < len1) {
                        sb1.append(" ");

                        i++;
                    }
                    numberText.setText(sb1.toString());
//  Logic for line number ends here
                    textedit.appendText(line);

                    textedit.appendText("\n");


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            filename = fileToLoad;
        }
    }

    // save As

    public void saveAs(ActionEvent actionEvent) {
        textedit.getCursor();
        FileChooser file = new FileChooser();
        file.setTitle("Save Image");
        File file1 = file.showSaveDialog(primaryStage);
        filelocation(file1);
        try {
            PrintWriter writer;
            writer = new PrintWriter(file1);
            writer.println(textedit.getText());
            writer.close();

        } catch (IOException ie) {
            System.out.print(ie);
        }
        filename = file1;
    }

    // save
    //
    public void save(ActionEvent actionEvent) {


        try {
            PrintWriter writer;
            writer = new PrintWriter(filename);
            writer.println(textedit.getText());
            writer.close();

        } catch (IOException ie) {
            System.out.print(ie);
        }


    }


    //Run
    public void run(ActionEvent actionEvent) {

        output.clear();

        String command = "java  " + filename;

        Process pro = null;

        try {

            pro = Runtime.getRuntime().exec(command);

            printLines(command + " stdout:", pro.getInputStream());

            printLines(command + " stderr:", pro.getErrorStream());
            output.appendText("\n");
            pro.waitFor();
            String line2 = command + " exitValue() " + pro.exitValue();
            output.appendText(line2);


        } catch (IOException | InterruptedException ioException) {
            ioException.printStackTrace();
        }
    }


    //compile
    public void compile(ActionEvent actionEvent) {
        output.clear();

        String command = "javac  " + filename;

        Process pro = null;

        try {

            pro = Runtime.getRuntime().exec(command);

            printLines(command + " stdout:", pro.getInputStream());

            printLines(command + " stderr:", pro.getErrorStream());
            output.appendText("\n");
            pro.waitFor();
            String line2 = command + " exitValue() " + pro.exitValue();

            output.appendText(line2);


        } catch (IOException | InterruptedException ioException) {
            ioException.printStackTrace();
        }
    }


    public void printLines(String cmd, InputStream ins) {
        String line;

        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        while (true) {

            try {
                line = in.readLine();

                if (line == null)

                    break;
                output.appendText(cmd + " " + line);
                output.appendText("\n");

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }

    }

    // Line Numbers
    public void enter(KeyEvent keyEvent) {

        if (linecount == 0 && a == 1) {

            sb1.append("1  ");
            linecount = 1;

        }


        switch (keyEvent.getCode()) {
            case ENTER:

                linecount = 1;

                a = a + 1;
                int i = 0;

                int len1 = 3 - String.valueOf(a).length();
                sb1.append(String.valueOf(a));
                while (i < len1) {
                    sb1.append(" ");
                    sb3.append(" ");
                    i++;
                }
                numberText.setText(sb1.toString());
                scroll.setVvalue(1.0);
                scroll.setPannable(true);

                scroll.viewportBoundsProperty();
                break;
            case SHIFT:

                int caretPosition = textedit.getCaretPosition();
                contextmenu.show(textedit, caretPosition, caretPosition);
                contextmenu.setOnAction(e -> textedit.replaceText(caretPosition - 1, caretPosition, ((MenuItem) e.getTarget()).getText()));
                break;

        }
    }


    //      Format
    public void format(ActionEvent actionEvent) throws IOException {

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
                if ((line = br.readLine()) == null) {

                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            if (line.contains(";") && !(line.contains("{") | line.contains("}"))) {
                if (!(line.contains("for"))) {

                    int j = 0;
                    while (j < count) {
                        sb.append(" ");
                        j++;
                    }

                    sb.append(line.stripLeading());
                    sb.append("\n");


                }
            }


            if (line.contains("}") && (line.stripLeading().stripTrailing().length() <= 1)) {

                count = count - 4;
                int i = 0;

                while (i < count) {
                    sb.append(" ");
                    i++;

                }
                sb.append(line.stripLeading());
                sb.append("\n");

            }

            if (line.contains("}") && !(line.stripLeading().stripTrailing().length() <= 1)) {

                int i = 0;

                while (i < count) {
                    sb.append(" ");
                    i++;

                }
                sb.append(line.replace("}", "\n").stripLeading());
                count = count - 4;

                int p = 0;
                while (p < count) {
                    sb.append(" ");
                    p++;

                }
                sb.append("}");
                sb.append("\n");


            }

            if (line.contains("{") && (line.stripLeading().stripTrailing().length() <= 1)) {

                int i = 0;

                while (i < count) {
                    sb.append(" ");
                    i++;

                }
                sb.append(line.stripLeading().stripTrailing());
                sb.append("\n");
                count = count + 4;
            }

            if (line.contains("{") && !(line.stripLeading().stripTrailing().length() <= 1)) {


                int i = 0;
                while (i < count) {
                    sb.append(" ");
                    i++;

                }
                sb.append(line.replace("{", "\n").stripLeading());
                int k = 0;
                while (k < count) {
                    sb.append(" ");
                    k++;

                }
                sb.append("{\n");
                count = count + 4;
            }

            if (!(line.contains("{") | line.contains(";") | line.contains("}"))) {
                if (!(line.stripLeading().stripTrailing().length() == 0)) {

                    int i = 0;
                    while (i < count) {

                        sb.append(" ");
                        i++;

                    }
                    sb.append(line.stripLeading());
                    sb.append("\n");

                }
            }


        }

        textedit.setText(sb.toString());

        br.close();
    }

    // git clone
    public void clone(ActionEvent actionEvent) throws IOException {
        BufferedReader reader = null;
        int count = 0;

        try {
            reader = new BufferedReader(new FileReader(filename1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (true) {

            if (!((line1 = reader.readLine()) != null)) break;
            if (!line1.equals(" ") && count == 0) {
                file = line1;


            }

            if (!line1.equals(" ") && count == 1) {
                uri = line1;

            }
            count++;
        }

        try {
            CloneCommand cloneCommand = new CloneCommand();

            cloneCommand.setURI(uri);

            cloneCommand.setDirectory(new File(file));
            cloneCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider("Sunitha01", "America0105$"));

            cloneCommand.call();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // git add

    public void addgit(ActionEvent actionEvent) throws IOException, GitAPIException {
        Git git = Git.open(new File(file));
        git.add().addFilepattern("Text").call();
    }

    // git commit

    public void commitgit(ActionEvent actionEvent) throws IOException, GitAPIException {
        Git git = Git.open(new File(file));
        RevCommit rev = git.commit().setMessage("My first commit").call();
        System.out.println(rev);
    }

    // git push

    public void pushgit(ActionEvent actionEvent) throws IOException, GitAPIException, URISyntaxException {


        SSH jschConfigSessionFactory = new SSH();
        JSch jsch = new JSch();

        SshSessionFactory.setInstance(jschConfigSessionFactory);

        Git git = Git.open(new File("C:\\Users\\sunitha\\Desktop\\CloneExample1"));
        Iterable<PushResult> pushResults = null;

        PushCommand pushCommand = git.push();

        pushCommand.setRemote("git@github.com:Sunitha01/552-IDE.git");

        pushCommand.setPushAll();

        pushResults = pushCommand.call();
        System.out.println(pushResults);

    }

    public void configureSaveAs(ActionEvent actionEvent) {
        FileChooser file = new FileChooser();
        file.setTitle("Save Image");
        File file1 = file.showSaveDialog(primaryStage);
        filelocation(file1);
        try {
            PrintWriter writer;
            writer = new PrintWriter(file1);
            writer.println(textedit.getText());
            writer.close();

        } catch (IOException ie) {
            System.out.print(ie);
        }

        filename = file1;
        filename1 = file1;


    }

    public void configureOpen(ActionEvent actionEvent) {


        FileChooser file = new FileChooser();
        file.setTitle("Open File");
        File fileToLoad = file.showOpenDialog(primaryStage);
        filelocation(fileToLoad);

        if (fileToLoad != null) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(fileToLoad));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            String line;
            StringBuilder totalFile = new StringBuilder();
            while (true) {
                try {
                    if (!((line = reader.readLine()) != null)) break;


                    textedit.appendText(line);

                    textedit.appendText("\n");


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            filename1 = fileToLoad;
            filename = fileToLoad;

        }
    }

    public void configureSave(ActionEvent actionEvent) {


        try {
            PrintWriter writer;
            writer = new PrintWriter(filename1);
            writer.println(textedit.getText());
            writer.close();

        } catch (IOException ie) {
            System.out.print(ie);
        }


    }


    public void clear(ActionEvent actionEvent) {
        textedit.clear();
        numberText.setText(" ");

        linecount = 0;
        sb1.setLength(0);
        a = 1;
    }


}


