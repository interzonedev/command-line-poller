package com.interzonedev.commandlinepoller;

/**
 * Interface for processing command line input.
 * 
 * @author interzone
 */
public interface PollProcessor {

    /**
     * Processes input from polling the command line.
     * 
     * @param args
     *            The whitespace separated arguments entered on the command line as a {@link String} array.
     */
    public void process(String args[]);

}
