package com.openyich.framework.boot.config;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

/**
 * Properties specific to OpenYich.
 * 
 * @author zhycn
 * @since 2.1.0 2018-11-09
 */
@ConfigurationProperties(prefix = "openyich", ignoreUnknownFields = false)
public class OpenYichProperties {

  private final Async async = new Async();

  private final Http http = new Http();

  private final Cache cache = new Cache();

  private final Mail mail = new Mail();

  private final Security security = new Security();

  private final Swagger swagger = new Swagger();

  private final Metrics metrics = new Metrics();

  private final Logging logging = new Logging();

  private final CorsConfiguration cors = new CorsConfiguration();

  private final Social social = new Social();

  private final Gateway gateway = new Gateway();

  private final Registry registry = new Registry();

  public Async getAsync() {
    return async;
  }

  public Http getHttp() {
    return http;
  }

  public Cache getCache() {
    return cache;
  }

  public Mail getMail() {
    return mail;
  }

  public Security getSecurity() {
    return security;
  }

  public Swagger getSwagger() {
    return swagger;
  }

  public Metrics getMetrics() {
    return metrics;
  }

  public Logging getLogging() {
    return logging;
  }

  public CorsConfiguration getCors() {
    return cors;
  }

  public Social getSocial() {
    return social;
  }

  public Gateway getGateway() {
    return gateway;
  }

  public Registry getRegistry() {
    return registry;
  }

  public static class Async {

    private int corePoolSize = 8;

    private int maxPoolSize = 50;

    private int keepAliveSeconds = 60 * 5;

    private int queueCapacity = 10000;

    private boolean allowCoreThreadTimeOut = false;

    private boolean waitForJobsToCompleteOnShutdown = false;

    private int awaitTerminationSeconds = 60 * 15;

    private String threadNamePrefix = "AOY-Executor";

    public int getCorePoolSize() {
      return corePoolSize;
    }

    public int getMaxPoolSize() {
      return maxPoolSize;
    }

    public int getKeepAliveSeconds() {
      return keepAliveSeconds;
    }

    public int getQueueCapacity() {
      return queueCapacity;
    }

    public boolean isAllowCoreThreadTimeOut() {
      return allowCoreThreadTimeOut;
    }

    public boolean isWaitForJobsToCompleteOnShutdown() {
      return waitForJobsToCompleteOnShutdown;
    }

    public int getAwaitTerminationSeconds() {
      return awaitTerminationSeconds;
    }

    public String getThreadNamePrefix() {
      return threadNamePrefix;
    }

    public void setCorePoolSize(int corePoolSize) {
      this.corePoolSize = corePoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
      this.maxPoolSize = maxPoolSize;
    }

    public void setKeepAliveSeconds(int keepAliveSeconds) {
      this.keepAliveSeconds = keepAliveSeconds;
    }

    public void setQueueCapacity(int queueCapacity) {
      this.queueCapacity = queueCapacity;
    }

    public void setAllowCoreThreadTimeOut(boolean allowCoreThreadTimeOut) {
      this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
    }

    public void setWaitForJobsToCompleteOnShutdown(boolean waitForJobsToCompleteOnShutdown) {
      this.waitForJobsToCompleteOnShutdown = waitForJobsToCompleteOnShutdown;
    }

    public void setAwaitTerminationSeconds(int awaitTerminationSeconds) {
      this.awaitTerminationSeconds = awaitTerminationSeconds;
    }

    public void setThreadNamePrefix(String threadNamePrefix) {
      this.threadNamePrefix = threadNamePrefix;
    }

  }

  public static class Http {

    public enum Version {
      V_1_1, V_2_0
    }

    private final Cache cache = new Cache();

    /**
     * Https has to be active with cipher suite define also
     */
    private boolean useUndertowUserCipherSuitesOrder = true;

    /**
     * HTTP version, must be "V_1_1" (for HTTP/1.1) or V_2_0 (for (HTTP/2)
     */
    public Version version = Version.V_1_1;

    public Cache getCache() {
      return cache;
    }

    public Version getVersion() {
      return version;
    }

    public void setVersion(Version version) {
      this.version = version;
    }

    public static class Cache {

      private int timeToLiveInDays = 1461; // 4 years (including leap day)

      public int getTimeToLiveInDays() {
        return timeToLiveInDays;
      }

      public void setTimeToLiveInDays(int timeToLiveInDays) {
        this.timeToLiveInDays = timeToLiveInDays;
      }
    }

    public boolean isUseUndertowUserCipherSuitesOrder() {
      return useUndertowUserCipherSuitesOrder;
    }

    public void setUseUndertowUserCipherSuitesOrder(boolean useUndertowUserCipherSuitesOrder) {
      this.useUndertowUserCipherSuitesOrder = useUndertowUserCipherSuitesOrder;
    }
  }

  public static class Cache {

    private final Hazelcast hazelcast = new Hazelcast();

    private final Ehcache ehcache = new Ehcache();

    private final Memcached memcached = new Memcached();

    public Hazelcast getHazelcast() {
      return hazelcast;
    }

    public Ehcache getEhcache() {
      return ehcache;
    }

    public Memcached getMemcached() {
      return memcached;
    }

    public static class Hazelcast {

      private int timeToLiveSeconds = 3600; // 1 hour

      private int backupCount = 1;

      private final ManagementCenter managementCenter = new ManagementCenter();

      public ManagementCenter getManagementCenter() {
        return managementCenter;
      }

      public static class ManagementCenter {

        private boolean enabled = false;

        private int updateInterval = 3;

        private String url = "";

        public boolean isEnabled() {
          return enabled;
        }

        public void setEnabled(boolean enabled) {
          this.enabled = enabled;
        }

        public int getUpdateInterval() {
          return updateInterval;
        }

        public void setUpdateInterval(int updateInterval) {
          this.updateInterval = updateInterval;
        }

        public String getUrl() {
          return url;
        }

        public void setUrl(String url) {
          this.url = url;
        }

      }

      public int getTimeToLiveSeconds() {
        return timeToLiveSeconds;
      }

      public void setTimeToLiveSeconds(int timeToLiveSeconds) {
        this.timeToLiveSeconds = timeToLiveSeconds;
      }

      public int getBackupCount() {
        return backupCount;
      }

      public void setBackupCount(int backupCount) {
        this.backupCount = backupCount;
      }
    }

    public static class Ehcache {

      private int timeToLiveSeconds = 3600; // 1 hour

      private long maxEntries = 100;

      public int getTimeToLiveSeconds() {
        return timeToLiveSeconds;
      }

      public void setTimeToLiveSeconds(int timeToLiveSeconds) {
        this.timeToLiveSeconds = timeToLiveSeconds;
      }

      public long getMaxEntries() {
        return maxEntries;
      }

      public void setMaxEntries(long maxEntries) {
        this.maxEntries = maxEntries;
      }
    }

    public static class Memcached {

      private boolean enabled = false;

      /**
       * Comma or whitespace separated list of servers' addresses.
       */
      private String servers = "localhost:11211";

      private int expiration = 300; // 5 minutes

      private boolean useBinaryProtocol = true;

      public boolean isEnabled() {
        return enabled;
      }

      public void setEnabled(boolean enabled) {
        this.enabled = enabled;
      }

      public String getServers() {
        return servers;
      }

      public void setServers(String servers) {
        this.servers = servers;
      }

      public int getExpiration() {
        return expiration;
      }

      public void setExpiration(int expiration) {
        this.expiration = expiration;
      }

      public boolean isUseBinaryProtocol() {
        return useBinaryProtocol;
      }

      public void setUseBinaryProtocol(boolean useBinaryProtocol) {
        this.useBinaryProtocol = useBinaryProtocol;
      }
    }
  }

  public static class Mail {

    private boolean enabled = false;

    private String from = "";

    private String baseUrl = "";

    public boolean isEnabled() {
      return enabled;
    }

    public void setEnabled(boolean enabled) {
      this.enabled = enabled;
    }

    public String getFrom() {
      return from;
    }

    public void setFrom(String from) {
      this.from = from;
    }

    public String getBaseUrl() {
      return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
      this.baseUrl = baseUrl;
    }
  }

  public static class Security {

    private final ClientAuthorization clientAuthorization = new ClientAuthorization();

    private final Authentication authentication = new Authentication();

    private final RememberMe rememberMe = new RememberMe();

    public ClientAuthorization getClientAuthorization() {
      return clientAuthorization;
    }

    public Authentication getAuthentication() {
      return authentication;
    }

    public RememberMe getRememberMe() {
      return rememberMe;
    }

    public static class ClientAuthorization {

      private String accessTokenUri = null;

      private String tokenServiceId = null;

      private String clientId = null;

      private String clientSecret = null;

      public String getAccessTokenUri() {
        return accessTokenUri;
      }

      public void setAccessTokenUri(String accessTokenUri) {
        this.accessTokenUri = accessTokenUri;
      }

      public String getTokenServiceId() {
        return tokenServiceId;
      }

      public void setTokenServiceId(String tokenServiceId) {
        this.tokenServiceId = tokenServiceId;
      }

      public String getClientId() {
        return clientId;
      }

      public void setClientId(String clientId) {
        this.clientId = clientId;
      }

      public String getClientSecret() {
        return clientSecret;
      }

      public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
      }
    }

    public static class Authentication {

      private final Jwt jwt = new Jwt();

      public Jwt getJwt() {
        return jwt;
      }

      public static class Jwt {

        private String secret = null;

        private String base64Secret = null;

        private long tokenValidityInSeconds = 1800; // 0.5 hour

        private long tokenValidityInSecondsForRememberMe = 2592000; // 30 days;

        public String getSecret() {
          return secret;
        }

        public void setSecret(String secret) {
          this.secret = secret;
        }

        public String getBase64Secret() {
          return base64Secret;
        }

        public void setBase64Secret(String base64Secret) {
          this.base64Secret = base64Secret;
        }

        public long getTokenValidityInSeconds() {
          return tokenValidityInSeconds;
        }

        public void setTokenValidityInSeconds(long tokenValidityInSeconds) {
          this.tokenValidityInSeconds = tokenValidityInSeconds;
        }

        public long getTokenValidityInSecondsForRememberMe() {
          return tokenValidityInSecondsForRememberMe;
        }

        public void setTokenValidityInSecondsForRememberMe(
            long tokenValidityInSecondsForRememberMe) {
          this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
        }
      }
    }

    public static class RememberMe {

      @NotNull
      private String key = null;

      public String getKey() {
        return key;
      }

      public void setKey(String key) {
        this.key = key;
      }
    }
  }

  public static class Swagger {

    private String title = "API Documentation";

    private String description = "Generated by Springfox Swagger.";

    private String version = "1.0";

    private String termsOfServiceUrl = null;

    private String contactName = null;

    private String contactUrl = null;

    private String contactEmail = null;

    private String license = null;

    private String licenseUrl = null;

    private String defaultIncludePattern = "/api/.*";

    private String host = null;

    private String[] protocols = {};

    private boolean useDefaultResponseMessages = true;

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public String getVersion() {
      return version;
    }

    public void setVersion(String version) {
      this.version = version;
    }

    public String getTermsOfServiceUrl() {
      return termsOfServiceUrl;
    }

    public void setTermsOfServiceUrl(String termsOfServiceUrl) {
      this.termsOfServiceUrl = termsOfServiceUrl;
    }

    public String getContactName() {
      return contactName;
    }

    public void setContactName(String contactName) {
      this.contactName = contactName;
    }

    public String getContactUrl() {
      return contactUrl;
    }

    public void setContactUrl(String contactUrl) {
      this.contactUrl = contactUrl;
    }

    public String getContactEmail() {
      return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
      this.contactEmail = contactEmail;
    }

    public String getLicense() {
      return license;
    }

    public void setLicense(String license) {
      this.license = license;
    }

    public String getLicenseUrl() {
      return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
      this.licenseUrl = licenseUrl;
    }

    public String getDefaultIncludePattern() {
      return defaultIncludePattern;
    }

    public void setDefaultIncludePattern(String defaultIncludePattern) {
      this.defaultIncludePattern = defaultIncludePattern;
    }

    public String getHost() {
      return host;
    }

    public void setHost(final String host) {
      this.host = host;
    }

    public String[] getProtocols() {
      return protocols;
    }

    public void setProtocols(final String[] protocols) {
      this.protocols = protocols;
    }

    public boolean isUseDefaultResponseMessages() {
      return useDefaultResponseMessages;
    }

    public void setUseDefaultResponseMessages(final boolean useDefaultResponseMessages) {
      this.useDefaultResponseMessages = useDefaultResponseMessages;
    }
  }

  public static class Metrics {

    private final Jmx jmx = new Jmx();

    private final Logs logs = new Logs();

    private final Prometheus prometheus = new Prometheus();

    public Jmx getJmx() {
      return jmx;
    }

    public Logs getLogs() {
      return logs;
    }

    public Prometheus getPrometheus() {
      return prometheus;
    }

    public static class Jmx {

      private boolean enabled = true;

      public boolean isEnabled() {
        return enabled;
      }

      public void setEnabled(boolean enabled) {
        this.enabled = enabled;
      }
    }

    public static class Logs {

      private boolean enabled = false;

      private long reportFrequency = 60;

      public boolean isEnabled() {
        return enabled;
      }

      public void setEnabled(boolean enabled) {
        this.enabled = enabled;
      }

      public long getReportFrequency() {
        return reportFrequency;
      }

      public void setReportFrequency(long reportFrequency) {
        this.reportFrequency = reportFrequency;
      }
    }

    public static class Prometheus {

      private boolean enabled = false;

      private String endpoint = "/prometheusMetrics";

      public boolean isEnabled() {
        return enabled;
      }

      public void setEnabled(boolean enabled) {
        this.enabled = enabled;
      }

      public String getEndpoint() {
        return endpoint;
      }

      public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
      }
    }
  }

  public static class Logging {

    private final Logstash logstash = new Logstash();

    public Logstash getLogstash() {
      return logstash;
    }

    public static class Logstash {

      private boolean enabled = false;

      private String host = "localhost";

      private int port = 5000;

      private int queueSize = 512;

      public boolean isEnabled() {
        return enabled;
      }

      public void setEnabled(boolean enabled) {
        this.enabled = enabled;
      }

      public String getHost() {
        return host;
      }

      public void setHost(String host) {
        this.host = host;
      }

      public int getPort() {
        return port;
      }

      public void setPort(int port) {
        this.port = port;
      }

      public int getQueueSize() {
        return queueSize;
      }

      public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
      }
    }
  }

  public static class Social {

    private String redirectAfterSignIn = "/#/home";

    public String getRedirectAfterSignIn() {
      return redirectAfterSignIn;
    }

    public void setRedirectAfterSignIn(String redirectAfterSignIn) {
      this.redirectAfterSignIn = redirectAfterSignIn;
    }
  }

  public static class Gateway {

    private final RateLimiting rateLimiting = new RateLimiting();

    public RateLimiting getRateLimiting() {
      return rateLimiting;
    }

    private Map<String, List<String>> authorizedMicroservicesEndpoints = new LinkedHashMap<>();

    public Map<String, List<String>> getAuthorizedMicroservicesEndpoints() {
      return authorizedMicroservicesEndpoints;
    }

    public void setAuthorizedMicroservicesEndpoints(
        Map<String, List<String>> authorizedMicroservicesEndpoints) {
      this.authorizedMicroservicesEndpoints = authorizedMicroservicesEndpoints;
    }

    public static class RateLimiting {

      private boolean enabled = false;

      private long limit = 100_000L;

      private int durationInSeconds = 3_600;

      public boolean isEnabled() {
        return enabled;
      }

      public void setEnabled(boolean enabled) {
        this.enabled = enabled;
      }

      public long getLimit() {
        return this.limit;
      }

      public void setLimit(long limit) {
        this.limit = limit;
      }

      public int getDurationInSeconds() {
        return durationInSeconds;
      }

      public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
      }
    }
  }

  public static class Registry {

    private String password = null;

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }
  }

}
