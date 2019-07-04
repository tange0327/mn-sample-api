package jp.co.softbank.cmeself.sales.test;

import java.net.URI;

/**
 * 結合テストの設定情報（環境情報など）です。
 */
public class IntegrationTestConfig {
  
  /* 結合テスト環境のエンドポイント。 */
  private URI apiEndpoint;
  /* REST Client の cookie を有効にするかどうか */
  private boolean enableCookies;
  /* wiremock のポート番号 */
  private int wiremockPort;
  
  public IntegrationTestConfig() {
    reload();
  }
  
  public void reload() {
    apiEndpoint = URI.create(System.getProperty("deployment.uri", "http://localhost:8080"));
    enableCookies = Boolean.getBoolean("rest.client.enable.cookies");
    wiremockPort = Integer.getInteger("wiremock.port", 38080);
  }
  
  public URI getApiEndpoint() {
    return apiEndpoint;
  }
  
  public boolean isEnableCookies() {
    return enableCookies;
  }
  
  public int getWiremockPort() {
    return wiremockPort;
  }
}
