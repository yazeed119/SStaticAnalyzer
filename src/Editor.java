import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;



@SuppressWarnings("serial")

public class Editor extends JFrame implements ActionListener, DocumentListener {


    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 500;
    private static final int APPROVE_OPTION = 0;
    private static final int CANCEL_OPTION = 1;
    private static final int MESSAGE_ERROR = 0;
    private static final int MESSAGE_WARNING = 2;
    private static final int OPTION_TYPE = 0;
    private static final int NO_OPTION = 1;
    private JEditorPane textPane;
    private JMenuBar menubar;
    private boolean isChanged = false;
    private File file;



    public Editor() {
        super("Text Editor");
        textPane = new JEditorPane();
        add(new JScrollPane(textPane), "Center");
        textPane.getDocument().addDocumentListener(this);
        menubar = new JMenuBar();
        setJMenuBar(menubar);
        buildMenu();
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void buildMenu() {
        buildFileMenu();
        buildEditMenu();
    }

    private void newFile(JMenu file) {
        JMenuItem newFile = new JMenuItem("New");
        newFile.setMnemonic('N');
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        newFile.addActionListener(this);
        file.add(newFile);
    }
    private void openFile(JMenu file) {
        JMenuItem open = new JMenuItem("Open");
        file.add(open);
        open.addActionListener(this);
        open.setMnemonic('O');
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
    }

    private void saveText(JMenu file) {
        JMenuItem save = new JMenuItem("Save");
        file.add(save);
        save.setMnemonic('S');
        save.addActionListener(this);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
    }
    private void saveAs(JMenu file) {
        JMenuItem saves =
                new JMenuItem("Save as:");
        file.add(saves);
        saves.addActionListener(this);

    }
    private void quitApplication(JMenu file) {
        JMenuItem quit = new JMenuItem("Quit");
        file.add(quit);
        quit.addActionListener(this);
        quit.setMnemonic('Q');
        quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
    }

    private void buildFileMenu() {
        JMenu keyfile = new JMenu("File");
        keyfile.setMnemonic('F');
        menubar.add(keyfile);
        newFile(keyfile);
        openFile(keyfile);
        saveText(keyfile);
        saveAs(keyfile);
        quitApplication(keyfile);

    }

    private void cutText(JMenu edit) {
        JMenuItem  cut = new JMenuItem("Cut");
        cut.addActionListener(this);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        cut.setMnemonic('T');
        edit.add(cut);
    }
    private void copyText(JMenu edit) {
        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(this);
        copy.setMnemonic('C');
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        edit.add(copy);
    }

    private void findText(JMenu edit) {
        JMenuItem find = new JMenuItem("Find");
        find.setMnemonic('F');
        find.addActionListener(this);
        edit.add(find);
        find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
    }
    private void pasteText(JMenu edit) {
        JMenuItem  paste = new JMenuItem("Paste");
        paste.setMnemonic('P');
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        edit.add(paste);
        paste.addActionListener(this);
    }

    private void selectAllText(JMenu edit) {
        JMenuItem  sall = new JMenuItem("Select All");
        sall.setMnemonic('A');
        sall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        sall.addActionListener(this);
        edit.add(sall);
    }
    private void buildEditMenu() {
        JMenu edit = new JMenu("Edit");
        menubar.add(edit);
        edit.setMnemonic('E');
        cutText(edit);
        copyText(edit);
        pasteText(edit);
        selectAllText(edit);
        findText(edit);

    }


    private void saveFile() {
        if (isChanged) {
            int ans = JOptionPane.showConfirmDialog(null,
                    "The file has changed. You want to save it?", "Save file",
                    OPTION_TYPE, MESSAGE_WARNING);
            if (ans == NO_OPTION) {
                return;
            }
            if (file == null) {
                saveAs("Save");
                return;
            }
            String text = textPane.getText();
            try {
                System.setErr(new PrintStream(new FileOutputStream(System.getProperty("text")+"/error.log")));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

            try (PrintWriter writer = new PrintWriter(file);) {
                if (!file.canWrite())
                    throw new IOException("Cannot write file!");
                writer.write(text);
                isChanged = false;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void newFile(){
        if(isChanged)
            saveFile();
        file = null;
        textPane.setText("");
        isChanged = false;
        setTitle("Editor");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case "Quit":
                System.exit(0);
                break;
            case "Open":
                loadFile();
                break;
            case "Save":
                saveFile();
                break;
            case "New":
                newFile();
                break;
            case "Save as...":
                saveAs("Save as...");
                break;
            case "Select All":
                textPane.selectAll();
                break;
            case "Copy":
                textPane.copy();
                break;
            case "Cut":
                textPane.cut();
                break;
            case "Paste":
                textPane.paste();
                break;
            case "Find":
                break;
            default:
        }
    }

    private  String readFile(File file)
    {
        StringBuilder builder = new StringBuilder();
        try (FileReader fileReader = new FileReader(file);
             BufferedReader reader = new BufferedReader(fileReader);) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot read file !", "Error !", MESSAGE_ERROR);
        }
        return builder.toString();
    }


    private void loadFile() {
        JFileChooser dialog = new JFileChooser(System.getProperty("user.home"));
        dialog.setMultiSelectionEnabled(false);
        try {
            int result = dialog.showOpenDialog(this);
            if (result == CANCEL_OPTION)
                return;
            if (result == APPROVE_OPTION) {
                if (isChanged) {
                    saveFile();
                }
                file = dialog.getSelectedFile();
                String read =readFile(file);
                textPane.setText(read);
                isChanged = false;
                setTitle("Editor - " + file.getName());
            }
        } catch (Exception error) {
            error.printStackTrace();
            JOptionPane.showMessageDialog(null, error, "Error", MESSAGE_ERROR);
        }
    }


    private void saveAs(String dialogTitle) {
        JFileChooser dialog = new JFileChooser(System.getProperty("user.home"));
        dialog.setDialogTitle(dialogTitle);
        int result = dialog.showSaveDialog(this);
        if (result != APPROVE_OPTION)
            return;
        file = dialog.getSelectedFile();
        try (PrintWriter writer = new PrintWriter(file);) {
            writer.write(textPane.getText());
            isChanged = false;
            setTitle("Editor - " + file.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        isChanged = true;
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        isChanged = true;
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        isChanged = true;
    }
}