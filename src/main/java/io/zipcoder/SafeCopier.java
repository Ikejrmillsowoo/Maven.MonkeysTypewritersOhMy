package io.zipcoder;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Make this extend the Copier like `UnsafeCopier`, except use locks to make sure that the actual intro gets printed
 * correctly every time.  Make the run method thread safe.
 */
public class SafeCopier extends Copier {

    private final ReentrantLock lock = new ReentrantLock();
    public SafeCopier(String toCopy) {
        super(toCopy);
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                while (this.stringIterator.hasNext()) {
                    this.copied = this.copied.concat(" " + this.stringIterator.next());
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
