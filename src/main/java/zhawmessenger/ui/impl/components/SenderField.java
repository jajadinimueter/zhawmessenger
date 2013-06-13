package zhawmessenger.ui.impl.components;

import java.util.Collection;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import zhawmessenger.messagesystem.api.contact.Contact;

/**
 */
public class SenderField<T extends Contact> extends JComboBox {
    public SenderField(Collection<T> contacts) {
        ComboBoxModel model = new DefaultComboBoxModel(contacts.toArray());
        this.setModel(model);
    }
}