package im.wangbo.bj58.ffmpeg.cli.ffmpeg.filter;

import com.google.auto.value.AutoValue;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * TODO more details here.
 * <p>
 * Created at 2019-07-20, by Elvis Wang
 */
@AutoValue
public abstract class FilterChain {
    public abstract ImmutableList<Filter> filters();

    public static FilterChain of(final ImmutableList<Filter> filters) {
        return new AutoValue_FilterChain(filters);
    }
}
