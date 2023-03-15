# SStaticAnalyzer
Issues Categories:

1- Code Smell:

a- critical:

L223 "private void loadFile() {". : Incorrect code conventions for function names can make the code less readable and harder to understand for other developers.





L60 "setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);".

L143 "public void actionPerformed(ActionEvent e) {".

L154 "ans = JOptionPane.showConfirmDialog(null, "The file has changed. You want to save it?", "Save file", 0, 2);".

L165 "throw new EditorSaveException("Cannot write file!");".

L224 "JFileChooser dialog = new JFileChooser(System.getProperty("user.home"));".

L122 "		/*

      		move = new JMenuItem("Move");
                  
      		move.setMnemonic('M');
                  
      		move.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
                  
      		edit.add(move);
                  
      		move.addActionListener(this);
                  
      		*/".
                  
L162 "System.out.println(text);".

L192 "System.out.println(text);".

L195 "throw new Exception("Cannot write file!");".

L240 "System.out.println("No change");".

L248 "System.out.println(text);".

L249 "try {

      						PrintWriter writer = new PrintWriter(file);
                                          
      						if (!file.canWrite())".
                                          
L252 "throw new Exception("Cannot write file!");".

L262 "try (	FileReader fr = new FileReader(file);

      						BufferedReader reader = new BufferedReader(fr);) {
                                          
      					String line;".
                                    
L307 "private void saveAsText(String dialogTitle) throws EditorSaveAsException {".




b- minor:



L36 "public JEditorPane TP;//Text Panel".

L37 "public JMenuBar menu;//Menu".

L38 "public JMenuItem copy, paste, cut, move;".

L39 "public boolean changed = false;".

L36 "private void BuildMenu() {".

L88 "saveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));".





2- Bugs:

L250 "PrintWriter writer = new PrintWriter(file);".

L177 "if (changed) {".

L234 "if (changed) {".

L294 "PrintWriter writer = getWriter(file);

      		writer.write(TP.getText());".
                  

