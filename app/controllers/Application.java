package controllers;

import play.*;
import play.libs.F;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    public static WebSocket<String> socket() {
		return new WebSocket<String>() {

			
			@Override
			public void onReady(WebSocket.In<String> in, 
					final WebSocket.Out<String> out) {
				in.onMessage(new F.Callback<String>() {

					@Override
					public void invoke(String event) throws Throwable {
						Integer count = 1 + Integer.parseInt(event);
						out.write("Just checking bitch " + count);
						Logger.info(event);
					}
				});

				out.write("Freeze Motherfucker");
			}

		};
    }

}
