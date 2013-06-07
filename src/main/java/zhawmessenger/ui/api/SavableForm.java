package zhawmessenger.ui.api;

import zhawmessenger.messagesystem.api.message.Message;

import javax.swing.*;

/**
 */
public interface SavableForm<T extends Message>
        extends SaveObservable, CancelObservable {
    /**
     * Must return a form. May not be null.
     *
     * @return A form which will be used to embedd the form.
     */
    JPanel getForm();
}
