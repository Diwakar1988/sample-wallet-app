package com.github.diwakar1988.noon.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by 'Diwakar Mishra' on 16,November,2018
 */

public class AsyncUtil {

    public static final int THREAD_POOL_SIZE = 5;
    private static final int MAX_THREADS = 10;
    private static final long IDLE_TIME = 30L; //seconds

    private static volatile AsyncUtil instance = createInstance();

    private class MainThreadExecutor implements Executor {
        private Handler handler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(Runnable command) {
            handler.post(command);
        }
    }
    private ThreadPoolExecutor threadPoolExecutor;
    private MainThreadExecutor mainThreadExecutor;

    private AsyncUtil() {
        if (instance != null) {
            throw new IllegalStateException("Instance already initialized!");
        }
        instantiateThreadPool();
        mainThreadExecutor = new MainThreadExecutor();
    }

    private void instantiateThreadPool() {
        threadPoolExecutor = new ThreadPoolExecutor(
                THREAD_POOL_SIZE,
                MAX_THREADS,
                IDLE_TIME,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    private static AsyncUtil createInstance() {
        if (instance==null){
            synchronized (AsyncUtil.class) {
                if (instance == null) {
                    instance = new AsyncUtil();
                }
            }

        }
        return instance;
    }

    /***
     * Run tasks on worker thread
     * @param runnable task tobe run
     */
    public static void run(Runnable runnable) {
        if (instance.threadPoolExecutor.isTerminated()){
            instance.instantiateThreadPool();
        }
        instance.threadPoolExecutor.execute(runnable);
    }
    /***
     * Run tasks on main/ui thread
     * @param runnable task tobe run
     */
    public static void runOnUi(Runnable runnable) {
        if (runningOnUIThread()){
            runnable.run();
        }else{
            instance.mainThreadExecutor.execute(runnable);
        }
    }

    public static void stop(){
        instance.threadPoolExecutor.shutdownNow();
    }

    public static boolean runningOnUIThread(){
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
