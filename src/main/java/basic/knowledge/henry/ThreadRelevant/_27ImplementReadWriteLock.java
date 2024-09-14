package basic.knowledge.henry.ThreadRelevant;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _27ImplementReadWriteLock {
    private int readers = 0;  // Number of active readers
    private boolean writing = false;  // Indicates if there's an active writer

    private final Lock lock = new ReentrantLock();
    private final Condition canRead = lock.newCondition();
    private final Condition canWrite = lock.newCondition();

    // Acquire the read lock
    public void lockRead() throws InterruptedException {
        lock.lock();
        try {
            // Wait while a writer is writing
            while (writing) {
                canRead.await();
            }
            readers++;
        } finally {
            lock.unlock();
        }
    }

    // Release the read lock
    public void unlockRead() {
        lock.lock();
        try {
            readers--;
            // If no more readers, signal writers
            if (readers == 0) {
                canWrite.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    // Acquire the write lock
    public void lockWrite() throws InterruptedException {
        lock.lock();
        try {
            // Wait while there are active readers or a writer is writing
            while (readers > 0 || writing) {
                canWrite.await();
            }
            writing = true;
        } finally {
            lock.unlock();
        }
    }

    // Release the write lock
    public void unlockWrite() {
        lock.lock();
        try {
            writing = false;
            // Signal all readers and writers
            canRead.signalAll();
            canWrite.signal();
        } finally {
            lock.unlock();
        }
    }
}

