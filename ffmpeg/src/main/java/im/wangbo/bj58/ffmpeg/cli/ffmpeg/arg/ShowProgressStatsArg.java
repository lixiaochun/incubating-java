package im.wangbo.bj58.ffmpeg.cli.ffmpeg.arg;

import com.google.auto.value.AutoValue;

import java.util.Optional;

/**
 * TODO add brief description here
 *
 * @author Elvis Wang
 */
@AutoValue
public abstract class ShowProgressStatsArg implements GlobalArg {
    @Override
    public final String name() {
        return showsProcessStats() ? "-stats" : "-nostats";
    }

    abstract boolean showsProcessStats();

    @Override
    public final String description() {
        return "Print encoding progress/statistics or not. It is on by default.";
    }

    @Override
    public final Optional<String> value() {
        return Optional.empty();
    }

    private static ShowProgressStatsArg of(boolean showsProcessStats) {
        return new AutoValue_ShowProgressStatsArg(showsProcessStats);
    }

    public static ShowProgressStatsArg on() {
        return of(true);
    }

    public static ShowProgressStatsArg off() {
        return of(false);
    }
}
