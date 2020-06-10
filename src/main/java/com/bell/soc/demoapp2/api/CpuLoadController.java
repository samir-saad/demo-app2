package com.bell.soc.demoapp2.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/cpu/load")
public class CpuLoadController {

    private final Logger logger = LoggerFactory.getLogger(CpuLoadController.class);

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public String load(@RequestParam(value = "cores", defaultValue = "1") int cores,
                       @RequestParam(value = "threadsPerCore", defaultValue = "2") int threadsPerCore,
                       @RequestParam(value = "load", defaultValue = ".8") double load,
                       @RequestParam(value = "duration", defaultValue = "1000") long duration) {
        logger.info("Generating CPU load");

        for (int thread = 0; thread < cores * threadsPerCore; thread++) {
            new BusyThread("Thread" + thread, load, duration).start();
        }

        return "CPU load finished";
    }

    private static class BusyThread extends Thread {
        private final Logger logger = LoggerFactory.getLogger(BusyThread.class);

        private double load;
        private long duration;

        /**
         * Constructor which creates the thread
         *
         * @param name     Name of this thread
         * @param load     Load % that this thread should generate
         * @param duration Duration that this thread should generate the load for
         */
        public BusyThread(String name, double load, long duration) {
            super(name);
            this.load = load;
            this.duration = duration;
        }

        /**
         * Generates the load when run
         */
        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            try {
                // Loop for the given duration
                while (System.currentTimeMillis() - startTime < duration) {
                    // Every 100ms, sleep for the percentage of unladen time
                    if (System.currentTimeMillis() % 100 == 0) {
                        Thread.sleep((long) Math.floor((1 - load) * 100));
                    }
                }
            } catch (Exception e) {
                logger.error("Error happened", e);
            }
        }
    }
}