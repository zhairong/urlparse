package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.UrlComponent;
import models.UrlParserRequest;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;
import play.Application;
import play.Mode;
import play.inject.guice.GuiceApplicationBuilder;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static play.test.Helpers.contentAsString;
import static play.test.Helpers.route;

/**
 * Created by zhairong on 22.09.16.
 */
public class UrlParserControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder()
                .in(Mode.TEST)
                .build();
    }

    // incomplete document. expected BAD_REQUEST(400)
    @Test
    public void testParserSuccess() {

        UrlParserRequest req = new UrlParserRequest();
        req.setUrl("https://www.domain.com/23/89");

        Http.RequestBuilder request = new Http.RequestBuilder()
                .method("POST")
                .bodyJson(Json.toJson(req))
                .uri("/v1/urlparse");
        Result result = route(request);
        Assert.assertEquals(HttpStatus.SC_OK, result.status());
        UrlComponent component = Json.fromJson(Json.parse(contentAsString(result)), UrlComponent.class);
        Assert.assertEquals("www.domain.com", component.getHost());
        Assert.assertEquals(443, component.getPort());
        Assert.assertEquals("/23/89", component.getPath());
        Assert.assertEquals("https", component.getProtocol());
    }
}
