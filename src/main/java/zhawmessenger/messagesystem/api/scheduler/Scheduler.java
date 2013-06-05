package zhawmessenger.messagesystem.api.scheduler;

/**
 */
public interface Scheduler {
    void startSchedule(Schedulable schedulable);

    void stopSchedule(Schedulable schedulable);
}
