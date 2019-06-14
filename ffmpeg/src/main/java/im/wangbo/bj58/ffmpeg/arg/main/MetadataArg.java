package im.wangbo.bj58.ffmpeg.arg.main;

import com.google.auto.value.AutoValue;

import java.util.Optional;

import im.wangbo.bj58.ffmpeg.arg.OutputArg;

/**
 * TODO add brief description here
 *
 * @author Elvis Wang
 */
@AutoValue
public abstract class MetadataArg implements OutputArg {
    @Override
    public final String argName() {
        return "-metadata";
    }

    @Override
    public final String description() {
        return "Set a metadata key/value pair.";
    }

    abstract String key();

    abstract String value();

    @Override
    public final Optional<String> argValue() {
        return Optional.of(key() + "=" + value());
    }

    public static MetadataArg of(final String key, final String value) {
        return new AutoValue_MetadataArg(key, value);
    }
}
