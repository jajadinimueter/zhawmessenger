package zhawmessenger.ui.impl.modules.print;

import zhawmessenger.messagesystem.api.contact.DisplayableContactProvider;
import zhawmessenger.messagesystem.api.modules.print.contact.Printer;
import zhawmessenger.messagesystem.api.modules.print.message.PrintJob;
import zhawmessenger.messagesystem.api.modules.print.persistance.PrinterRepository;
import zhawmessenger.messagesystem.api.util.Finder;
import zhawmessenger.ui.api.form.MessageForm;
import zhawmessenger.ui.api.formbuilder.FormBuilder;
import zhawmessenger.ui.impl.components.ReceiverTextArea;
import zhawmessenger.ui.impl.components.SendAtPanel;
import zhawmessenger.ui.impl.components.StopperGridBagConstraintsChanger;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

/**
 */
public class PrintForm
        extends MessageForm<PrintJob> {

    private final PrinterRepository printerRepository;
    private final Finder<String, DisplayableContactProvider> printerFinder;

    private JTextArea text;
    private SendAtPanel sendAtPanel;
    private ReceiverTextArea receiverField;

    protected PrintForm(final Window owner,
                        final PrintJob message,
                        final PrinterRepository printerRepository) {
        super(owner, message);
        this.printerRepository = printerRepository;
        this.printerFinder = new Finder<String, DisplayableContactProvider>() {
            @Override
            public Set<DisplayableContactProvider> find(String... findArgs) {
                HashSet<DisplayableContactProvider> foundPrinters
                        = new HashSet<DisplayableContactProvider>();
                for (String arg : findArgs) {
                    foundPrinters.addAll(printerRepository.find(arg, true));
                }
                return foundPrinters;
            }
        };

        this.buildForm();
    }

    private void buildForm() {
        FormBuilder builder = new FormBuilder(this, new Insets(3,3,3,3), false);

        receiverField = builder.addComponent(new JLabel("Drucker"),
                new ReceiverTextArea(owner,
                        Arrays.asList(printerFinder)));

        receiverField.setBorder(new LineBorder(Color.GRAY));

        text = new JTextArea(1, 1);
        builder.addField(new JScrollPane(text),
                new StopperGridBagConstraintsChanger());

        sendAtPanel = builder.addField(new SendAtPanel());
    }

    @Override
    public PrintJob getMessage() {
        return message;
    }

    @Override
    public PrintJob getSavedMessage() {
        message.setText(text.getText());
        for (DisplayableContactProvider dp : receiverField.getContactProviders()) {
            message.addContactProvider(dp);
        }
        message.setSendDate(sendAtPanel.getDate());
        return message;
    }
}
