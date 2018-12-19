package com.openyich.framework.boot.hazelcast;

import org.springframework.beans.factory.InitializingBean;

import com.hazelcast.config.Config;

public abstract class HazelcastConfigurer implements InitializingBean {

  private final HazelcastProperties hazelcast;
  private final Config config = new Config();

  public HazelcastConfigurer(HazelcastProperties properties) {
    this.hazelcast = properties;
  }

  /**
   * Customize for Hazelcast {@link Config}, will override the configuration properties
   */
  public abstract void customize(Config config);

  @Override
  public void afterPropertiesSet() throws Exception {
    config.setInstanceName(hazelcast.getInstanceName());
    config.setGroupConfig(hazelcast.getGroupConfig());
    config.setNetworkConfig(hazelcast.getNetworkConfig());
    config.setMapConfigs(hazelcast.getMapConfigs());
    config.setCacheConfigs(hazelcast.getCacheConfigs());
    config.setTopicConfigs(hazelcast.getTopicConfigs());
    config.setReliableTopicConfigs(hazelcast.getReliableTopicConfigs());
    config.setQueueConfigs(hazelcast.getQueueConfigs());
    config.setLockConfigs(hazelcast.getLockConfigs());
    config.setMultiMapConfigs(hazelcast.getMultiMapConfigs());
    config.setListConfigs(hazelcast.getListConfigs());
    config.setSetConfigs(hazelcast.getSetConfigs());
    config.setExecutorConfigs(hazelcast.getExecutorConfigs());
    config.setDurableExecutorConfigs(hazelcast.getDurableExecutorConfigs());
    config.setScheduledExecutorConfigs(hazelcast.getScheduledExecutorConfigs());
    config.setSemaphoreConfigs(hazelcast.getSemaphoreConfigs());
    config.setCountDownLatchConfigs(hazelcast.getCountDownLatchConfigs());
    config.setReplicatedMapConfigs(hazelcast.getReplicatedMapConfigs());
    config.setWanReplicationConfigs(hazelcast.getWanReplicationConfigs());
    config.setJobTrackerConfigs(hazelcast.getJobTrackerConfigs());
    config.setQuorumConfigs(hazelcast.getQuorumConfigs());
    config.setRingbufferConfigs(hazelcast.getRingbufferConfigs());
    config.setCardinalityEstimatorConfigs(hazelcast.getCardinalityEstimatorConfigs());
    config.setMapEventJournalConfigs(hazelcast.getMapEventJournalConfigs());
    config.setCacheEventJournalConfigs(hazelcast.getCacheEventJournalConfigs());
    config.setMapMerkleTreeConfigs(hazelcast.getMapMerkleTreeConfigs());
    config.setFlakeIdGeneratorConfigs(hazelcast.getFlakeIdGeneratorConfigMap());
    config.setAtomicLongConfigs(hazelcast.getAtomicLongConfigs());
    config.setAtomicReferenceConfigs(hazelcast.getAtomicReferenceConfigs());
    config.setPNCounterConfigs(hazelcast.getPnCounterConfigs());
    config.setServicesConfig(hazelcast.getServicesConfig());
    config.setSecurityConfig(hazelcast.getSecurityConfig());
    config.setPartitionGroupConfig(hazelcast.getPartitionGroupConfig());
    config.setManagementCenterConfig(hazelcast.getManagementCenterConfig());
    config.setSerializationConfig(hazelcast.getSerializationConfig());
    config.setMemberAttributeConfig(hazelcast.getMemberAttributeConfig());
    config.setNativeMemoryConfig(hazelcast.getNativeMemoryConfig());
    config.setHotRestartPersistenceConfig(hazelcast.getHotRestartPersistenceConfig());
    config.setUserCodeDeploymentConfig(hazelcast.getUserCodeDeploymentConfig());
    config.setCRDTReplicationConfig(hazelcast.getCrdtReplicationConfig());
    config.setLiteMember(hazelcast.isLiteMember());
    this.customize(config);
  }

  public Config getConfig() {
    return config;
  }

}
