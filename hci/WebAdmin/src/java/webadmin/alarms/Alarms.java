package webadmin.alarms;

import fr.unice.i3s.modalis.jseduite.technical.alarms.Alarm;
import fr.unice.i3s.modalis.jseduite.technical.breaktime.BreakTime;

/**
 *
 * @author Steve Colombié
 */
public class Alarms {
    private BreakTime breakTime;
    private Alarm alarmStart;
    private Alarm alarmAlmostEnd;
    private Alarm alarmEnd;

    public Alarms() {
        alarmEnd = new Alarm();
        alarmAlmostEnd = new Alarm();
        alarmStart = new Alarm();
    }

    public BreakTime getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(BreakTime breakTime) {
        this.breakTime = breakTime;
    }

    public Alarm getAlarmStart() {
        return alarmStart;
    }

    public void setAlarmStart(Alarm alarmStart) {
        this.alarmStart = alarmStart;
    }

    public Alarm getAlarmAlmostEnd() {
        return alarmAlmostEnd;
    }

    public void setAlarmAlmostEnd(Alarm alarmAlmostEnd) {
        this.alarmAlmostEnd = alarmAlmostEnd;
    }

    public Alarm getAlarmEnd() {
        return alarmEnd;
    }

    public void setAlarmEnd(Alarm alarmEnd) {
        this.alarmEnd = alarmEnd;
    }

    public void addAlarm(Alarm alarm) {
        if (alarm.getKind().equals("start")) {
            setAlarmStart(alarm);
        }
        else if (alarm.getKind().equals("almost_end")) {
            setAlarmAlmostEnd(alarm);
        }
        else if (alarm.getKind().equals("end")) {
            setAlarmEnd(alarm);
        }
    }
}
