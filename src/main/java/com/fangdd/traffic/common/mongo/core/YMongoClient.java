package com.fangdd.traffic.common.mongo.core;

import com.fangdd.traffic.common.mongo.exceptions.YMongoException;
import com.fangdd.traffic.common.mongo.utils.JacksonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import java.util.List;
import java.util.Map;

/**
 * @author 徐文振
 * @date 15/8/27
 */
public class YMongoClient {
    private static final Map<String, MongoClient> CLIENTS = Maps.newConcurrentMap();

    private String connections;

    private Integer connectionsPerHost;

    private Integer maxWaitTime;

    private String databaseName;

    /**
     * 获取某个数据库的连接
     *
     * @param database 数据库名称
     * @return
     */
    public MongoClient getClient(String database) {
        if (CLIENTS.containsKey(database)) {
            return CLIENTS.get(database);
        }

        if (Strings.isNullOrEmpty(connections)) {
            throw new YMongoException("Mongodb Configure Error, config is null!");
        }
        List<MongoConnectConf> connectConfigs = JacksonUtil.readValue(connections, new TypeReference<List<MongoConnectConf>>(){});

        if (connectConfigs == null || connectConfigs.isEmpty()) {
            throw new YMongoException("Mongodb Configure Error, can't parse Class MongoConnectConf!");
        }
        List<ServerAddress> serverAddresses = Lists.newArrayList();
        MongoCredential mongoCredential = null;
        for (MongoConnectConf config : connectConfigs) {
            serverAddresses.add(new ServerAddress(config.getHost(), config.getPort()));
            boolean hasCredentialInfo = !Strings.isNullOrEmpty(config.getUser()) && !Strings.isNullOrEmpty(config.getPassword());
            if (hasCredentialInfo && mongoCredential == null) {
                mongoCredential = MongoCredential.createScramSha1Credential(config.getUser(), database, config.getPassword().toCharArray());
            }
        }
        MongoClientOptions clientOptions = new MongoClientOptions
                .Builder()
                .connectionsPerHost(connectionsPerHost)
                .maxWaitTime(maxWaitTime)
                .build();

        MongoClient client;
        if (mongoCredential == null) {
            client = new MongoClient(serverAddresses, clientOptions);
        } else {
            client = new MongoClient(serverAddresses, mongoCredential, clientOptions);
        }
        CLIENTS.put(database, client);
        return client;
    }

    public String getConnections() {
        return connections;
    }

    public void setConnections(String connections) {
        this.connections = connections;
    }

    public Integer getConnectionsPerHost() {
        return connectionsPerHost;
    }

    public void setConnectionsPerHost(Integer connectionsPerHost) {
        this.connectionsPerHost = connectionsPerHost;
    }

    public Integer getMaxWaitTime() {
        return maxWaitTime;
    }

    public void setMaxWaitTime(Integer maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
}
