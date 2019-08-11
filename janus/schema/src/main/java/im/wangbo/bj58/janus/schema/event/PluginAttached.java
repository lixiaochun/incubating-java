package im.wangbo.bj58.janus.schema.event;

import com.google.auto.value.AutoValue;

import javax.json.Json;
import javax.json.JsonObject;

/**
 * TODO add brief description here
 *
 * @author Elvis Wang
 */
@AutoValue
public abstract class PluginAttached implements JsonableEvent {
    public abstract long sessionId();

    public abstract long handleId();

    @Override
    public final String type() {
        return MoreEvents.TYPE_PLUGIN_HANDLE_ATTACHED;
    }

    @Override
    public final JsonObject body() {
        return Json.createObjectBuilder()
            .add(MoreEvents.KEY_SESSION_ID, sessionId())
            .add(MoreEvents.KEY_PLUGINHANDLE_ID, handleId())
            .build();
    }

    public static PluginAttached of(final long sessionId, final long pluginHandleId) {
        return new AutoValue_PluginAttached(sessionId, pluginHandleId);
    }
}
