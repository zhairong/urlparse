package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import common.AccessLoggingAction;
import javafx.util.Pair;
import models.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhairong on 22.09.16.
 */
public class UrlParserController extends Controller {

    private static final Logger LOG = LoggerFactory.getLogger(UrlParserController.class);

    @With(AccessLoggingAction.class)
    public Result parse() {
        JsonNode json = request().body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        }
        String urlText = json.get("url").textValue();
        UrlComponent urlComponent = new UrlComponent();
        try {
            URL url = new URL(urlText);
            urlComponent.setAuthority(url.getAuthority());
            urlComponent.setFileName(url.getFile());
            urlComponent.setHost(url.getHost());
            urlComponent.setPath(url.getPath());
            urlComponent.setPort(url.getPort()!=-1?url.getPort():url.getDefaultPort());
            urlComponent.setProtocol(url.getProtocol());
            urlComponent.setQuery(url.getQuery());
            urlComponent.setRef(url.getRef());
        } catch (MalformedURLException e) {
            return badRequest("can not parse url, please sure u have gaven a valid url with this format: http://www.domain.com/path");
        }
        return ok(Json.toJson(urlComponent));
    }
}