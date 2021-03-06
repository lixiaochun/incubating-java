package im.wangbo.bj58.ffmpeg.cli.ffprobe.writer;

import im.wangbo.bj58.ffmpeg.cli.arg.Arg;
import im.wangbo.bj58.ffmpeg.cli.ffprobe.writer.json.JsonBasedWriterFormat;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * TODO add brief description here
 *
 * @author Elvis Wang
 */
public interface WriterFormat {
    WriterMeta meta();

    ImmutableList<Arg> args();

    static WriterFormat json() {
        return JsonBasedWriterFormat.of();
    }
}
