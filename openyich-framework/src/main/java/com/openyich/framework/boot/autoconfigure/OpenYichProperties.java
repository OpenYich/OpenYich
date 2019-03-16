package com.openyich.framework.boot.autoconfigure;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Properties specific to OpenYich.
 */
@ConfigurationProperties(prefix = "openyich", ignoreUnknownFields = false)
public class OpenYichProperties {

  private final Async async = new Async();
  
  private final Http http = new Http();

  private final Security security = new Security();

  private final Swagger swagger = new Swagger();

  private final Logging logging = new Logging();

  private final Map<String, CorsConfiguration> cors = Maps.newHashMap();

  private final Gateway gateway = new Gateway();

  public Async getAsync() {
    return async;
  }

  public Http getHttp() {
    return http;
  }

  public Security getSecurity() {
    return security;
  }

  public Swagger getSwagger() {
    return swagger;
  }

  public Logging getLogging() {
    return logging;
  }

  public Map<String, CorsConfiguration> getCors() {
    return cors;
  }

  public Gateway getGateway() {
    return gateway;
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

    /**
     * Https has to be active with cipher suite define also
     */
    private boolean useUndertowUserCipherSuitesOrder = true;

    /**
     * HTTP version, must be "V_1_1" (for HTTP/1.1) or V_2_0 (for (HTTP/2)
     */
    public Version version = Version.V_1_1;

    public Version getVersion() {
      return version;
    }

    public void setVersion(Version version) {
      this.version = version;
    }

    public boolean isUseUndertowUserCipherSuitesOrder() {
      return useUndertowUserCipherSuitesOrder;
    }

    public void setUseUndertowUserCipherSuitesOrder(boolean useUndertowUserCipherSuitesOrder) {
      this.useUndertowUserCipherSuitesOrder = useUndertowUserCipherSuitesOrder;
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

        private String secret = "";

        private String base64Secret = null;

        private long tokenValidityInSeconds = 1800; // 0.5 hour

        private long tokenValidityInSecondsForRememberMe = 2592000; // 30 days;
        
        private List<String> ignorings = Lists.newArrayList(); // antMatchers

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

        public List<String> getIgnorings() {
          return ignorings;
        }

        public void setIgnorings(List<String> ignorings) {
          this.ignorings = ignorings;
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

    private String description = "The Best APIs are Built with Swagger Tools";

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
    
    private final Authorization authorization = new Authorization();;

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
    
    public Authorization getAuthorization() {
      return authorization;
    }

    public static class Authorization {
      
      private boolean enabled;
      
      private String name = "Authorization";
      
      private String description = "Add authorization header";
      
      private String defaultValue = "Bearer ";
      
      private boolean required;
      
      private String paramType = "header";
      
      private String pattern = "";

      public boolean isEnabled() {
        return enabled;
      }

      public void setEnabled(boolean enabled) {
        this.enabled = enabled;
      }

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public String getDescription() {
        return description;
      }

      public void setDescription(String description) {
        this.description = description;
      }

      public String getDefaultValue() {
        return defaultValue;
      }

      public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
      }

      public boolean isRequired() {
        return required;
      }

      public void setRequired(boolean required) {
        this.required = required;
      }

      public String getParamType() {
        return paramType;
      }

      public void setParamType(String paramType) {
        this.paramType = paramType;
      }

      public String getPattern() {
        return pattern;
      }

      public void setPattern(String pattern) {
        this.pattern = pattern;
      }
      
    }
  }

  public static class Logging {

    private String path = ".";
    
    private String level = "INFO";
    
    private String maxFileSize = "100MB";
    
    private String maxHistory = "30";
    
    private String totalSizeCap = "50GB";

    public String getPath() {
      return path;
    }

    public String getLevel() {
      return level;
    }

    public String getMaxFileSize() {
      return maxFileSize;
    }

    public String getMaxHistory() {
      return maxHistory;
    }

    public String getTotalSizeCap() {
      return totalSizeCap;
    }

    public void setPath(String path) {
      this.path = path;
    }

    public void setLevel(String level) {
      this.level = level;
    }

    public void setMaxFileSize(String maxFileSize) {
      this.maxFileSize = maxFileSize;
    }

    public void setMaxHistory(String maxHistory) {
      this.maxHistory = maxHistory;
    }

    public void setTotalSizeCap(String totalSizeCap) {
      this.totalSizeCap = totalSizeCap;
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

}
