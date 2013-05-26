package zhawmessenger.ui.impl;

import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.swing.AdvancedTableModel;
import ca.odell.glazedlists.swing.GlazedListsSwing;
import ca.odell.glazedlists.swing.TableComparatorChooser;
import zhawmessenger.messagesystem.api.Message;
import zhawmessenger.messagesystem.api.MessageLogger;
import zhawmessenger.messagesystem.api.queue.MessageQueue;
import zhawmessenger.messagesystem.api.queue.QueuedMessage;
import zhawmessenger.messagesystem.api.transport.Transport;
import zhawmessenger.messagesystem.impl.queue.MessageQueueImpl;
import zhawmessenger.messagesystem.impl.transport.EmailTransport;
import zhawmessenger.messagesystem.util.ConsoleMessageLogger;
import zhawmessenger.ui.api.CreateFormFactory;
import zhawmessenger.ui.api.MessageFormFactory;
import zhawmessenger.ui.api.MessagePlugin;
import zhawmessenger.ui.api.SaveListener;
import zhawmessenger.ui.api.action.AbstractCreateAction;
import zhawmessenger.ui.api.action.AbstractManipluateAction;
import zhawmessenger.ui.impl.modules.email.EmailMessagePlugin;

import javax.swing.*;
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

    public ZhawMessengerUi(List<MessagePlugin> messagePlugins,
                           MessageQueue messageQueue) {
        this.messagePlugins = messagePlugins;
        this.messageQueue = messageQueue;
    }

    public void display() {
        JFrame frame = new JFrame("ZHAW Messenger");

        final JMenuBar mainMenuBar;
        final JMenu fileMenu, createMessageMenu;
        final JScrollPane queueScrollPane;
        final JTable messageTable;
        final JPanel mainPanel;
        final JToolBar mainToolbar;

        final EventList<QueuedMessage> queuedMessages = messageQueue.getQueuedMessages();

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        } catch (Exception e) {
            System.out.println("Unable to set native look and feel: " + e);
        }

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
                            sortedMessages, new MessageTableFormat());

            messageTable = new JTable(messageTableModel);

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
            AbstractManipluateAction a = new AbstractCreateAction(frame, mp.getName()) {
                @Override
                protected MessageFormFactory getForm() {
                    return mp.getFormFactory();
                }
            };
            a.addSaveListener(new SaveListener() {
                @Override
                public void saved(Message message) {
                    messageQueue.add(message);
                }
            });
            createMessageMenu.add(a);
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
                        if (mp.doesHandle(message.getMessage().getClass())) {
                            // we can suppress, because plugin did
                            // confirm it can handle the message
                            //noinspection unchecked
                            System.out.println("Edit is called!!");
                        }
                    }
                }
            }
        });

        mainToolbar.add(editButton);

        mainPanel.add(mainToolbar, BorderLayout.NORTH);

        mainPanel.add(queueScrollPane, BorderLayout.CENTER);

        // create frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setSize(540, 380);
        frame.getContentPane().add(mainPanel);
        frame.setJMenuBar(mainMenuBar);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        MessageLogger messageLogger = new ConsoleMessageLogger();

        List<MessagePlugin> plugins =
                new ArrayList<MessagePlugin>();
        plugins.add(new EmailMessagePlugin());

        List<Transport> transports = new ArrayList<Transport>();
        transports.add(new EmailTransport(messageLogger));

        MessageQueue queue = new MessageQueueImpl(transports);

        final ZhawMessengerUi messengerUi = new ZhawMessengerUi(plugins, queue);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                messengerUi.display();
            }
        });
    }

}
