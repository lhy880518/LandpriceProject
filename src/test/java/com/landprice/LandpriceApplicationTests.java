package com.landprice;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.landprice.vo.LandVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LandpriceApplicationTests {

    @Test
    public void contextLoads() throws UnsupportedEncodingException, MalformedURLException {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder urlBuilder = new StringBuilder("http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcLandTrade"); /*URL*/

        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=jCbfkABWG4witHxbIns6MpvrcajZQd%2BtCrImhtV%2F%2FbT%2FWyG70EA%2FZv1Wugwk%2BIp4iRnf40%2FjVXiweC6Q5L5kwA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("LAWD_CD","UTF-8") + "=" + URLEncoder.encode("11110", "UTF-8")); /*각 지역별 코드*/
        urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD","UTF-8") + "=" + URLEncoder.encode("201512", "UTF-8")); /*월 단위 신고자료*/
        URI uri = null;
        try {
            uri = new URI(urlBuilder.toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        LandVo landVo = null;
        HttpHeaders headers = new HttpHeaders();
        Charset utf8 = Charset.forName("UTF-8");
        MediaType mediaType = new MediaType("application", "json", utf8);
        headers.setContentType(mediaType);

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        System.out.println("uri.toURL().toString() = "+uri.toURL().toString());
        //System.out.println("restTemplate.exchange(uri.toURL().toString(), HttpMethod.GET, entity, String.class) = "+restTemplate.exchange(uri.toURL().toString(), HttpMethod.GET, entity, String.class));
        //System.out.println("restTemplate.getForObject(uri, String.class) = "+restTemplate.getForObject(uri, String.class));

        //getobject에서 리턴 String이 한글일경우 깨짐 방지
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        String getJson = restTemplate.getForObject(uri, String.class);

        System.out.println("getJson = "+getJson);
        landVo = new Gson().fromJson(getJson.toString(), LandVo.class);
        System.out.println("landVo = "+landVo.response.header.resultCode);
        System.out.println("landVo = "+landVo.response.body.items.item[0].kind);

    }

}

