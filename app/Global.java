import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.GlobalSettings;
import play.api.mvc.Handler;
import play.mvc.Http;

public class Global extends GlobalSettings {

    private static final Logger LOG = LoggerFactory.getLogger(Global.class);

    @Override
    public Handler onRouteRequest(Http.RequestHeader request) {
        LOG.info("get request uri: {}", request.uri());
        return super.onRouteRequest(request);
    }
}