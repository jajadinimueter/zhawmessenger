package zhawmessenger.ui.impl;

import zhawmessenger.messagesystem.api.modules.addressbook.Group;
import zhawmessenger.messagesystem.api.modules.addressbook.Person;
import zhawmessenger.messagesystem.api.modules.addressbook.persistance.GroupRepository;
import zhawmessenger.messagesystem.api.modules.addressbook.persistance.PersonRepository;
import zhawmessenger.messagesystem.api.modules.email.contact.EmailContact;
import zhawmessenger.messagesystem.api.modules.email.persistance.EmailContactRepository;
import zhawmessenger.messagesystem.api.modules.print.contact.Printer;
import zhawmessenger.messagesystem.api.modules.print.persistance.PrinterRepository;
import zhawmessenger.messagesystem.impl.modules.addressbook.GroupImpl;
import zhawmessenger.messagesystem.impl.modules.addressbook.PersonImpl;
import zhawmessenger.messagesystem.impl.modules.addressbook.persistance.MemoryGroupRepository;
import zhawmessenger.messagesystem.impl.modules.addressbook.persistance.MemoryPersonRepository;
import zhawmessenger.messagesystem.impl.modules.email.contact.EmailContactImpl;
import zhawmessenger.messagesystem.impl.modules.email.persistance.MemoryEmailContactRepository;
import zhawmessenger.messagesystem.impl.modules.print.contact.PrinterImpl;
import zhawmessenger.messagesystem.impl.modules.print.persistance.MemoryPrinterRepository;

import java.util.Arrays;
import java.util.UUID;

/**
 * Used to create the varios contacts and repositories
 * while not using a database
 */
public class UglyContactsCreationFactory {

    private static UglyContactsCreationFactory instance;

    private final Printer hpDeskJet;
    private final Printer brother;

    private final EmailContact bfuchsEmail1;
    private final EmailContact bfuchsEmail2;

    private final EmailContact fmuellerEmail1;
    private final EmailContact fmuellerEmail2;

    private final EmailContact bgreenEmail;
    private final EmailContact aeinsteinEmail;

    private final Person fmueller;
    private final Person bfuchs;
    private final Person aeinstein;
    private final Person bgreen;

    private final Group groupPhysisists;
    private final Group groupInfStudents;

    private final EmailContactRepository emailContactRepository;
    private final PersonRepository personRepository;
    private final GroupRepository groupRepository;
    private final PrinterRepository printerRepository;

    private UglyContactsCreationFactory() {
        aeinsteinEmail = new EmailContactImpl(UUID.randomUUID(), "a.einstein@gmx.ch");
        bgreenEmail = new EmailContactImpl(UUID.randomUUID(), "brian.green@mit.com");

        bfuchsEmail1 = new EmailContactImpl(UUID.randomUUID(), "bfuchs@zhaw.ch");
        bfuchsEmail2 = new EmailContactImpl(UUID.randomUUID(), "bernhard.fuchs@hotmail.com");

        fmuellerEmail1 = new EmailContactImpl(UUID.randomUUID(), "fmueller@zhaw.ch");
        fmuellerEmail2 = new EmailContactImpl(UUID.randomUUID(), "jajadinimueter@gmail.com");

        fmueller = new PersonImpl(UUID.randomUUID(), "Florian Müller", "fmueller");
        bfuchs = new PersonImpl(UUID.randomUUID(), "Bernhard Fuchs", "bfuchs");
        aeinstein = new PersonImpl(UUID.randomUUID(), "Albert Einstein", "aeinstein");
        bgreen = new PersonImpl(UUID.randomUUID(), "Brian Greene", "bgreen");

        groupPhysisists = new GroupImpl(UUID.randomUUID(), "Physiker");
        groupInfStudents = new GroupImpl(UUID.randomUUID(), "Informatik-Studenten");

        brother = new PrinterImpl(UUID.randomUUID(), "Brother Px44");
        hpDeskJet = new PrinterImpl(UUID.randomUUID(), "HP Desk Jet");

        groupPhysisists.addPerson(aeinstein);
        groupPhysisists.addPerson(bgreen);

        groupInfStudents.addPerson(fmueller);
        groupInfStudents.addPerson(bfuchs);

        fmueller.addEmailContact(fmuellerEmail1);
        fmueller.addEmailContact(fmuellerEmail2);

        bfuchs.addEmailContact(bfuchsEmail1);
        bfuchs.addEmailContact(bfuchsEmail2);

        aeinstein.addEmailContact(aeinsteinEmail);
        bgreen.addEmailContact(bgreenEmail);

        emailContactRepository = new MemoryEmailContactRepository(Arrays.asList(
                aeinsteinEmail,
                bgreenEmail,
                fmuellerEmail1,
                fmuellerEmail2,
                bfuchsEmail1,
                bfuchsEmail2
        ));

        personRepository = new MemoryPersonRepository(Arrays.asList(
                fmueller, bfuchs, aeinstein, bgreen
        ));

        groupRepository = new MemoryGroupRepository(Arrays.asList(
                groupPhysisists, groupInfStudents
        ));

        printerRepository = new MemoryPrinterRepository(Arrays.asList(
                brother, hpDeskJet
        ));

    }

    /**
     * This is NOT THREADSAFE
     *
     * @return an factory instalce
     */
    public static UglyContactsCreationFactory getInstance() {
        if (instance == null) {
            instance = new UglyContactsCreationFactory();
        }
        return instance;
    }

    public Printer getHpDeskJet() {
        return hpDeskJet;
    }

    public Printer getBrother() {
        return brother;
    }

    public EmailContact getBfuchsEmail1() {
        return bfuchsEmail1;
    }

    public EmailContact getBfuchsEmail2() {
        return bfuchsEmail2;
    }

    public EmailContact getFmuellerEmail1() {
        return fmuellerEmail1;
    }

    public EmailContact getFmuellerEmail2() {
        return fmuellerEmail2;
    }

    public EmailContact getBgreenEmail() {
        return bgreenEmail;
    }

    public EmailContact getAeinsteinEmail() {
        return aeinsteinEmail;
    }

    public Person getFmueller() {
        return fmueller;
    }

    public Person getBfuchs() {
        return bfuchs;
    }

    public Person getAeinstein() {
        return aeinstein;
    }

    public Person getBgreen() {
        return bgreen;
    }

    public Group getGroupPhysisists() {
        return groupPhysisists;
    }

    public Group getGroupInfStudents() {
        return groupInfStudents;
    }

    public PrinterRepository getPrinterRepository() {
        return printerRepository;
    }

    public EmailContactRepository getEmailContactRepository() {
        return emailContactRepository;
    }

    public PersonRepository getPersonRepository() {
        return personRepository;
    }

    public GroupRepository getGroupRepository() {
        return groupRepository;
    }

}
