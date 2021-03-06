package com.hartveld.stream.reactive.tests.concurrency;

import com.hartveld.stream.reactive.Observable;
import com.hartveld.stream.reactive.Observer;
import com.hartveld.stream.reactive.concurrency.Scheduler;
import java.util.List;

public interface VirtualTimeScheduler<T> extends Scheduler<Long, Long> {

	/**
	 * Schedule subscription on given source at given time.
	 *
	 * @param source The {@link Observable} to subscribe to. Must be
	 *               non-<code>null</code>.
	 *
	 * @param time   The time at which subscription must take place.
	 *
	 * @see #run(com.hartveld.stream.reactive.Observable)
	 */
	void subscribe(Observable<T> source, long time);

	/**
	 * Close subscription at the given timestamp.
	 *
	 * @param time The time at which the subscription must be closed.
	 *
	 * @see #run(com.hartveld.stream.reactive.Observable)
	 */
	void closeSubscription(long time);

	/**
	 * Run the scheduler along the virtual timeline until there are no more
	 * scheduled actions.
	 *
	 * @return A {@link List} of {@link Notification}s that are captured on an
	 *         internal {@link Observer} as generated by the source
	 *         {@link Observable}.
	 *
	 * @see #run(com.hartveld.stream.reactive.Observable)
	 */
	List<Notification<T>> run();

	/**
	 * Convenience method which runs subscription on the given
	 * {@link Observable} on time 100, and closes the subscription on time 1000.
	 *
	 * @param source The source {@link Observable} to subscribe to. Must be
	 *               non-<code>null</code>.
	 *
	 * @return A {@link List} of {@link Notification}s which are observed on the
	 *         internal {@link Observer} which is subscribed to the given
	 *         {@link Observable} source.
	 *
	 * @see #subscribe(com.hartveld.stream.reactive.Observable, long)
	 * @see #closeSubscription(long)
	 * @see #run()
	 */
	List<Notification<T>> run(final Observable<T> source);

}
