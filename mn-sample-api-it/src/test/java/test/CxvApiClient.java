package jp.co.softbank.cmeself.sales.test;

import static jp.co.softbank.cmeself.sales.util.TestUtils.urlEncode;

import java.net.URI;
import java.net.URISyntaxException;
import jp.co.softbank.cxr.customerexperienceviewer.application.payload.ContactHistoryRegisterRequest;
import jp.co.softbank.cxr.customerexperienceviewer.application.payload.ContactHistoryRegisterResponse;
import jp.co.softbank.cxr.customerexperienceviewer.application.payload.ContactHistoryUpdateRequest;
import jp.co.softbank.cxr.customerexperienceviewer.application.payload.ContactHistoryUpdateResponse;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

public class CxvApiClient {
  
  private TestRestTemplate client;
  private IntegrationTestConfig config;
  
  public CxvApiClient(TestRestTemplate client, IntegrationTestConfig config) {
    this.client = client;
    this.config = config;
  }
  
  /**
   * GINIE 顧客照会から Customer Experience Viewer に流入します。
   * 
   * @param msn MSN
   * @param agencyCode ショップコード
   * @param agencyName ショップ名
   * @param customerName 顧客氏名
   * @param customerNameKana 顧客氏名カナ
   * @param crewCode クルーコード
   * @param crewName クルー名
   * @param serviceNumber S#
   * @param billingNumber B#
   * @param contractNumberbC#
   * @return 流入した結果のレスポンス
   * @throws URISyntaxException
   */
  public ResponseEntity<String> initialize(String msn,
                                           String agencyCode,
                                           String agencyName,
                                           String customerName,
                                           String customerNameKana,
                                           String crewCode,
                                           String crewName,
                                           String serviceNumber,
                                           String billingNumber,
                                           String contractNumber) throws URISyntaxException {
    StringBuilder builder = new StringBuilder();
    builder.append("?msn=").append(msn);
    builder.append("&serviceNumber=").append(serviceNumber);
    builder.append("&billingNumber=").append(billingNumber);
    builder.append("&contractNumber=").append(contractNumber);
    builder.append("&agencyCode=").append(agencyCode);
    builder.append("&agencyName=").append(urlEncode(agencyName));
    builder.append("&customerName=").append(urlEncode(customerName));
    builder.append("&customerNameKana=").append(urlEncode(customerNameKana));
    builder.append("&crewCode=").append(crewCode);
    builder.append("&crewName=").append(urlEncode(crewName));
    
    URI uri = new URI(config.getApiEndpoint().toString() + "/customer-experience-viewer/entry" + builder.toString());
    RequestEntity requestEntity = RequestEntity.get(uri).build();
    return client.exchange(requestEntity, String.class);
  }
  
  
  /**
   * コンタクト履歴登録を実行します。
   */
  public ResponseEntity<ContactHistoryRegisterResponse> registerContactHistory(ContactHistoryRegisterRequest request) throws URISyntaxException {
    URI uri = new URI(config.getApiEndpoint().toString() + "/customer-experience-viewer/v1/histories");
    RequestEntity<ContactHistoryRegisterRequest> requestEntity
      = RequestEntity.post(uri).contentType(MediaType.APPLICATION_JSON).body(request);
    return client.exchange(requestEntity, ContactHistoryRegisterResponse.class);
  }
  
  /**
   * コンタクト履歴更新を実行します。
   */
  public ResponseEntity<ContactHistoryUpdateResponse> updateContactHistory(ContactHistoryUpdateRequest request) throws URISyntaxException {
    URI uri = new URI(config.getApiEndpoint().toString() + "/customer-experience-viewer/v1/histories/0000002798590");
    RequestEntity<ContactHistoryUpdateRequest> requestEntity
      = RequestEntity.put(uri).contentType(MediaType.APPLICATION_JSON).body(request);
    return client.exchange(requestEntity, ContactHistoryUpdateResponse.class);
  }
}
