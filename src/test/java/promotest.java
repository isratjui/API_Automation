


import io.restassured.response.ResponseBody;
import junit.framework.Assert;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static junit.framework.Assert.*;
import static org.codehaus.groovy.tools.shell.util.Logger.io;

public class promotest {
	@Test
	public void test() throws IOException {

        FileInputStream fstream = new FileInputStream("C:\\Users\\Ishrat Zahan Jui\\Downloads\\apitest.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        FileInputStream fstream1 = new FileInputStream("C:\\Users\\Ishrat Zahan Jui\\Downloads\\response.txt");
        BufferedReader br1 = new BufferedReader(new InputStreamReader(fstream1));
        String strLine;
        String strLine2;
        while ((strLine = br.readLine())!= null ) {
            strLine2 = br1.readLine();
            RequestSpecification request = RestAssured.given();

            request.header("Content-Type", "application/json");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("access_token", strLine);
            //jsonObject.put("lgcNumber", "7500037427390449");//send array type data
            request.body(jsonObject.toJSONString());
            Response post = request.post("https://test-api.obhai.com/get_all_promotion");
            int statusCode = post.statusCode();
            System.out.println("Status code =" + statusCode);

            assertEquals(statusCode, 200);
            String re = post.getContentType();
            System.out.println(re);

            String print = post.getStatusLine();
            System.out.println(print);
            ResponseBody body = post.getBody();
            System.out.println("Response Body is: " + body.asString());
            String bodyAsString = body.asString();
            //assertEquals(bodyAsString,String.valueOf(bodyAsString.contains("jui")));
            //assertThat(bodyAsString, containsString("promo"));
            assertThat(bodyAsString, containsString(strLine2));
            System.out.println(strLine2);
            // assertEquals(String.valueOf(bodyAsString.contains("Hyderabad")) /*Expected value*/, true /*Actual Value*/, "Response body contains Hyderabad");
        }
    }

	}
