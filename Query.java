import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;

/**
 * 
 */

/**
 * @author Iver3oN Zhang
 * @date 2016年4月26日
 * @email grepzwb@qq.com
 * Query.java
 * Impossible is nothing
 */
public class Query {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//httpClientGet();
		httpClientPost();
	}
	
	// HttpClient 的POST方式
    public static void httpClientPost() {
        String urlAddress = "http://m.join.qq.com/query/result";
        HttpPost httpPost = new HttpPost(urlAddress);
        List paras = new ArrayList<>();
        NameValuePair pair1 = new BasicNameValuePair("type", "query_result");
        NameValuePair pair2 = new BasicNameValuePair("idcard", "267*");
        NameValuePair pair3 = new BasicNameValuePair("phone","1832968****");
        paras.add(pair1);
        paras.add(pair2);
        paras.add(pair3);
        try {
            HttpEntity entity = new UrlEncodedFormEntity(paras, "gbk");
            httpPost.setEntity(entity);
            HttpClient client = new DefaultHttpClient();
            try {
                HttpResponse ht = client.execute(httpPost);
                // 连接成功
                if (ht.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity het = ht.getEntity();
                    InputStream is = het.getContent();
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(is, "UTF-8"));
                    String response = "";
                    String readLine = null;
                    while ((readLine = br.readLine()) != null) {
                        // response = br.readLine();
                        response = response + readLine;
                    }
                    is.close();
                    br.close();

                    // String str = EntityUtils.toString(he);
                   // System.out.println("=========&&" + response);
                    int i = response.indexOf("<p>");
                    int j = response.lastIndexOf("</p>");
                    System.out.println(response.substring(i, j).trim());       
                } else {
                }
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

	
	// httpClient 方式发送get请求
    //由于腾讯校招微信官网查看源代码  是通过post方式请求的，其实也必须是post方式。。。这个方式暂时不用
    public static void httpClientGet() {
        System.out.println("--------------");
        String urlAddress = "http://m.join.qq.com/query/result";
        String type = "query_result";
        //your id back 4
        String idcard = "267*";
        //your phonenumber
        String phone = "1832968****";
        String getUrl = urlAddress + "?type=" + type + "&idcard="
                + idcard +"&phone="+phone;
        HttpGet httpGet = new HttpGet(getUrl);
        HttpParams hp = httpGet.getParams();
        hp.getParameter("true");
        httpGet.setHeader("Referer", "http://m.join.qq.com/school/index");
        // hp.
        // httpGet.setp

        HttpClient hc = new DefaultHttpClient();
        try {
            HttpResponse ht = hc.execute(httpGet);
            System.out.println("xaxaxa");
            System.out.println(ht.getStatusLine().getStatusCode());
            if (ht.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                System.out.println("OK");
                HttpEntity he = ht.getEntity();
                InputStream is = he.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        is, "UTF-8"));
                String response = "";
                String readLine = null;
                while ((readLine = br.readLine()) != null) {
                    // response = br.readLine();
                    response = response + readLine;
                }
                is.close();
                br.close();
                // String str = EntityUtils.toString(he);
                System.out.println("=========" + response);
            } else {
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
