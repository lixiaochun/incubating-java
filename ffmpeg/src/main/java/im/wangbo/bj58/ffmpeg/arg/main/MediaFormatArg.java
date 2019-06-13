package im.wangbo.bj58.ffmpeg.arg.main;

import com.google.auto.value.AutoValue;

import java.util.Optional;

import im.wangbo.bj58.ffmpeg.arg.InputArg;
import im.wangbo.bj58.ffmpeg.ffmpeg.format.MediaFormat;

/**
 * TODO add brief description here
 *
 * @author Elvis Wang
 */
@AutoValue
public abstract class MediaFormatArg implements InputArg {
    @Override
    public final String argName() {
        return "-f";
    }

    @Override
    public final String description() {
        return "Force input or output file format. " +
                "The format is normally auto detected for input files and " +
                "guessed from the file extension for output files, " +
                "so this option is not needed in most cases";
    }

    abstract String format();

    @Override
    public final Optional<String> argValue() {
        return Optional.of(format());
    }

    private static MediaFormatArg create(final String f) {
        return new AutoValue_MediaFormatArg(f);
    }

    public static MediaFormatArg asInput(final MediaFormat f) {
        return create(f.demuxer().demuxerName());
    }

    public static MediaFormatArg asOutput(final MediaFormat f) {
        return create(f.muxer().muxerName());
    }
}
