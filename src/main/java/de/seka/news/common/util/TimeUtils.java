package de.seka.news.common.util;

import javax.annotation.Nullable;
import java.time.Duration;
import java.time.Instant;

/**
 * Utility methods for dealing with time. Particularly duration.
 */
public interface TimeUtils {

    /**
     * Get the duration between when something was started and finished.
     *
     * @param started  The start time. Can be null will automatically set the duration to 0
     * @param finished The finish time. If null will use (current time - started time) to get the duration
     * @return The duration or zero if no duration
     */
    static Duration getDuration(@Nullable final Instant started, @Nullable final Instant finished) {
        if (started == null || started.toEpochMilli() == 0L) {
            // Never started
            return Duration.ZERO;
        } else if (finished == null || finished.toEpochMilli() == 0L) {
            // Started but hasn't finished
            return Duration.ofMillis(Instant.now().toEpochMilli() - started.toEpochMilli());
        } else {
            // Started and finished
            return Duration.ofMillis(finished.toEpochMilli() - started.toEpochMilli());
        }
    }
}
