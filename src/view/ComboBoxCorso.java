package view;

import model.Corso;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ComboBoxCorso extends JComboBox implements ActionListener {

    public ComboBoxCorso(Vector<Corso> model)
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
        Corso item = (Corso)comboBox.getSelectedItem();
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
                Corso item = (Corso)value;
                if (item != null)
                setText( item.getNome().toUpperCase() );
            }

            if (index == -1)
            {
                Corso item = (Corso)value;
                if (item != null)
                setText( item.getId() + ": "+item.getNome() );
            }


            return this;
        }
    }


}
