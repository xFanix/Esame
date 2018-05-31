package view.components;

import model.Corso;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ComboBoxExtended<T> extends JComboBox implements ActionListener {

    public ComboBoxExtended(Vector<T> model)
    {
        super(model);
        this.addActionListener( this );
        this.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);

        //comboBox = new JComboBox( model );
        this.setRenderer( new ItemRenderer() );
        this.addActionListener( this );
    }

    public void actionPerformed(ActionEvent e)
    {
        JComboBox comboBox = (JComboBox)e.getSource();
        T item = (T)comboBox.getSelectedItem();
    }

    class ItemRenderer extends BasicComboBoxRenderer
    {
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus)
        {
            super.getListCellRendererComponent(list, value, index,
                    isSelected, cellHasFocus);

            if (value != null)
            {
                T item = (T)value;
                if (item != null)
                    setText( item.toString().toUpperCase() );
            }

            /*if (index == -1)
            {
                Corso item = (Corso)value;
                if (item != null)
                setText( item.getId() + ": "+item.getNome() );
            }*/


            return this;
        }
    }


}
