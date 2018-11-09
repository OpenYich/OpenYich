package com.openyich.framework.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to OpenYich.
 * 
 * @author zhycn
 * @since 2.1.0 2018-11-09
 */
@ConfigurationProperties(prefix = "openyich", ignoreUnknownFields = false)
public class OpenYichProperties {

  private Swagger swagger = new Swagger();

  public Swagger getSwagger() {
    return swagger;
  }

  public void setSwagger(Swagger swagger) {
    this.swagger = swagger;
  }

  public static class Swagger {

    private String title = "Application API";

    private String description = "API documentation";

    private String version = "1.0";

    private String termsOfServiceUrl;

    private String contactName;

    private String contactUrl;

    private String contactEmail;

    private String license;

    private String licenseUrl;

    private String defaultIncludePattern = "/api/.*";

    private String host;

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

}
