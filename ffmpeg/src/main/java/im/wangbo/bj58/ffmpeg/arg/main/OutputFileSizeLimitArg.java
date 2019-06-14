package im.wangbo.bj58.ffmpeg.arg.main;

import com.google.auto.value.AutoValue;

import java.net.URI;
import java.util.Optional;

import im.wangbo.bj58.ffmpeg.arg.InputArg;
import im.wangbo.bj58.ffmpeg.arg.OutputArg;
import im.wangbo.bj58.ffmpeg.arg.SizeInByte;

/**
 * TODO add brief description here
 *
 * @author Elvis Wang
 */
@AutoValue
public abstract class OutputFileSizeLimitArg implements OutputArg {
    @Override
    public final String argName() {
        return "-fs";
    }

    @Override
    public final String description() {
        return "Set the file size limit, expressed in bytes. " +
                "No further chunk of bytes is written after the limit is exceeded.";
    }

    abstract SizeInByte size();

    @Override
    public final Optional<String> argValue() {
        return Optional.of(size().asString());
    }

    public static OutputFileSizeLimitArg of(final SizeInByte size) {
        return new AutoValue_OutputFileSizeLimitArg(size);
    }
}