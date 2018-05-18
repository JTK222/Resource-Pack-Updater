package net.dark_roleplay.rpupdater.gui.components;

import javax.swing.JTextArea;
import javax.swing.UIManager;

public class JMultilineLabel extends JTextArea{
	
    private static final long serialVersionUID = 1L;
    
    public JMultilineLabel(String text){
        super(text);
        this.setEditable(false);  
        this.setCursor(null);  
        this.setOpaque(false);  
        this.setFocusable(false);  
        this.setFont(UIManager.getFont("Label.font"));      
        this.setWrapStyleWord(true);  
        this.setLineWrap(true);
    }
} 