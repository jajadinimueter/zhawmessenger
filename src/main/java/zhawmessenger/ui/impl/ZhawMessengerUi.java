package zhawmessenger.ui.impl;

import zhawmessenger.messagesystem.api.contact.DisplayableContactProvider;
import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.persistance.SearchableRepository;
import zhawmessenger.messagesystem.api.queue.MessageQueue;
import zhawmessenger.messagesystem.api.queue.QueuedMessage;
import zhawmessenger.messagesystem.api.remind.ReminderListener;
import zhawmessenger.messagesystem.api.transport.Transport;
import zhawmessenger.messagesystem.impl.modules.email.transport.FakeEmailTransportImpl;
import zhawmessenger.messagesystem.impl.modules.mobilephone.transport.FakeMobilePhoneTransport;
import zhawmessenger.messagesystem.impl.modules.print.transport.FakePrintTransportImpl;
import zhawmessenger.messagesystem.impl.queue.MemoryQueueRepository;
import zhawmessenger.messagesystem.impl.queue.MessageQueueImpl;
import zhawmessenger.messagesystem.impl.queue.QueuedMessageImpl;
import zhawmessenger.messagesystem.impl.scheduler.TimeIntervalScheduler;
import zhawmessenger.messagesystem.api.queue.persistance.QueueRepository;
import zhawmessenger.ui.api.*;
import zhawmessenger.ui.api.form.MessageFormFactory;
import zhawmessenger.ui.api.form.SavableForm;
import zhawmessenger.ui.api.plugin.MessagePlugin;
import zhawmessenger.ui.impl.components.ConsolePanel;
import zhawmessenger.ui.impl.queue.QueueTable;
import zhawmessenger.ui.impl.modules.email.EmailMessagePlugin;
import zhawmessenger.ui.impl.modules.mms.MmsMessagePlugin;
import zhawmessenger.ui.impl.modules.print.PrintMessagePlugin;
import zhawmessenger.ui.impl.modules.sms.SmsMessagePlugin;
import zhawmessenger.ui.impl.queue.QueueTableModel;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class ZhawMessengerUi {

    private final List<MessagePlugin> messagePlugins;
    private final MessageQueue messageQueue;
    private QueueTableModel tableModel;

    public ZhawMessengerUi(List<MessagePlugin> messagePlugins,
                           MessageQueue messageQueue) {
        this.messagePlugins = messagePlugins;
        this.messageQueue = messageQueue;
    }

    private void openMessage(MessagePlugin mp, JFrame owner) {
        openMessage(mp, owner, null);
    }

    private void openMessage(MessagePlugin mp, JFrame owner, final QueuedMessage qm) {
        MessageWindowFactory windowFactory = mp.getWindowFactory();
        final Window win = windowFactory.createWindow(owner);
        MessageFormFactory factory = mp.getFormFactory();

        Message message = null;

        if (qm != null) {
            qm.suspend();
            message = qm.getMessage();
        }

        if (message == null) {
            message = mp.getMessageFactory().createMessage();
        }

        //noinspection unchecked
        final SavableForm savableForm = factory.createForm(win, message);
        savableForm.addSaveListener(new SaveListener() {
            @Override
            public void saved(Message message) {
                win.dispose();
                if (message == null) {
                    throw new RuntimeException("Message cannot be null");
                }
                QueuedMessage q = messageQueue.add(message);
                q.resume();
            }
        });
        savableForm.addCancelListener(new CancelListener() {
            @Override
            public void canceled(Message message) {
                win.dispose();
                if (qm != null) {
                    qm.resume();
                }
            }
        });
        win.add(savableForm.getForm());
        win.setVisible(true);
    }

    public JFrame createUi() {
        final JFrame frame = new JFrame("ZHAW Messenger");

        final JMenuBar mainMenuBar;
        final JMenu fileMenu, createMessageMenu;
        final JScrollPane queueScrollPane;
        final JTable messageTable;
        final JPanel mainPanel;
        final JToolBar mainToolbar;

        tableModel = new QueueTableModel(messageQueue, messagePlugins);
        messageTable = new QueueTable(tableModel);

        queueScrollPane = new JScrollPane(messageTable);

        // create the menu structure
        mainMenuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        createMessageMenu = new JMenu("Neu");

        for (final MessagePlugin mp : messagePlugins) {
            Action createAction = new AbstractAction(mp.getName(), mp.getIcon()) {
                @Override
                public void actionPerformed(ActionEvent e) {
                    openMessage(mp, frame);
                }
            };

            createMessageMenu.add(createAction);
        }

        fileMenu.add(createMessageMenu);

        mainMenuBar.add(fileMenu);

        mainPanel = new JPanel(new BorderLayout());

        mainToolbar = new JToolBar();
        final JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selRow = messageTable.getSelectedRow();
                if ( selRow >= 0 ) {
                    final QueuedMessage message = tableModel.getMessages().get(selRow);
                    for (final MessagePlugin mp : messagePlugins) {
                        //noinspection unchecked
                        if (mp.doesHandle(message.getMessage().getClass())) {
                            openMessage(mp, frame, message);
                        }
                    }
                }
            }
        });

        final JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selRow = messageTable.getSelectedRow();
                if (selRow >= 0) {
                    QueuedMessage queuedMessage = messageQueue.getQueuedMessages().get(selRow);
                    messageQueue.remove(queuedMessage.getMessage());
                }
            }
        });

        mainToolbar.add(editButton);
        mainToolbar.add(deleteButton);

        mainPanel.add(mainToolbar, BorderLayout.NORTH);

        mainPanel.add(queueScrollPane, BorderLayout.CENTER);

        // create frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setSize(540, 380);
        frame.getContentPane().add(mainPanel);
        frame.setJMenuBar(mainMenuBar);

        return frame;
    }

    public static void main(String[] args) {
        final ConsolePanel consolePanel = new ConsolePanel();
        final TimeIntervalScheduler scheduler = new TimeIntervalScheduler(1000);

        UglyContactsCreationFactory repositoryFactory =
                UglyContactsCreationFactory.getInstance();

        // this is a little bit ugly...
        DefaultApplicationContext applicationContext =
                DefaultApplicationContext.getInstance();

        applicationContext.setUserLoggedIn(repositoryFactory.getFmueller());

        List<MessagePlugin> plugins =
                new ArrayList<MessagePlugin>();

        MessagePlugin printMessagePlugin
                = new PrintMessagePlugin(repositoryFactory.getPrinterRepository());

        List<SearchableRepository<? extends DisplayableContactProvider>> mobileRepos =
                new ArrayList<SearchableRepository<? extends DisplayableContactProvider>>();

        mobileRepos.add(repositoryFactory.getGroupRepository());
        mobileRepos.add(repositoryFactory.getPersonRepository());
        mobileRepos.add(repositoryFactory.getMobilePhoneContactRepository());

        MessagePlugin smsMessagePlugin = new SmsMessagePlugin(mobileRepos);
        MessagePlugin mmsMessagePlugin = new MmsMessagePlugin(mobileRepos);

        MessagePlugin emailMessagePlugin = new EmailMessagePlugin(
                repositoryFactory.getEmailContactRepository(),
                repositoryFactory.getPersonRepository(),
                repositoryFactory.getGroupRepository());

        plugins.add(smsMessagePlugin);
        plugins.add(mmsMessagePlugin);
        plugins.add(emailMessagePlugin);
        plugins.add(printMessagePlugin);

        List<Transport> transports = new ArrayList<Transport>();
        transports.add(new FakeEmailTransportImpl(consolePanel));
        transports.add(new FakePrintTransportImpl(consolePanel));
        transports.add(new FakeMobilePhoneTransport(consolePanel));

        QueueRepository repository = new MemoryQueueRepository(new ArrayList<QueuedMessageImpl>());
        MessageQueue queue = new MessageQueueImpl(transports, repository);

        scheduler.startSchedule(queue);

        final ZhawMessengerUi messengerUi = new ZhawMessengerUi(plugins, queue);

        queue.addReminderListener(new ReminderListener() {
            @Override
            public void remind(Message message) {
                consolePanel.logReminder(message);
            }
        });

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = messengerUi.createUi();
                frame.add(consolePanel, BorderLayout.SOUTH);
                frame.setVisible(true);
            }
        });
    }

}
