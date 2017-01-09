/**
 * Copyright (C) 2016 LibRec
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
 * <p>
=======
 * 
>>>>>>> master
=======
 * 
>>>>>>> last minute commit.
 * This file is part of LibRec.
 * LibRec is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
 * <p>
=======
 *
>>>>>>> master
=======
 *
>>>>>>> last minute commit.
 * LibRec is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
 * <p>
=======
 *
>>>>>>> master
=======
 *
>>>>>>> last minute commit.
 * You should have received a copy of the GNU General Public License
 * along with LibRec. If not, see <http://www.gnu.org/licenses/>.
 */
package net.librec.job.progress;

<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
import net.librec.job.JobStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.atomic.AtomicBoolean;
=======
=======
>>>>>>> last minute commit.
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.librec.job.JobStatus;
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
>>>>>>> master
=======
>>>>>>> last minute commit.

/**
 * Progress Reporter
 * @author YuFeng Wang
 */
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
public abstract class ProgressReporter implements Progressable, Runnable {
    private static final Log LOG = LogFactory.getLog(ProgressReporter.class);
    public static final int PROGRESS_INTERVAL = 1;
    private JobStatus jobStatus = new JobStatus();
    private Object lock = new Object();
    private AtomicBoolean taskDone = new AtomicBoolean(false);
    private AtomicBoolean progressFlag = new AtomicBoolean(false);

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        // get current flag value and reset it as well
        boolean sendProgress = resetProgressFlag();
        while (!taskDone.get()) {
            try {
                synchronized (lock) {
                    if (taskDone.get()) {
                        break;
                    }
                    lock.wait(PROGRESS_INTERVAL);
                    sendProgress = true;
                }
                if (sendProgress) {
                    this.progressx();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void setProgressFlag() {
        progressFlag.set(true);
    }

    boolean resetProgressFlag() {
        return progressFlag.getAndSet(false);
    }

    void setTaskFlag() {
        taskDone.set(true);
    }

    boolean resetTaskFlag() {
        return taskDone.getAndSet(false);
    }

    /**
     * @return the jobStatus
     */
    public JobStatus getJobStatus() {
        return jobStatus;
    }

    /**
     * progress
     */
    public void progressx() {
        progress();
//		LOG.info(jobStatus.getProgress());
    }
=======
=======
>>>>>>> last minute commit.
public abstract class ProgressReporter implements Progressable,Runnable {
	private static final Log LOG = LogFactory.getLog(ProgressReporter.class);
	public static final int PROGRESS_INTERVAL = 1;
	private JobStatus jobStatus = new JobStatus();
	private Object lock = new Object();
	private AtomicBoolean taskDone = new AtomicBoolean(false);
	private AtomicBoolean progressFlag = new AtomicBoolean(false);
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// get current flag value and reset it as well
		boolean sendProgress = resetProgressFlag();
		while (!taskDone.get()) {
			try {
				synchronized (lock) {
					if (taskDone.get()) {
						break;
					}
					lock.wait(PROGRESS_INTERVAL);
					sendProgress = true;
				}
				if (sendProgress) {
					this.progressx();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	void setProgressFlag() {
		progressFlag.set(true);
	}

	boolean resetProgressFlag() {
		return progressFlag.getAndSet(false);
	}

	void setTaskFlag() {
		taskDone.set(true);
	}

	boolean resetTaskFlag() {
		return taskDone.getAndSet(false);
	}

	/**
	 * @return the jobStatus
	 */
	public JobStatus getJobStatus() {
		return jobStatus;
	}
	/**
	 * progress
	 */
	public void progressx() {
		progress();
		LOG.info(jobStatus.getProgress());
	}
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
>>>>>>> master
=======
>>>>>>> last minute commit.
}
