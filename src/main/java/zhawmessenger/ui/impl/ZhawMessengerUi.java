package zhawmessenger.ui.impl;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.swing.AdvancedTableModel;
import ca.odell.glazedlists.swing.GlazedListsSwing;
import ca.odell.glazedlists.swing.TableComparatorChooser;
import zhawmessenger.messagesystem.api.message.Message;
import zhawmessenger.messagesystem.api.queue.MessageQueue;
import zhawmessenger.messagesystem.api.queue.QueuedMessage;
import zhawmessenger.messagesystem.api.transport.Transport;
import zhawmessenger.messagesystem.impl.modules.email.transport.FakeEmailTransportImpl;
import zhawmessenger.messagesystem.impl.queue.MemoryQueueRepository;
import zhawmessenger.messagesystem.impl.queue.MessageQueueImpl;
import zhawmessenger.messagesystem.impl.scheduler.TimeIntervalScheduler;
import zhawmessenger.messagesystem.persistance.QueueRepository;
import zhawmessenger.ui.api.*;
import zhawmessenger.ui.impl.components.JConsolePanel;
import zhawmessenger.ui.impl.components.QueueTable;
import zhawmessenger.ui.impl.modules.email.EmailMessagePlugin;
import zhawmessenger.ui.impl.modules.mms.MmsMessagePlugin;
import zhawmessenger.ui.impl.modules.print.PrintMessagePlugin;
import zhawmessenger.ui.impl.modules.sms.SmsMessagePlugin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 */
public class ZhawMessengerUi {

    private final List<MessagePlugin> messagePlugins;
    private final MessageQueue messageQueue;

    public ZhawMessengerUi(List<MessagePlugin> messagePlugins,
                           MessageQueue messageQueue) {
        this.messagePlugins = messagePlugins;
        this.messageQueue = messageQueue;
    }

    private void openMessage(MessagePlugin mp, JFrame owner) {
        openMessage(mp, owner, null);
    }

    private void openMessage(MessagePlugin mp, JFrame owner, Message message) {
        MessageWindowFactory windowFactory = mp.getWindowFactory();
        final Window win = windowFactory.createWindow(owner);
        MessageFormFactory factory = mp.getFormFactory();
        if (message == null) {
            message = mp.getMessageFactory().createMessage();
        }
        //noinspection unchecked
        final SavableForm savableForm = factory.createForm(win, message);
        savableForm.addSaveListener(new SaveListener() {
            @Override
            public void saved(Message message) {
                messageQueue.add(message);
            }
        });
        savableForm.addCancelListener(new CancelListener() {
            @Override
            public void canceled(Message message) {
                win.dispose();
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

        final EventList<QueuedMessage> queuedMessages = messageQueue.getQueuedMessages();
//
//        try {
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
//        } catch (Exception e) {
//            System.out.println("Unable to set native look and feel: " + e);
//        }

        // lock while creating the transformed models
        queuedMessages.getReadWriteLock().readLock().lock();
        try {
//            UsersSelect usersSelect = new UsersSelect(issuesEventList);
//
//            userSelectList = usersSelect.getJList();
//
//            FilterList<Issue> userFilteredIssues =
//                    new FilterList<Issue>(issuesEventList, usersSelect);
//
//            filterEdit = new JTextField(10);
//
//            FilterList<Issue> textFilteredIssues =
//                    new FilterList<Issue>(userFilteredIssues,
//                            new TextComponentMatcherEditor<Issue>(filterEdit,
//                                    new IssueTextFilterator()));
//
//            priorityFilteredIssues =
//                    new ThresholdList<Issue>(textFilteredIssues,
//                            new IssuePriorityThresholdEvaluator());

            SortedList<QueuedMessage> sortedMessages =
                    new SortedList<QueuedMessage>(queuedMessages,
                            new MessageComparator());

            // create the issues table
            AdvancedTableModel<QueuedMessage> messageTableModel =
                    GlazedListsSwing.eventTableModelWithThreadProxyList(
                            sortedMessages, new MessageTableFormat(messagePlugins));

            messageTable = new QueueTable(messageTableModel);

            //noinspection UnusedDeclaration
            TableComparatorChooser<QueuedMessage> tableSorter = TableComparatorChooser.install(
                    messageTable, sortedMessages, TableComparatorChooser.MULTIPLE_COLUMN_MOUSE);

            queueScrollPane = new JScrollPane(messageTable);
        } finally {
            queuedMessages.getReadWriteLock().readLock().unlock();
        }

        // create the menu structure
        mainMenuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        createMessageMenu = new JMenu("Nachricht erstellen");

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
                int selCol = messageTable.getSelectedColumn();
                if ( selCol >= 0 ) {
                    QueuedMessage message =
                            queuedMessages.get(selCol);
                    for (MessagePlugin mp : messagePlugins) {
                        //noinspection unchecked
                        if (mp.doesHandle(message.getMessage().getClass())) {
                            openMessage(mp, frame, message.getMessage());
                        }
                    }
                }
            }
        });

        final JButton sendNowButton = new JButton("Sofort versenden");
        sendNowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selCol = messageTable.getSelectedColumn();
                if (selCol >= 0) {
                    // set send time to now, the message is
                    // sent asap
                    QueuedMessage message =
                            queuedMessages.get(selCol);
                    Message msg = message.getMessage();
                    msg.setSendTime(new Date().getTime());
                }
            }
        });

        final JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selCol = messageTable.getSelectedColumn();
                if (selCol >= 0) {
                    QueuedMessage queuedMessage = queuedMessages.get(selCol);
                    queuedMessage.suspend();
                    messageQueue.remove(queuedMessage);
                }
            }
        });

        mainToolbar.add(editButton);
        mainToolbar.add(sendNowButton);

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
        final JConsolePanel consolePanel = new JConsolePanel();
        final TimeIntervalScheduler scheduler = new TimeIntervalScheduler(1000);

        UglyContactsCreationFactory repositoryFactory =
                UglyContactsCreationFactory.getInstance();

        // this is a little bit ugly...
        DefaultApplicationContext applicationContext =
                DefaultApplicationContext.getInstance();

        applicationContext.setUserLoggedIn(repositoryFactory.getFmueller());

        List<MessagePlugin> plugins =
                new ArrayList<MessagePlugin>();

        MessagePlugin printMessagePlugin = new PrintMessagePlugin();

        MessagePlugin smsMessagePlugin = new SmsMessagePlugin();

        MessagePlugin mmsMessagePlugin = new MmsMessagePlugin();

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

        EventList<QueuedMessage> messages = new BasicEventList<QueuedMessage>();
        QueueRepository repository = new MemoryQueueRepository(messages);
        MessageQueue queue = new MessageQueueImpl(transports, repository, messages);

        scheduler.startSchedule(queue);

        final ZhawMessengerUi messengerUi = new ZhawMessengerUi(plugins, queue);

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
