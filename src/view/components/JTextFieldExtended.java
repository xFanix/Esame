package view.components;

import javax.swing.*;

public class JTextFieldExtended<T> extends JTextField{

    private T object;

    public JTextFieldExtended() {
        super();
    }

    public void setText(T object){
        this.setText(object.toString());
    }

    public void setObject(T object){
        this.object = object;
    }

    public T getObject() {
        return object;
    }
}
