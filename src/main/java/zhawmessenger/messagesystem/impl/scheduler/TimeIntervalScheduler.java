package zhawmessenger.messagesystem.impl.scheduler;

import zhawmessenger.messagesystem.api.scheduler.Schedulable;
import zhawmessenger.messagesystem.api.scheduler.Scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 */
public class TimeIntervalScheduler implements Scheduler {
    private final Timer timer;
    private final long interval;

    private final List<CallSchedulableTask> tasks;

    public TimeIntervalScheduler(long interval) {
        this.interval = interval;
        this.timer = new Timer();
        this.tasks = new ArrayList<CallSchedulableTask>();
    }

    @Override
    public void startSchedule(Schedulable schedulable) {
        final CallSchedulableTask task = new CallSchedulableTask(schedulable);
        this.timer.scheduleAtFixedRate(task, this.interval, this.interval);
        this.tasks.add(task);
    }

    @Override
    public void stopSchedule(Schedulable schedulable) {
        for (CallSchedulableTask task : tasks) {
            if (task.getSchedulable().equals(schedulable)) {
                task.cancel();
            }
        }
    }

    class CallSchedulableTask extends TimerTask {
        private final Schedulable schedulable;

        public CallSchedulableTask(Schedulable schedulable) {
            this.schedulable = schedulable;
        }

        public Schedulable getSchedulable() {
            return schedulable;
        }

        @Override
        public void run() {
            this.schedulable.schedule();
        }
    }
}
