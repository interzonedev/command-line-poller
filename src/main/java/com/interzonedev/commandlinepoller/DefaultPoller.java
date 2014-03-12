package com.interzonedev.commandlinepoller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple {@link Poller} implemenation that takes a {@link PollProcessor} to call back on command line input.
 * 
 * @author interzone
 * 
 */
public class DefaultPoller implements Poller {

    private static final Logger log = LoggerFactory.getLogger(DefaultPoller.class);

    private static final List<String> DEFAULT_QUIT_INPUTS = Arrays.asList(new String[] { "quit", "q" });

    private final PollProcessor pollProcessor;

    private final List<String> quitInputs;

    public DefaultPoller(PollProcessor pollerProcessor, List<String> quitInputs) {
        this.pollProcessor = pollerProcessor;
        this.quitInputs = quitInputs;
    }

    public DefaultPoller(PollProcessor pollerProcessor) {
        this.pollProcessor = pollerProcessor;
        this.quitInputs = DEFAULT_QUIT_INPUTS;
    }

    @Override
    public void poll() {

        log.debug("poll: Start");

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String input = br.readLine();

            while ((null != input) && !quitInputs.contains(input.trim())) {

                if (!"".equals(input.trim())) {
                    String[] args = input.trim().split("\\s+");
                    pollProcessor.process(args);
                }

                input = br.readLine();

            }
        } catch (Exception e) {
            log.error("poll: Error reading command line input", e);
        }

        log.debug("poll: End");

    }

}
