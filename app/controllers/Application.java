package controllers;

import models.ChatRoomActor;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
import views.html.index;

import com.fasterxml.jackson.databind.JsonNode;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render("Your new application is ready."));
	}

	public static WebSocket<JsonNode> chat(final String username) {
		return new WebSocket<JsonNode>() {

			// Called when the Websocket Handshake is done.
			public void onReady(WebSocket.In<JsonNode> in,
					WebSocket.Out<JsonNode> out) {

				// Join the chat room.
				try {
					ChatRoomActor.join(username, in, out);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};
	}

}
