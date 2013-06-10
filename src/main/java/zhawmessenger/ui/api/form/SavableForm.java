package zhawmessenger.ui.api.form;

import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.ui.api.CancelObservable;
import zhawmessenger.ui.api.SaveObservable;

import javax.swing.*;

/**
 */
public interface SavableForm<T extends Message>
        extends SaveObservable, CancelObservable {
    /**
     * Must return a formbuilder. May not be null.
     *
     * @return A formbuilder which will be used to embedd the formbuilder.
     */
    JPanel getForm();
}
