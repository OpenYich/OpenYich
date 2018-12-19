package com.openyich.framework.boot.hazelcast;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.hazelcast.config.AtomicLongConfig;
import com.hazelcast.config.AtomicReferenceConfig;
import com.hazelcast.config.CRDTReplicationConfig;
import com.hazelcast.config.CacheSimpleConfig;
import com.hazelcast.config.CardinalityEstimatorConfig;
import com.hazelcast.config.CountDownLatchConfig;
import com.hazelcast.config.DurableExecutorConfig;
import com.hazelcast.config.EventJournalConfig;
import com.hazelcast.config.ExecutorConfig;
import com.hazelcast.config.FlakeIdGeneratorConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.config.HotRestartPersistenceConfig;
import com.hazelcast.config.JobTrackerConfig;
import com.hazelcast.config.ListConfig;
import com.hazelcast.config.LockConfig;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MemberAttributeConfig;
import com.hazelcast.config.MerkleTreeConfig;
import com.hazelcast.config.MultiMapConfig;
import com.hazelcast.config.NativeMemoryConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.config.PNCounterConfig;
import com.hazelcast.config.PartitionGroupConfig;
import com.hazelcast.config.QueueConfig;
import com.hazelcast.config.QuorumConfig;
import com.hazelcast.config.ReliableTopicConfig;
import com.hazelcast.config.ReplicatedMapConfig;
import com.hazelcast.config.RingbufferConfig;
import com.hazelcast.config.ScheduledExecutorConfig;
import com.hazelcast.config.SecurityConfig;
import com.hazelcast.config.SemaphoreConfig;
import com.hazelcast.config.SerializationConfig;
import com.hazelcast.config.ServicesConfig;
import com.hazelcast.config.SetConfig;
import com.hazelcast.config.TopicConfig;
import com.hazelcast.config.UserCodeDeploymentConfig;
import com.hazelcast.config.WanReplicationConfig;

/**
 * Properties specific to Hazelcast
 */
@ConfigurationProperties(prefix = "openyich.hazelcast")
public class HazelcastProperties {

  private String instanceName = "hazelcast";

  private GroupConfig groupConfig = new GroupConfig();

  private NetworkConfig networkConfig = new NetworkConfig();

  private final Map<String, MapConfig> mapConfigs = new ConcurrentHashMap<String, MapConfig>();

  private final Map<String, CacheSimpleConfig> cacheConfigs =
      new ConcurrentHashMap<String, CacheSimpleConfig>();

  private final Map<String, TopicConfig> topicConfigs =
      new ConcurrentHashMap<String, TopicConfig>();

  private final Map<String, ReliableTopicConfig> reliableTopicConfigs =
      new ConcurrentHashMap<String, ReliableTopicConfig>();

  private final Map<String, QueueConfig> queueConfigs =
      new ConcurrentHashMap<String, QueueConfig>();

  private final Map<String, LockConfig> lockConfigs = new ConcurrentHashMap<String, LockConfig>();

  private final Map<String, MultiMapConfig> multiMapConfigs =
      new ConcurrentHashMap<String, MultiMapConfig>();

  private final Map<String, ListConfig> listConfigs = new ConcurrentHashMap<String, ListConfig>();

  private final Map<String, SetConfig> setConfigs = new ConcurrentHashMap<String, SetConfig>();

  private final Map<String, ExecutorConfig> executorConfigs =
      new ConcurrentHashMap<String, ExecutorConfig>();

  private final Map<String, DurableExecutorConfig> durableExecutorConfigs =
      new ConcurrentHashMap<String, DurableExecutorConfig>();

  private final Map<String, ScheduledExecutorConfig> scheduledExecutorConfigs =
      new ConcurrentHashMap<String, ScheduledExecutorConfig>();

  private final Map<String, SemaphoreConfig> semaphoreConfigs =
      new ConcurrentHashMap<String, SemaphoreConfig>();

  private final Map<String, CountDownLatchConfig> countDownLatchConfigs =
      new ConcurrentHashMap<String, CountDownLatchConfig>();

  private final Map<String, ReplicatedMapConfig> replicatedMapConfigs =
      new ConcurrentHashMap<String, ReplicatedMapConfig>();

  private final Map<String, WanReplicationConfig> wanReplicationConfigs =
      new ConcurrentHashMap<String, WanReplicationConfig>();

  private final Map<String, JobTrackerConfig> jobTrackerConfigs =
      new ConcurrentHashMap<String, JobTrackerConfig>();

  private final Map<String, QuorumConfig> quorumConfigs =
      new ConcurrentHashMap<String, QuorumConfig>();

  private final Map<String, RingbufferConfig> ringbufferConfigs =
      new ConcurrentHashMap<String, RingbufferConfig>();

  private final Map<String, CardinalityEstimatorConfig> cardinalityEstimatorConfigs =
      new ConcurrentHashMap<String, CardinalityEstimatorConfig>();

  private final Map<String, EventJournalConfig> mapEventJournalConfigs =
      new ConcurrentHashMap<String, EventJournalConfig>();

  private final Map<String, EventJournalConfig> cacheEventJournalConfigs =
      new ConcurrentHashMap<String, EventJournalConfig>();

  private final Map<String, MerkleTreeConfig> mapMerkleTreeConfigs =
      new ConcurrentHashMap<String, MerkleTreeConfig>();

  private final Map<String, FlakeIdGeneratorConfig> flakeIdGeneratorConfigMap =
      new ConcurrentHashMap<String, FlakeIdGeneratorConfig>();

  private final Map<String, AtomicLongConfig> atomicLongConfigs =
      new ConcurrentHashMap<String, AtomicLongConfig>();

  private final Map<String, AtomicReferenceConfig> atomicReferenceConfigs =
      new ConcurrentHashMap<String, AtomicReferenceConfig>();

  private final Map<String, PNCounterConfig> pnCounterConfigs =
      new ConcurrentHashMap<String, PNCounterConfig>();

  private ServicesConfig servicesConfig = new ServicesConfig();

  private SecurityConfig securityConfig = new SecurityConfig();

  private PartitionGroupConfig partitionGroupConfig = new PartitionGroupConfig();

  private ManagementCenterConfig managementCenterConfig = new ManagementCenterConfig();

  private SerializationConfig serializationConfig = new SerializationConfig();

  private MemberAttributeConfig memberAttributeConfig = new MemberAttributeConfig();

  private NativeMemoryConfig nativeMemoryConfig = new NativeMemoryConfig();

  private HotRestartPersistenceConfig hotRestartPersistenceConfig =
      new HotRestartPersistenceConfig();

  private UserCodeDeploymentConfig userCodeDeploymentConfig = new UserCodeDeploymentConfig();

  private CRDTReplicationConfig crdtReplicationConfig = new CRDTReplicationConfig();

  private boolean liteMember;

  public String getInstanceName() {
    return instanceName;
  }

  public GroupConfig getGroupConfig() {
    return groupConfig;
  }

  public NetworkConfig getNetworkConfig() {
    return networkConfig;
  }

  public Map<String, MapConfig> getMapConfigs() {
    return mapConfigs;
  }

  public Map<String, CacheSimpleConfig> getCacheConfigs() {
    return cacheConfigs;
  }

  public Map<String, TopicConfig> getTopicConfigs() {
    return topicConfigs;
  }

  public Map<String, ReliableTopicConfig> getReliableTopicConfigs() {
    return reliableTopicConfigs;
  }

  public Map<String, QueueConfig> getQueueConfigs() {
    return queueConfigs;
  }

  public Map<String, LockConfig> getLockConfigs() {
    return lockConfigs;
  }

  public Map<String, MultiMapConfig> getMultiMapConfigs() {
    return multiMapConfigs;
  }

  public Map<String, ListConfig> getListConfigs() {
    return listConfigs;
  }

  public Map<String, SetConfig> getSetConfigs() {
    return setConfigs;
  }

  public Map<String, ExecutorConfig> getExecutorConfigs() {
    return executorConfigs;
  }

  public Map<String, DurableExecutorConfig> getDurableExecutorConfigs() {
    return durableExecutorConfigs;
  }

  public Map<String, ScheduledExecutorConfig> getScheduledExecutorConfigs() {
    return scheduledExecutorConfigs;
  }

  public Map<String, SemaphoreConfig> getSemaphoreConfigs() {
    return semaphoreConfigs;
  }

  public Map<String, CountDownLatchConfig> getCountDownLatchConfigs() {
    return countDownLatchConfigs;
  }

  public Map<String, ReplicatedMapConfig> getReplicatedMapConfigs() {
    return replicatedMapConfigs;
  }

  public Map<String, WanReplicationConfig> getWanReplicationConfigs() {
    return wanReplicationConfigs;
  }

  public Map<String, JobTrackerConfig> getJobTrackerConfigs() {
    return jobTrackerConfigs;
  }

  public Map<String, QuorumConfig> getQuorumConfigs() {
    return quorumConfigs;
  }

  public Map<String, RingbufferConfig> getRingbufferConfigs() {
    return ringbufferConfigs;
  }

  public Map<String, CardinalityEstimatorConfig> getCardinalityEstimatorConfigs() {
    return cardinalityEstimatorConfigs;
  }

  public Map<String, EventJournalConfig> getMapEventJournalConfigs() {
    return mapEventJournalConfigs;
  }

  public Map<String, EventJournalConfig> getCacheEventJournalConfigs() {
    return cacheEventJournalConfigs;
  }

  public Map<String, MerkleTreeConfig> getMapMerkleTreeConfigs() {
    return mapMerkleTreeConfigs;
  }

  public Map<String, FlakeIdGeneratorConfig> getFlakeIdGeneratorConfigMap() {
    return flakeIdGeneratorConfigMap;
  }

  public Map<String, AtomicLongConfig> getAtomicLongConfigs() {
    return atomicLongConfigs;
  }

  public Map<String, AtomicReferenceConfig> getAtomicReferenceConfigs() {
    return atomicReferenceConfigs;
  }

  public Map<String, PNCounterConfig> getPnCounterConfigs() {
    return pnCounterConfigs;
  }

  public ServicesConfig getServicesConfig() {
    return servicesConfig;
  }

  public SecurityConfig getSecurityConfig() {
    return securityConfig;
  }

  public PartitionGroupConfig getPartitionGroupConfig() {
    return partitionGroupConfig;
  }

  public ManagementCenterConfig getManagementCenterConfig() {
    return managementCenterConfig;
  }

  public SerializationConfig getSerializationConfig() {
    return serializationConfig;
  }

  public MemberAttributeConfig getMemberAttributeConfig() {
    return memberAttributeConfig;
  }

  public NativeMemoryConfig getNativeMemoryConfig() {
    return nativeMemoryConfig;
  }

  public HotRestartPersistenceConfig getHotRestartPersistenceConfig() {
    return hotRestartPersistenceConfig;
  }

  public UserCodeDeploymentConfig getUserCodeDeploymentConfig() {
    return userCodeDeploymentConfig;
  }

  public CRDTReplicationConfig getCrdtReplicationConfig() {
    return crdtReplicationConfig;
  }

  public boolean isLiteMember() {
    return liteMember;
  }

  public void setInstanceName(String instanceName) {
    this.instanceName = instanceName;
  }

  public void setGroupConfig(GroupConfig groupConfig) {
    this.groupConfig = groupConfig;
  }

  public void setNetworkConfig(NetworkConfig networkConfig) {
    this.networkConfig = networkConfig;
  }

  public void setServicesConfig(ServicesConfig servicesConfig) {
    this.servicesConfig = servicesConfig;
  }

  public void setSecurityConfig(SecurityConfig securityConfig) {
    this.securityConfig = securityConfig;
  }

  public void setPartitionGroupConfig(PartitionGroupConfig partitionGroupConfig) {
    this.partitionGroupConfig = partitionGroupConfig;
  }

  public void setManagementCenterConfig(ManagementCenterConfig managementCenterConfig) {
    this.managementCenterConfig = managementCenterConfig;
  }

  public void setSerializationConfig(SerializationConfig serializationConfig) {
    this.serializationConfig = serializationConfig;
  }

  public void setMemberAttributeConfig(MemberAttributeConfig memberAttributeConfig) {
    this.memberAttributeConfig = memberAttributeConfig;
  }

  public void setNativeMemoryConfig(NativeMemoryConfig nativeMemoryConfig) {
    this.nativeMemoryConfig = nativeMemoryConfig;
  }

  public void setHotRestartPersistenceConfig(
      HotRestartPersistenceConfig hotRestartPersistenceConfig) {
    this.hotRestartPersistenceConfig = hotRestartPersistenceConfig;
  }

  public void setUserCodeDeploymentConfig(UserCodeDeploymentConfig userCodeDeploymentConfig) {
    this.userCodeDeploymentConfig = userCodeDeploymentConfig;
  }

  public void setCrdtReplicationConfig(CRDTReplicationConfig crdtReplicationConfig) {
    this.crdtReplicationConfig = crdtReplicationConfig;
  }

  public void setLiteMember(boolean liteMember) {
    this.liteMember = liteMember;
  }

}
