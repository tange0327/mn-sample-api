package jp.co.softbank.cmeself.sales.test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static jp.co.softbank.cmeself.sales.util.TestUtils.readBodyFile;

import java.io.IOException;

public class WiremockHelper {

  /**
   * 指定されたレスポンスファイルで CM_IF_301 オーダー一覧検索のスタブを作成します。
   * 
   * @param responseFile 返却させるレスポンス
   */
  public static void stubForSearchOrderList(String responseFile) throws IOException {
    stubFor(post(urlEqualTo("/Multa-WebAPI/rest/multa/mulsend"))
        .withRequestBody(equalToJson(readBodyFile("SearchOrderList/request_ok.json"), true, true))
        .willReturn(aResponse().withStatus(200)
        .withHeader("Content-Type", "application/json")
        .withBodyFile("SearchOrderList/" + responseFile)));
  }
  
  /**
   * 指定されたレスポンスファイルで CM_IF_201 ケース更新のスタブを作成します。
   * 
   * @param responseFile 返却させるレスポンスs
   */
  public static void stubForUpdateCase(String requestFile, String responseFile) throws IOException {
    stubFor(post(urlEqualTo("/Multa-WebAPI/rest/multa/mulsend"))
        .withRequestBody(equalToJson(readBodyFile("UpdateCase/request_ok.json"), true, true))
        .withRequestBody(matchingJsonPath("$.payload.value.serviceRequestDto.timestamp"))
        .withRequestBody(matchingJsonPath("$.payload.value.serviceRequestDto.message"))
        .willReturn(aResponse().withStatus(200)
        .withHeader("Content-Type", "application/json")
        .withBodyFile("UpdateCase/" + responseFile)));
  } 
  
}
