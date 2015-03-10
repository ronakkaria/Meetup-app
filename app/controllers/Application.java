package controllers;

import play.Logger;
import play.api.libs.concurrent.Promise;
import play.libs.F;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
import views.html.index;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    public static WebSocket<String> socket() {
    	return WebSocket.withActor(MyWebSocketActor::props);
    }

}
