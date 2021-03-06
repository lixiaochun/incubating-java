package im.wangbo.bj58.janus.schema.transport;

import com.google.auto.value.AutoValue;

/**
 * TODO add brief description here
 *
 * @author Elvis Wang
 * @author Elvis Wang
 */
@AutoValue
public abstract class SessionId {
    public abstract long id();

    public static SessionId of(final long id) {
        return new AutoValue_SessionId(id);
    }
}
