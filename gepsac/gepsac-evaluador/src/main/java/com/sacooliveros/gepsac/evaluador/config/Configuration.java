/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.evaluador.config;

import com.sacooliveros.gepsac.evaluador.util.ResourceHelper;
import java.util.Properties;

/**
 *
 * @author Ricardo
 */
public class Configuration {

    private final String brokerName;
    private final int timeForThreads;
    private final int brokerInterval;

    private final int numThreads;
    private final String serverName;

    public Configuration(String configname) {
        this(ResourceHelper.loadConfig(configname));
    }

    public Configuration(Properties config) {
        brokerName = (String) config.getProperty("BrokerName");
        timeForThreads = Integer.parseInt((String) config.getProperty("BrokerTimeToBusyThreads"));
        brokerInterval = Integer.parseInt((String) config.getProperty("BrokerInterval"));

        numThreads = Integer.parseInt((String) config.getProperty("ControllerThreads"));
        serverName = (String) config.getProperty("SIXServer");
    }

    public String getBrokerName() {
        return brokerName;
    }

    public int getTimeForThreads() {
        return timeForThreads;
    }

    public int getBrokerInterval() {
        return brokerInterval;
    }

    public int getNumThreads() {
        return numThreads;
    }

    public String getServerName() {
        return serverName;
    }

    @Override
    public String toString() {
        return "Configuration{" + "brokerName=" + brokerName + ", timeForThreads=" + timeForThreads + ", brokerInterval=" + brokerInterval + ", numThreads=" + numThreads + ", serverName=" + serverName + '}';
    }

}
