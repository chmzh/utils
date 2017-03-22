package com.cmz.jcsp;

import org.jcsp.lang.Alternative;
import org.jcsp.lang.AltingChannelInputInt;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.CSTimer;
import org.jcsp.lang.ChannelOutputInt;
import org.jcsp.lang.Guard;

public class ScaleInt implements CSProcess {
	private int s;
	private final ChannelOutputInt out, factor;
	private final AltingChannelInputInt in, suspend, inject;

	public ScaleInt(int s, AltingChannelInputInt suspend, AltingChannelInputInt in, ChannelOutputInt factor,
			AltingChannelInputInt inject, ChannelOutputInt out) {
		this.s = s;
		this.in = in;
		this.out = out;
		this.suspend = suspend;
		this.factor = factor;
		this.inject = inject;
	}

	public void run() {
		final long second = 1000; // Java timings are in millisecs
		final long doubleInterval = 5 * second;
		final CSTimer timer = new CSTimer();
		final Alternative normalAlt = new Alternative(new Guard[] { suspend, timer, in });

		final int NORMAL_SUSPEND = 0, NORMAL_TIMER = 1, NORMAL_IN = 2;
		final Alternative suspendedAlt = new Alternative(new Guard[] { inject, in });

		final int SUSPENDED_INJECT = 0, SUSPENDED_IN = 1;

		long timeout = timer.read() + doubleInterval;
		timer.setAlarm(timeout);
		while (true) {
			switch (normalAlt.priSelect()) {
			case NORMAL_SUSPEND:
				suspend.read(); // don't care what's sent
				factor.write(s); // reply with the crucial information
				boolean suspended = true;
				while (suspended) {
					switch (suspendedAlt.priSelect()) {
					case SUSPENDED_INJECT: // this is the resume signal as well
						s = inject.read(); // get the new scaling factor
						suspended = false; // and resume normal operations
						timeout = timer.read() + doubleInterval;
						timer.setAlarm(timeout);
						break;
					case SUSPENDED_IN:
						out.write(in.read());
						break;
					}
				}
				break;
			case NORMAL_TIMER:
				timeout = timer.read() + doubleInterval;
				timer.setAlarm(timeout);
				s = s * 2;
				break;
			case NORMAL_IN:
				out.write(s * in.read());
				break;
			}
		}
	}
}
