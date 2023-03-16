# SStaticAnalyzer
         
	 
	 
## 1- list all problems in the application:	 
![image](https://user-images.githubusercontent.com/94912770/225721958-0787f143-b51b-483a-84f8-3cba2aef4d28.png)
![image](https://user-images.githubusercontent.com/94912770/225722422-16f7be06-012b-424f-a585-7dc87da8bbe0.png)
![image](https://user-images.githubusercontent.com/94912770/225722528-b93a5a65-8898-4a8e-ac59-64f31e7dbd74.png)


# 2- Issues Categories:
## 1- Code Smell:
### A- Major:




### L84, 120
```
// System.out.println(e.getKeyCode());
```

```
// closeDialog();
```
### Suspending parts of the code can increase code size and thus memory consumption, and can also cause performance delays.

## L26 
```
JButton find, close;
```

### When variables are declared on the same line, the code may affect maintainability, readability, and modification in the future.

### L122
```
		/*
		move = new JMenuItem("Move");
		move.setMnemonic('M');
		move.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
		edit.add(move);
		move.addActionListener(this);
		*/
```
### make the code difficult to understand and maintenance.

		
### L162
```
System.out.println(text);
```
### It can lead to problems in managing and maintaining the system.

### L192
```
System.out.println(text);
```
### It can lead to problems in managing and maintaining the system.

#### L284
```
System.out.println(text);
```
### It can lead to problems in managing and maintaining the system.

#### L240
```
System.out.println("No change");
```
### It can lead to problems in managing and maintaining the system.


#### L252
```
					try {
						PrintWriter writer = new PrintWriter(file);
						if (!file.canWrite())
							throw new Exception("Cannot write file!");
						writer.write(text);
						changed = false;
					} catch (Exception e) {
						e.printStackTrace();
					}
```
#### The Print Writer is not closed after it has been used in the code, which leads to excessive use of device resources.


#### L262
```
		try (	FileReader fr = new FileReader(file);		
			BufferedReader reader = new BufferedReader(fr);) {
			String line;
			while ((line = reader.readLine()) != null) {
				rs.append(line + "\n");
					}
			        } catch (IOException e) {
				e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Cannot read file !", "Error !", 0);//0 means show Error Dialog
				}
```				
#### 				


#### L307
```
	private void saveAsText(String dialogTitle) throws EditorSaveAsException {
		JFileChooser dialog = new JFileChooser(System.getProperty("user.home"));
		dialog.setDialogTitle(dialogTitle);
		int result = dialog.showSaveDialog(this);
		if (result != 0)//0 value if approve (yes, ok) is chosen.
			return;
		file = dialog.getSelectedFile();
		try (PrintWriter writer = new PrintWriter(file);){
			writer.write(TP.getText());
			changed = false;
			setTitle("Save as Text Editor - " + file.getName());
		} catch (FileNotFoundException e) {
			throw new EditorSaveAsException(e.getMessage());
		}
	}	
```
##### SaveFile is not used, and deleting it will not affect system operation or results.
##### Remaining and iterating makes the code more difficult, and it is difficult to maintain and modify.

## B- Critical:

## L115
```
	public void keyTyped(KeyEvent e) {
	}
```

```
	public void keyReleased(KeyEvent e) {
	}
```

### Existing empty functions in the code, it causes difficulty in understanding and maintaining the code, and affects the performance of the program.



```
if (changed) {
				// 0 means yes and no option, 2 Used for warning messages.
				ans = JOptionPane.showConfirmDialog(null, "The file has changed. You want to save it?", "Save file", 0, 2);
			}
```
### Magic numbers can make code harder to read and understand, as there is no clear indication of what the number represents. This can make it more difficult for other developers to modify or debug the code.


### L134
```
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals(actions[4])) {
			System.exit(0);
		} else if (action.equals(actions[0])) {
			loadFile();
		} else if (action.equals(actions[1])) {
			//Save file
			int ans = 0;
			if (changed) {
				// 0 means yes and no option, 2 Used for warning messages.
				ans = JOptionPane.showConfirmDialog(null, "The file has changed. You want to save it?", "Save file", 0, 2);
			}
			//1 value from class method if NO is chosen.
			if (ans != 1) {
				if (file == null) {
					saveAs(actions[1]);
				} else {
					String text = TP.getText();
					System.out.println(text);
					try (PrintWriter writer = new PrintWriter(file);){
						if (!file.canWrite())
							throw new EditorSaveException("Cannot write file!");
						writer.write(text);
						changed = false;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		} else if (action.equals(actions[2])) {
			//New file 
			if (changed) {
				//Save file 
				if (changed) {
					// 0 means yes and no option, 2 Used for warning messages.
					int ans = JOptionPane.showConfirmDialog(null, "The file has changed. You want to save it?", "Save file",
							0, 2);
					//1 value from class method if NO is chosen.
					if (ans == 1)
						return;
				} else {
					return;
				}
				if (file == null) {
					saveAs(actions[1]);
					return;
				}
				String text = TP.getText();
				System.out.println(text);
				try (PrintWriter writer = new PrintWriter(file);){
					if (!file.canWrite())
						throw new Exception("Cannot write file!");
					writer.write(text);
					changed = false;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			file = null;
			TP.setText("");
			changed = false;
			setTitle("Editor");
		} else if (action.equals(actions[5])) {
			saveAs(actions[5]);
		} else if (action.equals("Select All")) {
			TP.selectAll();
		} else if (action.equals("Copy")) {
			TP.copy();
		} else if (action.equals("Cut")) {
			TP.cut();
		} else if (action.equals("Paste")) {
			TP.paste();
		} else if (action.equals("Find")) {
			FindDialog find = new FindDialog(this, true);
			find.showDialog();
		}
	}
```
###A long function can be difficult to read and understand, especially if it contains complex logic. This can make it harder for other developers to work with the code or for the original developer to come back to the code after a long period of time.


## C- Minor

## L88
```
		saveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
```

### Using code that has become outdated can affect system performance and make it difficult to
fix bugs and update the system for new requirements. Outdated code may contain
unresolved technical or security issues, which may cause unexpected errors or operational
problems.


## L63

```
	private void BuildMenu() {
		buildFileMenu();
		buildEditMenu();
	}
```

###The effect of following the naming convention in the system is to improve the reading and understanding of the source code by other developers, especially if they are not the original developer of the source code.



## L39
```
public boolean changed = false;
```
### When a public scope is given to class variables, it allows these variables to be accessed and modified from any other part of the code, which exposes the code to error-handling issues and difficulty in future maintenance.


## L38
```
public JMenuItem copy, paste, cut, move;
```
### When variables are declared on the same line, the code may affect maintainability, readability, and modification in the future.


## L36
```
public JEditorPane TP;//Text Panel
```
### Poorly named variables can make the code difficult to read and understand.




## D- Blocker:

## L22
```
Editor parent;
```

```
Matcher matcher;
```

### The sub class field will "shadow" or override the parent class field in this situation, which may result in unexpected behavior and confusion.




	


## 2- Bugs:
### B- Major:


#### L234

```
				if (changed){
					//Save file
					if (changed) {
						int ans = JOptionPane.showConfirmDialog(null, "The file has changed. You want to save it?", "Save file",
								0, 2);//0 means yes and no question and 2 mean warning dialog
						if (ans == 1)// no option 
							return;
					}
```
### this leads to increase in the complexity of the code, therefore, it will increase the time needed to compile the code.


#### L294

```
	private void saveAs(String dialogTitle) {
		dialogTitle = dialogTitle.toUpperCase();
		JFileChooser dialog = new JFileChooser(System.getProperty("user.home"));
		dialog.setDialogTitle(dialogTitle);
		int result = dialog.showSaveDialog(this);
		if (result != 0)//0 value if approve (yes, ok) is chosen.
			return;
		file = dialog.getSelectedFile();
		PrintWriter writer = getWriter(file);
		writer.write(TP.getText());
		changed = false;
		setTitle("Editor - " + file.getName());
	}
```
##### Empty values cause problems with the application crashing and running as well.





