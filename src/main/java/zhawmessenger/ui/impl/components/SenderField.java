package zhawmessenger.ui.impl.components;

import com.sun.javaws.exceptions.JRESelectException;
import zhawmessenger.messagesystem.api.contact.Contact;
import zhawmessenger.messagesystem.api.util.Finder;

import javax.swing.*;
import java.util.Collection;
import java.util.List;

/**
 */
public class SenderField<T extends Contact> extends JComboBox {
    public SenderField(Collection<T> contacts) {
        ComboBoxModel model = new DefaultComboBoxModel(contacts.toArray());
        this.setModel(model);
    }
}