package im.wangbo.bj58.ffmpeg.cli.ffmpeg.arg;

import com.google.auto.value.AutoValue;
import im.wangbo.bj58.ffmpeg.common.SeekPosition;

import java.util.Optional;

/**
 * TODO add brief description here
 *
 * @author Elvis Wang
 */
@AutoValue
public abstract class SeekingOffsetArg implements InOutputArg {
    abstract SeekPosition position();

    @Override
    public final String name() {
        return "-ss";
    }

    @Override
    public final String description() {
        return "When used as an input option, seeks in this input file to position " +
            "(in most formats it is not possible to seek exactly, " +
            "so ffmpeg will seek to the closest seek point before position.)." +
            "When used as an output option, decodes but discards input until the timestamps reach position.";
    }

    @Override
    public final Optional<String> value() {
        return Optional.of(position().asString());
    }

    public static SeekingOffsetArg of(final SeekPosition position) {
        return new AutoValue_SeekingOffsetArg(position);
    }
}
